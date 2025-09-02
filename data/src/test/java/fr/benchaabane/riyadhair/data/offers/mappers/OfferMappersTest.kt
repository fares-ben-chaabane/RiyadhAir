package fr.benchaabane.riyadhair.data.offers.mappers

import fr.benchaabane.riyadhair.data.offers.api.DestinationDto
import fr.benchaabane.riyadhair.data.offers.api.OfferDto
import fr.benchaabane.riyadhair.data.offers.dao.OfferEntity
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import fr.benchaabane.riyadhair.domain.offers.models.Offer
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class OfferMappersTest {

    private lateinit var offerEntity: OfferEntity
    private lateinit var offer: Offer
    private lateinit var offerDto: OfferDto
    private lateinit var destinationDto: DestinationDto

    @Before
    fun setUp() {
        // Given
        offerEntity = OfferEntity(
            id = "1",
            destinationId = "dest1",
            destinationName = "Paris",
            destinationCityName = "Paris",
            destinationCountryName = "France",
            destinationAirportCode = "CDG",
            destinationImageUrl = "https://example.com/paris.jpg",
            destinationDescription = "City of Light",
            destinationAverageTemperature = "20°C",
            destinationTimeZone = "CET",
            originalPrice = 500.0,
            discountedPrice = 350.0,
            discountPercentage = 30,
            validUntil = "2024-12-31",
            description = "Beautiful city",
            termsAndConditions = "Valid until end of year"
        )

        offer = Offer(
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

        destinationDto = DestinationDto(
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

        offerDto = OfferDto(
            id = "1",
            destination = destinationDto,
            originalPrice = 500.0,
            discountedPrice = 350.0,
            discountPercentage = 30,
            validUntil = "2024-12-31",
            description = "Beautiful city",
            termsAndConditions = "Valid until end of year"
        )
    }

    @Test
    fun `toDomain should correctly map OfferEntity to Offer`() {
        // When
        val result = offerEntity.toDomain()

        // Then
        result.id shouldBeEqualTo "1"
        result.destination.id shouldBeEqualTo "dest1"
        result.destination.name shouldBeEqualTo "Paris"
        result.destination.cityName shouldBeEqualTo "Paris"
        result.destination.countryName shouldBeEqualTo "France"
        result.destination.airportCode shouldBeEqualTo "CDG"
        result.destination.imageUrl shouldBeEqualTo "https://example.com/paris.jpg"
        result.destination.description shouldBeEqualTo "City of Light"
        result.destination.averageTemperature shouldBeEqualTo "20°C"
        result.destination.timeZone shouldBeEqualTo "CET"
        result.originalPrice shouldBeEqualTo 500.0
        result.discountedPrice shouldBeEqualTo 350.0
        result.discountPercentage shouldBeEqualTo 30
        result.validUntil shouldBeEqualTo "2024-12-31"
        result.description shouldBeEqualTo "Beautiful city"
        result.termsAndConditions shouldBeEqualTo "Valid until end of year"
    }

    @Test
    fun `toEntity should correctly map Offer to OfferEntity`() {
        // When
        val result = offer.toEntity()

        // Then
        result.id shouldBeEqualTo "1"
        result.destinationId shouldBeEqualTo "dest1"
        result.destinationName shouldBeEqualTo "Paris"
        result.destinationCityName shouldBeEqualTo "Paris"
        result.destinationCountryName shouldBeEqualTo "France"
        result.destinationAirportCode shouldBeEqualTo "CDG"
        result.destinationImageUrl shouldBeEqualTo "https://example.com/paris.jpg"
        result.destinationDescription shouldBeEqualTo "City of Light"
        result.destinationAverageTemperature shouldBeEqualTo "20°C"
        result.destinationTimeZone shouldBeEqualTo "CET"
        result.originalPrice shouldBeEqualTo 500.0
        result.discountedPrice shouldBeEqualTo 350.0
        result.discountPercentage shouldBeEqualTo 30
        result.validUntil shouldBeEqualTo "2024-12-31"
        result.description shouldBeEqualTo "Beautiful city"
        result.termsAndConditions shouldBeEqualTo "Valid until end of year"
    }

    @Test
    fun `toDomain should correctly map OfferDto to Offer`() {
        // When
        val result = offerDto.toDomain()

        // Then
        result.id shouldBeEqualTo "1"
        result.destination.id shouldBeEqualTo "dest1"
        result.destination.name shouldBeEqualTo "Paris"
        result.destination.cityName shouldBeEqualTo "Paris"
        result.destination.countryName shouldBeEqualTo "France"
        result.destination.airportCode shouldBeEqualTo "CDG"
        result.destination.imageUrl shouldBeEqualTo "https://example.com/paris.jpg"
        result.destination.description shouldBeEqualTo "City of Light"
        result.destination.averageTemperature shouldBeEqualTo "20°C"
        result.destination.timeZone shouldBeEqualTo "CET"
        result.originalPrice shouldBeEqualTo 500.0
        result.discountedPrice shouldBeEqualTo 350.0
        result.discountPercentage shouldBeEqualTo 30
        result.validUntil shouldBeEqualTo "2024-12-31"
        result.description shouldBeEqualTo "Beautiful city"
        result.termsAndConditions shouldBeEqualTo "Valid until end of year"
    }

    @Test
    fun `toEntity should correctly map OfferDto to OfferEntity`() {
        // When
        val result = offerDto.toEntity()

        // Then
        result.id shouldBeEqualTo "1"
        result.destinationId shouldBeEqualTo "dest1"
        result.destinationName shouldBeEqualTo "Paris"
        result.destinationCityName shouldBeEqualTo "Paris"
        result.destinationCountryName shouldBeEqualTo "France"
        result.destinationAirportCode shouldBeEqualTo "CDG"
        result.destinationImageUrl shouldBeEqualTo "https://example.com/paris.jpg"
        result.destinationDescription shouldBeEqualTo "City of Light"
        result.destinationAverageTemperature shouldBeEqualTo "20°C"
        result.destinationTimeZone shouldBeEqualTo "CET"
        result.originalPrice shouldBeEqualTo 500.0
        result.discountedPrice shouldBeEqualTo 350.0
        result.discountPercentage shouldBeEqualTo 30
        result.validUntil shouldBeEqualTo "2024-12-31"
        result.description shouldBeEqualTo "Beautiful city"
        result.termsAndConditions shouldBeEqualTo "Valid until end of year"
    }

    @Test
    fun `toDomain should correctly map DestinationDto to Destination`() {
        // When
        val result = destinationDto.toDomain()

        // Then
        result.id shouldBeEqualTo "dest1"
        result.name shouldBeEqualTo "Paris"
        result.cityName shouldBeEqualTo "Paris"
        result.countryName shouldBeEqualTo "France"
        result.airportCode shouldBeEqualTo "CDG"
        result.imageUrl shouldBeEqualTo "https://example.com/paris.jpg"
        result.description shouldBeEqualTo "City of Light"
        result.averageTemperature shouldBeEqualTo "20°C"
        result.timeZone shouldBeEqualTo "CET"
    }
}
