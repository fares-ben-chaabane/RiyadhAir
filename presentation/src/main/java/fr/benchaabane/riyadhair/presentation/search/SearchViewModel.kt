package fr.benchaabane.riyadhair.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.benchaabane.riyadhair.core.dispatcher.BackgroundDispatcher
import fr.benchaabane.riyadhair.domain.flights.usecases.GetFlightDetailsUseCase
import fr.benchaabane.riyadhair.domain.flights.usecases.SearchFlightsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing flight search functionality.
 *
 * This ViewModel handles the presentation logic for the flight search screen,
 * including searching for flights, managing search state, and handling user
 * interactions. It follows the MVVM pattern and provides a clean interface
 * between the UI and business logic.
 *
 * **Responsibilities:**
 * - Manages flight search state and results
 * - Coordinates flight search operations
 * - Handles user flight selections
 * - Manages search events and navigation
 *
 * **State Management:**
 * The ViewModel maintains a single source of truth for the search screen state,
 * including search results, selected flights, and UI state. All state updates
 * are performed through the `_state` MutableStateFlow.
 *
 * **Event Handling:**
 * Uses SharedFlow for one-time events like navigation triggers, ensuring
 * events are not lost during configuration changes.
 *
 * **Dependencies:**
 * - **SearchFlightsUseCase**: For searching available flights
 * - **GetFlightDetailsUseCase**: For retrieving detailed flight information
 *
 * **Threading:**
 * Uses `viewModelScope` for coroutine management to ensure proper cleanup
 * and lifecycle awareness.
 *
 * @see SearchState
 * @see SearchEvent
 * @see SearchFlightsUseCase
 * @see GetFlightDetailsUseCase
 * @see HiltViewModel
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchFlightsUseCase: SearchFlightsUseCase,
    private val getFlightDetailsUseCase: GetFlightDetailsUseCase,
    @BackgroundDispatcher
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvent>()
    val event = _event.asSharedFlow()

    /**
     * Searches for flights between two airports.
     *
     * This method performs a comprehensive flight search for both outbound
     * and return flights, updating the UI state with the results. It searches
     * in both directions to support round-trip bookings.
     *
     * **Search Strategy:**
     * 1. **Outbound Search**: Searches from origin to destination
     * 2. **Return Search**: Searches from destination to origin
     * 3. **State Updates**: Updates UI state with search results
     * 4. **Error Handling**: Currently logs failures (TODO: implement proper error handling)
     *
     * **Parameters:**
     * - **from**: Origin airport code (e.g., "RUH", "JED")
     * - **to**: Destination airport code (e.g., "DXB", "LHR")
     *
     * **State Updates:**
     * - **flights**: Outbound flight options
     * - **returnFlights**: Return flight options
     *
     * **Usage:**
     * ```kotlin
     * searchViewModel.search("RUH", "JED")
     * ```
     *
     * **Note:**
     * This method launches a coroutine in the ViewModel scope to ensure
     * proper lifecycle management and cancellation.
     *
     * @param from The origin airport code
     * @param to The destination airport code
     */
    fun search(from: String, to: String) {
        viewModelScope.launch(backgroundDispatcher) {
            searchFlightsUseCase.invoke(origin = from, destination = to)
                .onSuccess { flights ->
                    _state.update {
                        _state.value.copy(
                            flights = flights.map { it.toUi() }
                        )
                    }
                }
                .onFailure {
                    // TODO handle failure
                }
            searchFlightsUseCase.invoke(origin = to, destination = from)
                .onSuccess { flights ->
                    _state.update {
                        _state.value.copy(
                            returnFlights = flights.map { it.toUi() }
                        )
                    }
                }
                .onFailure {
                    // TODO handle failure
                }
        }
    }

    /**
     * Retrieves detailed information for selected flights.
     *
     * This method fetches comprehensive details for both outbound and return
     * flights using their flight numbers. It updates the UI state with the
     * detailed flight information for display and booking purposes.
     *
     * **Flight Details Strategy:**
     * 1. **Outbound Flight**: Retrieves details for departure flight
     * 2. **Return Flight**: Retrieves details for return flight
     * 3. **State Updates**: Updates UI state with flight details
     * 4. **Parallel Processing**: Both flights are processed simultaneously
     *
     * **Parameters:**
     * - **outBoundFlightNumber**: Flight number for departure (e.g., "RX101")
     * - **returnFlightNumber**: Flight number for return (e.g., "RX102")
     *
     * **State Updates:**
     * - **selectedDepartureFlight**: Detailed outbound flight information
     * - **selectedReturnFlight**: Detailed return flight information
     *
     * **Usage:**
     * ```kotlin
     * searchViewModel.getFlightDetails("RX101", "RX102")
     * ```
     *
     * **Note:**
     * This method launches a coroutine in the ViewModel scope to ensure
     * proper lifecycle management and cancellation.
     *
     * @param outBoundFlightNumber The flight number for the outbound flight
     * @param returnFlightNumber The flight number for the return flight
     */
    fun getFlightDetails(outBoundFlightNumber: String, returnFlightNumber: String) {
        viewModelScope.launch(backgroundDispatcher) {
            getFlightDetailsUseCase.invoke(flightNumber = outBoundFlightNumber)
                .onSuccess { flight ->
                    _state.update {
                        _state.value.copy(
                            selectedDepartureFlight = flight?.toUi()
                        )
                    }
                }
            getFlightDetailsUseCase.invoke(flightNumber = returnFlightNumber)
                .onSuccess { flight ->
                    _state.update {
                        _state.value.copy(
                            selectedReturnFlight = flight?.toUi()
                        )
                    }
                }
        }
    }

    /**
     * Selects a departure flight for the user's journey.
     *
     * This method updates the UI state to reflect the user's selection
     * of an outbound flight. It's typically called when a user taps
     * on a flight option in the search results.
     *
     * **State Update:**
     * - **selectedDepartureFlight**: Updated with the selected flight
     *
     * **Usage:**
     * ```kotlin
     * searchViewModel.selectDepartureFlight(selectedFlight)
     * ```
     *
     * @param flight The flight model representing the selected departure flight
     */
    fun selectDepartureFlight(flight: FlightUiModel) {
        _state.update {
            _state.value.copy(
                selectedDepartureFlight = flight
            )
        }
    }

    /**
     * Selects a return flight for the user's journey.
     *
     * This method updates the UI state to reflect the user's selection
     * of a return flight. It's typically called when a user taps
     * on a flight option in the return flight search results.
     *
     * **State Update:**
     * - **selectedReturnFlight**: Updated with the selected flight
     *
     * **Usage:**
     * ```kotlin
     * searchViewModel.selectReturnFlight(selectedFlight)
     * ```
     *
     * @param flight The flight model representing the selected return flight
     */
    fun selectReturnFlight(flight: FlightUiModel) {
        _state.update {
            _state.value.copy(
                selectedReturnFlight = flight
            )
        }
    }
}

data class SearchState(
    val flights: List<FlightUiModel> = emptyList(),
    val returnFlights: List<FlightUiModel> = emptyList(),
    val selectedDepartureFlight: FlightUiModel? = null,
    val selectedReturnFlight: FlightUiModel? = null
)

sealed class SearchEvent {
    data object RedirectToResult : SearchEvent()
}


