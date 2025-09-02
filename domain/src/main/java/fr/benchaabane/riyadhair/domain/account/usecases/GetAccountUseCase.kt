package fr.benchaabane.riyadhair.domain.account.usecases

import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.repositories.AccountRepository
import javax.inject.Inject

/**
 * Use case for retrieving the current user's account information.
 * 
 * This use case follows the command pattern and provides a single responsibility
 * for fetching account data from the repository layer. It encapsulates the business
 * logic for account retrieval and provides a clean interface for the presentation layer.
 * 
 * **Responsibilities:**
 * - Fetches current user account information
 * - Handles account data retrieval errors gracefully
 * - Provides a consistent Result-based API
 * 
 * **Usage Pattern:**
 * This use case is typically invoked by ViewModels when they need to display
 * or update account-related information in the UI.
 * 
 * **Architecture Layer:**
 * This use case belongs to the Domain layer and depends on the AccountRepository
 * interface. It doesn't know about the implementation details of the repository.
 * 
 * @property repository The account repository interface for data access
 * 
 * @sample
 * ```kotlin
 * // In a ViewModel
 * class AccountViewModel @Inject constructor(
 *     private val getAccountUseCase: GetAccountUseCase
 * ) : ViewModel() {
 *     
 *     fun loadAccount() {
 *         viewModelScope.launch {
 *             getAccountUseCase.invoke()
 *                 .onSuccess { account ->
 *                     _uiState.update { it.copy(account = account) }
 *                 }
 *                 .onFailure { error ->
 *                     _uiState.update { it.copy(error = error.message) }
 *                 }
 *         }
 *     }
 * }
 * ```
 * 
 * @see Account
 * @see AccountRepository
 * @see Result
 */
class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    /**
     * Retrieves the current user's account information.
     * 
     * This method fetches account data from the repository and returns a Result
     * that can contain either the account data or an error. The method handles
     * both successful retrievals and error cases gracefully.
     * 
     * **Return Values:**
     * - `Result.success(Account?)` - Account data retrieved successfully
     * - `Result.failure(Exception)` - Error occurred during retrieval
     * 
     * **Error Handling:**
     * The method returns errors wrapped in a Result, allowing the caller to
     * handle different types of failures appropriately (network errors, database
     * errors, etc.).
     * 
     * **Suspending Function:**
     * This method is marked as `suspend` because it performs asynchronous
     * operations that may involve network calls or database queries.
     * 
     * @return Result containing the account data or an error
     * 
     * @sample
     * ```kotlin
     * val result = getAccountUseCase.invoke()
     * result.onSuccess { account ->
     *     when (account) {
     *         null -> println("No account found")
     *         else -> println("Account: ${account.fullName}")
     *     }
     * }.onFailure { error ->
     *     when (error) {
     *         is NetworkException -> println("Network error: ${error.message}")
     *         is DatabaseException -> println("Database error: ${error.message}")
     *         else -> println("Unexpected error: ${error.message}")
     *     }
     * }
     * ```
     * 
     * @see Account
     * @see AccountRepository
     * @see Result
     */
    suspend operator fun invoke(): Result<Account?> = repository.getAccount()
}
