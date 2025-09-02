package fr.benchaabane.riyadhair.domain.flights.usecases

import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository

/**
 * Use case for searching flights based on origin and destination.
 * 
 * This use case follows the command pattern and provides a single responsibility
 * for searching flights from the repository layer. It encapsulates the business
 * logic for flight search operations and provides a clean interface for the
 * presentation layer.
 *
 * **Responsibilities:**
 * - Searches flights by origin and destination airports
 * - Handles flight search errors gracefully
 * - Provides a consistent Result-based API
 * - Coordinates with the flight repository for data access
 *
 * **Usage Pattern:**
 * This use case is typically invoked by ViewModels when users search
 * for available flights between two locations.
 *
 * **Architecture Layer:**
 * This use case belongs to the Domain layer and depends on the FlightRepository
 * interface. It doesn't know about the implementation details of the repository.
 *
 * @property flightRepository The flight repository interface for data access
 *
 * @sample
 * ```kotlin
 * // In a ViewModel
 * class SearchViewModel @Inject constructor(
 *     private val searchFlightsUseCase: SearchFlightsUseCase
 * ) : ViewModel() {
 *     
 *     fun searchFlights(from: String, to: String) {
 *         viewModelScope.launch {
 *             searchFlightsUseCase.invoke(origin = from, destination = to)
 *                 .onSuccess { flights ->
 *                     _uiState.update { it.copy(flights = flights) }
 *                 }
 *                 .onFailure { error ->
 *                     _uiState.update { it.copy(error = error.message) }
 *                 }
 *         }
 *     }
 * }
 * ```
 *
 * @see Flight
 * @see FlightRepository
 * @see Result
 */
class SearchFlightsUseCase(private val flightRepository: FlightRepository) {
    /**
     * Searches for available flights between the specified origin and destination.
     *
     * This method performs a flight search operation by delegating to the
     * flight repository. It handles the business logic for flight search
     * and provides a consistent error handling mechanism.
     *
     * **Search Parameters:**
     * - **Origin**: Departure airport code (e.g., "RUH", "JED")
     * - **Destination**: Arrival airport code (e.g., "DXB", "LHR")
     *
     * **Return Values:**
     * - `Result.success(List<Flight>)` - List of available flights
     * - `Result.failure(Exception)` - Error occurred during search
     *
     * **Error Scenarios:**
     * - Network connectivity issues
     * - Invalid airport codes
     * - Repository access failures
     * - No flights available for the route
     *
     * **Usage:**
     * ```kotlin
     * val result = searchFlightsUseCase.invoke(
     *     origin = "RUH",
     *     destination = "JED"
     * )
     * 
     * result.onSuccess { flights ->
     *     println("Found ${flights.size} flights")
     * }.onFailure { error ->
     *     println("Search failed: ${error.message}")
     * }
     * ```
     *
     * @param origin The departure airport code or city name
     * @param destination The arrival airport code or city name
     * @return A [Result] containing a list of available flights.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     *
     * @see Flight
     * @see Result
     */
    suspend operator fun invoke(origin: String, destination: String): Result<List<Flight>> =
        flightRepository.getFlights(origin, destination)
}