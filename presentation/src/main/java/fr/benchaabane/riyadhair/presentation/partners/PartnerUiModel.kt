package fr.benchaabane.riyadhair.presentation.partners

import androidx.compose.runtime.Immutable

/**
 * UI model for displaying business partner information in the presentation layer.
 *
 * This data class represents a formatted and UI-ready version of business partners,
 * containing all the necessary information for displaying partner content
 * in the user interface. It's designed for optimal performance and user experience.
 *
 * **Data Structure:**
 * - **Visual Content**: Cover image for immediate visual identification
 * - **Partner Information**: Name and category for clear identification
 * - **Promotional Data**: Discount information to highlight benefits
 * - **Brand Recognition**: Consistent partner presentation across the app
 *
 * **UI Presentation Features:**
 * - **Image Display**: High-quality partner logos and brand images
 * - **Text Formatting**: Pre-formatted strings for immediate UI consumption
 * - **Category Display**: Clear partner type classification
 * - **Discount Highlighting**: Prominent display of partner benefits
 *
 * **Performance Optimizations:**
 * - **@Immutable Annotation**: Optimizes Compose recomposition performance
 * - **Data Class**: Automatic equals, hashCode, and copy methods
 * - **Lightweight Structure**: Minimal memory footprint for UI rendering
 * - **Efficient Updates**: Only updates changed properties trigger recomposition
 *
 * **Use Cases:**
 * - Home screen partners section
 * - Partners list displays
 * - Business collaboration showcase
 * - Partner benefits presentation
 * - Brand partnership marketing
 *
 * **Design System Integration:**
 * - **Consistent Layout**: Uniform presentation across all partner displays
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
 * **Business Context:**
 * - **Partnership Display**: Shows business collaboration opportunities
 * - **User Benefits**: Highlights discounts and special offers
 * - **Brand Recognition**: Establishes partner brand presence
 * - **Trust Building**: Demonstrates business relationships
 *
 * @property coverImage URL or path to the partner's cover image or logo
 * @property name The name of the business partner
 * @property categoryName The category or type of business partner
 * @property discountInfo Formatted discount or benefit information
 */
@Immutable
data class PartnerUiModel(
    val coverImage: String,
    val name: String,
    val categoryName: String,
    val discountInfo: String,
)