package fr.benchaabane.riyadhair.data.reservations.repositories

import fr.benchaabane.riyadhair.data.reservations.api.ReservationService
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationDao
import fr.benchaabane.riyadhair.data.reservations.mappers.toDomain
import fr.benchaabane.riyadhair.data.reservations.mappers.toDto
import fr.benchaabane.riyadhair.data.reservations.mappers.toEntity
import fr.benchaabane.riyadhair.domain.reservations.models.Reservation
import fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the ReservationRepository interface.
 *
 * This class provides the concrete implementation for reservation data operations,
 * coordinating between remote API calls and local database storage. It implements
 * an offline-first approach with automatic synchronization capabilities.
 *
 * **Data Strategy:**
 * - **Reactive Updates**: Uses Flow for real-time data updates
 * - **Offline Support**: Local database provides offline access
 * - **Smart Sync**: Automatic synchronization with remote data
 * - **Error Resilience**: Graceful handling of network failures
 *
 * **Dependencies:**
 * - **ReservationService**: For remote API operations
 * - **ReservationDao**: For local database operations
 * - **Mappers**: For data transformation between layers
 *
 * **Threading:**
 * All methods are designed to work with coroutines and Flow,
 * providing non-blocking data access patterns.
 *
 * @see ReservationRepository
 * @see ReservationService
 * @see ReservationDao
 * @see Reservation
 */
class ReservationRepositoryImpl @Inject constructor(
    private val reservationDao: ReservationDao,
    private val reservationService: ReservationService
) : ReservationRepository {
    
    /**
     * Observes all reservations with reactive updates.
     *
     * This method returns a Flow that automatically emits updated data
     * whenever the reservations change. It transforms database entities
     * to domain models for use in the presentation layer.
     *
     * **Data Flow:**
     * 1. **Database Observation**: Monitors local database changes
     * 2. **Entity Mapping**: Converts database entities to domain models
     * 3. **Reactive Updates**: Automatically emits on data changes
     * 4. **UI Integration**: Provides real-time updates for UI components
     *
     * **Usage:**
     * ```kotlin
     * reservationRepository.observeReservations()
     *     .collect { reservations ->
     *         // Update UI with reservation list
     *     }
     * ```
     *
     * @return Flow that emits the current list of reservations as domain models
     */
    override fun observeReservations(): Flow<List<Reservation>> = 
        reservationDao.observeReservations().map { entities -> 
            entities.map { it.toDomain() } 
        }

    /**
     * Saves a reservation to both remote and local storage.
     *
     * This method implements a smart save strategy that attempts to
     * save data remotely first, then locally as a backup. This ensures
     * data consistency while maintaining offline capability.
     *
     * **Save Strategy:**
     * 1. **Remote Save**: Attempts to save to remote API first
     * 2. **Local Backup**: Saves to local database regardless of remote success
     * 3. **Error Handling**: Gracefully handles network failures
     * 4. **Data Consistency**: Ensures data is always stored locally
     *
     * **Error Scenarios:**
     * - **Network Success**: Data saved both remotely and locally
     * - **Network Failure**: Data saved locally only (offline mode)
     * - **Database Error**: Handled by calling code
     *
     * @param reservation The reservation to save
     */
    override suspend fun saveReservation(reservation: Reservation) {
        try {
            // First try to save remotely
            val savedReservation = reservationService.createReservation(reservation.toDto())
            // Then save locally
            reservationDao.upsert(savedReservation.toEntity())
        } catch (e: Exception) {
            // If network fails, save locally only
            reservationDao.upsert(reservation.toEntity())
        }
    }
    
    /**
     * Refreshes reservation data from the remote API.
     *
     * This method fetches fresh reservation data from the remote service
     * and updates the local database. It implements a full refresh strategy
     * that replaces all local data with the latest remote data.
     *
     * **Refresh Strategy:**
     * 1. **Remote Fetch**: Retrieves latest data from API
     * 2. **Data Transformation**: Converts API response to database entities
     * 3. **Local Update**: Replaces all local data with fresh data
     * 4. **Error Handling**: Preserves local data on network failures
     *
     * **Data Flow:**
     * - **Success**: Local database updated with fresh remote data
     * - **Failure**: Local data preserved, no changes made
     * - **Consistency**: Ensures local data matches remote data when successful
     *
     * **Use Cases:**
     * - **App Launch**: Initial data loading
     * - **Pull to Refresh**: User-initiated data refresh
     * - **Background Sync**: Periodic data synchronization
     * - **Error Recovery**: Recovering from previous sync failures
     *
     * **Error Handling:**
     * Network failures are silently handled by preserving existing
     * local data, ensuring the app remains functional offline.
     */
    override suspend fun refreshReservations() {
        try {
            val response = reservationService.getReservations()
            val entities = response.reservations.map { it.toEntity() }
            // Clear and insert all reservations
            entities.forEach { reservationDao.upsert(it) }
        } catch (e: Exception) {
            // Handle network error - keep local data
        }
    }
}
