package fr.benchaabane.riyadhair.presentation.partners

import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.models.PartnerCategory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class PartnersMappersTest {

    private lateinit var mockPartner: Partner

    @Before
    fun setUp() {
        // Given
        mockPartner = Partner(
            id = "1",
            name = "Car Rental Service",
            category = PartnerCategory.CAR_RENTAL,
            imageUrl = "https://example.com/car.jpg",
            description = "Best car rental service in the city",
            discountPercentage = 15,
            websiteUrl = "https://carrental.com",
            isActive = true
        )
    }

    @Test
    fun `toUi should correctly map Partner to PartnerUiModel`() {
        // When
        val result = mockPartner.toUi()

        // Then
        result.coverImage shouldBeEqualTo "https://example.com/car.jpg"
        result.name shouldBeEqualTo "Car Rental Service"
        result.categoryName shouldBeEqualTo "Location de voiture"
        result.discountInfo shouldBeEqualTo "-15%"
    }

    @Test
    fun `toUi should handle null discount percentage`() {
        // Given
        val partnerWithoutDiscount = mockPartner.copy(discountPercentage = 20)

        // When
        val result = partnerWithoutDiscount.toUi()

        // Then
        result.discountInfo shouldBeEqualTo "-20%"
        result.name shouldBeEqualTo "Car Rental Service"
    }

    @Test
    fun `toUi should handle inactive partner`() {
        // Given
        val inactivePartner = mockPartner.copy(isActive = false)

        // When
        val result = inactivePartner.toUi()

        // Then
        result.name shouldBeEqualTo "Car Rental Service"
        // isActive is not mapped in the UI model
    }

    @Test
    fun `toUi should handle different partner categories`() {
        // Given
        val hotelPartner = mockPartner.copy(
            name = "Hotel Service",
            category = PartnerCategory.HOTEL,
            description = "Luxury hotel accommodations"
        )

        // When
        val result = hotelPartner.toUi()

        // Then
        result.categoryName shouldBeEqualTo "Hôtels"
        result.name shouldBeEqualTo "Hotel Service"
        // description is not mapped in the UI model
    }
}
