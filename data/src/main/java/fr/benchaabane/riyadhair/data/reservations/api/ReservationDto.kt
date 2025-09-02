package fr.benchaabane.riyadhair.data.reservations.api

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for flight reservation information from the API.
 *
 * This DTO represents a flight reservation made by a passenger, including
 * flight details, passenger information, seat assignment, and reservation status.
 * It serves as the data structure for API communication when managing
 * flight reservations.
 *
 * **Reservation Components:**
 * - **Flight Information**: Reference to the specific flight
 * - **Passenger Details**: Name of the person making the reservation
 * - **Seat Assignment**: Specific seat on the aircraft
 * - **Status Tracking**: Current state of the reservation
 *
 * **Data Structure:**
 * - **Core Fields**: ID, flight ID, passenger name, seat, status
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Data Validation**: Ensures required fields are present
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete reservation data.
 *
 * @property id Unique identifier for the reservation
 * @property flightId Reference to the specific flight being reserved
 * @property passengerName Full name of the passenger making the reservation
 * @property seat Assigned seat number or designation on the aircraft
 * @property status Current status of the reservation (e.g., "confirmed", "pending", "cancelled")
 *
 * @see ReservationsResponse
 * @see fr.benchaabane.riyadhair.domain.reservations.models.Reservation
 */
@Serializable
data class ReservationDto(
    val id: String,
    val flightId: String,
    val passengerName: String,
    val seat: String,
    val status: String
)

/**
 * Data Transfer Object for reservations search response from the API.
 *
 * This DTO wraps a list of reservations returned from the reservations API endpoint.
 * It provides a structured response format for bulk reservation data retrieval,
 * typically used when fetching a user's reservation history or managing multiple
 * reservations.
 *
 * **Response Structure:**
 * - **Reservations List**: Collection of ReservationDto objects
 * - **Bulk Data**: Efficient retrieval of multiple reservations
 * - **API Consistency**: Standard response format
 * - **Data Organization**: Structured collection for easy processing
 *
 * **Usage Context:**
 * - **API Responses**: Wrapping reservation search results
 * - **Data Processing**: Bulk reservation data handling
 * - **Repository Layer**: Converting API responses to domain models
 * - **Cache Management**: Storing multiple reservations efficiently
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * Designed to handle API response structures consistently.
 *
 * @property reservations List of reservations returned from the API
 *
 * @see ReservationDto
 * @see fr.benchaabane.riyadhair.domain.reservations.models.Reservation
 */
@Serializable
data class ReservationsResponse(
    val reservations: List<ReservationDto>
)
