package fr.benchaabane.riyadhair.presentation.offers

import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class OffersMappersTest {

    private lateinit var mockOffer: Offer
    private lateinit var mockDestination: Destination

    @Before
    fun setUp() {
        // Given
        mockDestination = Destination(
            id = "dest1",
            name = "Paris",
            cityName = "Paris",
            countryName = "France",
            airportCode = "CDG",
            imageUrl = "https://example.com/paris.jpg",
            description = "City of Light",
            averageTemperature = "20°C",
            timeZone = "CET"
        )
        
        mockOffer = Offer(
            id = "1",
            destination = mockDestination,
            originalPrice = 500.0,
            discountedPrice = 350.0,
            discountPercentage = 30,
            validUntil = "2024-12-31",
            description = "Beautiful city",
            termsAndConditions = "Valid until end of year"
        )
    }

    @Test
    fun `toUi should correctly map Offer to OfferUiModel`() {
        // When
        val result = mockOffer.toUi()

        // Then
        result.coverImage shouldBeEqualTo "https://example.com/paris.jpg"
        result.destination shouldBeEqualTo "Paris"
        result.country shouldBeEqualTo "France"
        result.priceInfo shouldBeEqualTo "From 350 EUR"
        result.discountInfo shouldBeEqualTo "-30%"
    }

    @Test
    fun `toUi should handle null discounted price`() {
        // Given
        val offerWithoutDiscount = mockOffer.copy(discountedPrice = 0.0, discountPercentage = 0)

        // When
        val result = offerWithoutDiscount.toUi()

        // Then
        result.priceInfo shouldBeEqualTo "From 0 EUR"
        result.discountInfo shouldBeEqualTo "-0%"
    }

    @Test
    fun `toUi should handle null original price`() {
        // Given
        val offerWithoutOriginalPrice = mockOffer.copy(originalPrice = 0.0)

        // When
        val result = offerWithoutOriginalPrice.toUi()

        // Then
        result.priceInfo shouldBeEqualTo "From 350 EUR"
        result.discountInfo shouldBeEqualTo "-30%"
    }

    @Test
    fun `toUi should handle null discount percentage`() {
        // Given
        val offerWithoutDiscountPercentage = mockOffer.copy(discountPercentage = 0)

        // When
        val result = offerWithoutDiscountPercentage.toUi()

        // Then
        result.discountInfo shouldBeEqualTo "-0%"
    }

    @Test
    fun `toUi should handle null destination image`() {
        // Given
        val destinationWithoutImage = mockDestination.copy(imageUrl = "")
        val offerWithoutImage = mockOffer.copy(destination = destinationWithoutImage)

        // When
        val result = offerWithoutImage.toUi()

        // Then
        result.coverImage shouldBeEqualTo ""
    }
}
