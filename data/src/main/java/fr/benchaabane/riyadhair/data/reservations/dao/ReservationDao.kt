package fr.benchaabane.riyadhair.data.reservations.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for reservation-related database operations.
 *
 * This DAO provides methods for accessing and manipulating reservation data
 * in the local Room database. It supports reactive data access through Flow
 * and follows Room's best practices for data access.
 *
 * **Supported Operations:**
 * - **Read**: Retrieve reservations with reactive updates
 * - **Write**: Insert/update reservation data
 * - **Delete**: Remove specific reservations
 *
 * **Reactive Features:**
 * - **Flow Support**: Real-time updates when data changes
 * - **Observable Queries**: Automatic UI updates on data changes
 * - **Background Processing**: Database operations don't block UI
 *
 * **Threading:**
 * All methods are designed to work with coroutines and Flow,
 * providing non-blocking data access patterns.
 *
 * @see ReservationEntity
 * @see fr.benchaabane.riyadhair.domain.reservations.models.Reservation
 */
@Dao
interface ReservationDao {
    /**
     * Observes all reservations in the database with reactive updates.
     *
     * This method returns a Flow that automatically emits updated data
     * whenever the reservations table changes. It's ideal for UI components
     * that need to display real-time reservation information.
     *
     * **Query Details:**
     * - **SQL**: `SELECT * FROM reservations`
     * - **Result**: Flow of reservation entity lists
     * - **Updates**: Automatically emits on data changes
     * - **Performance**: Room optimizes the query execution
     *
     * **Usage:**
     * ```kotlin
     * reservationDao.observeReservations()
     *     .collect { reservations ->
     *         // Update UI with reservation list
     *     }
     * ```
     *
     * @return Flow that emits the current list of reservations
     */
    @Query("SELECT * FROM reservations")
    fun observeReservations(): Flow<List<ReservationEntity>>
    
    /**
     * Retrieves a specific reservation by its unique identifier.
     *
     * This method fetches a single reservation entity from the database
     * based on the provided ID. It's useful for detailed views or
     * individual reservation operations.
     *
     * **Query Details:**
     * - **SQL**: `SELECT * FROM reservations WHERE id = :id`
     * - **Result**: Single reservation entity or null
     * - **Performance**: Optimized with indexed primary key lookup
     *
     * @param id The unique identifier of the reservation to retrieve
     * @return The reservation entity if found, null otherwise
     */
    @Query("SELECT * FROM reservations WHERE id = :id")
    suspend fun getReservationById(id: String): ReservationEntity?
    
    /**
     * Inserts or updates a reservation in the database.
     *
     * This method uses Room's `@Upsert` annotation to automatically
     * handle both insert and update operations. If a reservation with
     * the same ID exists, it will be updated; otherwise, a new
     * reservation will be inserted.
     *
     * **Upsert Behavior:**
     * - **Insert**: Creates new reservation if ID doesn't exist
     * - **Update**: Modifies existing reservation if ID already exists
     * - **Conflict Resolution**: Automatically handled by Room
     *
     * @param reservation The reservation entity to insert or update
     */
    @Upsert
    suspend fun upsert(reservation: ReservationEntity)
    
    /**
     * Removes a specific reservation from the database.
     *
     * This method deletes a reservation based on its unique identifier.
     * The operation is irreversible and should be used with caution.
     *
     * **Query Details:**
     * - **SQL**: `DELETE FROM reservations WHERE id = :id`
     * - **Effect**: Removes the specified reservation
     * - **Use Case**: Cancellation, cleanup, user request
     *
     * @param id The unique identifier of the reservation to delete
     */
    @Query("DELETE FROM reservations WHERE id = :id")
    suspend fun deleteById(id: String)
}
