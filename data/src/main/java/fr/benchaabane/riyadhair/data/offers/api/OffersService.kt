package fr.benchaabane.riyadhair.data.offers.api

import retrofit2.http.GET

/**
 * Retrofit service interface for offers-related API operations.
 *
 * This interface defines the HTTP endpoints and methods for retrieving
 * travel offers through the remote API service. It provides methods
 * for fetching the best available offers.
 *
 * **API Endpoints:**
 * - **Base Path**: `/offers/best`
 * - **HTTP Methods**: GET
 * - **Authentication**: May require valid user authentication
 * - **Response Format**: JSON with OffersResponse structure
 *
 * **Service Features:**
 * - **Best Offers**: Retrieves top travel offers
 * - **Suspending Functions**: Coroutine-friendly API calls
 * - **Type Safety**: Strong typing with OffersResponse
 * - **Simple Interface**: Single method for offers retrieval
 *
 * **Network Integration:**
 * - **Retrofit Integration**: Uses Retrofit for HTTP communication
 * - **Coroutine Support**: All methods are suspending functions
 * - **Error Handling**: Network errors handled by calling code
 * - **Response Mapping**: Automatic JSON parsing to DTOs
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by OffersRepositoryImpl
 * - **Offers Display**: Fetching offers for home screen
 * - **Data Synchronization**: Keeping local and remote data in sync
 * - **API Communication**: Network layer for offers operations
 *
 * **Error Scenarios:**
 * - **Network Failures**: Connection issues and timeouts
 * - **Authentication Errors**: Invalid or expired credentials
 * - **Server Errors**: API endpoint failures and errors
 * - **Data Validation**: Invalid response data
 *
 * @see OffersResponse
 * @see OfferDto
 * @see fr.benchaabane.riyadhair.data.offers.repositories.OffersRepositoryImpl
 */
interface OffersService {
    /**
     * Retrieves the best available travel offers from the API.
     *
     * This method fetches the top travel offers available from the
     * remote service, typically including special deals, discounts,
     * and featured destinations.
     *
     * **HTTP Details:**
     * - **Method**: GET
     * - **Endpoint**: `/offers/best`
     * - **Authentication**: May be required (user must be logged in)
     * - **Response**: OffersResponse with best offers list
     *
     * **Response Handling:**
     * - **Success**: Returns OffersResponse with best offers
     * - **No Offers**: Returns empty offers list
     * - **Error**: Throws exception for network or server errors
     *
     * **Data Flow:**
     * 1. **API Call**: HTTP GET request to best offers endpoint
     * 2. **Response Parsing**: JSON response converted to OffersResponse
     * 3. **Data Return**: Best offers list with offer details
     *
     * **Usage Context:**
     * - **Home Screen**: Displaying featured offers to users
     * - **Offers Showcase**: Highlighting best deals and promotions
     * - **User Engagement**: Encouraging travel planning and booking
     * - **Data Synchronization**: Updating local offers cache
     *
     * **Business Logic:**
     * - **Featured Offers**: Curated selection of best deals
     * - **User Experience**: Providing attractive travel options
     * - **Promotional Content**: Highlighting special offers
     * - **Travel Inspiration**: Encouraging user engagement
     *
     * @return OffersResponse containing the best available travel offers
     * @throws retrofit2.HttpException for HTTP errors (4xx, 5xx)
     * @throws java.io.IOException for network errors
     */
    @GET("offers/best")
    suspend fun getBestOffers(): OffersResponse
}
