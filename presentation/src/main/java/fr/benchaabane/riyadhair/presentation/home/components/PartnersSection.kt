package fr.benchaabane.riyadhair.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.components.images.RiyadhAirAsyncImage
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.models.PartnerCategory
import fr.benchaabane.riyadhair.presentation.partners.PartnerUiModel
import kotlinx.coroutines.delay

/**
 * A comprehensive section component for displaying business partners.
 *
 * This composable provides a visually appealing showcase of business
 * partners with animated entrance effects and interactive partner cards.
 * It features a horizontal scrolling carousel of partner information
 * with staggered animation timing for optimal user experience.
 *
 * **Section Features:**
 * - **Animated Entrance**: Staggered slide-in animation with fade effect
 * - **Section Header**: Clear title and descriptive subtitle
 * - **Partner Carousel**: Horizontal scrolling list of partner cards
 * - **Interactive Cards**: Clickable partner cards with detailed information
 * - **Responsive Layout**: Adapts to different screen sizes and content
 *
 * **Animation System:**
 * - **Staggered Timing**: 900ms delay for coordinated entrance after offers
 * - **Slide Animation**: Vertical slide-in from bottom with fade effect
 * - **Smooth Transitions**: Optimized animation performance
 * - **Visual Hierarchy**: Creates engaging user experience
 *
 * **Layout Structure:**
 * - **Header Section**: Title and subtitle with proper spacing
 * - **Carousel Container**: Horizontal scrolling partner list
 * - **Card Layout**: Consistent card sizing and spacing
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **User Experience:**
 * - **Visual Appeal**: Engaging animations and visual design
 * - **Easy Navigation**: Horizontal scrolling for partner browsing
 * - **Clear Information**: Well-organized partner details
 * - **Interactive Elements**: Clickable cards for partner exploration
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Business Context:**
 * - **Partner Showcase**: Highlights business collaborations
 * - **Brand Recognition**: Establishes partner brand presence
 * - **User Benefits**: Shows available partner discounts and services
 * - **Trust Building**: Demonstrates business relationships
 *
 * **Use Cases:**
 * - Home screen partner showcase
 * - Business collaboration display
 * - Partner benefits presentation
 * - Brand partnership marketing
 * - User engagement and conversion
 *
 * @param partners List of partner information to display
 * @param modifier Modifier to apply to the partners section container
 * @param onPartnerClick Callback when a partner card is selected
 */
@Composable
fun PartnersSection(
    partners: List<PartnerUiModel>,
    modifier: Modifier = Modifier,
    onPartnerClick: (PartnerUiModel) -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(900) // Delay for staggered animation after offers
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
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            // Section header
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.our_partners),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = RiyadhAirSpacing.lg)
            )
            
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.go_further_text),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = RiyadhAirSpacing.lg)
            )

            // Partners horizontal carousel
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = RiyadhAirSpacing.lg),
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                items(partners) { partner ->
                    PartnerCard(
                        partner = partner,
                        onClick = { onPartnerClick(partner) },
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }
    }
}

/**
 * An interactive card component for displaying individual partner information.
 *
 * This composable creates a visually rich partner presentation with
 * background images, gradient overlays, and well-organized content layout.
 * It provides a compelling visual experience that encourages user
 * engagement and partner exploration.
 *
 * **Visual Design Features:**
 * - **Background Image**: High-quality partner logo or brand image
 * - **Gradient Overlay**: Dark gradient for text readability over images
 * - **Card Elevation**: Material Design elevation for depth perception
 * - **Square Aspect Ratio**: 1:1 ratio for consistent visual presentation
 * - **Rounded Corners**: Consistent with RiyadhAir design system
 *
 * **Content Layout:**
 * - **Discount Badge**: Prominent display of partner benefits at the top
 * - **Partner Information**: Name and category at the bottom
 * - **Visual Hierarchy**: Clear distinction between different information levels
 * - **Text Contrast**: White text with proper opacity for readability
 * - **Overflow Handling**: Ellipsis for long text content
 *
 * **Interactive Features:**
 * - **Clickable Card**: Entire card is interactive for user engagement
 * - **Touch Feedback**: Material Design ripple effects
 * - **Accessibility**: Proper content descriptions for screen readers
 * - **Touch Targets**: Adequate sizing for mobile interaction
 *
 * **Performance Optimizations:**
 * - **Async Image Loading**: Efficient image loading with Coil
 * - **Content Scale**: Crop scaling for optimal image display
 * - **Memory Management**: Proper image caching and disposal
 * - **Efficient Rendering**: Optimized for smooth scrolling performance
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Gold for discount badges)
 * - **Typography**: Consistent text styles and font weights
 * - **Shapes**: Unified shape system for rounded corners
 * - **Spacing**: Standardized spacing values throughout
 *
 * **Business Logic:**
 * - **Discount Display**: Shows available partner benefits
 * - **Category Information**: Provides partner type classification
 * - **Brand Recognition**: Establishes partner visual identity
 * - **User Engagement**: Encourages partner exploration and interaction
 *
 * **Use Cases:**
 * - Partner showcase displays
 * - Business collaboration presentation
 * - Partner benefits highlighting
 * - Brand partnership marketing
 * - User engagement and conversion
 *
 * @param partner The partner information to display in the card
 * @param onClick Callback when the partner card is tapped
 * @param modifier Modifier to apply to the partner card container
 */
@Composable
private fun PartnerCard(
    partner: PartnerUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f), // Square aspect ratio
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            RiyadhAirAsyncImage(
                imageUrl = partner.coverImage,
                contentDescription = "Image of ${partner.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                shape = RiyadhAirShapes.large
            )

            // Gradient overlay for better text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            // Content overlay
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(RiyadhAirSpacing.md),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Discount badge (if available)
                if (partner.discountInfo.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = RiyadhAirColors.Gold,
                                shape = RiyadhAirShapes.small
                            )
                            .padding(
                                horizontal = RiyadhAirSpacing.sm,
                                vertical = RiyadhAirSpacing.xs
                            )
                    ) {
                        Text(
                            text = partner.discountInfo,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                } else {
                    // Empty space to maintain layout consistency
                    Box(modifier = Modifier.size(0.dp))
                }

                // Partner info
                Column(
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    // Partner name
                    Text(
                        text = partner.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    // Category
                    Text(
                        text = partner.categoryName,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

/**
 * Preview function for PartnersSection component.
 *
 * This preview demonstrates the PartnersSection with sample partner
 * data, showing how the component appears in the design system
 * preview environment. It includes multiple partner cards to showcase
 * the carousel layout and visual design.
 *
 * **Preview Configuration:**
 * - **Sample Partners**: Uses Hertz car rental as example partner
 * - **Multiple Cards**: Shows three partner cards for carousel effect
 * - **Full Layout**: Displays complete section with header and carousel
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Section Header**: Shows "Our Partners" title and subtitle
 * - **Partner Carousel**: Displays horizontal scrolling partner list
 * - **Partner Cards**: Shows individual partner cards with images
 * - **Discount Badges**: Demonstrates discount information display
 * - **Responsive Design**: Shows how the section adapts to content
 * - **Visual Hierarchy**: Displays proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete partners showcase interface
 *
 * **Sample Data:**
 * - **Partner Name**: Hertz (car rental company)
 * - **Category**: Car Rental partner type
 * - **Discount**: 15% off promotion
 * - **Image**: Placeholder image URL for testing
 */
@Preview(showBackground = true)
@Composable
private fun PartnersSectionPreview() {
    RiyadhAirTheme {
        PartnersSection(
            partners = listOf(
                PartnerUiModel(
                    name = "Hertz",
                    categoryName = PartnerCategory.CAR_RENTAL.displayName,
                    coverImage = "https://example.com/hertz.jpg",
                    discountInfo = "-15%",
                ),
                PartnerUiModel(
                    name = "Hertz",
                    categoryName = PartnerCategory.CAR_RENTAL.displayName,
                    coverImage = "https://example.com/hertz.jpg",
                    discountInfo = "-15%",
                ),
                PartnerUiModel(
                    name = "Hertz",
                    categoryName = PartnerCategory.CAR_RENTAL.displayName,
                    coverImage = "https://example.com/hertz.jpg",
                    discountInfo = "-15%",
                )
            ),
            modifier = Modifier.padding(vertical = RiyadhAirSpacing.lg)
        )
    }
}
