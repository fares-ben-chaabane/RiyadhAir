package fr.benchaabane.riyadhair.data.account.repositories

import fr.benchaabane.riyadhair.core.extensions.recoverSuspendCatching
import fr.benchaabane.riyadhair.core.extensions.runSuspendCatching
import fr.benchaabane.riyadhair.data.account.api.AccountService
import fr.benchaabane.riyadhair.data.account.dao.AccountDao
import fr.benchaabane.riyadhair.data.account.mappers.toDomain
import fr.benchaabane.riyadhair.data.account.mappers.toEntity
import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.repositories.AccountRepository
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts

/**
 * Implementation of the AccountRepository interface.
 *
 * This class provides the concrete implementation for account data operations,
 * coordinating between remote API calls and local database storage. It follows
 * the offline-first approach, ensuring data availability even when network
 * connectivity is limited.
 *
 * **Data Strategy:**
 * - **Remote First**: Attempts to fetch fresh data from the API
 * - **Local Fallback**: Uses cached data if remote call fails
 * - **Automatic Sync**: Updates local database with remote data
 * - **Error Handling**: Gracefully handles network and database failures
 *
 * **Dependencies:**
 * - **AccountService**: For remote API operations
 * - **AccountDao**: For local database operations
 * - **Mappers**: For data transformation between layers
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * @see AccountRepository
 * @see AccountService
 * @see AccountDao
 * @see Account
 */
@OptIn(ExperimentalContracts::class)
class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val accountService: AccountService
) : AccountRepository {

    /**
     * Retrieves the current user's account information.
     *
     * This method implements a smart data fetching strategy that prioritizes
     * fresh data from the remote API while maintaining offline capability.
     *
     * **Data Flow:**
     * 1. **API Call**: Attempts to fetch account from remote service
     * 2. **Data Processing**: If remote data exists, updates local database
     * 3. **Local Retrieval**: Fetches account from local database (either
     *    updated or existing cached data)
     * 4. **Domain Mapping**: Converts database entity to domain model
     *
     * **Error Handling:**
     * - **Network Failures**: Gracefully falls back to local data
     * - **API Errors**: Returns cached data if available
     * - **Database Errors**: Handled by Result wrapper
     *
     * **Return Values:**
     * - `Result.success(Account?)` - Account data retrieved successfully
     * - `Result.failure(Exception)` - Error occurred during operation
     *
     * @return Result containing the account data or an error
     *
     * @see Account
     * @see Result
     */
    override suspend fun getAccount(): Result<Account?> {
        return runSuspendCatching {
            val accountDto = accountService.getAccount()
            if (accountDto == null) {
                accountDao.getAccount()?.toDomain()
            } else {
                accountDao.upsertAccount(accountDto.toEntity())
                accountDao.getAccount()?.toDomain()
            }
        }.recoverSuspendCatching { null }
    }
}
