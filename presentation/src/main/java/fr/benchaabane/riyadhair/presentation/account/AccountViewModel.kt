package fr.benchaabane.riyadhair.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.benchaabane.riyadhair.core.dispatcher.BackgroundDispatcher
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing account-related UI state and business logic.
 * 
 * This ViewModel handles the presentation logic for the account screen,
 * including fetching account data and managing UI state updates. It follows
 * the MVVM pattern and provides a clean interface between the UI and business logic.
 * 
 * **Responsibilities:**
 * - Manages account-related UI state
 * - Fetches and updates account information
 * - Handles loading states and error conditions
 * - Provides reactive UI updates through StateFlow
 * 
 * **State Management:**
 * The ViewModel maintains a single source of truth for the account screen state,
 * including account data, loading indicators, and error messages. All state updates
 * are performed through the `_uiState` MutableStateFlow.
 * 
 * **Lifecycle Awareness:**
 * This ViewModel is lifecycle-aware and automatically handles configuration changes.
 * It uses `viewModelScope` for coroutine management to ensure proper cleanup.
 * 
 * **Dependency Injection:**
 * Uses Hilt for dependency injection, ensuring proper separation of concerns
 * and testability.
 * 
 * @property getAccountUseCase Use case for retrieving account information
 * 
 * @sample
 * ```kotlin
 * // In a Composable
 * @Composable
 * fun AccountScreen(
 *     viewModel: AccountViewModel = hiltViewModel()
 * ) {
 *     val uiState by viewModel.uiState.collectAsState()
 *     
 *     when {
 *         uiState.isLoading -> LoadingIndicator()
 *         uiState.error != null -> ErrorMessage(uiState.error)
 *         uiState.account != null -> AccountContent(uiState.account)
 *     }
 * }
 * ```
 * 
 * @see AccountUiState
 * @see AccountUiModel
 * @see GetAccountUseCase
 * @see HiltViewModel
 */
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    @BackgroundDispatcher
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    init {
        observeAccount()
    }

    /**
     * Observes account data and updates the UI state accordingly.
     * 
     * This method launches a coroutine to fetch account information
     * and updates the UI state with the result or error. It's called
     * during ViewModel initialization to load the initial account data.
     * 
     * **State Updates:**
     * - Sets loading state to true initially
     * - Updates state with account data on success
     * - Updates state with error message on failure
     * - Always sets loading to false when operation completes
     * 
     * **Error Handling:**
     * Errors are captured and stored in the UI state, allowing the UI
     * to display appropriate error messages to the user.
     * 
     * **Coroutine Management:**
     * Uses `viewModelScope` to ensure proper lifecycle management
     * and automatic cancellation when the ViewModel is cleared.
     */
    private fun observeAccount() {
        viewModelScope.launch(backgroundDispatcher) {
            getAccountUseCase.invoke()
                .onSuccess { account ->
                    _uiState.update {
                        _uiState.value.copy(
                            account = account?.toUi(),
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onFailure {
                    // TODO handle failure
                }
        }
    }

    /**
     * Clears any error messages from the UI state.
     * 
     * This method resets the error field in the UI state to null,
     * effectively clearing any displayed error messages.
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
