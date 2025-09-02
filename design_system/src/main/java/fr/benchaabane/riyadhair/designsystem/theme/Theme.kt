package fr.benchaabane.riyadhair.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * RiyadhAir theme composable that provides consistent design system styling.
 *
 * This function wraps the Material 3 theme with RiyadhAir-specific design tokens,
 * including custom colors, typography, and shapes. It ensures consistent
 * appearance across all components in the application.
 *
 * **Design System Integration:**
 * - **Colors**: RiyadhAir brand colors with light/dark theme support
 * - **Typography**: Custom type scale optimized for readability
 * - **Shapes**: Consistent corner radius and elevation patterns
 * - **Material 3**: Built on Material 3 design principles
 *
 * **Theme Features:**
 * - **Dynamic Theme Switching**: Automatic light/dark theme based on parameter
 * - **Brand Consistency**: All components use unified design tokens
 * - **Accessibility**: Proper contrast ratios and readable typography
 * - **Performance**: Efficient theme composition and updates
 *
 * **Usage Pattern:**
 * ```kotlin
 * @Composable
 * fun App() {
 *     RiyadhAirTheme {
 *         // All child components automatically use RiyadhAir design system
 *         Surface(
 *             modifier = Modifier.fillMaxSize(),
 *             color = MaterialTheme.colorScheme.background
 *         ) {
 *             // App content
 *         }
 *     }
 * }
 * ```
 *
 * **Theme Inheritance:**
 * - Child components automatically inherit theme values
 * - Access design tokens through MaterialTheme.colorScheme, etc.
 * - Consistent spacing, colors, and typography throughout
 * - Automatic theme-aware component styling
 *
 * **Design Token Access:**
 * ```kotlin
 * // Colors
 * val primaryColor = MaterialTheme.colorScheme.primary
 * val surfaceColor = MaterialTheme.colorScheme.surface
 * 
 * // Typography
 * val headlineStyle = MaterialTheme.typography.headlineMedium
 * val bodyStyle = MaterialTheme.typography.bodyLarge
 * 
 * // Shapes
 * val cardShape = MaterialTheme.shapes.medium
 * val buttonShape = MaterialTheme.shapes.small
 * ```
 *
 * @param darkTheme Whether to use the dark theme variant
 * @param content The composable content that will use this theme
 */
@Composable
fun RiyadhAirTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) RiyadhAirDarkColors else RiyadhAirLightColors,
        typography = RiyadhAirTypography,
        shapes = RiyadhAirShapes,
        content = content
    )
}

