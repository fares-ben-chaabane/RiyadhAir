package fr.benchaabane.riyadhair.data.partners.api

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for partner information from the API.
 *
 * This DTO represents a business partner or affiliate that provides
 * services or products related to travel. Partners can offer discounts,
 * special deals, or additional services to RiyadhAir customers.
 *
 * **Partner Types:**
 * - **Travel Services**: Hotels, car rentals, travel insurance
 * - **Lifestyle**: Restaurants, entertainment, shopping
 * - **Business**: Corporate services, meeting facilities
 * - **Local Partners**: Destination-specific services and attractions
 *
 * **Data Structure:**
 * - **Core Information**: ID, name, category, description
 * - **Visual Elements**: Image URL for partner branding
 * - **Business Details**: Discount offers, website, active status
 * - **Flexibility**: Most fields are nullable for API compatibility
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are designed to handle partial data gracefully.
 *
 * @property id Unique identifier for the partner
 * @property name Display name of the partner (optional)
 * @property category Business category or type of partner (optional)
 * @property imageUrl URL to the partner's logo or image (optional)
 * @property description Description of the partner's services (optional)
 * @property discountPercentage Discount percentage offered to customers (optional, defaults to null)
 * @property websiteUrl Partner's website URL (optional)
 * @property isActive Whether the partner is currently active (optional, defaults to true)
 *
 * @see PartnersResponse
 * @see fr.benchaabane.riyadhair.domain.partners.models.Partner
 */
@Serializable
data class PartnerDto(
    val id: String,
    val name: String?,
    val category: String?,
    val imageUrl: String?,
    val description: String?,
    val discountPercentage: Int? = null,
    val websiteUrl: String?,
    val isActive: Boolean? = true
)

/**
 * Data Transfer Object for partners search response from the API.
 *
 * This DTO wraps a list of partners returned from the partners API endpoint.
 * It provides a structured response format for bulk partner data retrieval.
 *
 * **Response Structure:**
 * - **Partners List**: Collection of PartnerDto objects
 * - **Bulk Data**: Efficient retrieval of multiple partners
 * - **API Consistency**: Standard response format
 * - **Data Organization**: Structured collection for easy processing
 *
 * **Usage Context:**
 * - **API Responses**: Wrapping partner search results
 * - **Data Processing**: Bulk partner data handling
 * - **Repository Layer**: Converting API responses to domain models
 * - **Cache Management**: Storing multiple partners efficiently
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * Designed to handle API response structures consistently.
 *
 * @property partners List of available partners returned from the API
 *
 * @see PartnerDto
 * @see fr.benchaabane.riyadhair.domain.partners.models.Partner
 */
@Serializable
data class PartnersResponse(
    val partners: List<PartnerDto>
)
