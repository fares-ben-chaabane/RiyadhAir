package fr.benchaabane.riyadhair.presentation.account

import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class AccountMappersTest {

    private lateinit var mockAccount: Account
    private lateinit var mockLoyaltyLevel: LoyaltyLevel

    @Before
    fun setUp() {
        // Given
        mockLoyaltyLevel = LoyaltyLevel(
            name = "Silver",
            tier = LoyaltyTier.SILVER,
            color = "#C0C0C0"
        )
        
        mockAccount = Account(
            id = "1",
            firstName = "John",
            lastName = "Doe",
            email = "john@example.com",
            phoneNumber = "+1234567890",
            loyaltyLevel = mockLoyaltyLevel,
            milesPoints = 5000,
            xpPoints = 150,
            profileImageUrl = null
        )
    }

    @Test
    fun `toUi should correctly map Account to AccountUiModel`() {
        // When
        val result = mockAccount.toUi()

        // Then
        result.accountName shouldBeEqualTo "John Doe"
        result.accountLoyaltyLevel shouldBeEqualTo mockLoyaltyLevel
        result.accountXpPoints shouldBeEqualTo 150
        result.accountMiles shouldBeEqualTo 5000
        result.phoneNumber shouldBeEqualTo "+1234567890"
    }

    @Test
    fun `toUi should handle null loyalty level`() {
        // Given
        val accountWithoutLoyalty = mockAccount.copy(loyaltyLevel = null)

        // When
        val result = accountWithoutLoyalty.toUi()

        // Then
        result.accountLoyaltyLevel shouldBeEqualTo null
        result.accountName shouldBeEqualTo "John Doe"
    }

    @Test
    fun `toUi should handle null phone number`() {
        // Given
        val accountWithoutPhone = mockAccount.copy(phoneNumber = null)

        // When
        val result = accountWithoutPhone.toUi()

        // Then
        result.phoneNumber shouldBeEqualTo ""
    }

    @Test
    fun `toUi should handle null profile image`() {
        // Given
        val accountWithoutImage = mockAccount.copy(profileImageUrl = null)

        // When
        val result = accountWithoutImage.toUi()

        // Then
        result.accountName shouldBeEqualTo "John Doe"
        // Profile image is not mapped in the current UI model
    }
}
