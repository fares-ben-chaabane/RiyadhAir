package fr.benchaabane.riyadhair.designsystem.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * Defines the available sizes for RiyadhAir buttons.
 *
 * Each size provides different dimensions and spacing to accommodate
 * various UI contexts and design requirements.
 *
 * **Size Specifications:**
 * - **Small**: Compact size for secondary actions or limited space
 * - **Medium**: Standard size for most primary actions
 * - **Large**: Prominent size for important call-to-action buttons
 */
enum class RiyadhAirButtonSize {
    Small, Medium, Large
}

/**
 * Defines the visual variants for RiyadhAir buttons.
 *
 * Each variant provides a different visual style and emphasis level
 * to guide users through the interface hierarchy.
 *
 * **Variant Styles:**
 * - **Primary**: High emphasis, used for main actions
 * - **Secondary**: Medium emphasis, used for supporting actions
 * - **Tertiary**: Low emphasis, used for subtle actions
 * - **Outlined**: Bordered style, used for alternative actions
 */
enum class RiyadhAirButtonVariant {
    Primary, Secondary, Tertiary, Outlined
}

/**
 * A customizable button component that follows RiyadhAir design system guidelines.
 *
 * This button provides consistent styling, sizing, and behavior across the
 * application. It supports various visual variants, sizes, and states to
 * accommodate different use cases and user interactions.
 *
 * **Features:**
 * - **Multiple Variants**: Primary, Secondary, Tertiary, and Outlined styles
 * - **Flexible Sizing**: Small, Medium, and Large size options
 * - **Icon Support**: Leading and trailing icons with automatic sizing
 * - **Loading State**: Built-in loading indicator with proper state management
 * - **Accessibility**: Proper content descriptions and state handling
 *
 * **Size Specifications:**
 * - **Small**: 36dp height, compact padding, labelMedium typography
 * - **Medium**: 48dp height, standard padding, labelLarge typography
 * - **Large**: 56dp height, generous padding, titleMedium typography
 *
 * **Usage Examples:**
 * ```kotlin
 * // Primary action button
 * RiyadhAirButton(
 *     text = "Book Flight",
 *     onClick = { /* Handle booking */ },
 *     variant = RiyadhAirButtonVariant.Primary,
 *     size = RiyadhAirButtonSize.Large
 * )
 *
 * // Secondary action with icon
 * RiyadhAirButton(
 *     text = "Search",
 *     onClick = { /* Handle search */ },
 *     variant = RiyadhAirButtonVariant.Secondary,
 *     leadingIcon = Icons.Default.Search
 * )
 *
 * // Loading state
 * RiyadhAirButton(
 *     text = "Processing",
 *     onClick = { /* No-op during loading */ },
 *     loading = true
 * )
 * ```
 *
 * **State Management:**
 * - Automatically disables interaction during loading
 * - Maintains consistent sizing across all states
 * - Provides visual feedback for different states
 *
 * @param text The text to display on the button
 * @param onClick The action to perform when the button is clicked
 * @param modifier Modifier to apply to the button
 * @param variant The visual style variant of the button
 * @param size The size category of the button
 * @param enabled Whether the button is interactive
 * @param loading Whether to show a loading indicator
 * @param leadingIcon Optional icon to display before the text
 * @param trailingIcon Optional icon to display after the text
 */
@Composable
fun RiyadhAirButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: RiyadhAirButtonVariant = RiyadhAirButtonVariant.Primary,
    size: RiyadhAirButtonSize = RiyadhAirButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val height = when (size) {
        RiyadhAirButtonSize.Small -> 36.dp
        RiyadhAirButtonSize.Medium -> 48.dp
        RiyadhAirButtonSize.Large -> 56.dp
    }

    val contentPadding = when (size) {
        RiyadhAirButtonSize.Small -> PaddingValues(
            horizontal = RiyadhAirSpacing.md,
            vertical = RiyadhAirSpacing.xs
        )

        RiyadhAirButtonSize.Medium -> PaddingValues(
            horizontal = RiyadhAirSpacing.lg,
            vertical = RiyadhAirSpacing.sm
        )

        RiyadhAirButtonSize.Large -> PaddingValues(
            horizontal = RiyadhAirSpacing.xl,
            vertical = RiyadhAirSpacing.md
        )
    }

    val textStyle = when (size) {
        RiyadhAirButtonSize.Small -> MaterialTheme.typography.labelMedium
        RiyadhAirButtonSize.Medium -> MaterialTheme.typography.labelLarge
        RiyadhAirButtonSize.Large -> MaterialTheme.typography.titleMedium
    }

    val iconSize = when (size) {
        RiyadhAirButtonSize.Small -> 16.dp
        RiyadhAirButtonSize.Medium -> 20.dp
        RiyadhAirButtonSize.Large -> 24.dp
    }

    when (variant) {
        RiyadhAirButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = modifier.height(height),
                enabled = enabled && !loading,
                contentPadding = contentPadding
            ) {
                ButtonContent(
                    text = text,
                    textStyle = textStyle,
                    loading = loading,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    iconSize = iconSize
                )
            }
        }

        RiyadhAirButtonVariant.Secondary -> {
            FilledTonalButton(
                onClick = onClick,
                modifier = modifier.height(height),
                enabled = enabled && !loading,
                contentPadding = contentPadding
            ) {
                ButtonContent(
                    text = text,
                    textStyle = textStyle,
                    loading = loading,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    iconSize = iconSize
                )
            }
        }

        RiyadhAirButtonVariant.Tertiary -> {
            TextButton(
                onClick = onClick,
                modifier = modifier.height(height),
                enabled = enabled && !loading,
                contentPadding = contentPadding
            ) {
                ButtonContent(
                    text = text,
                    textStyle = textStyle,
                    loading = loading,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    iconSize = iconSize
                )
            }
        }

        RiyadhAirButtonVariant.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier.height(height),
                enabled = enabled && !loading,
                contentPadding = contentPadding
            ) {
                ButtonContent(
                    text = text,
                    textStyle = textStyle,
                    loading = loading,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    iconSize = iconSize
                )
            }
        }
    }
}

/**
 * Internal component that renders the button content based on its state.
 *
 * This function handles the rendering logic for button content, including
 * loading states, icons, and text. It ensures consistent layout and
 * proper spacing regardless of the button variant or size.
 *
 * **Content Rendering:**
 * - **Loading State**: Shows a circular progress indicator
 * - **Normal State**: Displays icons and text with proper spacing
 * - **Icon Handling**: Conditionally renders leading and trailing icons
 * - **Spacing**: Applies consistent spacing between elements
 *
 * **Layout Behavior:**
 * - Icons are sized according to the button size
 * - Text uses the provided text style with medium font weight
 * - Spacing follows the design system's spacing scale
 * - Content is centered within the button bounds
 *
 * @param text The text to display
 * @param textStyle The typography style for the text
 * @param loading Whether to show loading state
 * @param leadingIcon Optional icon to display before text
 * @param trailingIcon Optional icon to display after text
 * @param iconSize The size for both leading and trailing icons
 */
@Composable
private fun ButtonContent(
    text: String,
    textStyle: TextStyle,
    loading: Boolean,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
    iconSize: Dp
) {
    if (loading) {
        CircularProgressIndicator(
            modifier = Modifier.size(iconSize),
            strokeWidth = 2.dp
        )
    } else {
        leadingIcon?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.padding(end = RiyadhAirSpacing.xs))
        }

        Text(
            text = text,
            style = textStyle.copy(fontWeight = FontWeight.Medium)
        )

        trailingIcon?.let { icon ->
            Spacer(modifier = Modifier.padding(start = RiyadhAirSpacing.xs))
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

/**
 * Preview function for RiyadhAirButton component.
 *
 * This preview demonstrates all button variants and states in the
 * RiyadhAir design system, providing a comprehensive view for
 * designers and developers during the design process.
 *
 * **Preview Content:**
 * - Primary button (default variant)
 * - Secondary button
 * - Outlined button
 * - Tertiary (text) button
 * - Loading state button
 *
 * **Layout:**
 * - Vertical arrangement with consistent spacing
 * - Applied padding for visual separation
 * - Uses RiyadhAir theme for consistent styling
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirButtonPreview() {
    RiyadhAirTheme {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(
                RiyadhAirSpacing.sm
            )
        ) {
            RiyadhAirButton(text = "Primary Button", onClick = {})
            RiyadhAirButton(
                text = "Secondary Button",
                onClick = {},
                variant = RiyadhAirButtonVariant.Secondary
            )
            RiyadhAirButton(
                text = "Outlined Button",
                onClick = {},
                variant = RiyadhAirButtonVariant.Outlined
            )
            RiyadhAirButton(
                text = "Text Button",
                onClick = {},
                variant = RiyadhAirButtonVariant.Tertiary
            )
            RiyadhAirButton(text = "Loading", onClick = {}, loading = true)
        }
    }
}
