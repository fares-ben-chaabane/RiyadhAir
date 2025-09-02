package fr.benchaabane.riyadhair.data.account.api

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for account information from the API.
 *
 * This DTO represents a user account profile received from the RiyadhAir backend,
 * including personal information, loyalty program details, and user preferences.
 * It serves as the data structure for API communication when managing user accounts.
 *
 * **Account Components:**
 * - **Personal Information**: Name, email, phone number
 * - **Loyalty Program**: Miles, XP points, loyalty level details
 * - **User Preferences**: Language, location, profile image
 * - **Account Status**: Active account information
 *
 * **Data Structure:**
 * - **Core Fields**: ID, names, email, phone, loyalty details
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Data Validation**: Ensures required fields are present
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete account data.
 *
 * @property id Unique identifier for the user account
 * @property firstName User's first name (optional)
 * @property lastName User's last name (optional)
 * @property email User's email address (optional)
 * @property phoneNumber User's phone number (optional)
 * @property loyaltyLevel User's loyalty level information
 * @property milesPoints User's accumulated miles points
 * @property xpPoints User's accumulated XP points
 * @property profileImageUrl URL to user's profile image (optional)
 * @property preferredLanguage User's preferred language (optional)
 * @property currentLocation User's current location (optional)
 *
 * @see LoyaltyLevelDto
 * @see fr.benchaabane.riyadhair.domain.account.models.Account
 */
@Serializable
data class AccountDto(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phoneNumber: String?,
    val loyaltyLevel: LoyaltyLevelDto,
    val milesPoints: Int,
    val xpPoints: Int,
    val profileImageUrl: String?,
    val preferredLanguage: String?,
    val currentLocation: String?
)

/**
 * Data Transfer Object for loyalty level information from the API.
 *
 * This DTO represents the user's current loyalty tier and associated benefits
 * within the RiyadhAir loyalty program. It includes visual and functional
 * information about the loyalty level.
 *
 * **Loyalty Features:**
 * - **Tier Information**: Current loyalty tier level
 * - **Visual Elements**: Color coding for UI representation
 * - **Display Names**: Human-readable loyalty level names
 * - **Program Integration**: Links to loyalty program benefits
 *
 * **Data Structure:**
 * - **Core Fields**: Name, tier, color
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **UI Support**: Provides color information for visual representation
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete loyalty level data.
 *
 * @property name Display name of the loyalty level
 * @property tier Loyalty tier identifier
 * @property color Color code for visual representation
 *
 * @see fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
 */
@Serializable
data class LoyaltyLevelDto(
    val name: String,
    val tier: String,
    val color: String,
)
