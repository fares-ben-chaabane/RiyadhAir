package fr.benchaabane.riyadhair.presentation.home

import androidx.compose.runtime.Stable
import fr.benchaabane.riyadhair.presentation.account.AccountUiModel
import fr.benchaabane.riyadhair.presentation.offers.OfferUiModel
import fr.benchaabane.riyadhair.presentation.partners.PartnerUiModel

/**
 * UI state data class for the home screen.
 *
 * This data class represents the complete state of the home screen,
 * including user account information, travel offers, business partners,
 * and various loading and error states. It provides a centralized
 * way to manage all UI-related data for the home screen.
 *
 * **State Management:**
 * - **Account Information**: User profile and loyalty details
 * - **Travel Offers**: Best deals and promotional offers
 * - **Business Partners**: Partner information and benefits
 * - **Carousel Control**: Current offer index for rotation
 * - **Loading States**: Individual loading indicators for each data type
 * - **Error Handling**: Error messages for failed operations
 *
 * **Data Organization:**
 * - **Primary Data**: Account, offers, and partners information
 * - **UI State**: Loading indicators and error messages
 * - **Carousel State**: Current offer index for automatic rotation
 * - **Default Values**: Sensible defaults for all properties
 *
 * **Stability Features:**
 * - **@Stable Annotation**: Optimizes Compose recomposition
 * - **Immutable Design**: All properties are read-only
 * - **Data Class**: Automatic equals, hashCode, and copy methods
 * - **Type Safety**: Strong typing for all state properties
 *
 * **Use Cases:**
 * - Home screen state management
 * - UI state observation and updates
 * - Data loading state tracking
 * - Error state handling
 * - Carousel position management
 *
 * **Performance Considerations:**
 * - **Efficient Updates**: Only updates changed properties
 * - **Minimal Recomposition**: Stable annotation reduces unnecessary updates
 * - **Memory Efficient**: Lightweight data structure
 * - **State Synchronization**: Ensures UI consistency
 *
 * @property account The user's account information, null if not loaded
 * @property bestOffers List of best travel offers, empty if none available
 * @property partners List of business partners, empty if none available
 * @property currentOfferIndex Current index in the offers carousel (0-based)
 * @property isLoadingAccount Whether account data is currently being loaded
 * @property isLoadingOffers Whether offers data is currently being loaded
 * @property isLoadingPartners Whether partners data is currently being loaded
 * @property accountError Error message for account loading failures, null if no error
 * @property offersError Error message for offers loading failures, null if no error
 * @property partnersError Error message for partners loading failures, null if no error
 */
@Stable
data class HomeUiState(
    val account: AccountUiModel? = null,
    val bestOffers: List<OfferUiModel> = emptyList(),
    val partners: List<PartnerUiModel> = emptyList(),
    val currentOfferIndex: Int = 0,
    val isLoadingAccount: Boolean = false,
    val isLoadingOffers: Boolean = false,
    val isLoadingPartners: Boolean = false,
    val accountError: String? = null,
    val offersError: String? = null,
    val partnersError: String? = null
)
