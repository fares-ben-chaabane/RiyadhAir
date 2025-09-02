package fr.benchaabane.riyadhair.data.partners.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a business partner in the local database.
 *
 * This entity stores partner information locally for offline access and
 * caching purposes. It represents business partners, affiliates, and
 * service providers that collaborate with RiyadhAir to offer additional
 * travel-related services and benefits to customers.
 *
 * **Database Structure:**
 * - **Table Name**: `partners`
 * - **Primary Key**: `id` (String)
 * - **Data Persistence**: Local storage for offline access
 * - **Cache Management**: Stores partner data for quick retrieval
 *
 * **Partner Information:**
 * - **Core Details**: ID, name, category, description
 * - **Visual Assets**: Image URL for partner branding
 * - **Business Information**: Website URL, active status
 * - **Special Offers**: Discount percentage for customer benefits
 *
 * **Data Usage:**
 * - **Offline Support**: Provides partner data without network
 * - **Quick Access**: Fast local database queries
 * - **Data Synchronization**: Local cache of remote partner data
 * - **User Experience**: Immediate partner information display
 *
 * **Room Integration:**
 * - **Entity Annotation**: Maps to `partners` table
 * - **Primary Key**: Unique identifier for each partner
 * - **Column Mapping**: Automatic field-to-column mapping
 * - **Query Support**: Enables efficient database operations
 *
 * **Business Context:**
 * - **Partner Management**: Manages business partner information
 * - **Service Integration**: Tracks available partner services
 * - **Discount Programs**: Manages partner discount offerings
 * - **Offline Access**: Ensures partner data availability
 *
 * @property id Unique identifier for the partner (primary key)
 * @property name Display name of the partner
 * @property category Business category or type of partner
 * @property imageUrl URL to the partner's logo or image
 * @property description Description of the partner's services
 * @property discountPercentage Discount percentage offered to customers (optional)
 * @property websiteUrl Partner's website URL
 * @property isActive Whether the partner is currently active
 *
 * @see PartnerDao
 * @see fr.benchaabane.riyadhair.domain.partners.models.Partner
 * @see fr.benchaabane.riyadhair.data.partners.api.PartnerDto
 */
@Entity(tableName = "partners")
data class PartnerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val description: String,
    val discountPercentage: Int?,
    val websiteUrl: String,
    val isActive: Boolean
)
