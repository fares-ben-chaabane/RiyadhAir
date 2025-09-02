package fr.benchaabane.riyadhair.data.offers.api

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for travel offer information from the API.
 *
 * This DTO represents a travel offer received from the RiyadhAir backend,
 * including destination details, pricing information, promotional offers,
 * and validity periods. It serves as the data structure for API communication
 * when retrieving travel deals and special offers.
 *
 * **Offer Components:**
 * - **Destination Information**: Location details and descriptions
 * - **Pricing Details**: Original and discounted prices
 * - **Promotional Information**: Discount percentages and validity
 * - **Travel Details**: Descriptions and terms
 *
 * **Data Structure:**
 * - **Core Fields**: ID, destination, pricing, discount, validity
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Data Validation**: Ensures required fields are present
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete offer data.
 *
 * @property id Unique identifier for the travel offer
 * @property destination Destination information for this offer
 * @property originalPrice Original price before discount
 * @property discountedPrice Discounted price after promotional reduction
 * @property discountPercentage Percentage discount offered
 * @property validUntil Offer validity end date
 * @property description Description of the travel offer
 * @property termsAndConditions Terms and conditions for the offer
 *
 * @see DestinationDto
 * @see OffersResponse
 * @see fr.benchaabane.riyadhair.domain.offers.models.Offer
 */
@Serializable
data class OfferDto(
    val id: String,
    val destination: DestinationDto,
    val originalPrice: Double?,
    val discountedPrice: Double?,
    val discountPercentage: Int?,
    val validUntil: String?,
    val description: String?,
    val termsAndConditions: String?
)

/**
 * Data Transfer Object for destination information from the API.
 *
 * This DTO represents destination details including location information,
 * descriptions, and visual assets. It's used within offer DTOs to
 * provide comprehensive destination information for travel offers.
 *
 * **Destination Information:**
 * - **Location Details**: City, country, and airport information
 * - **Descriptive Content**: Descriptions and cultural information
 * - **Visual Assets**: Image URLs for destination representation
 * - **Environmental Data**: Temperature and timezone information
 *
 * **Data Structure:**
 * - **Core Fields**: ID, names, descriptions, images, environmental data
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Visual Support**: Provides image URLs for UI display
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete destination data.
 *
 * @property id Unique identifier for the destination
 * @property name Display name of the destination
 * @property cityName City name where the destination is located
 * @property countryName Country name where the destination is located
 * @property airportCode IATA airport code for the destination
 * @property imageUrl URL to destination image for visual representation
 * @property description Description of the destination and its attractions
 * @property averageTemperature Average temperature at the destination
 * @property timeZone Timezone of the destination
 *
 * @see fr.benchaabane.riyadhair.domain.offers.models.Destination
 */
@Serializable
data class DestinationDto(
    val id: String,
    val name: String?,
    val cityName: String?,
    val countryName: String?,
    val airportCode: String?,
    val imageUrl: String?,
    val description: String?,
    val averageTemperature: String,
    val timeZone: String
)

/**
 * Data Transfer Object for offers search response from the API.
 *
 * This DTO wraps a list of travel offers returned from the offers API endpoint.
 * It provides a structured response format for bulk offer data retrieval,
 * typically used when fetching best offers or searching for travel deals.
 *
 * **Response Structure:**
 * - **Offers List**: Collection of OfferDto objects
 * - **Bulk Data**: Efficient retrieval of multiple offers
 * - **API Consistency**: Standard response format
 * - **Data Organization**: Structured collection for easy processing
 *
 * **Usage Context:**
 * - **API Responses**: Wrapping offers search results
 * - **Data Processing**: Bulk offer data handling
 * - **Repository Layer**: Converting API responses to domain models
 * - **Cache Management**: Storing multiple offers efficiently
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * Designed to handle API response structures consistently.
 *
 * @property offers List of available travel offers matching search criteria
 *
 * @see OfferDto
 * @see fr.benchaabane.riyadhair.domain.offers.models.Offer
 */
@Serializable
data class OffersResponse(
    val offers: List<OfferDto>? = emptyList()
)
