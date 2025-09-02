package fr.benchaabane.riyadhair.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * RiyadhAir shape system that provides consistent corner radius values throughout the application.
 *
 * This object defines a comprehensive set of rounded corner shapes that follow
 * Material 3 design principles. The shape system provides progressive corner
 * radius values for different component types and use cases, ensuring visual
 * consistency and modern design aesthetics.
 *
 * **Shape Philosophy:**
 * - **Progressive Scale**: Corner radius increases with component size
 * - **Material 3 Compliance**: Follows Material 3 shape guidelines
 * - **Visual Hierarchy**: Larger components get more rounded corners
 * - **Accessibility**: Consistent visual patterns for user recognition
 *
 * **Shape Scale:**
 * - **Extra Small (4dp)**: Minimal rounding for small, dense elements
 * - **Small (8dp)**: Subtle rounding for compact components
 * - **Medium (12dp)**: Standard rounding for most UI elements
 * - **Large (16dp)**: Generous rounding for prominent components
 * - **Extra Large (24dp)**: Maximum rounding for immersive containers
 *
 * **Component Usage Guidelines:**
 * - **Extra Small (4dp)**: Chips, badges, small indicators
 * - **Small (8dp)**: Buttons, small cards, form inputs
 * - **Medium (12dp)**: Cards, dialogs, content containers
 * - **Large (16dp)**: Bottom sheets, large cards, major containers
 * - **Extra Large (24dp)**: Full-screen containers, hero sections
 *
 * **Design Benefits:**
 * - **Consistency**: Unified corner radius across all components
 * - **Modern Aesthetics**: Contemporary rounded design language
 * - **Visual Comfort**: Softer edges reduce visual harshness
 * - **Brand Identity**: Distinctive RiyadhAir visual style
 * - **Accessibility**: Clear visual boundaries and component definition
 *
 * **Implementation Examples:**
 * ```kotlin
 * // Small components (buttons, chips)
 * Button(
 *     shape = RiyadhAirShapes.small,
 *     onClick = { ... }
 * ) { ... }
 *
 * // Medium components (cards, dialogs)
 * Card(
 *     shape = RiyadhAirShapes.medium,
 *     modifier = Modifier.fillMaxWidth()
 * ) { ... }
 *
 * // Large components (bottom sheets)
 * BottomSheet(
 *     shape = RiyadhAirShapes.large
 * ) { ... }
 *
 * // Full-screen containers
 * Surface(
 *     shape = RiyadhAirShapes.extraLarge,
 *     modifier = Modifier.fillMaxSize()
 * ) { ... }
 * ```
 *
 * **Shape Selection Logic:**
 * - Choose shape based on component size and prominence
 * - Maintain consistency within related component groups
 * - Consider visual hierarchy and user attention
 * - Balance aesthetics with functional requirements
 */
val RiyadhAirShapes = Shapes(
    // Extra Small - for small elements like chips, badges
    extraSmall = RoundedCornerShape(4.dp),
    
    // Small - for buttons, small cards
    small = RoundedCornerShape(8.dp),
    
    // Medium - for cards, dialogs
    medium = RoundedCornerShape(12.dp),
    
    // Large - for bottom sheets, large containers
    large = RoundedCornerShape(16.dp),
    
    // Extra Large - for full screen containers
    extraLarge = RoundedCornerShape(24.dp)
)
