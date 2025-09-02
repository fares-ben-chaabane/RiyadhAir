package fr.benchaabane.riyadhair.domain.reservations.models

/**
 * Represents a flight reservation in the RiyadhAir system.
 *
 * This data class encapsulates all the essential information about a flight
 * reservation, including passenger details, flight association, seat assignment,
 * and current reservation status. It serves as the core domain model for
 * reservation management throughout the application.
 *
 * **Reservation Components:**
 * - **Identification**: Unique reservation identifier
 * - **Flight Association**: Reference to the specific flight
 * - **Passenger Information**: Name of the passenger
 * - **Seat Assignment**: Specific seat on the aircraft
 * - **Status Tracking**: Current state of the reservation
 *
 * **Use Cases:**
 * - Flight booking confirmation
 * - Reservation management and updates
 * - Passenger check-in processes
 * - Seat assignment tracking
 * - Reservation status monitoring
 *
 * **Data Integrity:**
 * - All fields are required for complete reservation information
 * - Immutable data structure ensures consistency
 * - String-based fields allow for flexible data representation
 *
 * @param id Unique identifier for the reservation
 * @param flightId Reference to the associated flight
 * @param passengerName Full name of the passenger
 * @param seat Assigned seat number or designation
 * @param status Current status of the reservation (e.g., "Confirmed", "Pending", "Cancelled")
 */
data class Reservation(
    val id: String,
    val flightId: String,
    val passengerName: String,
    val seat: String,
    val status: String
)