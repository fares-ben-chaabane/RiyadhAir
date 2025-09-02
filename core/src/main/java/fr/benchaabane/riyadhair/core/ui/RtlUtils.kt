package fr.benchaabane.riyadhair.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/**
 * Checks if the current layout direction is Right-to-Left (RTL).
 *
 * This composable function determines the current layout direction
 * by accessing the LocalLayoutDirection composition local.
 * It's useful for implementing RTL-aware UI components that need
 * to adapt their layout based on the user's language preferences.
 *
 * **Usage:**
 * ```kotlin
 * @Composable
 * fun MyComponent() {
 *     if (isRtl()) {
 *         // RTL-specific layout logic
 *         Text(text = "مرحبا", textAlign = TextAlign.End)
 *     } else {
 *         // LTR-specific layout logic
 *         Text(text = "Hello", textAlign = TextAlign.Start)
 *     }
 * }
 * ```
 *
 * @return `true` if the layout direction is RTL, `false` if it's LTR
 */
@Composable
fun isRtl(): Boolean {
    return LocalLayoutDirection.current == LayoutDirection.Rtl
}

/**
 * Returns a value based on the current layout direction.
 *
 * This utility function provides a convenient way to choose between
 * two values based on whether the current layout is RTL or LTR.
 * It's commonly used for positioning, alignment, and other layout
 * decisions that need to adapt to different text directions.
 *
 * **Usage Examples:**
 * ```kotlin
 * // For positioning
 * val startPadding = rtlAware(16.dp, 0.dp)
 * val endPadding = rtlAware(0.dp, 16.dp)
 *
 * // For alignment
 * val textAlign = rtlAware(TextAlign.Start, TextAlign.End)
 *
 * // For margins
 * val marginStart = rtlAware(8.dp, 0.dp)
 * val marginEnd = rtlAware(0.dp, 8.dp)
 * ```
 *
 * @param T The type of values to choose between
 * @param ltrValue The value to use for Left-to-Right layouts
 * @param rtlValue The value to use for Right-to-Left layouts
 * @return The appropriate value based on the current layout direction
 */
@Composable
fun <T> rtlAware(ltrValue: T, rtlValue: T): T {
    return if (isRtl()) rtlValue else ltrValue
}

