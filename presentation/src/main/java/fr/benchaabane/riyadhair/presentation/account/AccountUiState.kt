package fr.benchaabane.riyadhair.presentation.account

import androidx.compose.runtime.Immutable

/**
 * Represents the UI state for the account screen in the RiyadhAir application.
 *
 * This data class encapsulates all the state information needed to render
 * the account screen, including account data, loading states, error handling,
 * and miles expiration information. It follows the immutable state pattern
 * for predictable UI updates and state management.
 *
 * **State Components:**
 * - **Account Data**: User account information and profile details
 * - **Loading States**: Different loading indicators for various operations
 * - **Error Handling**: Error messages and error state management
 * - **Miles Information**: Loyalty program miles and expiration details
 *
 * **Loading State Management:**
 * - **Initial Loading**: `isLoading` for first-time data fetch
 * - **Refresh Loading**: `isRefreshing` for pull-to-refresh operations
 * - **State Transitions**: Clear loading state progression
 *
 * **Error Handling:**
 * - **Error Messages**: User-friendly error descriptions
 * - **Error State**: Clear indication of error conditions
 * - **Recovery**: Error state can be cleared on retry
 *
 * **Miles Management:**
 * - **Expiration Tracking**: Miles expiration date information
 * - **Loyalty Program**: Integration with RiyadhAir loyalty system
 * - **User Engagement**: Encourages miles usage and redemption
 *
 * **Immutable Design:**
 * - **@Immutable Annotation**: Compose optimization for state changes
 * - **Predictable Updates**: State changes only through proper channels
 * - **Performance**: Efficient Compose recomposition
 *
 * @param account The user's account information and profile details
 * @param isLoading Whether the account data is being loaded initially
 * @param isRefreshing Whether the account data is being refreshed
 * @param error Error message if account loading failed
 * @param milesExpirationDate Date when accumulated miles will expire
 */
@Immutable
data class AccountUiState(
    val account: AccountUiModel? = null,
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val milesExpirationDate: String? = "12/08/2027"
)