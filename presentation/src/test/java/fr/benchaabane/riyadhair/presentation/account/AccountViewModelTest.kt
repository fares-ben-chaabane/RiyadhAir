package fr.benchaabane.riyadhair.presentation.account

import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModelTest {

    private lateinit var viewModel: AccountViewModel
    private lateinit var mockGetAccountUseCase: GetAccountUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockGetAccountUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `AccountViewModel should initialize with empty state`() = runTest {
        // Given
        coEvery { mockGetAccountUseCase.invoke() } returns Result.success(null)

        // When
        viewModel = AccountViewModel(mockGetAccountUseCase, testDispatcher)
        advanceUntilIdle()

        // Then
        val initialState = viewModel.uiState.value
        initialState.account shouldBeEqualTo null
    }

    @Test
    fun `AccountViewModel should load account successfully`() = runTest {
        // Given
        val mockAccount = Account(
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
        coEvery { mockGetAccountUseCase.invoke() } returns Result.success(mockAccount)

        // When
        viewModel = AccountViewModel(mockGetAccountUseCase, testDispatcher)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.account shouldBeEqualTo mockAccount.toUi()
        state.account?.accountName shouldBeEqualTo "John Doe"
        state.account?.accountLoyaltyLevel?.tier shouldBeEqualTo LoyaltyTier.SILVER
    }

    @Test
    fun `AccountViewModel should handle account failure gracefully`() = runTest {
        // Given
        coEvery { mockGetAccountUseCase.invoke() } returns Result.failure(Exception("Network error"))

        // When
        viewModel = AccountViewModel(mockGetAccountUseCase, testDispatcher)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.account shouldBeEqualTo null
    }
}
