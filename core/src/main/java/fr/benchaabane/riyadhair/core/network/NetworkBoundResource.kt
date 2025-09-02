package fr.benchaabane.riyadhair.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * A generic class that can provide a resource backed by both the local database and the network.
 *
 * This class implements the NetworkBoundResource pattern, which provides a unified way to
 * handle data that can come from multiple sources (local database, remote API, etc.).
 * It follows the offline-first approach, ensuring data availability even when network
 * connectivity is limited.
 *
 * **Data Flow Strategy:**
 * 1. **Initial Load**: Emits loading state with null data
 * 2. **Database Check**: Loads data from local database
 * 3. **Network Decision**: Determines if network fetch is needed
 * 4. **Network Fetch**: Attempts to fetch fresh data from remote source
 * 5. **Data Persistence**: Saves network result to local database
 * 6. **Final Emission**: Emits success with updated data
 *
 * **Key Benefits:**
 * - **Offline Support**: Data available even without network
 * - **Real-time Updates**: Fresh data when network is available
 * - **Error Resilience**: Graceful fallback to local data on network failures
 * - **Consistent API**: Uniform data access pattern across the app
 *
 * **Usage Pattern:**
 * Extend this class and implement the abstract methods to define
 * your specific data fetching and persistence logic.
 *
 * You can read more about it in the [Architecture Guide](https://developer.android.com/arch).
 *
 * @param ResultType The type of data that will be provided
 * @param RequestType The type of data that will be fetched from the network
 *
 * @sample
 * ```kotlin
 * class FlightNetworkBoundResource(
 *     private val flightDao: FlightDao,
 *     private val flightService: FlightService,
 *     private val origin: String,
 *     private val destination: String
 * ) : NetworkBoundResource<List<Flight>, FlightsResponse>() {
 *     
 *     override fun loadFromDb(): Flow<List<Flight>> = flightDao.getFlights(origin, destination)
 *     
 *     override fun shouldFetch(data: List<Flight>?): Boolean = data.isNullOrEmpty()
 *     
 *     override suspend fun createCall(): FlightsResponse = flightService.searchFlights(origin, destination)
 *     
 *     override suspend fun saveCallResult(data: FlightsResponse) {
 *         flightDao.insertAll(data.flights.map { it.toEntity() })
 *     }
 * }
 * ```
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading(data = null))

        val dbValue = loadFromDb().first()

        if (shouldFetch(dbValue)) {
            emit(Resource.Loading(data = dbValue))

            try {
                val networkResult = createCall()
                saveCallResult(networkResult)
                emitAll(loadFromDb().map { Resource.Success(it) })
            } catch (exception: Exception) {
                onFetchFailed(exception)
                emitAll(loadFromDb().map { Resource.Error(exception.message ?: "Unknown error", it) })
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    /**
     * Called when a network fetch fails.
     *
     * This method provides a hook for handling network failures. Override it
     * to implement custom error handling logic such as logging, analytics,
     * or user notification.
     *
     * **Default Implementation:**
     * Does nothing by default, allowing subclasses to implement custom
     * error handling as needed.
     *
     * @param exception The exception that caused the network fetch to fail
     */
    protected open fun onFetchFailed(exception: Exception) {}

    /**
     * Loads data from the local database.
     *
     * This method should return a Flow that emits the data stored in the
     * local database. The data will be used for offline access and as
     * a fallback when network requests fail.
     *
     * **Implementation Requirements:**
     * - Return a Flow that emits the local data
     * - Handle database access errors appropriately
     * - Ensure the Flow is properly configured for the data type
     *
     * @return Flow that emits the data from the local database
     */
    protected abstract fun loadFromDb(): Flow<ResultType>

    /**
     * Determines whether a network fetch is needed.
     *
     * This method implements the caching strategy by deciding whether
     * to fetch fresh data from the network or use cached local data.
     *
     * **Common Strategies:**
     * - **Always Fetch**: Return true to always get fresh data
     * - **Cache First**: Return false if local data exists and is recent
     * - **Conditional Fetch**: Return true based on data age, size, or other criteria
     *
     * @param data The current data from the local database, or null if no data exists
     * @return true if a network fetch should be performed, false otherwise
     */
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    /**
     * Fetches data from the network.
     *
     * This method performs the actual network request to fetch fresh data.
     * It should handle network-specific logic such as API calls, authentication,
     * and request formatting.
     *
     * **Implementation Requirements:**
     * - Perform the network request
     * - Handle network errors appropriately
     - Return the network response data
     * - Ensure proper error propagation
     *
     * @return The data fetched from the network
     * @throws Exception if the network request fails
     */
    protected abstract suspend fun createCall(): RequestType

    /**
     * Saves the network response to the local database.
     *
     * This method persists the network data locally for offline access
     * and future use. It should handle data transformation and storage
     * operations.
     *
     * **Implementation Requirements:**
     * - Transform network data to local format if needed
     * - Store data in the local database
     * - Handle storage errors appropriately
     * - Ensure data consistency
     *
     * @param data The data received from the network to be saved locally
     */
    protected abstract suspend fun saveCallResult(data: RequestType)
}

/**
 * Sealed class representing the state of a resource operation.
 *
 * This class provides a unified way to represent different states of
 * data operations (loading, success, error) across the application.
 * It's commonly used with NetworkBoundResource and ViewModels to
 * provide consistent state management.
 *
 * **State Types:**
 * - **Success**: Operation completed successfully with data
 * - **Loading**: Operation is in progress, may include cached data
 * - **Error**: Operation failed with error message and optional cached data
 *
 * **Usage Pattern:**
 * ```kotlin
 * when (resource) {
 *     is Resource.Success -> {
 *         // Handle successful data
 *         displayData(resource.data)
 *     }
 *     is Resource.Loading -> {
 *         // Show loading state
 *         showLoading(resource.data) // Show cached data if available
 *     }
 *     is Resource.Error -> {
 *         // Handle error state
 *         showError(resource.message, resource.data) // Show cached data if available
 *     }
 * }
 * ```
 *
 * @param T The type of data this resource represents
 * @param data The data associated with this resource state
 * @param message The message associated with this resource state (typically error messages)
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * Represents a successful resource operation.
     *
     * This state indicates that the operation completed successfully
     * and contains the resulting data.
     *
     * @param data The data resulting from the successful operation
     */
    class Success<T>(data: T) : Resource<T>(data)
    
    /**
     * Represents a resource operation that is currently in progress.
     *
     * This state indicates that the operation is ongoing. It may include
     * cached data from previous operations to provide a better user
     * experience during loading.
     *
     * @param data Optional cached data to display while loading
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
    
    /**
     * Represents a failed resource operation.
     *
     * This state indicates that the operation failed and includes
     * an error message. It may also include cached data from previous
     * successful operations to maintain UI functionality.
     *
     * @param message The error message describing what went wrong
     * @param data Optional cached data to display despite the error
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
