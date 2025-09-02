package fr.benchaabane.riyadhair.designsystem.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * Defines the visual variants for RiyadhAir cards.
 *
 * Each variant provides a different visual style and emphasis level
 * to accommodate various design contexts and user interface needs.
 *
 * **Variant Styles:**
 * - **Filled**: Standard card with surface color background
 * - **Elevated**: Card with shadow for depth and prominence
 * - **Outlined**: Card with border for subtle definition
 */
enum class RiyadhAirCardVariant {
    Filled, Elevated, Outlined
}

/**
 * Defines the size categories for RiyadhAir cards.
 *
 * Each size provides different padding and spacing to accommodate
 * various content types and layout requirements.
 *
 * **Size Specifications:**
 * - **Small**: Compact padding (md) for minimal content
 * - **Medium**: Standard padding (lg) for typical content
 * - **Large**: Generous padding (xl) for rich content
 */
enum class RiyadhAirCardSize {
    Small, Medium, Large
}

/**
 * A versatile card component that follows RiyadhAir design system guidelines.
 *
 * This component provides consistent card styling with multiple variants
 * and sizes. It supports both interactive (clickable) and non-interactive
 * modes, making it suitable for various use cases throughout the application.
 *
 * **Features:**
 * - **Multiple Variants**: Filled, Elevated, and Outlined styles
 * - **Flexible Sizing**: Small, Medium, and Large size options
 * - **Interactive Support**: Optional click handling for interactive cards
 * - **Consistent Spacing**: Automatic padding based on size selection
 * - **Material Design**: Follows Material 3 design principles
 *
 * **Variant Details:**
 * - **Filled**: Uses surface color with standard elevation
 * - **Elevated**: Adds shadow with size-dependent elevation (2dp, 4dp, 6dp)
 * - **Outlined**: Adds 1dp border with surface color background
 *
 * **Size Specifications:**
 * - **Small**: RiyadhAirSpacing.md padding
 * - **Medium**: RiyadhAirSpacing.lg padding (default)
 * - **Large**: RiyadhAirSpacing.xl padding
 *
 * **Usage Examples:**
 * ```kotlin
 * // Basic filled card
 * RiyadhAirCard {
 *     Text("Simple content")
 * }
 *
 * // Interactive elevated card
 * RiyadhAirCard(
 *     variant = RiyadhAirCardVariant.Elevated,
 *     size = RiyadhAirCardSize.Large,
 *     onClick = { /* Handle click */ }
 * ) {
 *     Text("Clickable content")
 * }
 *
 * // Outlined card for subtle emphasis
 * RiyadhAirCard(
 *     variant = RiyadhAirCardVariant.Outlined,
 *     size = RiyadhAirCardSize.Small
 * ) {
 *     Text("Compact outlined content")
 * }
 * ```
 *
 * **Design Considerations:**
 * - Cards automatically adapt their elevation based on variant and size
 * - Content is wrapped in a Column with appropriate padding
 * - Colors automatically adapt to the current theme
 * - Interactive cards provide proper click feedback
 *
 * @param modifier Modifier to apply to the card
 * @param variant The visual style variant of the card
 * @param size The size category that determines padding
 * @param onClick Optional click handler for interactive cards
 * @param content The content to display within the card
 */
@Composable
fun RiyadhAirCard(
    modifier: Modifier = Modifier,
    variant: RiyadhAirCardVariant = RiyadhAirCardVariant.Filled,
    size: RiyadhAirCardSize = RiyadhAirCardSize.Medium,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val contentPadding = when (size) {
        RiyadhAirCardSize.Small -> RiyadhAirSpacing.md
        RiyadhAirCardSize.Medium -> RiyadhAirSpacing.lg
        RiyadhAirCardSize.Large -> RiyadhAirSpacing.xl
    }
    
    when (variant) {
        RiyadhAirCardVariant.Filled -> {
            Card(
                modifier = modifier,
                onClick = onClick ?: {},
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Column(
                    modifier = Modifier.padding(contentPadding),
                    content = content
                )
            }
        }
        
        RiyadhAirCardVariant.Elevated -> {
            ElevatedCard(
                modifier = modifier,
                onClick = onClick ?: {},
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = when (size) {
                        RiyadhAirCardSize.Small -> 2.dp
                        RiyadhAirCardSize.Medium -> 4.dp
                        RiyadhAirCardSize.Large -> 6.dp
                    }
                )
            ) {
                Column(
                    modifier = Modifier.padding(contentPadding),
                    content = content
                )
            }
        }
        
        RiyadhAirCardVariant.Outlined -> {
            OutlinedCard(
                modifier = modifier,
                onClick = onClick ?: {},
                colors = CardDefaults.outlinedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                border = CardDefaults.outlinedCardBorder().copy(
                    width = 1.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(contentPadding),
                    content = content
                )
            }
        }
    }
}

/**
 * A specialized card component for displaying flight information.
 *
 * This component provides a pre-configured layout specifically designed
 * for flight details, including origin/destination, timing, airline,
 * and pricing information. It uses the elevated card variant for
 * visual prominence and includes proper spacing and typography.
 *
 * **Flight Information Display:**
 * - **Route**: Origin → Destination with arrow indicator
 * - **Timing**: Departure and arrival times
 * - **Airline**: Operating airline name
 * - **Pricing**: Cost per person with label
 *
 * **Layout Structure:**
 * - **Left Side**: Route, timing, and airline information
 * - **Right Side**: Pricing information with per-person label
 * - **Typography**: Hierarchical text styles for information hierarchy
 * - **Colors**: Theme-aware colors for different information types
 *
 * **Usage Example:**
 * ```kotlin
 * FlightCard(
 *     origin = "RUH",
 *     destination = "DXB",
 *     departureTime = "10:30",
 *     arrivalTime = "13:45",
 *     airline = "RiyadhAir",
 *     price = "$299",
 *     onClick = { /* Navigate to flight details */ }
 * )
 * ```
 *
 * **Design Features:**
 * - Uses elevated card variant for prominence
 * - Primary color for route information
 * - Secondary color for pricing
 * - Variant color for supporting text
 * - Proper spacing between information elements
 *
 * @param origin The departure airport code
 * @param destination The arrival airport code
 * @param departureTime The departure time string
 * @param arrivalTime The arrival time string
 * @param airline The operating airline name
 * @param price The flight price string
 * @param modifier Modifier to apply to the card
 * @param onClick Optional click handler for navigation
 */
@Composable
fun FlightCard(
    origin: String,
    destination: String,
    departureTime: String,
    arrivalTime: String,
    airline: String,
    price: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    RiyadhAirCard(
        modifier = modifier,
        variant = RiyadhAirCardVariant.Elevated,
        onClick = onClick
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$origin → $destination",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(vertical = RiyadhAirSpacing.xs))
                Text(
                    text = "$departureTime - $arrivalTime",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = airline,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "per person",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Preview function for RiyadhAirCard components.
 *
 * This preview demonstrates all card variants and the specialized
 * FlightCard component. It provides a comprehensive view of the
 * design system's card components for designers and developers.
 *
 * **Preview Content:**
 * - **Filled Card**: Standard card with surface color
 * - **Elevated Card**: Card with shadow and depth
 * - **Outlined Card**: Card with border definition
 * - **Flight Card**: Specialized flight information display
 *
 * **Layout:**
 * - Vertical arrangement with consistent spacing
 * - Applied padding for visual separation
 * - Uses RiyadhAir theme for consistent styling
 * - Demonstrates different content types and layouts
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirCardPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            RiyadhAirCard {
                Text("Filled Card", style = MaterialTheme.typography.titleMedium)
                Text("This is a filled card with content")
            }
            
            RiyadhAirCard(variant = RiyadhAirCardVariant.Elevated) {
                Text("Elevated Card", style = MaterialTheme.typography.titleMedium)
                Text("This is an elevated card with shadow")
            }
            
            RiyadhAirCard(variant = RiyadhAirCardVariant.Outlined) {
                Text("Outlined Card", style = MaterialTheme.typography.titleMedium)
                Text("This is an outlined card with border")
            }
            
            FlightCard(
                origin = "RUH",
                destination = "DXB",
                departureTime = "10:30",
                arrivalTime = "13:45",
                airline = "RiyadhAir",
                price = "$299"
            )
        }
    }
}