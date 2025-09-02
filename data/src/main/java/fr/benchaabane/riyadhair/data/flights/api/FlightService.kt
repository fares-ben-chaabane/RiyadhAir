package fr.benchaabane.riyadhair.data.flights.api

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for flight-related API operations.
 *
 * This interface defines the HTTP endpoints and methods for searching
 * and retrieving flight information through the remote API service.
 * It provides methods for flight search operations with pagination support.
 *
 * **API Endpoints:**
 * - **Base Path**: `/flights`
 * - **HTTP Methods**: GET
 * - **Authentication**: May require valid user authentication
 * - **Response Format**: JSON with FlightsResponse structure
 *
 * **Service Features:**
 * - **Flight Search**: Search flights by origin and destination
 * - **Pagination Support**: Configurable page size and page numbers
 * - **Query Parameters**: Flexible search criteria
 * - **Suspending Functions**: Coroutine-friendly API calls
 *
 * **Network Integration:**
 * - **Retrofit Integration**: Uses Retrofit for HTTP communication
 * - **Coroutine Support**: All methods are suspending functions
 * - **Error Handling**: Network errors handled by calling code
 * - **Response Mapping**: Automatic JSON parsing to DTOs
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by FlightRepositoryImpl
 * - **Flight Search**: Finding available flights for users
 * - **Data Synchronization**: Keeping local and remote data in sync
 * - **API Communication**: Network layer for flight operations
 *
 * **Error Scenarios:**
 * - **Network Failures**: Connection issues and timeouts
 * - **Authentication Errors**: Invalid or expired credentials
 * - **Server Errors**: API endpoint failures and errors
 * - **Data Validation**: Invalid search parameters
 *
 * @see FlightsResponse
 * @see FlightDto
 * @see fr.benchaabane.riyadhair.data.flights.repositories.FlightRepositoryImpl
 */
interface FlightService {
    /**
     * Searches for available flights based on origin and destination.
     *
     * This method performs a flight search operation using the specified
     * origin and destination airports, with optional pagination support
     * for handling large result sets.
     *
     * **HTTP Details:**
     * - **Method**: GET
     * - **Endpoint**: `/flights`
     * - **Query Parameters**: origin, destination, page, limit
     * - **Response**: FlightsResponse with matching flights
     *
     * **Search Parameters:**
     * - **origin**: Departure airport code (e.g., "CDG", "JFK")
     * - **destination**: Arrival airport code (e.g., "LAX", "LHR")
     * - **page**: Page number for pagination (default: 1)
     * - **limit**: Number of results per page (default: 20)
     *
     * **Response Handling:**
     * - **Success**: Returns FlightsResponse with flight list
     * - **No Results**: Returns empty flights list
     * - **Error**: Throws exception for network or server errors
     *
     * **Data Flow:**
     * 1. **Request Preparation**: Query parameters prepared
     * 2. **API Call**: HTTP GET request with search criteria
     * 3. **Response Parsing**: JSON response converted to FlightsResponse
     * 4. **Data Return**: Flight search results with pagination info
     *
     * **Usage Context:**
     * - **Flight Search**: Finding available flights for users
     * - **Route Planning**: Discovering flight options between airports
     * - **Pagination**: Handling large result sets efficiently
     * - **Data Synchronization**: Updating local flight cache
     *
     * **Pagination Details:**
     * - **Page Numbers**: 1-based page numbering
     * - **Page Size**: Configurable results per page
     * - **Result Limits**: Maximum results per request
     * - **Efficient Loading**: Load results in manageable chunks
     *
     * @param origin The departure airport code (e.g., "CDG", "JFK")
     * @param destination The arrival airport code (e.g., "LAX", "LHR")
     * @param page The page number for pagination (default: 1)
     * @param limit The number of results per page (default: 20)
     * @return FlightsResponse containing matching flights and pagination info
     * @throws retrofit2.HttpException for HTTP errors (4xx, 5xx)
     * @throws java.io.IOException for network errors
     * @throws retrofit2.HttpException.BadRequest for invalid search parameters
     */
    @GET("flights")
    suspend fun searchFlights(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): FlightsResponse
}
