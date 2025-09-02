package fr.benchaabane.riyadhair.data.account.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for account-related database operations.
 *
 * This DAO provides methods for accessing and manipulating account data
 * in the local Room database. It supports basic CRUD operations and
 * follows Room's best practices for data access.
 *
 * **Supported Operations:**
 * - **Read**: Retrieve account information
 * - **Write**: Insert/update account data
 * - **Delete**: Clear account data
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * @see AccountEntity
 * @see fr.benchaabane.riyadhair.domain.account.models.Account
 */
@Dao
interface AccountDao {
    /**
     * Retrieves the current user's account from the database.
     *
     * This method fetches the first (and typically only) account record
     * from the database. It's designed for single-user applications
     * where only one account is stored at a time.
     *
     * **Query Details:**
     * - **SQL**: `SELECT * FROM account LIMIT 1`
     * - **Result**: Single account entity or null if no account exists
     * - **Performance**: Optimized with LIMIT clause
     *
     * @return The account entity if found, null otherwise
     */
    @Query("SELECT * FROM account LIMIT 1")
    suspend fun getAccount(): AccountEntity?
    
    /**
     * Inserts or updates an account in the database.
     *
     * This method uses Room's `@Upsert` annotation to automatically
     * handle both insert and update operations. If an account with
     * the same ID exists, it will be updated; otherwise, a new
     * account will be inserted.
     *
     * **Upsert Behavior:**
     * - **Insert**: Creates new account if ID doesn't exist
     * - **Update**: Modifies existing account if ID already exists
     * - **Conflict Resolution**: Automatically handled by Room
     *
     * @param account The account entity to insert or update
     */
    @Upsert
    suspend fun upsertAccount(account: AccountEntity)
    
    /**
     * Removes all account data from the database.
     *
     * This method clears the entire account table, typically used
     * during logout or account deletion scenarios. Use with caution
     * as this operation is irreversible.
     *
     * **Query Details:**
     * - **SQL**: `DELETE FROM account`
     * - **Effect**: Removes all account records
     * - **Use Case**: Logout, account deletion, data reset
     */
    @Query("DELETE FROM account")
    suspend fun clearAccount()
}
