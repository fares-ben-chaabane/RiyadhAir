package fr.benchaabane.riyadhair.data.offers.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for offer-related database operations.
 *
 * This DAO provides methods for accessing and manipulating offer data
 * in the local Room database. It supports basic CRUD operations and
 * follows Room's best practices for data access.
 *
 * **Supported Operations:**
 * - **Read**: Retrieve offers with sorting by discount percentage
 * - **Write**: Insert/update offer data in bulk
 * - **Delete**: Clear all offers from the database
 *
 * **Data Organization:**
 * - **Discount Sorting**: Offers are ordered by discount percentage (highest first)
 * - **Bulk Operations**: Efficient handling of multiple offers
 * - **Transaction Support**: Safe database operations
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by OffersRepositoryImpl
 * - **Offers Display**: Retrieving offers for UI presentation
 * - **Data Synchronization**: Updating local offer cache
 * - **Offline Support**: Providing offers when network is unavailable
 *
 * @see OfferEntity
 * @see fr.benchaabane.riyadhair.domain.offers.models.Offer
 */
@Dao
interface OfferDao {
    /**
     * Retrieves all offers from the database, sorted by discount percentage.
     *
     * This method fetches all stored offers and orders them by discount
     * percentage in descending order, ensuring the best deals appear first.
     *
     * **Query Details:**
     * - **SQL**: `SELECT * FROM offers ORDER BY discountPercentage DESC`
     * - **Sorting**: Highest discount percentage first
     * - **Result**: Complete list of all stored offers
     * - **Performance**: Efficient query with proper indexing
     *
     * **Data Ordering:**
     * - **Primary Sort**: Discount percentage (descending)
     * - **Best Deals**: Highest discounts appear first
     * - **User Experience**: Most attractive offers shown prominently
     * - **Business Logic**: Encourages user engagement with best deals
     *
     * **Usage Context:**
     * - **Home Screen**: Displaying featured offers
     * - **Offers List**: Showing all available offers
     * - **Data Presentation**: Organizing offers by attractiveness
     * - **User Engagement**: Highlighting best deals
     *
     * @return List of all offers sorted by discount percentage (highest first)
     */
    @Query("SELECT * FROM offers ORDER BY discountPercentage DESC")
    suspend fun getOffers(): List<OfferEntity>
    
    /**
     * Inserts or updates multiple offers in the database.
     *
     * This method uses Room's `@Upsert` annotation to automatically
     * handle both insert and update operations for a list of offers.
     * If an offer with the same ID exists, it will be updated;
     * otherwise, new offers will be inserted.
     *
     * **Upsert Behavior:**
     * - **Insert**: Creates new offers if IDs don't exist
     * - **Update**: Modifies existing offers if IDs already exist
     * - **Conflict Resolution**: Automatically handled by Room
     * - **Bulk Processing**: Efficient handling of multiple offers
     *
     * **Transaction Safety:**
     * - **Atomic Operations**: All offers processed in single transaction
     * - **Data Consistency**: Ensures database integrity
     * - **Rollback Support**: Automatic rollback on failures
     * - **Performance**: Optimized for bulk operations
     *
     * **Usage Context:**
     * - **Data Synchronization**: Updating local cache with remote data
     * - **Bulk Operations**: Efficiently processing multiple offers
     * - **Cache Management**: Maintaining fresh offer data
     * - **Offline Support**: Ensuring local data availability
     *
     * @param offers List of offers to insert or update in the database
     */
    @Upsert
    suspend fun upsertAll(offers: List<OfferEntity>)
    
    /**
     * Removes all offers from the database.
     *
     * This method clears the entire offers table, typically used
     * during data refresh operations or when clearing cached data.
     * Use with caution as this operation is irreversible.
     *
     * **Query Details:**
     * - **SQL**: `DELETE FROM offers`
     * - **Effect**: Removes all offer records
     * - **Use Case**: Data refresh, cache clearing, data reset
     * - **Performance**: Efficient bulk deletion operation
     *
     * **Usage Context:**
     * - **Data Refresh**: Clearing old data before fetching new offers
     * - **Cache Management**: Removing stale or expired offers
     * - **Data Reset**: Clearing all offers for fresh start
     * - **Maintenance**: Database cleanup operations
     *
     * **Safety Considerations:**
     * - **Irreversible**: All offer data is permanently deleted
     * - **Bulk Operation**: Affects entire offers table
     * - **Use Cases**: Limited to specific scenarios requiring complete data refresh
     */
    @Query("DELETE FROM offers")
    suspend fun clearAll()
}
