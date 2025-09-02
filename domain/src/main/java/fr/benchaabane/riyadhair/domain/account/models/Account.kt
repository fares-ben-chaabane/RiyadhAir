package fr.benchaabane.riyadhair.domain.account.models

data class Account(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?,
    val loyaltyLevel: LoyaltyLevel?,
    val milesPoints: Int,
    val xpPoints: Int,
    val profileImageUrl: String?,
    val preferredLanguage: String = "en",
    val currentLocation: String? = null
)

data class LoyaltyLevel(
    val name: String,
    val tier: LoyaltyTier,
    val color: String,
)

enum class LoyaltyTier {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND
}
