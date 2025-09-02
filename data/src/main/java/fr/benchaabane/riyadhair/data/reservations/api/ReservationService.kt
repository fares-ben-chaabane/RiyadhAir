package fr.benchaabane.riyadhair.data.reservations.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Retrofit service interface for reservation-related API operations.
 *
 * This service defines the API endpoints for managing flight reservations
 * through the RiyadhAir backend. It provides access to reservation
 * creation, retrieval, and management operations.
 *
 * **API Endpoints:**
 * - **Get Reservations**: Retrieves user's reservation history
 * - **Create Reservation**: Books a new flight reservation
 * - **Data Format**: JSON requests and responses
 * - **Authentication**: Requires valid user authentication
 *
 * **Reservation Operations:**
 * - **Read**: Fetch existing reservations for the user
 * - **Create**: Book new flight reservations
 * - **Data Validation**: Ensures reservation data integrity
 * - **Error Handling**: Provides meaningful error responses
 *
 * **Service Features:**
 * - **Suspending Functions**: Designed for coroutine-based async operations
 * - **Retrofit Integration**: Uses Retrofit for HTTP communication
 * - **Type Safety**: Strongly typed requests and responses
 * - **Network Layer**: Part of the data layer's network infrastructure
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by ReservationRepositoryImpl
 * - **Data Synchronization**: Fetching and creating reservation data
 * - **User Experience**: Managing flight bookings
 * - **Business Logic**: Handling reservation workflows
 *
 * @see ReservationDto
 * @see ReservationsResponse
 * @see fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository
 */
interface ReservationService {
    /**
     * Retrieves all reservations for the current user.
     *
     * This method fetches the complete reservation history for the
     * authenticated user, including all past and current flight bookings.
     * The response contains detailed information about each reservation.
     *
     * **API Details:**
     * - **Endpoint**: `GET /reservations`
     * - **Authentication**: Requires valid user session
     * - **Response**: Complete list of user's reservations
     * - **Data Scope**: All reservations associated with the user
     *
     * **Data Content:**
     * - **Reservation Details**: IDs, flight references, passenger names
     * - **Seat Information**: Assigned seats for each reservation
     * - **Status Tracking**: Current status of each reservation
     * - **Flight References**: Links to specific flight information
     *
     * **Error Handling:**
     * - **Authentication Errors**: May require user re-login
     * - **Network Failures**: Handled by calling repository
     * - **Data Validation**: Ensures response integrity
     * - **User Experience**: Provides complete reservation history
     *
     * **Performance Considerations:**
     * - **Async Operation**: Non-blocking network call
     * - **Data Size**: Response may contain many reservations
     * - **Caching Strategy**: Local storage for offline access
     * - **User Experience**: Quick access to booking history
     *
     * @return ReservationsResponse containing the user's reservation history
     */
    @GET("reservations")
    suspend fun getReservations(): ReservationsResponse
    
    /**
     * Creates a new flight reservation.
     *
     * This method books a new flight reservation for the authenticated user.
     * It sends reservation details to the server and returns the confirmed
     * reservation with any additional information from the booking process.
     *
     * **API Details:**
     * - **Endpoint**: `POST /reservations`
     * - **Authentication**: Requires valid user session
     * - **Request Body**: Complete reservation details
     * - **Response**: Confirmed reservation information
     *
     * **Request Data:**
     * - **Flight Selection**: Specific flight to be reserved
     * - **Passenger Details**: Name and contact information
     * - **Seat Preference**: Desired or assigned seat
     * - **Booking Confirmation**: Reservation status and details
     *
     * **Business Logic:**
     * - **Availability Check**: Verifies flight and seat availability
     * - **Booking Confirmation**: Creates confirmed reservation
     * - **Seat Assignment**: Assigns or confirms seat selection
     * - **Status Update**: Sets initial reservation status
     *
     * **Error Handling:**
     * - **Validation Errors**: Invalid reservation data
     * - **Availability Issues**: Flight or seat not available
     * - **Authentication Problems**: User session issues
     * - **System Errors**: Server or network problems
     *
     * **Performance Considerations:**
     * - **Async Operation**: Non-blocking network call
     * - **Data Validation**: Server-side reservation validation
     * - **Confirmation Process**: Real-time booking confirmation
     * - **User Experience**: Immediate feedback on reservation status
     *
     * @param reservation Complete reservation details to be created
     * @return ReservationDto containing the confirmed reservation information
     */
    @POST("reservations")
    suspend fun createReservation(@Body reservation: ReservationDto): ReservationDto
}
