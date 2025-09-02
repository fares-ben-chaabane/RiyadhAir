package fr.benchaabane.riyadhair.data.account.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

/**
 * Retrofit service interface for account-related API operations.
 *
 * This interface defines the HTTP endpoints and methods for managing
 * user account information through the remote API service. It provides
 * methods for retrieving and updating account profiles.
 *
 * **API Endpoints:**
 * - **Base Path**: `/account/profile`
 * - **HTTP Methods**: GET, PUT
 * - **Authentication**: Requires valid user authentication
 * - **Response Format**: JSON with AccountDto structure
 *
 * **Service Features:**
 * - **Profile Retrieval**: Fetch current user account information
 * - **Profile Updates**: Modify existing account details
 * - **Suspending Functions**: Coroutine-friendly API calls
 * - **Type Safety**: Strong typing with AccountDto models
 *
 * **Network Integration:**
 * - **Retrofit Integration**: Uses Retrofit for HTTP communication
 * - **Coroutine Support**: All methods are suspending functions
 * - **Error Handling**: Network errors handled by calling code
 * - **Response Mapping**: Automatic JSON parsing to DTOs
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by AccountRepositoryImpl
 * - **Data Synchronization**: Keeping local and remote data in sync
 * - **User Management**: Managing user profile information
 * - **API Communication**: Network layer for account operations
 *
 * **Error Scenarios:**
 * - **Network Failures**: Connection issues and timeouts
 * - **Authentication Errors**: Invalid or expired credentials
 * - **Server Errors**: API endpoint failures and errors
 * - **Data Validation**: Invalid request data or responses
 *
 * @see AccountDto
 * @see fr.benchaabane.riyadhair.data.account.repositories.AccountRepositoryImpl
 */
interface AccountService {
    /**
     * Retrieves the current user's account profile from the API.
     *
     * This method fetches the complete account information for the
     * authenticated user, including personal details, loyalty information,
     * and preferences.
     *
     * **HTTP Details:**
     * - **Method**: GET
     * - **Endpoint**: `/account/profile`
     * - **Authentication**: Required (user must be logged in)
     * - **Response**: AccountDto or null if not found
     *
     * **Response Handling:**
     * - **Success**: Returns AccountDto with user profile data
     * - **Not Found**: Returns null if profile doesn't exist
     * - **Error**: Throws exception for network or server errors
     *
     * **Data Flow:**
     * 1. **API Call**: HTTP GET request to profile endpoint
     * 2. **Response Parsing**: JSON response converted to AccountDto
     * 3. **Data Return**: Profile data or null for missing profiles
     *
     * **Usage Context:**
     * - **Profile Loading**: Initial account data retrieval
     * - **Data Synchronization**: Updating local profile cache
     * - **User Authentication**: Verifying user login status
     *
     * @return AccountDto containing user profile information, or null if not found
     * @throws retrofit2.HttpException for HTTP errors (4xx, 5xx)
     * @throws java.io.IOException for network errors
     */
    @GET("account/profile")
    suspend fun getAccount(): AccountDto?
    
    /**
     * Updates the current user's account profile on the API.
     *
     * This method sends updated account information to the server,
     * allowing users to modify their profile details, preferences,
     * and personal information.
     *
     * **HTTP Details:**
     * - **Method**: PUT
     * - **Endpoint**: `/account/profile`
     * - **Authentication**: Required (user must be logged in)
     * - **Request Body**: AccountDto with updated information
     * - **Response**: Updated AccountDto from server
     *
     * **Request Handling:**
     * - **Data Validation**: Server validates request data
     * - **Update Processing**: Server processes profile changes
     * - **Response**: Returns updated profile data
     *
     * **Data Flow:**
     * 1. **Request Preparation**: AccountDto prepared with updates
     * 2. **API Call**: HTTP PUT request with updated data
     * 3. **Server Processing**: Server validates and applies changes
     * 4. **Response**: Updated AccountDto returned
     *
     * **Usage Context:**
     * - **Profile Updates**: Modifying user information
     * - **Preference Changes**: Updating user settings
     * - **Data Synchronization**: Keeping server data current
     *
     * @param account The updated account information to send to the server
     * @return AccountDto containing the updated profile information from the server
     * @throws retrofit2.HttpException for HTTP errors (4xx, 5xx)
     * @throws java.io.IOException for network errors
     * @throws retrofit2.HttpException.BadRequest for invalid data
     */
    @PUT("account/profile")
    suspend fun updateAccount(@Body account: AccountDto): AccountDto
}
