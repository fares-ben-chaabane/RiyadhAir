package fr.benchaabane.riyadhair.domain.offers.usecases

import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import fr.benchaabane.riyadhair.domain.offers.repositories.OffersRepository
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
class GetBestOffersUseCaseTest {

    private lateinit var useCase: GetBestOffersUseCase
    private lateinit var mockRepository: OffersRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockRepository = mockk()
        useCase = GetBestOffersUseCase(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return success with offers when repository succeeds`() = runTest {
        // Given
        val expectedOffers = listOf(
            Offer(
                id = "1",
                destination = Destination(
                    id = "dest1",
                    name = "Paris",
                    cityName = "Paris",
                    countryName = "France",
                    airportCode = "CDG",
                    imageUrl = "https://example.com/paris.jpg",
                    description = "Beautiful city",
                    averageTemperature = "20°C",
                    timeZone = "CET"
                ),
                originalPrice = 500.0,
                discountedPrice = 350.0,
                discountPercentage = 30,
                validUntil = "2024-12-31",
                description = "Beautiful city",
                termsAndConditions = "Valid until end of year"
            ),
            Offer(
                id = "2",
                destination = Destination(
                    id = "dest2",
                    name = "Tokyo",
                    cityName = "Tokyo",
                    countryName = "Japan",
                    airportCode = "NRT",
                    imageUrl = "https://example.com/tokyo.jpg",
                    description = "Amazing city",
                    averageTemperature = "18°C",
                    timeZone = "JST"
                ),
                originalPrice = 800.0,
                discountedPrice = 600.0,
                discountPercentage = 25,
                validUntil = "2024-12-31",
                description = "Amazing city",
                termsAndConditions = "Valid until end of year"
            )
        )
        coEvery { mockRepository.getBestOffers() } returns Result.success(expectedOffers)

        // When
        val result = useCase()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull() shouldBeEqualTo expectedOffers
        result.getOrNull()?.size shouldBeEqualTo 2
    }

    @Test
    fun `invoke should return failure when repository fails`() = runTest {
        // Given
        val expectedError = Exception("Network error")
        coEvery { mockRepository.getBestOffers() } returns Result.failure(expectedError)

        // When
        val result = useCase()

        // Then
        result.isFailure shouldBeEqualTo true
        result.exceptionOrNull() shouldBeEqualTo expectedError
        result.exceptionOrNull() shouldBeInstanceOf Exception::class
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        coEvery { mockRepository.getBestOffers() } returns Result.success(emptyList())

        // When
        val result = useCase()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull()?.isEmpty() shouldBeEqualTo true
        result.getOrNull()?.size shouldBeEqualTo 0
    }
}
