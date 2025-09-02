package fr.benchaabane.riyadhair.domain.flights.usecases

import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.models.Airport
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository
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
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class SearchFlightsUseCaseTest {

    private lateinit var useCase: SearchFlightsUseCase
    private lateinit var mockRepository: FlightRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockRepository = mockk()
        useCase = SearchFlightsUseCase(mockRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should return success with flights when repository succeeds`() = runTest {
        // Given
        val origin = "CDG"
        val destination = "JFK"
        val expectedFlights = listOf(
            Flight(
                id = "1",
                flightNumber = "AF001",
                airline = "Air France",
                departureAirport = Airport(
                    code = origin,
                    name = "Charles de Gaulle Airport",
                    city = "Paris",
                    country = "France",
                    timezone = "CET"
                ),
                arrivalAirport = Airport(
                    code = destination,
                    name = "John F. Kennedy Airport",
                    city = "New York",
                    country = "USA",
                    timezone = "EST"
                ),
                departureTime = LocalDateTime.now().plusHours(2),
                arrivalTime = LocalDateTime.now().plusHours(8),
                duration = "6h 0m",
                price = 450.0,
                cabinClass = CabinClass.ECONOMY,
                availableSeats = 150,
                aircraft = "Boeing 777"
            ),
            Flight(
                id = "2",
                flightNumber = "AF002",
                airline = "Air France",
                departureAirport = Airport(
                    code = origin,
                    name = "Charles de Gaulle Airport",
                    city = "Paris",
                    country = "France",
                    timezone = "CET"
                ),
                arrivalAirport = Airport(
                    code = destination,
                    name = "John F. Kennedy Airport",
                    city = "New York",
                    country = "USA",
                    timezone = "EST"
                ),
                departureTime = LocalDateTime.now().plusHours(4),
                arrivalTime = LocalDateTime.now().plusHours(10),
                duration = "6h 0m",
                price = 520.0,
                cabinClass = CabinClass.ECONOMY,
                availableSeats = 120,
                aircraft = "Airbus A350"
            )
        )
        coEvery { mockRepository.getFlights(origin, destination) } returns Result.success(expectedFlights)

        // When
        val result = useCase(origin, destination)

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull() shouldBeEqualTo expectedFlights
        result.getOrNull()?.size shouldBeEqualTo 2
        result.getOrNull()?.first()?.departureAirport?.code shouldBeEqualTo origin
        result.getOrNull()?.first()?.arrivalAirport?.code shouldBeEqualTo destination
    }

    @Test
    fun `invoke should return failure when repository fails`() = runTest {
        // Given
        val origin = "CDG"
        val destination = "JFK"
        val expectedError = Exception("Network error")
        coEvery { mockRepository.getFlights(origin, destination) } returns Result.failure(expectedError)

        // When
        val result = useCase(origin, destination)

        // Then
        result.isFailure shouldBeEqualTo true
        result.exceptionOrNull() shouldBeEqualTo expectedError
        result.exceptionOrNull() shouldBeInstanceOf Exception::class
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        // Given
        val origin = "CDG"
        val destination = "JFK"
        coEvery { mockRepository.getFlights(origin, destination) } returns Result.success(emptyList())

        // When
        val result = useCase(origin, destination)

        // Then
        result.isSuccess shouldBeEqualTo true
        result.getOrNull()?.isEmpty() shouldBeEqualTo true
        result.getOrNull()?.size shouldBeEqualTo 0
    }
}
