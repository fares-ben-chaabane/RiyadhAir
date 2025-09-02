package fr.benchaabane.riyadhair.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import kotlinx.coroutines.delay

/**
 * A marketing section component for promoting offers and encouraging user engagement.
 *
 * This composable creates an engaging marketing section that showcases
 * available offers with animated entrance effects and interactive elements.
 * It features a compelling headline, offer count information, and a
 * call-to-action button to view all available offers.
 *
 * **Section Features:**
 * - **Animated Entrance**: Smooth slide-in animation with fade effect
 * - **Marketing Headline**: Compelling text to attract user attention
 * - **Offer Count Display**: Dynamic count of available offers
 * - **Interactive CTA**: Clickable "View All" button for user engagement
 * - **Responsive Layout**: Adapts to different screen sizes and content
 *
 * **Animation System:**
 * - **Staggered Timing**: 300ms delay for coordinated entrance with other sections
 * - **Slide Animation**: Vertical slide-in from bottom with fade effect
 * - **Smooth Transitions**: Optimized animation performance
 * - **Visual Hierarchy**: Creates engaging user experience
 *
 * **Layout Structure:**
 * - **Section Header**: Clear title for the offers section
 * - **Main Message**: Large headline with "View All" action button
 * - **Offer Count**: Subtitle showing number of available offers
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **User Experience Features:**
 * - **Visual Appeal**: Engaging animations and visual design
 * - **Clear Call-to-Action**: Prominent "View All" button for user engagement
 * - **Information Hierarchy**: Well-organized content structure
 * - **Interactive Elements**: Clickable elements with proper feedback
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Business Logic:**
 * - **Offer Promotion**: Highlights available travel offers
 * - **User Engagement**: Encourages exploration of all offers
 * - **Dynamic Content**: Shows real-time offer count
 * - **Conversion Focus**: Drives users toward offer browsing
 *
 * **Content Management:**
 * - **Localized Text**: Uses string resources for internationalization
 * - **Pluralization**: Proper grammar for different offer counts
 * - **Dynamic Content**: Adapts to actual offer availability
 * - **Consistent Messaging**: Maintains brand voice and tone
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Royal Purple for CTA)
 * - **Typography**: Consistent text styles and font weights
 * - **Spacing**: Standardized spacing values throughout
 * - **Theme Integration**: Proper Material Design 3 theming
 *
 * **Use Cases:**
 * - Home screen marketing section
 * - Offer promotion and discovery
 * - User engagement and conversion
 * - Content marketing display
 * - Call-to-action presentation
 *
 * @param modifier Modifier to apply to the marketing section container
 * @param offerCount The number of available offers to display
 * @param onViewAllClick Callback when the "View All" button is tapped
 */
@Composable
fun MarketingSection(
    modifier: Modifier = Modifier,
    offerCount: Int = 0,
    onViewAllClick: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300) // Slight delay for staggered animation
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 }
        ) + fadeIn()
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
        ) {
            // Section header
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.offers_location),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Main marketing message with "Voir tout" link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.discover_best_offers_text),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = MaterialTheme.typography.headlineMedium.lineHeight,
                    modifier = Modifier.weight(1f)
                )
                
                // "Voir tout" clickable text
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.view_all),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = RiyadhAirColors.RoyalPurple,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(start = RiyadhAirSpacing.md).clickable { onViewAllClick() }
                )
            }

            // Subtitle with offer count
            Text(
                text = pluralStringResource(fr.benchaabane.riyadhair.presentation.R.plurals.offers_disclaimer, count = offerCount, offerCount),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Preview function for MarketingSection component.
 *
 * This preview demonstrates the MarketingSection with sample offer
 * count data, showing how the component appears in the design system
 * preview environment. It includes the complete marketing layout
 * with all text elements and interactive components.
 *
 * **Preview Configuration:**
 * - **Sample Offer Count**: Uses 3 offers for testing pluralization
 * - **Full Layout**: Displays complete section with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Section Header**: Shows "Offers Location" title
 * - **Marketing Headline**: Displays "Discover Best Offers" message
 * - **View All Button**: Shows interactive "View All" call-to-action
 * - **Offer Count**: Demonstrates pluralization with 3 offers
 * - **Responsive Design**: Shows how the section adapts to content
 * - **Visual Hierarchy**: Displays proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete marketing interface
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of section layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 * - **Content Testing**: Validates text display and pluralization
 */
@Preview(showBackground = true)
@Composable
private fun MarketingSectionPreview() {
    RiyadhAirTheme {
        MarketingSection(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            offerCount = 3
        )
    }
}
