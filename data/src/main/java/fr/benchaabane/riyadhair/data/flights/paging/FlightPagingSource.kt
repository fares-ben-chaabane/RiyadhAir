package fr.benchaabane.riyadhair.data.flights.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import fr.benchaabane.riyadhair.data.flights.api.FlightService
import fr.benchaabane.riyadhair.data.flights.mappers.toDomain
import fr.benchaabane.riyadhair.domain.flights.models.Flight
import javax.inject.Inject

/**
 * PagingSource implementation for flight data pagination.
 *
 * This class provides pagination support for flight search results,
 * enabling efficient loading of large datasets in manageable chunks.
 * It integrates with the Android Paging library to provide smooth
 * scrolling and memory-efficient data loading.
 *
 * **Paging Features:**
 * - **Incremental Loading**: Loads data page by page as needed
 * - **Memory Efficiency**: Only keeps necessary data in memory
 * - **Smooth Scrolling**: Provides seamless user experience
 * - **Error Handling**: Graceful handling of loading failures
 *
 * **Data Strategy:**
 * - **Page-based Loading**: Loads data in configurable page sizes
 * - **API Integration**: Fetches data from remote flight service
 * - **Data Mapping**: Converts API DTOs to domain models
 * - **State Management**: Tracks loading state and pagination keys
 *
 * **Dependencies:**
 * - **FlightService**: For remote API operations
 * - **Mappers**: For data transformation
 * - **Paging Library**: For pagination infrastructure
 *
 * **Threading:**
 * All methods are designed to work with coroutines and
 * the Android Paging library's background thread management.
 *
 * @see PagingSource
 * @see FlightService
 * @see Flight
 */
class FlightPagingSource @Inject constructor(
    private val flightService: FlightService,
    private val origin: String,
    private val destination: String
) : PagingSource<Int, Flight>() {

    /**
     * Loads a page of flight data from the remote API.
     *
     * This method is called by the Paging library to load data
     * for a specific page. It handles API calls, data mapping,
     * and pagination key management.
     *
     * **Loading Process:**
     * 1. **Page Calculation**: Determines the page to load
     * 2. **API Call**: Fetches flight data from remote service
     * 3. **Data Mapping**: Converts DTOs to domain models
     * 4. **Pagination Keys**: Calculates previous and next page keys
     * 5. **Result Creation**: Returns LoadResult with data and keys
     *
     * **Pagination Logic:**
     * - **Page Numbers**: 1-based page numbering
     * - **Previous Key**: null for first page, page-1 for others
     * - **Next Key**: page+1 if data exists, null if empty
     * - **Load Size**: Uses params.loadSize for page size
     *
     * **Error Handling:**
     * - **API Failures**: Catches exceptions and returns LoadResult.Error
     * - **Network Issues**: Handles connection problems gracefully
     * - **Data Issues**: Manages malformed responses
     *
     * **Performance Considerations:**
     * - **Efficient Loading**: Only loads requested page
     * - **Memory Management**: Processes data in chunks
     * - **Background Processing**: Runs on background threads
     *
     * @param params LoadParams containing page key and load size
     * @return LoadResult with flight data and pagination keys
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Flight> {
        return try {
            val page = params.key ?: 1
            val response = flightService.searchFlights(
                origin = origin,
                destination = destination,
                page = page,
                limit = params.loadSize
            )
            
            val flights = response.flights.map { it.toDomain() }
            
            LoadResult.Page(
                data = flights,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (flights.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    /**
     * Calculates the refresh key for the current paging state.
     *
     * This method determines which page should be loaded when
     * the user refreshes the data, ensuring a smooth refresh
     * experience without losing the current scroll position.
     *
     * **Refresh Logic:**
     * - **Anchor Position**: Uses the current scroll position
     * - **Closest Page**: Finds the page closest to current position
     * - **Key Calculation**: Determines appropriate refresh page
     * - **State Preservation**: Maintains user's current view
     *
     * **Key Calculation:**
     * - **Previous Key**: Uses prevKey + 1 if available
     * - **Next Key**: Uses nextKey - 1 as fallback
     * - **Fallback**: Returns null if no suitable key found
     *
     * **Usage Context:**
     * - **Pull to Refresh**: Handles user-initiated refresh
     * - **State Restoration**: Maintains scroll position after refresh
     * - **Smooth Experience**: Ensures seamless refresh behavior
     *
     * **Implementation Details:**
     * The method analyzes the current paging state to determine
     * the most appropriate page to load during refresh, ensuring
     * that the user's current view is maintained.
     *
     * @param state The current PagingState containing scroll and page information
     * @return The page number to load during refresh, or null if indeterminate
     */
    override fun getRefreshKey(state: PagingState<Int, Flight>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
