package fr.benchaabane.riyadhair.presentation.offers

import androidx.compose.runtime.Immutable

/**
 * UI model for displaying travel offers in the presentation layer.
 *
 * This data class represents a formatted and UI-ready version of travel offers,
 * containing all the necessary information for displaying promotional content
 * in the user interface. It's designed for optimal performance and user experience.
 *
 * **Data Structure:**
 * - **Visual Content**: Cover image for immediate visual appeal
 * - **Location Information**: Destination and country for geographic context
 * - **Pricing Details**: Formatted price information for user decision making
 * - **Promotional Data**: Discount information to highlight savings
 *
 * **UI Presentation Features:**
 * - **Image Display**: High-quality cover images for destination visualization
 * - **Text Formatting**: Pre-formatted strings for immediate UI consumption
 * - **Price Clarity**: Clear pricing information with currency and context
 * - **Discount Highlighting**: Prominent display of savings opportunities
 *
 * **Performance Optimizations:**
 * - **@Immutable Annotation**: Optimizes Compose recomposition performance
 * - **Data Class**: Automatic equals, hashCode, and copy methods
 * - **Lightweight Structure**: Minimal memory footprint for UI rendering
 * - **Efficient Updates**: Only updates changed properties trigger recomposition
 *
 * **Use Cases:**
 * - Home screen offers carousel
 * - Offers list displays
 * - Marketing content presentation
 * - Price comparison interfaces
 * - Destination browsing
 *
 * **Design System Integration:**
 * - **Consistent Layout**: Uniform presentation across all offer displays
 * - **Visual Hierarchy**: Clear information organization and prioritization
 * - **Responsive Design**: Adapts to different screen sizes and orientations
 * - **Accessibility**: Proper content structure for screen readers
 *
 * **Data Flow:**
 * - **Domain Layer**: Receives data from business logic layer
 * - **Mapping Layer**: Transformed by extension functions
 * - **UI Layer**: Consumed by composable components
 * - **State Management**: Integrated with ViewModel state flows
 *
 * @property coverImage URL or path to the destination cover image
 * @property destination Name of the travel destination (e.g., "Paris", "Dubai")
 * @property country Country where the destination is located (e.g., "France", "UAE")
 * @property priceInfo Formatted price information (e.g., "From 599 EUR")
 * @property discountInfo Formatted discount information (e.g., "-25%")
 */
@Immutable
data class OfferUiModel(
    val coverImage: String,
    val destination: String,
    val country: String,
    val priceInfo: String,
    val discountInfo: String
)