package fr.benchaabane.riyadhair.designsystem.theme

import androidx.compose.ui.unit.dp

/**
 * RiyadhAir spacing system that provides consistent spacing values throughout the application.
 *
 * This object defines a comprehensive spacing scale based on an 8dp grid system,
 * ensuring consistent visual rhythm and proper component spacing. The spacing
 * system follows Material Design principles and provides both micro and macro
 * spacing options for various design needs.
 *
 * **Spacing Philosophy:**
 * - **8dp Grid System**: Based on Material Design's 8dp grid for consistency
 * - **Scalable Scale**: Progressive spacing values for different contexts
 * - **Component Integration**: Pre-defined spacing for common UI patterns
 * - **Accessibility**: Adequate spacing for touch targets and readability
 *
 * **Base Unit System:**
 * - **Base Unit**: 4dp (fundamental spacing unit)
 * - **Micro Spacing**: 4dp to 24dp for fine-tuned layouts
 * - **Macro Spacing**: 32dp to 48dp for larger layout sections
 * - **Component Spacing**: Pre-defined values for common UI patterns
 *
 * **Spacing Scale:**
 * - **xs (4dp)**: Minimal spacing, borders, dividers
 * - **sm (8dp)**: Small gaps, icon spacing, tight layouts
 * - **md (12dp)**: Medium spacing, content padding, form fields
 * - **lg (16dp)**: Standard padding, button padding, card margins
 * - **xl (20dp)**: Generous spacing, section margins, large components
 * - **xxl (24dp)**: Section spacing, major component separation
 * - **xxxl (32dp)**: Screen margins, major section separation
 * - **huge (40dp)**: Large screen margins, hero section spacing
 * - **massive (48dp)**: Maximum spacing, full-screen margins
 *
 * **Usage Guidelines:**
 * - Use micro spacing for fine-tuned component layouts
 * - Use macro spacing for major layout sections
 * - Maintain consistent spacing within related components
 * - Consider touch target sizes (minimum 48dp) for interactive elements
 * - Use component-specific spacing for common UI patterns
 *
 * **Component-Specific Spacing:**
 * - **Button Padding**: Horizontal lg, vertical md for optimal touch targets
 * - **Card Padding**: lg for comfortable content breathing room
 * - **Screen Padding**: lg for consistent screen edge margins
 * - **Section Spacing**: xxl for clear content separation
 * - **Item Spacing**: md for list and grid item separation
 *
 * **Examples:**
 * ```kotlin
 * // Component padding
 * Card(
 *     modifier = Modifier.padding(RiyadhAirSpacing.cardPadding)
 * ) { ... }
 *
 * // Layout spacing
 * Column(
 *     verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.itemSpacing)
 * ) { ... }
 *
 * // Screen margins
 * Surface(
 *     modifier = Modifier.padding(RiyadhAirSpacing.screenPadding)
 * ) { ... }
 * ```
 */
object RiyadhAirSpacing {
    // Base spacing unit (4dp)
    val unit = 4.dp
    
    // Micro spacing
    val xs = 4.dp    // 1 unit
    val sm = 8.dp    // 2 units
    val md = 12.dp   // 3 units
    val lg = 16.dp   // 4 units
    val xl = 20.dp   // 5 units
    val xxl = 24.dp  // 6 units
    
    // Macro spacing
    val xxxl = 32.dp  // 8 units
    val huge = 40.dp  // 10 units
    val massive = 48.dp // 12 units
    
    // Component-specific spacing
    val buttonPaddingHorizontal = lg
    val buttonPaddingVertical = md
    val cardPadding = lg
    val screenPadding = lg
    val sectionSpacing = xxl
    val itemSpacing = md
}
