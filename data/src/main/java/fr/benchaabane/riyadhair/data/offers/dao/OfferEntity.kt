package fr.benchaabane.riyadhair.data.offers.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a travel offer in the local database.
 *
 * This entity stores travel offer information locally for offline access and
 * caching purposes. It represents a travel deal including destination details,
 * pricing information, promotional offers, and validity periods.
 *
 * **Database Structure:**
 * - **Table Name**: `offers`
 * - **Primary Key**: `id` (String)
 * - **Data Persistence**: Local storage for offline access
 * - **Cache Management**: Stores offer data for quick retrieval
 *
 * **Offer Information:**
 * - **Core Details**: ID, destination information, pricing
 * - **Promotional Data**: Discount percentages and validity periods
 * - **Destination Content**: Names, descriptions, images, environmental data
 * - **Business Rules**: Terms, conditions, and offer details
 *
 * **Data Usage:**
 * - **Offline Support**: Provides offer data without network
 * - **Quick Access**: Fast local database queries
 * - **Data Synchronization**: Local cache of remote offer data
 * - **User Experience**: Immediate offer information display
 *
 * **Room Integration:**
 * - **Entity Annotation**: Maps to `offers` table
 * - **Primary Key**: Unique identifier for each offer
 * - **Column Mapping**: Automatic field-to-column mapping
 * - **Query Support**: Enables efficient database operations
 *
 * **Business Context:**
 * - **Travel Deals**: Manages promotional travel offers
 * - **Destination Information**: Stores comprehensive location details
 * - **Pricing Management**: Tracks original and discounted prices
 * - **Offer Validity**: Manages time-limited promotional deals
 *
 * @property id Unique identifier for the travel offer (primary key)
 * @property destinationId Unique identifier for the destination
 * @property destinationName Display name of the destination
 * @property destinationCityName City name where the destination is located
 * @property destinationCountryName Country name where the destination is located
 * @property destinationAirportCode IATA airport code for the destination
 * @property destinationImageUrl URL to destination image for visual representation
 * @property destinationDescription Description of the destination and its attractions
 * @property destinationAverageTemperature Average temperature at the destination (optional)
 * @property destinationTimeZone Timezone of the destination (optional)
 * @property originalPrice Original price before discount
 * @property discountedPrice Discounted price after promotional reduction
 * @property discountPercentage Percentage discount offered
 * @property validUntil Offer validity end date
 * @property description Description of the travel offer
 * @property termsAndConditions Terms and conditions for the offer
 *
 * @see OfferDao
 * @see fr.benchaabane.riyadhair.domain.offers.models.Offer
 * @see fr.benchaabane.riyadhair.data.offers.api.OfferDto
 */
@Entity(tableName = "offers")
data class OfferEntity(
    @PrimaryKey val id: String,
    val destinationId: String,
    val destinationName: String,
    val destinationCityName: String,
    val destinationCountryName: String,
    val destinationAirportCode: String,
    val destinationImageUrl: String,
    val destinationDescription: String,
    val destinationAverageTemperature: String?,
    val destinationTimeZone: String?,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercentage: Int,
    val validUntil: String,
    val description: String,
    val termsAndConditions: String
)
