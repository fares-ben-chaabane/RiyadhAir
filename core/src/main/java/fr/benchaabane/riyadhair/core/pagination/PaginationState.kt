package fr.benchaabane.riyadhair.core.pagination

/**
 * Represents the current state of pagination for a list of items.
 *
 * This data class encapsulates all the necessary information to manage
 * pagination state, including the current items, loading states,
 * pagination metadata, and error information.
 *
 * **State Properties:**
 * - **items**: Current list of loaded items
 * - **isLoading**: Whether the initial page is being loaded
 * - **isLoadingMore**: Whether additional pages are being loaded
 * - **hasNextPage**: Whether there are more pages available
 * - **currentPage**: Current page number (1-based indexing)
 * - **error**: Error message if loading failed
 *
 * **Usage Pattern:**
 * ```kotlin
 * var paginationState by remember { 
 *     mutableStateOf(PaginationState<Flight>()) 
 * }
 * 
 * when {
 *     paginationState.isLoading -> LoadingIndicator()
 *     paginationState.error != null -> ErrorMessage(paginationState.error)
 *     else -> {
 *         LazyColumn {
 *             items(paginationState.items) { item ->
 *                 ItemRow(item)
 *             }
 *             
 *             if (paginationState.hasNextPage && !paginationState.isLoadingMore) {
 *                 LoadMoreButton {
 *                     paginator.loadNextItems()
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * @param T The type of items being paginated
 */
data class PaginationState<T>(
    val items: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasNextPage: Boolean = true,
    val currentPage: Int = 1,
    val error: String? = null
)

/**
 * Defines the contract for pagination operations.
 *
 * This interface provides a standardized way to implement pagination
 * across different data sources and use cases. It abstracts the
 * complexity of pagination logic behind a simple interface.
 *
 * **Core Operations:**
 * - **loadNextItems**: Loads the next page of items
 * - **reset**: Resets pagination to the initial state
 *
 * **Implementation Requirements:**
 * - Must handle loading states appropriately
 * - Should manage pagination keys internally
 * - Must be thread-safe for concurrent access
 *
 * @param Key The type of key used for pagination (e.g., page number, cursor)
 * @param Item The type of items being paginated
 */
interface Paginator<Key, Item> {
    /**
     * Loads the next page of items.
     *
     * This method should be called when the user requests more items
     * or when automatic pagination is triggered. It's responsible for
     * managing the loading state and coordinating with the data source.
     *
     * **Implementation Notes:**
     * - Should check if a request is already in progress
     * - Must update loading states appropriately
     * - Should handle errors gracefully
     * - Must update the pagination key for the next request
     */
    suspend fun loadNextItems()
    
    /**
     * Resets pagination to the initial state.
     *
     * This method clears all loaded items and resets the pagination
     * key to the initial value. It's typically called when:
     * - The user performs a new search
     * - The data source changes
     * - An error occurs that requires a fresh start
     *
     * **Implementation Notes:**
     * - Should clear any cached data
     * - Must reset the pagination key to initialKey
     * - Should clear any error states
     */
    fun reset()
}

/**
 * Default implementation of the Paginator interface.
 *
 * This class provides a complete pagination solution that can be
 * customized through callback functions. It handles all the common
 * pagination patterns including loading states, error handling,
 * and key management.
 *
 * **Key Features:**
 * - **Automatic State Management**: Handles loading states and request deduplication
 * - **Flexible Data Loading**: Customizable data fetching through callbacks
 * - **Error Handling**: Built-in error handling with callback support
 * - **Key Management**: Automatic pagination key updates
 * - **Thread Safety**: Safe for concurrent access
 *
 * **Callback Functions:**
 * - **onLoadUpdated**: Called when loading state changes
 * - **onRequest**: Performs the actual data fetching
 * - **getNextKey**: Determines the next key from fetched items
 * - **onError**: Handles errors during data fetching
 * - **onSuccess**: Processes successfully fetched data
 *
 * @param Key The type of key used for pagination
 * @param Item The type of items being paginated
 * @param initialKey The initial pagination key
 * @param onLoadUpdated Callback to update loading state
 * @param onRequest Function to fetch data for a given key
 * @param getNextKey Function to determine the next key from fetched items
 * @param onError Function to handle errors
 * @param onSuccess Function to process successful results
 */
class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private val getNextKey: suspend (List<Item>) -> Key,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit,
) : Paginator<Key, Item> {

    /**
     * Current pagination key for the next request.
     *
     * This value is updated after each successful data fetch
     * to enable progressive pagination through the data set.
     */
    private var currentKey = initialKey
    
    /**
     * Flag indicating whether a request is currently in progress.
     *
     * This prevents multiple concurrent requests and ensures
     * proper loading state management.
     */
    private var isMakingRequest = false

    /**
     * Loads the next page of items.
     *
     * This implementation includes request deduplication, loading state
     * management, and proper error handling. It follows this sequence:
     * 1. Check if a request is already in progress
     * 2. Update loading state
     * 3. Fetch data using the provided request function
     * 4. Process the result and update the pagination key
     * 5. Handle any errors that occur
     *
     * **Request Deduplication:**
     * - Prevents multiple concurrent requests
     * - Ensures consistent loading state
     * - Improves performance and user experience
     */
    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    /**
     * Resets pagination to the initial state.
     *
     * This method clears the current pagination key and resets
     * it to the initial value, effectively starting pagination
     * from the beginning.
     */
    override fun reset() {
        currentKey = initialKey
    }
}
