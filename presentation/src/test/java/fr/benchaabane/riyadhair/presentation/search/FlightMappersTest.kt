package fr.benchaabane.riyadhair.presentation.search

import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.models.Airport
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class FlightMappersTest {

    private lateinit var mockFlight: Flight
    private lateinit var mockDepartureAirport: Airport
    private lateinit var mockArrivalAirport: Airport

    @Before
    fun setUp() {
        // Given
        mockDepartureAirport = Airport(
            code = "CDG",
            name = "Charles de Gaulle Airport",
            city = "Paris",
            country = "France",
            timezone = "CET"
        )
        
        mockArrivalAirport = Airport(
            code = "JFK",
            name = "John F. Kennedy Airport",
            city = "New York",
            country = "USA",
            timezone = "EST"
        )
        
        mockFlight = Flight(
            id = "1",
            flightNumber = "AF001",
            airline = "Air France",
            departureAirport = mockDepartureAirport,
            arrivalAirport = mockArrivalAirport,
            departureTime = LocalDateTime.of(2024, 6, 15, 10, 0),
            arrivalTime = LocalDateTime.of(2024, 6, 15, 16, 0),
            duration = "6h 0m",
            price = 450.0,
            cabinClass = CabinClass.ECONOMY,
            availableSeats = 150,
            aircraft = "Boeing 777"
        )
    }

    @Test
    fun `toUi should correctly map Flight to FlightUiModel`() {
        // When
        val result = mockFlight.toUi()

        // Then
        result.companyName shouldBeEqualTo "Air France"
        result.flightNumber shouldBeEqualTo "AF001"
        result.departureTime shouldBeEqualTo "10:0"
        result.arrivalTime shouldBeEqualTo "16:0"
        result.departureAirportCode shouldBeEqualTo "CDG"
        result.arrivalAirportCode shouldBeEqualTo "JFK"
        result.departureCity shouldBeEqualTo "Paris"
        result.arrivalCity shouldBeEqualTo "New York"
        result.duration shouldBeEqualTo "6h 0m"
        result.price shouldBeEqualTo "450.0 EUR"
        result.cabin shouldBeEqualTo "Economy"
        result.availableSeats shouldBeEqualTo 150
        result.aircraftType shouldBeEqualTo "Boeing 777"
    }

    @Test
    fun `toUi should handle different cabin classes`() {
        // Given
        val businessFlight = mockFlight.copy(cabinClass = CabinClass.BUSINESS)

        // When
        val result = businessFlight.toUi()

        // Then
        result.cabin shouldBeEqualTo "Business"
        result.flightNumber shouldBeEqualTo "AF001"
    }

    @Test
    fun `toUi should handle different airlines`() {
        // Given
        val lufthansaFlight = mockFlight.copy(
            flightNumber = "LH123",
            airline = "Lufthansa"
        )

        // When
        val result = lufthansaFlight.toUi()

        // Then
        result.companyName shouldBeEqualTo "Lufthansa"
        result.flightNumber shouldBeEqualTo "LH123"
    }

    @Test
    fun `toUi should handle different airports`() {
        // Given
        val londonFlight = mockFlight.copy(
            departureAirport = Airport(
                code = "LHR",
                name = "Heathrow Airport",
                city = "London",
                country = "UK",
                timezone = "GMT"
            )
        )

        // When
        val result = londonFlight.toUi()

        // Then
        result.departureAirportCode shouldBeEqualTo "LHR"
        result.arrivalAirportCode shouldBeEqualTo "JFK"
    }

    @Test
    fun `toUi should handle different times`() {
        // Given
        val eveningFlight = mockFlight.copy(
            departureTime = LocalDateTime.of(2024, 6, 15, 22, 30),
            arrivalTime = LocalDateTime.of(2024, 6, 16, 4, 30)
        )

        // When
        val result = eveningFlight.toUi()

        // Then
        result.departureTime shouldBeEqualTo "22:30"
        result.arrivalTime shouldBeEqualTo "4:30"
    }
}
