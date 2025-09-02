package fr.benchaabane.riyadhair.presentation.account

import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel

/**
 * UI model for displaying account information in the presentation layer.
 *
 * This data class represents the account information that is specifically
 * formatted and structured for UI presentation. It transforms domain models
 * into UI-friendly formats and provides default values for optional fields
 * to ensure consistent UI rendering.
 *
 * **UI Presentation Features:**
 * - **Account Identification**: User's display name and contact information
 * - **Loyalty Program**: Current loyalty level and accumulated points
 * - **Rewards System**: XP points and miles for gamification
 * - **Contact Information**: Phone number for account verification
 *
 * **Loyalty Program Integration:**
 * - **Loyalty Levels**: Bronze, Silver, Gold, Platinum, Diamond tiers
 * - **XP Points**: Experience points for level progression
 * - **Miles**: Airline miles for flight redemption
 * - **Gamification**: User engagement through rewards system
 *
 * **Data Transformation:**
 * - **Domain to UI**: Converts domain models to presentation format
 * - **Default Values**: Ensures UI consistency with fallback values
 * - **Optional Handling**: Gracefully handles missing loyalty information
 * - **UI Optimization**: Structured for efficient Compose rendering
 *
 * **Use Cases:**
 * - Account screen display
 * - Profile information presentation
 * - Loyalty program status
 * - User engagement tracking
 * - Contact information display
 *
 * @param accountName The display name for the user's account
 * @param accountLoyaltyLevel Current loyalty tier level (optional)
 * @param accountXpPoints Experience points accumulated in the loyalty program
 * @param accountMiles Airline miles available for redemption
 * @param phoneNumber Contact phone number for the account
 */
data class AccountUiModel(
    val accountName: String,
    val accountLoyaltyLevel: LoyaltyLevel?,
    val accountXpPoints: Int = 0,
    val accountMiles: Int = 0,
    val phoneNumber: String
)
