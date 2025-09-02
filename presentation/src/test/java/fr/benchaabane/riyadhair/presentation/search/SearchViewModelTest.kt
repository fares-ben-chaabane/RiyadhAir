package fr.benchaabane.riyadhair.presentation.search

import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.domain.flights.models.Airport
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.usecases.SearchFlightsUseCase
import fr.benchaabane.riyadhair.domain.flights.usecases.GetFlightDetailsUseCase
import fr.benchaabane.riyadhair.presentation.search.toUi
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
import org.junit.Test
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var mockSearchFlightsUseCase: SearchFlightsUseCase
    private lateinit var mockGetFlightDetailsUseCase: GetFlightDetailsUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Given
        mockSearchFlightsUseCase = mockk()
        mockGetFlightDetailsUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `SearchViewModel should initialize with empty state`() = runTest {
        // When
        viewModel = SearchViewModel(mockSearchFlightsUseCase, mockGetFlightDetailsUseCase)
        advanceUntilIdle()

        // Then
        val initialState = viewModel.state.value
        initialState.flights shouldBeEqualTo emptyList()
        initialState.returnFlights shouldBeEqualTo emptyList()
        initialState.selectedDepartureFlight shouldBeEqualTo null
        initialState.selectedReturnFlight shouldBeEqualTo null
    }

    @Test
    fun `SearchViewModel should search flights successfully`() = runTest {
        // Given
        val mockFlights = listOf(
            Flight(
                id = "1",
                flightNumber = "AF001",
                airline = "Air France",
                departureAirport = Airport(
                    code = "CDG",
                    name = "Charles de Gaulle Airport",
                    city = "Paris",
                    country = "France",
                    timezone = "CET"
                ),
                arrivalAirport = Airport(
                    code = "JFK",
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
            )
        )
        coEvery { mockSearchFlightsUseCase.invoke(origin = "CDG", destination = "JFK") } returns Result.success(mockFlights)
        coEvery { mockSearchFlightsUseCase.invoke(origin = "JFK", destination = "CDG") } returns Result.success(mockFlights)

        // When
        viewModel = SearchViewModel(mockSearchFlightsUseCase, mockGetFlightDetailsUseCase)
        viewModel.search("CDG", "JFK")
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.flights shouldBeEqualTo mockFlights.map { it.toUi() }
        state.flights.size shouldBeEqualTo 1
        state.flights.first().flightNumber shouldBeEqualTo "AF001"
    }

    @Test
    fun `SearchViewModel should handle search failure gracefully`() = runTest {
        // Given
        coEvery { mockSearchFlightsUseCase.invoke(origin = "CDG", destination = "JFK") } returns Result.failure(Exception("Network error"))
        coEvery { mockSearchFlightsUseCase.invoke(origin = "JFK", destination = "CDG") } returns Result.failure(Exception("Network error"))

        // When
        viewModel = SearchViewModel(mockSearchFlightsUseCase, mockGetFlightDetailsUseCase)
        viewModel.search("CDG", "JFK")
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.flights shouldBeEqualTo emptyList()
        // Should not crash
    }

    @Test
    fun `SearchViewModel should select departure flight`() = runTest {
        // Given
        val mockFlight = FlightUiModel(
            companyName = "Air France",
            flightNumber = "AF001",
            departureTime = "10:00",
            arrivalTime = "16:00",
            departureAirportCode = "CDG",
            arrivalAirportCode = "JFK",
            departureCity = "Paris",
            arrivalCity = "New York",
            duration = "6h 0m",
            price = "€450",
            cabin = "Economy",
            availableSeats = 150,
            aircraftType = "Boeing 777"
        )

        // When
        viewModel = SearchViewModel(mockSearchFlightsUseCase, mockGetFlightDetailsUseCase)
        viewModel.selectDepartureFlight(mockFlight)
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.selectedDepartureFlight shouldBeEqualTo mockFlight
    }

    @Test
    fun `SearchViewModel should select return flight`() = runTest {
        // Given
        val mockFlight = FlightUiModel(
            companyName = "Air France",
            flightNumber = "AF002",
            departureTime = "18:00",
            arrivalTime = "00:00",
            departureAirportCode = "JFK",
            arrivalAirportCode = "CDG",
            departureCity = "New York",
            arrivalCity = "Paris",
            duration = "6h 0m",
            price = "€450",
            cabin = "Economy",
            availableSeats = 150,
            aircraftType = "Boeing 777"
        )

        // When
        viewModel = SearchViewModel(mockSearchFlightsUseCase, mockGetFlightDetailsUseCase)
        viewModel.selectReturnFlight(mockFlight)
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        state.selectedReturnFlight shouldBeEqualTo mockFlight
    }
}
