package fr.benchaabane.riyadhair.data.offers.repositories

import fr.benchaabane.riyadhair.data.offers.api.DestinationDto
import fr.benchaabane.riyadhair.data.offers.api.OffersResponse
import fr.benchaabane.riyadhair.data.offers.api.OffersService
import fr.benchaabane.riyadhair.data.offers.api.OfferDto
import fr.benchaabane.riyadhair.data.offers.dao.OfferDao
import fr.benchaabane.riyadhair.data.offers.dao.OfferEntity
import fr.benchaabane.riyadhair.data.offers.mappers.toDomain
import fr.benchaabane.riyadhair.domain.offers.models.Offer
import io.mockk.coEvery
import io.mockk.coVerify
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
class OffersRepositoryImplTest {

    private lateinit var repository: OffersRepositoryImpl
    private lateinit var mockOfferDao: OfferDao
    private lateinit var mockOffersService: OffersService
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockOfferDao = mockk(relaxed = true)
        mockOffersService = mockk(relaxed = true)
        repository = OffersRepositoryImpl(mockOfferDao, mockOffersService)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getBestOffers should return offers from API when available`() = runTest {
        // Given
        val apiOffers = listOf(
            OfferDto(
                id = "1",
                destination = DestinationDto(
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
        coEvery { mockOffersService.getBestOffers() } returns OffersResponse(apiOffers)
        coEvery { mockOfferDao.clearAll() } returns Unit
        coEvery { mockOfferDao.upsertAll(any()) } returns Unit

        // When
        val result = repository.getBestOffers()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull()?.size shouldBeEqualTo 1
        result.getOrNull()?.first()?.id shouldBeEqualTo "1"
        coVerify { mockOfferDao.clearAll() }
        coVerify { mockOfferDao.upsertAll(any()) }
    }

    @Test
    fun `getBestOffers should return offers from database when API returns empty`() = runTest {
        // Given
        val dbOffers = listOf(
            OfferEntity(
                id = "2",
                destinationId = "dest2",
                destinationName = "London",
                destinationCityName = "London",
                destinationCountryName = "UK",
                destinationAirportCode = "LHR",
                destinationImageUrl = "https://example.com/london.jpg",
                destinationDescription = "Big Ben",
                destinationAverageTemperature = "15°C",
                destinationTimeZone = "GMT",
                originalPrice = 600.0,
                discountedPrice = 400.0,
                discountPercentage = 33,
                validUntil = "2024-12-31",
                description = "Historic city",
                termsAndConditions = "Valid until end of year"
            )
        )
        coEvery { mockOffersService.getBestOffers() } returns OffersResponse(emptyList())
        coEvery { mockOfferDao.getOffers() } returns dbOffers

        // When
        val result = repository.getBestOffers()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull()?.size shouldBeEqualTo 1
        result.getOrNull()?.first()?.id shouldBeEqualTo "2"
        coVerify(exactly = 0) { mockOfferDao.clearAll() }
        coVerify(exactly = 0) { mockOfferDao.upsertAll(any()) }
    }

    @Test
    fun `getBestOffers should return empty list when both API and database fail`() = runTest {
        // Given
        coEvery { mockOffersService.getBestOffers() } throws Exception("Network error")
        coEvery { mockOfferDao.getOffers() } throws Exception("Database error")

        // When
        val result = repository.getBestOffers()

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull()?.isEmpty() shouldBeEqualTo true
    }

}
