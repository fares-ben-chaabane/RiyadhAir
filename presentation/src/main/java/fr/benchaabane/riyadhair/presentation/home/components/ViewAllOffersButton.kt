package fr.benchaabane.riyadhair.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource

import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import kotlinx.coroutines.delay

/**
 * An animated call-to-action button for viewing all available offers.
 *
 * This composable creates a prominent button that encourages users
 * to explore all available travel offers. It features smooth entrance
 * animations and integrates seamlessly with the home screen's staggered
 * animation sequence for an engaging user experience.
 *
 * **Button Features:**
 * - **Full Width Design**: Spans the entire available width for prominence
 * - **Animated Entrance**: Smooth slide-in animation with fade effect
 * - **Interactive State**: Supports enabled/disabled states for user feedback
 * - **Responsive Layout**: Adapts to different screen sizes and orientations
 * - **Accessibility**: Proper touch targets and content descriptions
 *
 * **Animation System:**
 * - **Staggered Timing**: 600ms delay for coordinated entrance with other sections
 * - **Slide Animation**: Vertical slide-in from bottom with fade effect
 * - **Smooth Transitions**: Optimized animation performance
 * - **Visual Hierarchy**: Creates engaging user experience
 *
 * **Layout Structure:**
 * - **Button Container**: Full-width button with proper padding
 * - **Text Content**: Localized "View All" text with consistent styling
 * - **Responsive Design**: Adapts to different content lengths
 * - **Material Design**: Follows Material Design 3 button guidelines
 *
 * **User Experience Features:**
 * - **Visual Appeal**: Engaging animations and visual design
 * - **Clear Call-to-Action**: Prominent button for user engagement
 * - **Interactive Feedback**: Proper button states and touch responses
 * - **Consistent Styling**: Integrates with RiyadhAir design system
 * - **Accessibility**: Proper touch targets and screen reader support
 *
 * **Business Logic:**
 * - **Offer Discovery**: Drives users toward comprehensive offer browsing
 * - **User Engagement**: Encourages exploration of all available options
 * - **Conversion Focus**: Primary call-to-action for offer exploration
 * - **Navigation Support**: Facilitates user flow to offers section
 *
 * **State Management:**
 * - **Visibility Control**: Animated entrance with proper timing
 * - **Enabled State**: Supports disabled state for user feedback
 * - **Click Handling**: Proper callback management for user interactions
 * - **Animation State**: Manages entrance animation lifecycle
 *
 * **Design System Integration:**
 * - **Material Design**: Follows Material Design 3 button guidelines
 * - **Typography**: Consistent text styling and font weights
 * - **Spacing**: Standardized spacing values throughout
 * - **Theme Integration**: Proper Material Design 3 theming
 *
 * **Use Cases:**
 * - Home screen call-to-action
 * - Offer discovery encouragement
 * - User engagement and conversion
 * - Navigation facilitation
 * - Content exploration promotion
 *
 * @param onViewAllClick Callback when the button is tapped
 * @param modifier Modifier to apply to the button container
 * @param enabled Whether the button is interactive (default: true)
 */
@Composable
fun ViewAllOffersButton(
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600) // Delay for staggered animation
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 }
        ) + fadeIn()
    ) {
        Button(
            onClick = onViewAllClick,
            modifier = modifier.fillMaxWidth(),
            enabled = enabled
        ) {
            Text(stringResource(fr.benchaabane.riyadhair.presentation.R.string.view_all_text))
        }
    }
}

/**
 * Preview function for ViewAllOffersButton component.
 *
 * This preview demonstrates the ViewAllOffersButton with default
 * configuration, showing how the component appears in the design
 * system preview environment. It includes the complete button layout
 * with animation state and interactive elements.
 *
 * **Preview Configuration:**
 * - **Default State**: Uses enabled state for normal operation
 * - **Empty Callback**: Empty callback implementation for testing
 * - **Full Layout**: Displays complete button with all components
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Button Display**: Shows full-width button with proper styling
 * - **Text Content**: Displays "View All" text with consistent typography
 * - **Animation State**: Demonstrates entrance animation timing
 * - **Interactive Elements**: Shows button in enabled state
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Validation**: Ensures button renders correctly
 * - **User Experience**: Shows the complete button interface
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of button layout and styling
 * - **Component Validation**: Ensures button renders correctly
 * - **Theme Verification**: Confirms design system consistency
 * - **Interaction Testing**: Validates button behavior and appearance
 */
@Preview(showBackground = true)
@Composable
private fun ViewAllOffersButtonPreview() {
    RiyadhAirTheme {
        ViewAllOffersButton(
            onViewAllClick = {},
            modifier = Modifier
        )
    }
}
