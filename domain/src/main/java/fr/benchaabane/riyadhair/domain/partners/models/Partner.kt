package fr.benchaabane.riyadhair.domain.partners.models

/**
 * Represents a business partner in the RiyadhAir ecosystem.
 *
 * This data class encapsulates all the information about a business partner,
 * including their services, benefits, and partnership details. Partners
 * provide additional services to RiyadhAir customers, enhancing the overall
 * travel experience through discounts and specialized offerings.
 *
 * **Partner Information:**
 * - **Identification**: Unique partner identifier and name
 * - **Category**: Type of service or business category
 * - **Visual**: Image and description for presentation
 * - **Benefits**: Discount information and website access
 * - **Status**: Active/inactive partnership status
 *
 * **Use Cases:**
 * - Partner service presentation
 * - Customer benefit management
 * - Partnership program administration
 * - Service category organization
 * - Customer engagement and offers
 *
 * **Business Logic:**
 * - Partners can offer optional discounts
 * - Active status controls partner visibility
 * - Website URLs enable direct customer access
 * - Categories organize partners by service type
 *
 * @param id Unique identifier for the partner
 * @param name Display name of the partner business
 * @param category Business category or service type
 * @param imageUrl URL to partner logo or image
 * @param description Marketing description of partner services
 * @param discountPercentage Optional discount percentage for RiyadhAir customers
 * @param websiteUrl Direct link to partner's website
 * @param isActive Whether the partnership is currently active
 */
data class Partner(
    val id: String,
    val name: String,
    val category: PartnerCategory,
    val imageUrl: String,
    val description: String,
    val discountPercentage: Int?,
    val websiteUrl: String,
    val isActive: Boolean = true
)

/**
 * Defines the categories of business partners in the RiyadhAir ecosystem.
 *
 * This enum represents the different types of services and businesses that
 * can partner with RiyadhAir to provide additional value to customers.
 * Each category has a display name for user interface presentation.
 *
 * **Partner Categories:**
 * - **CAR_RENTAL**: Vehicle rental services at destinations
 * - **INTERNET**: Internet connectivity and mobile services
 * - **PARKING**: Airport parking and transportation services
 * - **HOTEL**: Accommodation and lodging services
 * - **INSURANCE**: Travel insurance and protection services
 * - **SHOPPING**: Retail and shopping opportunities
 * - **RESTAURANT**: Dining and culinary experiences
 * - **TRANSPORT**: Local transportation and mobility services
 *
 * **Use Cases:**
 * - Partner categorization and organization
 * - Service type filtering and search
 * - Customer benefit presentation
 * - Partnership program management
 * - Service discovery and recommendations
 *
 * **Display Names:**
 * - French language display names for user interface
 * - Consistent with RiyadhAir's multilingual approach
 * - Clear category identification for customers
 *
 * @param displayName The user-friendly name for the category in French
 */
enum class PartnerCategory(val displayName: String) {
    CAR_RENTAL("Location de voiture"),
    INTERNET("Connexion internet"),
    PARKING("Parking aéroport"),
    HOTEL("Hôtels"),
    INSURANCE("Assurance voyage"),
    SHOPPING("Shopping"),
    RESTAURANT("Restaurants"),
    TRANSPORT("Transport")
}
