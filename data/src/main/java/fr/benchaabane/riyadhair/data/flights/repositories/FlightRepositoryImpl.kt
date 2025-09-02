package fr.benchaabane.riyadhair.data.flights.repositories

import fr.benchaabane.riyadhair.core.extensions.recoverSuspendCatching
import fr.benchaabane.riyadhair.core.extensions.runSuspendCatching
import fr.benchaabane.riyadhair.data.flights.api.FlightService
import fr.benchaabane.riyadhair.data.flights.mappers.toDomain
import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts

/**
 * Implementation of the FlightRepository interface.
 *
 * This class provides the concrete implementation for flight data operations,
 * coordinating between remote API calls and local data processing. It follows
 * the repository pattern and provides a clean interface for flight-related
 * operations.
 *
 * **Data Strategy:**
 * - **Remote First**: Fetches data from the API service
 * - **Data Processing**: Applies business logic filters to API responses
 * - **Error Handling**: Gracefully handles network and processing failures
 * - **Result Wrapping**: Returns Result type for proper error handling
 *
 * **Dependencies:**
 * - **FlightService**: For remote API operations
 * - **Mappers**: For data transformation between layers
 * - **Core Extensions**: For safe operation execution
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * **Mock API Handling:**
 * This implementation includes filtering logic to simulate a coherent
 * API response, as the mock API may return inconsistent data that
 * needs to be filtered for business logic compliance.
 *
 * @see FlightRepository
 * @see FlightService
 * @see Flight
 */
@OptIn(ExperimentalContracts::class)
class FlightRepositoryImpl @Inject constructor(
    private val flightService: FlightService,
) : FlightRepository {

    /**
     * Retrieves flights based on origin and destination airports.
     *
     * This method searches for available flights between the specified
     * airports, fetching data from the remote API and applying business
     * logic filters to ensure data consistency.
     *
     * **Data Flow:**
     * 1. **API Call**: Fetches flights from remote service
     * 2. **Data Mapping**: Converts DTOs to domain models
     * 3. **Business Filtering**: Applies origin/destination validation
     * 4. **Result Return**: Returns filtered flight list
     *
     * **Business Logic:**
     * - **Route Validation**: Ensures flights match requested route
     * - **Data Consistency**: Filters out inconsistent API responses
     * - **Mock Handling**: Simulates coherent API behavior
     *
     * **Error Handling:**
     * - **Network Failures**: Returns empty list on API errors
     * - **Processing Errors**: Graceful fallback to empty results
     * - **Data Validation**: Filters invalid flight data
     *
     * **Filtering Details:**
     * The filter ensures that returned flights have matching
     * origin and destination airports, handling cases where
     * the mock API might return inconsistent data.
     *
     * @param origin The departure airport code (e.g., "CDG", "JFK")
     * @param destination The arrival airport code (e.g., "LAX", "LHR")
     * @return Result containing list of matching flights or empty list on error
     */
    override suspend fun getFlights(origin: String, destination: String): Result<List<Flight>> {
        return runSuspendCatching {
            val response = flightService.searchFlights(origin, destination)
            // The Filter is needed here to mock a coherent api response
            response.flights.map { it.toDomain() }.filter { it.departureAirport.code == origin && it.arrivalAirport.code == destination }
        }.recoverSuspendCatching {
            emptyList()
        }
    }

    /**
     * Retrieves a specific flight by flight number.
     *
     * This method searches for a flight with the specified flight number,
     * fetching data from the remote API and applying business logic
     * filters to ensure data accuracy.
     *
     * **Data Flow:**
     * 1. **API Call**: Fetches all flights from remote service
     * 2. **Flight Search**: Finds flight matching the flight number
     * 3. **Data Mapping**: Converts found DTO to domain model
     * 4. **Result Return**: Returns matching flight or null
     *
     * **Business Logic:**
     * - **Flight Identification**: Searches by unique flight number
     * - **Data Consistency**: Ensures flight data accuracy
     * - **Mock Handling**: Simulates coherent API behavior
     *
     * **Error Handling:**
     * - **Network Failures**: Returns null on API errors
     * - **Processing Errors**: Graceful fallback to null result
     * - **Flight Not Found**: Returns null for non-existent flights
     *
     * **Search Strategy:**
     * Currently fetches all flights and filters by flight number.
     * In a production environment, this could be optimized with
     * a direct flight number search endpoint.
     *
     * **Mock API Handling:**
     * Uses empty strings for origin/destination to fetch all
     * available flights, then filters by flight number for
     * business logic compliance.
     *
     * @param flightNumber The unique flight number to search for
     * @return Result containing the matching flight or null if not found/error
     */
    override suspend fun getFlight(flightNumber: String): Result<Flight?> {
        return runSuspendCatching {
            val response = flightService.searchFlights("", "")
            // The Filter is needed here to mock a coherent api response
            response.flights.find { it.flightNumber == flightNumber }?.toDomain()
        }.recoverSuspendCatching {
            null
        }
    }
}
