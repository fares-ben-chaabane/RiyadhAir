package fr.benchaabane.riyadhair.data.reservations.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a flight reservation in the local database.
 *
 * This entity stores reservation information locally for offline access and
 * caching purposes. It represents a user's flight booking including flight
 * details, passenger information, seat assignment, and reservation status.
 *
 * **Database Structure:**
 * - **Table Name**: `reservations`
 * - **Primary Key**: `id` (String)
 * - **Data Persistence**: Local storage for offline access
 * - **Cache Management**: Stores reservation data for quick retrieval
 *
 * **Reservation Information:**
 * - **Core Details**: ID, flight ID, passenger name, seat, status
 * - **Flight Reference**: Links to specific flight information
 * - **Passenger Data**: Name of the person making the reservation
 * - **Seat Assignment**: Specific seat on the aircraft
 * - **Status Tracking**: Current state of the reservation
 *
 * **Data Usage:**
 * - **Offline Support**: Provides reservation data without network
 * - **Quick Access**: Fast local database queries
 * - **Data Synchronization**: Local cache of remote reservation data
 * - **User Experience**: Immediate reservation information display
 *
 * **Room Integration:**
 * - **Entity Annotation**: Maps to `reservations` table
 * - **Primary Key**: Unique identifier for each reservation
 * - **Column Mapping**: Automatic field-to-column mapping
 * - **Query Support**: Enables efficient database operations
 *
 * **Business Context:**
 * - **Flight Bookings**: Manages user's flight reservations
 * - **Seat Management**: Tracks assigned seats for each booking
 * - **Status Monitoring**: Tracks reservation lifecycle
 * - **User History**: Maintains booking history for users
 *
 * @property id Unique identifier for the reservation (primary key)
 * @property flightId Reference to the specific flight being reserved
 * @property passengerName Full name of the passenger making the reservation
 * @property seat Assigned seat number or designation on the aircraft
 * @property status Current status of the reservation (e.g., "confirmed", "pending", "cancelled")
 *
 * @see ReservationDao
 * @see fr.benchaabane.riyadhair.domain.reservations.models.Reservation
 * @see fr.benchaabane.riyadhair.data.reservations.api.ReservationDto
 */
@Entity(tableName = "reservations")
data class ReservationEntity(
    @PrimaryKey val id: String,
    val flightId: String,
    val passengerName: String,
    val seat: String,
    val status: String
)
