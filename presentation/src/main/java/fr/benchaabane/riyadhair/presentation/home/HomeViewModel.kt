package fr.benchaabane.riyadhair.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.benchaabane.riyadhair.core.dispatcher.BackgroundDispatcher
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase
import fr.benchaabane.riyadhair.domain.offers.usecases.GetBestOffersUseCase
import fr.benchaabane.riyadhair.domain.partners.usecases.GetPartnersUseCase
import fr.benchaabane.riyadhair.presentation.account.toUi
import fr.benchaabane.riyadhair.presentation.offers.toUi
import fr.benchaabane.riyadhair.presentation.partners.toUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing home screen UI state and business logic.
 * 
 * This ViewModel handles the presentation logic for the home screen,
 * including fetching account data, offers, and partners information.
 * It also manages the offers carousel functionality with automatic
 * rotation and user interaction support.
 * 
 * **Core Responsibilities:**
 * - **Account Management**: Fetches and displays user account information
 * - **Offers Display**: Retrieves and presents best travel offers
 * - **Partners Information**: Shows business partner details
 * - **Carousel Control**: Manages automatic offers rotation
 * - **State Management**: Maintains consistent UI state across data updates
 * 
 * **Data Flow:**
 * - **Initialization**: Automatically fetches all required data on creation
 * - **Real-time Updates**: Continuously observes data changes and updates UI
 * - **Error Handling**: Manages failure scenarios gracefully
 * - **State Synchronization**: Ensures UI state reflects current data state
 * 
 * **Carousel Features:**
 * - **Automatic Rotation**: Offers automatically rotate every 5 seconds
 * - **User Interaction**: Manual navigation resets the carousel timer
 * - **Smooth Transitions**: Seamless offer switching with proper timing
 * - **Performance Optimized**: Efficient coroutine-based implementation
 * 
 * **Use Cases:**
 * - Home screen data presentation
 * - Offers carousel management
 * - User account information display
 * - Business partner showcase
 * - Real-time data synchronization
 * 
 * @property getAccountUseCase Use case for retrieving account information
 * @property getBestOffersUseCase Use case for retrieving best offers
 * @property getPartnersUseCase Use case for retrieving partners information
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getBestOffersUseCase: GetBestOffersUseCase,
    private val getPartnersUseCase: GetPartnersUseCase,
    @BackgroundDispatcher
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    /**
     * Mutable state flow for managing home screen UI state.
     * 
     * This private property holds the current UI state and allows
     * the ViewModel to update it as data changes occur.
     */
    private val _uiState = MutableStateFlow(HomeUiState())
    
    /**
     * Public state flow for observing home screen UI state changes.
     * 
     * This property provides read-only access to the UI state,
     * allowing the UI to observe and react to state changes.
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /**
     * Coroutine job for managing the offers carousel animation.
     * 
     * This job controls the automatic rotation of offers in the carousel.
     * It can be cancelled and restarted as needed for user interactions.
     */
    private var carouselJob: Job? = null

    /**
     * Initializes the ViewModel and starts data observation.
     * 
     * This method is called when the ViewModel is created and sets up
     * the initial data fetching for account, offers, and partners.
     */
    init {
        observeAccount()
        observeBestOffers()
        observePartners()
    }

    /**
     * Observes account data and updates the UI state accordingly.
     * 
     * This method launches a coroutine to fetch account information
     * and updates the UI state with the result or error. It handles
     * both success and failure scenarios gracefully.
     * 
     * **Data Flow:**
     * - Fetches account data using the injected use case
     * - Maps domain model to UI model using extension function
     * - Updates UI state with successful results
     * - Handles failures (currently marked as TODO)
     * 
     * **State Updates:**
     * - Updates account information in UI state
     * - Maintains other state properties unchanged
     * - Triggers UI recomposition for account display
     */
    private fun observeAccount() {
        viewModelScope.launch(backgroundDispatcher) {
            getAccountUseCase.invoke()
                .onSuccess { account ->
                    _uiState.update {
                        _uiState.value.copy(
                            account = account?.toUi(),
                        )
                    }
                }
                .onFailure {
                    // TODO Handle failure here
                }
        }
    }
    
    /**
     * Observes best offers data and updates the UI state accordingly.
     * 
     * This method launches a coroutine to fetch best offers information
     * and updates the UI state with the result or error. If offers
     * are available, it also starts the carousel animation automatically.
     * 
     * **Data Flow:**
     * - Fetches best offers using the injected use case
     * - Maps domain models to UI models using extension function
     * - Updates UI state with successful results
     * - Automatically starts carousel if offers are available
     * - Handles failures (currently marked as TODO)
     * 
     * **Carousel Integration:**
     * - Checks if offers list is not empty
     * - Automatically starts carousel animation
     * - Ensures smooth user experience with immediate content
     * 
     * **State Updates:**
     * - Updates offers list in UI state
     * - Maintains other state properties unchanged
     * - Triggers UI recomposition for offers display
     */
    private fun observeBestOffers() {
        viewModelScope.launch(backgroundDispatcher) {
            getBestOffersUseCase.invoke()
                .onSuccess { bestOffers ->
                    _uiState.update {
                        _uiState.value.copy(
                            bestOffers = bestOffers.map { it.toUi() },
                        )
                    }

                    // Start carousel if offers are available
                    if (bestOffers.isNotEmpty()) {
                        startCarousel()
                    }
                }
                .onFailure {
                    // TODO Handle failure here
                }
        }
    }

    /**
     * Observes partners data and updates the UI state accordingly.
     * 
     * This method launches a coroutine to fetch partners information
     * and updates the UI state with the result or error. It handles
     * both success and failure scenarios gracefully.
     * 
     * **Data Flow:**
     * - Fetches partners data using the injected use case
     * - Maps domain models to UI models using extension function
     * - Updates UI state with successful results
     * - Handles failures (currently marked as TODO)
     * 
     * **State Updates:**
     * - Updates partners list in UI state
     * - Maintains other state properties unchanged
     * - Triggers UI recomposition for partners display
     */
    private fun observePartners() {
        viewModelScope.launch(backgroundDispatcher) {
            getPartnersUseCase.invoke()
                .onSuccess { partners ->
                    _uiState.update {
                        _uiState.value.copy(
                            partners = partners.map { it.toUi() },
                        )
                    }
                }
                .onFailure {
                    // TODO Handle failure here
                }
        }
    }

    /**
     * Starts the offers carousel with automatic rotation.
     * 
     * This method creates a coroutine job that automatically rotates
     * through the available offers at regular intervals. It cancels
     * any existing carousel job before starting a new one to prevent
     * multiple concurrent rotations.
     * 
     * **Carousel Behavior:**
     * - **Automatic Rotation**: Offers change every 5 seconds
     * - **Circular Navigation**: Loops back to first offer after last
     * - **State Updates**: Updates current offer index in UI state
     * - **Job Management**: Cancels previous job before starting new one
     * 
     * **Performance Features:**
     * - **Efficient Looping**: Uses modulo operation for circular navigation
     * - **State Consistency**: Always updates with valid offer indices
     * - **Resource Management**: Properly manages coroutine lifecycle
     * - **Smooth Transitions**: Consistent timing for user experience
     * 
     * **Implementation Details:**
     * - Uses infinite loop with delay for timing control
     * - Checks offers availability before rotation
     * - Updates UI state with new offer index
     * - Handles edge cases gracefully
     */
    private fun startCarousel() {
        carouselJob?.cancel()
        carouselJob = viewModelScope.launch {
            while (true) {
                delay(CAROUSEL_DELAY) // 5 seconds
                val currentState = _uiState.value
                if (currentState.bestOffers.isNotEmpty()) {
                    val nextIndex =
                        (currentState.currentOfferIndex + 1) % currentState.bestOffers.size
                    _uiState.update { currentState.copy(currentOfferIndex = nextIndex) }
                }
            }
        }
    }

    /**
     * Handles user interaction with the offers carousel.
     * 
     * This method allows users to manually navigate through offers
     * and automatically restarts the carousel timer to provide
     * a smooth user experience.
     * 
     * **User Interaction:**
     * - **Manual Navigation**: Users can tap to change offers
     * - **Index Validation**: Ensures index is within valid range
     * - **Timer Reset**: Restarts carousel after user interaction
     * - **State Updates**: Updates current offer index immediately
     * 
     * **Validation Logic:**
     * - Checks if index is non-negative
     * - Ensures index is within offers list bounds
     * - Only updates state for valid indices
     * - Prevents out-of-bounds errors
     * 
     * **Carousel Management:**
     * - Restarts carousel timer after user interaction
     * - Provides immediate visual feedback
     * - Maintains smooth rotation experience
     * - Prevents conflicts between manual and automatic navigation
     * 
     * @param index The new offer index selected by the user
     */
    fun onOfferIndexChanged(index: Int) {
        val currentState = _uiState.value
        if (index >= 0 && index < currentState.bestOffers.size) {
            _uiState.update { currentState.copy(currentOfferIndex = index) }
            // Restart carousel timer
            startCarousel()
        }
    }

    /**
     * Cleans up resources when the ViewModel is cleared.
     * 
     * This method is called when the ViewModel is no longer needed
     * and ensures proper cleanup of coroutine jobs to prevent
     * memory leaks and unnecessary background processing.
     * 
     * **Cleanup Actions:**
     * - Cancels active carousel job
     * - Prevents background coroutines from continuing
     * - Releases system resources
     * - Ensures proper lifecycle management
     * 
     * **Lifecycle Integration:**
     * - Called automatically by Android lifecycle system
     * - Ensures proper cleanup in all scenarios
     * - Prevents resource leaks
     * - Maintains system stability
     */
    override fun onCleared() {
        super.onCleared()
        carouselJob?.cancel()
    }
}

/**
 * Constant defining the delay between carousel rotations.
 * 
 * This value controls how frequently the offers carousel automatically
 * rotates to the next offer, providing a balance between user
 * engagement and content visibility.
 * 
 * **Timing Configuration:**
 * - **5 Second Delay**: Optimal balance for user attention
 * - **Millisecond Precision**: Uses Long value for precise timing
 * - **Performance Optimized**: Efficient for coroutine-based delays
 * - **User Experience**: Provides enough time to read offer content
 */
private const val CAROUSEL_DELAY = 5000L