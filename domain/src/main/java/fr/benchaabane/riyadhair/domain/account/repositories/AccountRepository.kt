package fr.benchaabane.riyadhair.domain.account.repositories

import fr.benchaabane.riyadhair.domain.account.models.Account
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for account-related data operations.
 * 
 * This interface defines the contract for accessing and managing account data
 * from various data sources (remote API, local database, etc.). It follows
 * the Repository pattern to abstract data access details from the domain layer.
 * 
 * **Responsibilities:**
 * - Provides a unified interface for account data access
 * - Abstracts data source implementation details
 * - Ensures consistent data access patterns across the application
 * - Supports both local and remote data operations
 * 
 * **Data Sources:**
 * The repository may coordinate between multiple data sources:
 * - **Remote API**: For real-time account updates
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
 * @see Account
 * @see Result
 * 
 * @sample
 * ```kotlin
 * // Implementation example
 * class AccountRepositoryImpl @Inject constructor(
 *     private val accountDao: AccountDao,
 *     private val accountService: AccountService
 * ) : AccountRepository {
 *     
 *     override suspend fun getAccount(): Result<Account?> {
 *         return try {
 *             // Try remote first, fallback to local
 *             val remoteAccount = accountService.getAccount()
 *             if (remoteAccount != null) {
 *                 accountDao.upsertAccount(remoteAccount.toEntity())
 *                 Result.success(remoteAccount.toDomain())
 *             } else {
 *                 Result.success(accountDao.getAccount()?.toDomain())
 *             }
 *         } catch (e: Exception) {
 *             Result.failure(e)
 *         }
 *     }
 * }
 * ```
 */
interface AccountRepository {
    /**
     * Retrieves the current user's account information.
     * 
     * This method fetches the current user's account data from the appropriate
     * data source. The implementation may use different strategies:
     * 
     * **Data Source Priority:**
     * 1. **Remote API**: For real-time data and updates
     * 2. **Local Database**: For offline access and caching
     * 3. **Memory Cache**: For performance optimization
     * 
     * **Return Values:**
     * - `Result.success(Account)` - Account found and retrieved successfully
     * - `Result.success(null)` - No account found (user not logged in)
     * - `Result.failure(Exception)` - Error occurred during retrieval
     * 
     * **Error Scenarios:**
     * - Network connectivity issues
     * - Authentication failures
     * - Database corruption or access issues
     * - Server errors or timeouts
     * 
     * **Performance Considerations:**
     * - First call may involve network request
     * - Subsequent calls may use cached data
     * - Database operations are performed on background threads
     * 
     * @return A [Result] containing the account information if found, or null if not found.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     * 
     * @sample
     * ```kotlin
     * val result = accountRepository.getAccount()
     * result.onSuccess { account ->
     *     when (account) {
     *         null -> println("User not logged in")
     *         else -> println("Welcome back, ${account.fullName}!")
     *     }
     * }.onFailure { error ->
     *     println("Failed to retrieve account: ${error.message}")
     * }
     * ```
     * 
     * @see Account
     * @see Result
     */
    suspend fun getAccount(): Result<Account?>
}
