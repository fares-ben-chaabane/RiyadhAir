package fr.benchaabane.riyadhair.presentation.home

import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase
import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import fr.benchaabane.riyadhair.domain.offers.usecases.GetBestOffersUseCase
import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.models.PartnerCategory
import fr.benchaabane.riyadhair.domain.partners.usecases.GetPartnersUseCase
import fr.benchaabane.riyadhair.presentation.account.toUi
import fr.benchaabane.riyadhair.presentation.offers.toUi
import fr.benchaabane.riyadhair.presentation.partners.toUi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var mockGetAccountUseCase: GetAccountUseCase
    private lateinit var mockGetBestOffersUseCase: GetBestOffersUseCase
    private lateinit var mockGetPartnersUseCase: GetPartnersUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockGetAccountUseCase = mockk()
        mockGetBestOffersUseCase = mockk()
        mockGetPartnersUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `HomeViewModel should initialize with empty state`() = runTest {
        // Given
        coEvery { mockGetAccountUseCase.invoke() } returns Result.success(null)
        coEvery { mockGetBestOffersUseCase.invoke() } returns Result.success(emptyList())
        coEvery { mockGetPartnersUseCase.invoke() } returns Result.success(emptyList())

        // When
        viewModel = HomeViewModel(
            mockGetAccountUseCase,
            mockGetBestOffersUseCase,
            mockGetPartnersUseCase,
            testDispatcher
        )
        advanceUntilIdle()

        // Then
        val initialState = viewModel.uiState.value
        initialState.account shouldBeEqualTo null
        initialState.bestOffers shouldBeEqualTo emptyList()
        initialState.partners shouldBeEqualTo emptyList()
    }

    @Test
    fun `HomeViewModel should load account successfully`() = runTest {
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
        coEvery { mockGetBestOffersUseCase.invoke() } returns Result.success(emptyList())
        coEvery { mockGetPartnersUseCase.invoke() } returns Result.success(emptyList())

        // When
        viewModel = HomeViewModel(
            mockGetAccountUseCase,
            mockGetBestOffersUseCase,
            mockGetPartnersUseCase,
            testDispatcher
        )
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.account shouldBeEqualTo mockAccount.toUi()
        state.account?.accountName shouldBeEqualTo "John Doe"
        state.account?.accountLoyaltyLevel?.tier shouldBeEqualTo LoyaltyTier.SILVER
    }

    @Test
    @Ignore("To be implemented")
    fun `HomeViewModel should load best offers successfully`() = runTest {
        // Given
        val mockOffers = listOf(
            Offer(
                id = "1",
                destination = Destination(
                    id = "dest1",
                    name = "Paris",
                    cityName = "Paris",
                    countryName = "France",
                    airportCode = "CDG",
                    imageUrl = "https://example.com/paris.jpg",
                    description = "City of Light",
                    averageTemperature = "20°C",
                    timeZone = "CET"
                ),
                originalPrice = 500.0,
                discountedPrice = 350.0,
                discountPercentage = 30,
                validUntil = "2024-12-31",
                description = "Beautiful city",
                termsAndConditions = "Valid until end of year"
            )
        )
        coEvery { mockGetAccountUseCase.invoke() } returns Result.success(null)
        coEvery { mockGetBestOffersUseCase.invoke() } returns Result.success(mockOffers)
        coEvery { mockGetPartnersUseCase.invoke() } returns Result.success(emptyList())

        // When
        viewModel = HomeViewModel(
            mockGetAccountUseCase,
            mockGetBestOffersUseCase,
            mockGetPartnersUseCase,
            testDispatcher
        )
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.bestOffers shouldBeEqualTo mockOffers.map { it.toUi() }
        state.bestOffers.size shouldBeEqualTo 1
        state.bestOffers.first().destination shouldBeEqualTo "Paris"
    }

    @Test
    fun `HomeViewModel should load partners successfully`() = runTest {
        // Given
        val mockPartners = listOf(
            Partner(
                id = "1",
                name = "Car Rental",
                category = PartnerCategory.CAR_RENTAL,
                imageUrl = "https://example.com/car.jpg",
                description = "Best car rental service",
                discountPercentage = 15,
                websiteUrl = "https://carrental.com"
            )
        )
        coEvery { mockGetAccountUseCase.invoke() } returns Result.success(null)
        coEvery { mockGetBestOffersUseCase.invoke() } returns Result.success(emptyList())
        coEvery { mockGetPartnersUseCase.invoke() } returns Result.success(mockPartners)

        // When
        viewModel = HomeViewModel(
            mockGetAccountUseCase,
            mockGetBestOffersUseCase,
            mockGetPartnersUseCase,
            testDispatcher
        )
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.partners shouldBeEqualTo mockPartners.map { it.toUi() }
        state.partners.size shouldBeEqualTo 1
        state.partners.first().name shouldBeEqualTo "Car Rental"
    }

    @Test
    fun `HomeViewModel should handle account failure gracefully`() = runTest {
        // Given
        coEvery { mockGetAccountUseCase.invoke() } returns Result.failure(Exception("Network error"))
        coEvery { mockGetBestOffersUseCase.invoke() } returns Result.success(emptyList())
        coEvery { mockGetPartnersUseCase.invoke() } returns Result.success(emptyList())

        // When
        viewModel = HomeViewModel(
            mockGetAccountUseCase,
            mockGetBestOffersUseCase,
            mockGetPartnersUseCase,
            testDispatcher
        )
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        state.account shouldBeEqualTo null
        // Should not crash and continue loading other data
    }
}
