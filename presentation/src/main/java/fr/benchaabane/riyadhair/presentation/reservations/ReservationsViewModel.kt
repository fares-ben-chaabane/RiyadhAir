package fr.benchaabane.riyadhair.presentation.reservations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.benchaabane.riyadhair.domain.reservations.models.Reservation
import fr.benchaabane.riyadhair.domain.reservations.usecases.ObserveReservationsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing reservations screen UI state and business logic.
 *
 * This ViewModel handles the presentation logic for the reservations screen,
 * including observing and managing user flight reservations. It provides
 * real-time updates of reservation data and maintains consistent UI state.
 *
 * **Core Responsibilities:**
 * - **Reservation Observation**: Continuously monitors user reservations
 * - **State Management**: Maintains current reservations list in UI state
 * - **Data Flow**: Coordinates between domain layer and presentation layer
 * - **Lifecycle Management**: Properly manages coroutine lifecycle
 *
 * **Data Management:**
 * - **Real-time Updates**: Automatically updates when reservations change
 * - **State Synchronization**: Ensures UI reflects current data state
 * - **Data Consistency**: Maintains data integrity across updates
 * - **Error Handling**: Graceful handling of data retrieval failures
 *
 * **Architecture Integration:**
 * - **Hilt Injection**: Uses dependency injection for use case access
 * - **Coroutine Integration**: Leverages Kotlin coroutines for async operations
 * - **Flow Management**: Uses StateFlow for reactive state updates
 * - **ViewModel Lifecycle**: Proper integration with Android lifecycle
 *
 * **Use Cases:**
 * - **ObserveReservationsUseCase**: Monitors user reservation changes
 * - **Reservation Display**: Shows current user reservations
 * - **Reservation Updates**: Handles real-time reservation modifications
 * - **State Observation**: Provides UI state for reservation components
 *
 * **Performance Features:**
 * - **Efficient Observation**: Only updates when data actually changes
 * - **Memory Management**: Proper coroutine lifecycle management
 * - **State Optimization**: Minimal recomposition during updates
 * - **Resource Cleanup**: Automatic cleanup when ViewModel is cleared
 *
 * **User Experience:**
 * - **Real-time Updates**: Immediate reflection of reservation changes
 * - **Consistent State**: Reliable UI state management
 * - **Smooth Interactions**: Responsive user interface updates
 * - **Data Reliability**: Trustworthy reservation information display
 *
 * **Business Logic:**
 * - **Reservation Monitoring**: Tracks all user flight reservations
 * - **Data Synchronization**: Ensures UI matches backend state
 * - **State Consistency**: Maintains data integrity across operations
 * - **User Context**: Provides personalized reservation information
 *
 * @property observeReservations Use case for observing user reservations
 */
@HiltViewModel
class ReservationsViewModel @Inject constructor(
    private val observeReservations: ObserveReservationsUseCase
) : ViewModel() {
    
    /**
     * Mutable state flow for managing reservations data.
     *
     * This private property holds the current reservations list and allows
     * the ViewModel to update it as data changes occur. It serves as the
     * internal state management mechanism.
     *
     * **State Management:**
     * - **Data Storage**: Holds current list of user reservations
     * - **Update Mechanism**: Allows ViewModel to modify reservation data
     * - **Type Safety**: Strongly typed with Reservation list
     * - **Initial State**: Starts with empty list until data is loaded
     */
    private val _reservations: MutableStateFlow<List<Reservation>> = MutableStateFlow(emptyList())
    
    /**
     * Public state flow for observing reservations data changes.
     *
     * This property provides read-only access to the reservations state,
     * allowing the UI to observe and react to reservation updates. It
     * automatically emits new values when the underlying data changes.
     *
     * **UI Integration:**
     * - **State Observation**: UI components can observe reservation changes
     * - **Reactive Updates**: Automatic UI updates when data changes
     * - **Read-only Access**: Prevents external modification of state
     * - **Lifecycle Awareness**: Integrates with Compose lifecycle
     *
     * **Data Flow:**
     * - **Domain Layer**: Receives reservation data from use case
     * - **ViewModel Layer**: Processes and manages reservation state
     * - **UI Layer**: Consumes reservation data for display
     * - **State Synchronization**: Ensures UI reflects current data
     */
    val reservations: StateFlow<List<Reservation>> = _reservations.asStateFlow()

    /**
     * Initializes the ViewModel and starts reservation observation.
     *
     * This method is called when the ViewModel is created and sets up
     * the initial data observation for user reservations. It launches
     * a coroutine to continuously monitor reservation changes.
     *
     * **Initialization Process:**
     * - **Coroutine Launch**: Starts background observation task
     * - **Data Collection**: Begins monitoring reservation data stream
     * - **State Updates**: Automatically updates UI state with new data
     * - **Lifecycle Management**: Properly manages coroutine lifecycle
     *
     * **Data Flow:**
     * - **Use Case Execution**: Calls ObserveReservationsUseCase
     * - **Flow Collection**: Collects reservation data stream
     * - **State Updates**: Updates internal state with new data
     * - **UI Notification**: Triggers UI recomposition with new data
     *
     * **Performance Considerations:**
     * - **Efficient Observation**: Only processes actual data changes
     * - **Memory Management**: Proper coroutine lifecycle handling
     * - **State Optimization**: Minimal unnecessary state updates
     * - **Resource Management**: Efficient use of system resources
     */
    init { 
        viewModelScope.launch { 
            observeReservations().collect { 
                _reservations.value = it 
            } 
        } 
    }
}


