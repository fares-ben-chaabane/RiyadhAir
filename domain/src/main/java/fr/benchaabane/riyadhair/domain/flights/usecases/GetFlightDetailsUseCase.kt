package fr.benchaabane.riyadhair.domain.flights.usecases

import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository

/**
 * Use case for retrieving detailed information about a specific flight.
 *
 * This use case follows the command pattern and provides a single responsibility
 * for fetching comprehensive flight details from the repository layer. It's
 * used when users need detailed information about a specific flight, such as
 * during flight selection or booking processes.
 *
 * **Use Case Scenarios:**
 * - Flight selection and comparison
 * - Detailed flight information display
 * - Booking confirmation and details
 * - Flight status and information lookup
 * - Customer service and support
 *
 * **Business Logic:**
 * - Retrieves flight by unique flight number
 * - Returns null if flight is not found
 * - Wraps result in Result type for error handling
 * - Delegates to repository for data access
 *
 * **Error Handling:**
 * - Network failures are handled gracefully
 * - Database errors are captured in Result
 * - Invalid flight numbers return null in success case
 * - Repository exceptions are wrapped in Result.failure
 *
 * @param flightRepository The flight repository interface for data access
 */
class GetFlightDetailsUseCase(private val flightRepository: FlightRepository) {
    /**
     * Retrieves detailed information for a specific flight.
     *
     * This method fetches comprehensive flight details including route information,
     * timing, pricing, and availability. It's designed to provide all necessary
     * information for flight selection and booking decisions.
     *
     * **Flight Information Retrieved:**
     * - Route details (origin, destination, stops)
     * - Timing information (departure, arrival, duration)
     * - Pricing and availability
     * - Aircraft and seat information
     * - Airline and service details
     *
     * **Return Value:**
     * - **Success with Flight**: When flight is found and retrieved
     * - **Success with null**: When flight number doesn't exist
     * - **Failure**: When repository operation fails
     *
     * @param flightNumber The unique flight number to retrieve details for
     * @return Result containing the flight details or null if not found
     */
    suspend operator fun invoke(flightNumber: String): Result<Flight?> =
        flightRepository.getFlight(flightNumber = flightNumber)
}