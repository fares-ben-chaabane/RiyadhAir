package fr.benchaabane.riyadhair.domain.account.usecases

import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.domain.account.repositories.AccountRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAccountUseCaseTest {

    private lateinit var useCase: GetAccountUseCase
    private lateinit var mockRepository: AccountRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockRepository = mockk()
        useCase = GetAccountUseCase(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return success with account when repository succeeds`() = runTest {
        // Given
        val expectedAccount = Account(
            id = "1",
            firstName = "John",
            lastName = "Doe",
            email = "john@example.com",
            phoneNumber = "+1234567890",
            loyaltyLevel = LoyaltyLevel(
                name = "Silver",
                tier = LoyaltyTier.SILVER,
                color = "#C0C0C0"
            ),
            milesPoints = 5000,
            xpPoints = 150,
            profileImageUrl = null
        )
        coEvery { mockRepository.getAccount() } returns Result.success(expectedAccount)

        // When
        val result = useCase()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull() shouldBeEqualTo expectedAccount
        result.getOrNull()?.firstName shouldBeEqualTo "John"
        result.getOrNull()?.lastName shouldBeEqualTo "Doe"
        result.getOrNull()?.loyaltyLevel?.tier shouldBeEqualTo LoyaltyTier.SILVER
    }

    @Test
    fun `invoke should return success with null when repository returns null`() = runTest {
        // Given
        coEvery { mockRepository.getAccount() } returns Result.success(null)

        // When
        val result = useCase()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull() shouldBeEqualTo null
    }

    @Test
    fun `invoke should return failure when repository fails`() = runTest {
        // Given
        val expectedError = Exception("Database error")
        coEvery { mockRepository.getAccount() } returns Result.failure(expectedError)

        // When
        val result = useCase()

        // Then
        result.isFailure shouldBeEqualTo true
        result.exceptionOrNull() shouldBeEqualTo expectedError
        result.exceptionOrNull() shouldBeInstanceOf Exception::class
    }
}
