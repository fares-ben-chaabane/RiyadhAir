package fr.benchaabane.riyadhair.domain.offers.models

/**
 * Represents a promotional offer for flights to specific destinations.
 *
 * This data class encapsulates all the information about a promotional offer,
 * including pricing details, destination information, validity period, and
 * terms. It serves as the core domain model for managing special offers
 * and promotional campaigns in the RiyadhAir system.
 *
 * **Offer Components:**
 * - **Identification**: Unique offer identifier
 * - **Destination**: Complete destination information
 * - **Pricing**: Original and discounted prices with discount percentage
 * - **Validity**: Time-limited offer availability
 * - **Details**: Description and terms of the offer
 *
 * **Use Cases:**
 * - Promotional campaign management
 * - Special pricing for destinations
 * - Limited-time offers and deals
 * - Marketing and customer engagement
 * - Revenue optimization strategies
 *
 * **Business Logic:**
 * - Discount percentage is calculated from original and discounted prices
 * - Offers have expiration dates for urgency
 * - Terms and conditions provide legal framework
 * - Destination details enable comprehensive offer presentation
 *
 * @param id Unique identifier for the offer
 * @param destination Complete destination information for the offer
 * @param originalPrice The standard price before discount
 * @param discountedPrice The special price during the offer period
 * @param discountPercentage The percentage discount applied (e.g., 20 for 20%)
 * @param validUntil Expiration date/time for the offer
 * @param description Marketing description of the offer
 * @param termsAndConditions Legal terms and conditions for the offer
 */
data class Offer(
    val id: String,
    val destination: Destination,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercentage: Int,
    val validUntil: String,
    val description: String,
    val termsAndConditions: String
)

/**
 * Represents a travel destination with comprehensive location information.
 *
 * This data class provides detailed information about a travel destination,
 * including geographical details, airport information, visual content, and
 * environmental factors. It serves as a rich data model for destination
 * presentation and offer management.
 *
 * **Destination Information:**
 * - **Geographical**: City, country, and airport details
 * - **Visual**: Image and description for presentation
 * - **Environmental**: Temperature and timezone information
 * - **Identification**: Unique destination identifier and codes
 *
 * **Use Cases:**
 * - Destination presentation and marketing
 * - Offer creation and management
 * - Flight search and booking
 * - Travel planning and information
 * - Customer engagement and inspiration
 *
 * **Data Features:**
 * - Comprehensive location information
 * - Visual content for rich presentation
 * - Optional environmental data for planning
 * - Standardized airport code for system integration
 *
 * @param id Unique identifier for the destination
 * @param name Display name of the destination
 * @param cityName Name of the city
 * @param countryName Name of the country
 * @param airportCode IATA airport code (e.g., "DXB" for Dubai)
 * @param imageUrl URL to destination image for visual presentation
 * @param description Marketing description of the destination
 * @param averageTemperature Average temperature information (optional)
 * @param timeZone Timezone information for travel planning (optional)
 */
data class Destination(
    val id: String,
    val name: String,
    val cityName: String,
    val countryName: String,
    val airportCode: String,
    val imageUrl: String,
    val description: String,
    val averageTemperature: String?,
    val timeZone: String?
)
