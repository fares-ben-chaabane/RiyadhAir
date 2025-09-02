package fr.benchaabane.riyadhair.domain.flights.repositories

import fr.benchaabane.riyadhair.domain.flights.models.Flight

/**
 * Repository interface for flight-related data operations.
 *
 * This interface defines the contract for accessing and managing flight data
 * from various data sources (remote API, local database, etc.). It follows
 * the Repository pattern to abstract data access details from the domain layer.
 *
 * **Responsibilities:**
 * - Provides a unified interface for flight data access
 * - Abstracts data source implementation details
 * - Ensures consistent data access patterns across the application
 * - Supports both local and remote data operations
 *
 * **Data Sources:**
 * The repository may coordinate between multiple data sources:
 * - **Remote API**: For real-time flight availability
 * - **Local Database**: For offline access and caching
 * - **Memory Cache**: For performance optimization
 *
 * **Error Handling:**
 * All methods return Result types to handle potential failures
 * gracefully, including network errors, database errors, and validation errors.
 *
 * **Threading:**
 * All methods are suspending functions, designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * @see Flight
 * @see Result
 *
 * @sample
 * ```kotlin
 * // Implementation example
 * class FlightRepositoryImpl @Inject constructor(
 *     private val flightService: FlightService
 * ) : FlightRepository {
 *     
 *     override suspend fun getFlights(origin: String, destination: String): Result<List<Flight>> {
 *         return try {
 *             val response = flightService.searchFlights(origin, destination)
 *             Result.success(response.flights.map { it.toDomain() })
 *         } catch (e: Exception) {
 *             Result.failure(e)
 *     }
 * }
 * ```
 */
interface FlightRepository {
    /**
     * Retrieves flights based on origin and destination.
     *
     * This method searches for available flights between two airports,
     * providing real-time availability information for flight booking.
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
     * val result = flightRepository.getFlights("RUH", "JED")
     * result.onSuccess { flights ->
     *     println("Found ${flights.size} flights from Riyadh to Jeddah")
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
    suspend fun getFlights(origin: String, destination: String): Result<List<Flight>>
    
    /**
     * Retrieves a specific flight by its flight number.
     *
     * This method fetches detailed information about a specific flight
     * using its unique flight number identifier.
     *
     * **Search Parameters:**
     * - **Flight Number**: Unique flight identifier (e.g., "RX101", "EK123")
     *
     * **Return Values:**
     * - `Result.success(Flight)` - Flight found successfully
     * - `Result.success(null)` - Flight not found
     * - `Result.failure(Exception)` - Error occurred during search
     *
     * **Error Scenarios:**
     * - Network connectivity issues
     * - Invalid flight number format
     * - Repository access failures
     * - Flight number doesn't exist
     *
     * **Usage:**
     * ```kotlin
     * val result = flightRepository.getFlight("RX101")
     * result.onSuccess { flight ->
     *     when (flight) {
     *         null -> println("Flight RX101 not found")
     *         else -> println("Flight: ${flight.airline} ${flight.flightNumber}")
     *     }
     * }.onFailure { error ->
     *     println("Search failed: ${error.message}")
     * }
     * ```
     *
     * @param flightNumber The unique identifier for the flight
     * @return A [Result] containing the flight if found, or null if not found.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     *
     * @see Flight
     * @see Result
     */
    suspend fun getFlight(flightNumber: String): Result<Flight?>
}