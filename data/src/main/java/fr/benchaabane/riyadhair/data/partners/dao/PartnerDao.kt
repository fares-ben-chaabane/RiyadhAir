package fr.benchaabane.riyadhair.data.partners.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for partner-related database operations.
 *
 * This DAO provides methods for accessing and manipulating partner data
 * in the local Room database. It supports basic CRUD operations and
 * follows Room's best practices for data access.
 *
 * **Supported Operations:**
 * - **Read**: Retrieve active partners sorted by name
 * - **Write**: Insert/update partner data in bulk
 * - **Delete**: Clear all partners from the database
 *
 * **Data Organization:**
 * - **Active Filtering**: Only returns active partners
 * - **Name Sorting**: Partners ordered alphabetically by name
 * - **Bulk Operations**: Efficient handling of multiple partners
 * - **Transaction Support**: Safe database operations
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by PartnerRepositoryImpl
 * - **Partners Display**: Retrieving partners for UI presentation
 * - **Data Synchronization**: Updating local partner cache
 * - **Offline Support**: Providing partner information when network is unavailable
 *
 * @see PartnerEntity
 * @see fr.benchaabane.riyadhair.domain.partners.models.Partner
 */
@Dao
interface PartnerDao {
    /**
     * Retrieves all active partners from the database, sorted by name.
     *
     * This method fetches only active partners and orders them alphabetically
     * by name for consistent and user-friendly display.
     *
     * **Query Details:**
     * - **SQL**: `SELECT * FROM partners WHERE isActive = 1 ORDER BY name ASC`
     * - **Active Filter**: Only returns partners where `isActive = true`
     * - **Sorting**: Alphabetical order by partner name
     * - **Result**: List of active partners for display
     *
     * **Data Filtering:**
     * - **Active Status**: Filters out inactive or disabled partners
     * - **User Experience**: Ensures only relevant partners are shown
     * - **Data Quality**: Maintains current and valid partner information
     * - **Business Logic**: Reflects current partnership status
     *
     * **Usage Context:**
     * - **Partners List**: Displaying available partners to users
     * - **UI Components**: Populating partner selection interfaces
     * - **Data Presentation**: Organizing partners for user browsing
     * - **Offline Access**: Providing cached partner data
     *
     * @return List of active partners sorted alphabetically by name
     */
    @Query("SELECT * FROM partners WHERE isActive = 1 ORDER BY name ASC")
    suspend fun getPartners(): List<PartnerEntity>
    
    /**
     * Inserts or updates multiple partners in the database.
     *
     * This method uses Room's `@Upsert` annotation to automatically
     * handle both insert and update operations for a list of partners.
     * If a partner with the same ID exists, it will be updated;
     * otherwise, new partners will be inserted.
     *
     * **Upsert Behavior:**
     * - **Insert**: Creates new partners if IDs don't exist
     * - **Update**: Modifies existing partners if IDs already exist
     * - **Conflict Resolution**: Automatically handled by Room
     * - **Bulk Processing**: Efficient handling of multiple partners
     *
     * **Transaction Safety:**
     * - **Atomic Operations**: All partners processed in single transaction
     * - **Data Consistency**: Ensures database integrity
     * - **Rollback Support**: Automatic rollback on failures
     * - **Performance**: Optimized for bulk operations
     *
     * **Usage Context:**
     * - **Data Synchronization**: Updating local cache with remote data
     * - **Bulk Operations**: Efficiently processing multiple partners
     * - **Cache Management**: Maintaining fresh partner data
     * - **Offline Support**: Ensuring local data availability
     *
     * @param partners List of partners to insert or update in the database
     */
    @Upsert
    suspend fun upsertAll(partners: List<PartnerEntity>)
    
    /**
     * Removes all partners from the database.
     *
     * This method clears the entire partners table, typically used
     * during data refresh operations or when clearing cached data.
     * Use with caution as this operation is irreversible.
     *
     * **Query Details:**
     * - **SQL**: `DELETE FROM partners`
     * - **Effect**: Removes all partner records
     * - **Use Case**: Data refresh, cache clearing, data reset
     * - **Performance**: Efficient bulk deletion operation
     *
     * **Usage Context:**
     * - **Data Refresh**: Clearing old data before fetching new partners
     * - **Cache Management**: Removing stale or expired partner data
     * - **Data Reset**: Clearing all partners for fresh start
     * - **Maintenance**: Database cleanup operations
     *
     * **Safety Considerations:**
     * - **Irreversible**: All partner data is permanently deleted
     * - **Bulk Operation**: Affects entire partners table
     * - **Use Cases**: Limited to specific scenarios requiring complete data refresh
     */
    @Query("DELETE FROM partners")
    suspend fun clearAll()
}
