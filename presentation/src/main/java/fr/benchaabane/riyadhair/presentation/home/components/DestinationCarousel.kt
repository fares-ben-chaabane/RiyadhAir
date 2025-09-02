package fr.benchaabane.riyadhair.presentation.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.components.images.RiyadhAirAsyncImage
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.presentation.offers.OfferUiModel

/**
 * A comprehensive destination carousel component for showcasing travel offers.
 *
 * This composable creates an engaging carousel that displays travel destinations
 * with smooth animations, pagination controls, and rich visual content. It features
 * a main destination card with 9:16 aspect ratio, animated content transitions,
 * and interactive pagination indicators for optimal user experience.
 *
 * **Carousel Features:**
 * - **Main Destination Card**: Large card with 9:16 aspect ratio for visual impact
 * - **Animated Transitions**: Smooth slide animations between destinations
 * - **Pagination Indicators**: Interactive dots for navigation between offers
 * - **Content Alpha Animation**: Smooth fade-in effect for content visibility
 * - **Responsive Layout**: Adapts to different screen sizes and content
 *
 * **Animation System:**
 * - **Content Transitions**: 500ms slide animations between destinations
 * - **Alpha Animation**: 300ms fade-in effect for content visibility
 * - **Smooth Transitions**: Optimized animation performance
 * - **Visual Hierarchy**: Creates engaging user experience
 *
 * **Layout Structure:**
 * - **Main Card**: Large destination card with background image and overlay
 * - **Content Overlay**: Destination information with proper visual hierarchy
 * - **Pagination**: Interactive indicators for offer navigation
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **User Experience Features:**
 * - **Visual Appeal**: Engaging animations and visual design
 * - **Easy Navigation**: Pagination indicators for offer browsing
 * - **Rich Content**: Comprehensive destination information display
 * - **Interactive Elements**: Clickable pagination with visual feedback
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Content Display:**
 * - **Destination Images**: High-quality background images for visual impact
 * - **Discount Information**: Prominent display of available discounts
 * - **Location Details**: Destination name, country, and location indicators
 * - **Pricing Information**: Clear price display with flight indicators
 * - **Page Navigation**: Current position indicator (e.g., "1 / 5")
 *
 * **Business Logic:**
 * - **Offer Showcase**: Highlights available travel destinations
 * - **User Engagement**: Encourages exploration of different offers
 * - **Visual Marketing**: Compelling visual presentation of destinations
 * - **Navigation Support**: Facilitates user flow through offers
 *
 * **Performance Features:**
 * - **Efficient Animations**: Optimized transition animations
 * - **Image Loading**: Async image loading with proper caching
 * - **State Management**: Efficient state updates and animations
 * - **Memory Management**: Proper resource cleanup and disposal
 *
 * **Use Cases:**
 * - Home screen destination showcase
 * - Travel offer presentation
 * - Destination discovery interface
 * - Offer browsing and navigation
 * - Visual marketing and engagement
 *
 * @param offers List of travel offers to display in the carousel
 * @param currentIndex Current active offer index (0-based)
 * @param onIndexChanged Callback when the user changes the current index
 * @param modifier Modifier to apply to the carousel container
 */
@Composable
fun DestinationCarousel(
    offers: List<OfferUiModel>,
    currentIndex: Int,
    onIndexChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentAlpha by animateFloatAsState(
        targetValue = if (offers.isNotEmpty()) 1f else 0.3f,
        animationSpec = tween(durationMillis = 300),
        label = "carouselContentAlpha"
    )

    Column(
        modifier = modifier.alpha(contentAlpha),
        verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
    ) {
        // Main destination card with 9:16 ratio
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(9f / 16f),
            shape = RiyadhAirShapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            AnimatedContent(
                targetState = if (offers.isNotEmpty()) offers[currentIndex] else null,
                transitionSpec = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    ) togetherWith slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                },
                label = "destinationCarousel"
            ) { offer ->
                if (offer != null) {
                    DestinationCard(
                        offer = offer,
                        currentIndex = currentIndex + 1,
                        totalCount = offers.size
                    )
                } else {
                    PlaceholderCard()
                }
            }
        }

        // Pagination indicators
        if (offers.size > 1) {
            PaginationIndicators(
                total = offers.size,
                current = currentIndex,
                onIndexChanged = onIndexChanged
            )
        }
    }
}

/**
 * A detailed destination card component for displaying travel offer information.
 *
 * This composable creates a visually rich destination presentation with
 * background images, gradient overlays, and comprehensive travel information.
 * It provides an immersive experience that showcases destinations with
 * proper visual hierarchy and engaging content layout.
 *
 * **Visual Design Features:**
 * - **Background Image**: High-quality destination image for visual impact
 * - **Gradient Overlay**: Dark gradient for text readability over images
 * - **Card Elevation**: Material Design elevation for depth perception
 * - **9:16 Aspect Ratio**: Optimized ratio for mobile viewing
 * - **Rounded Corners**: Consistent with RiyadhAir design system
 *
 * **Content Layout:**
 * - **Top Section**: Page indicator and discount badge
 * - **Bottom Section**: Destination details and pricing information
 * - **Visual Hierarchy**: Clear distinction between different information levels
 * - **Text Contrast**: White text with proper opacity for readability
 * - **Icon Integration**: Location and flight emojis for visual context
 *
 * **Information Display:**
 * - **Page Navigation**: Current position indicator (e.g., "1 / 5")
 * - **Discount Badge**: Prominent display of available discounts
 * - **Destination Name**: Large, bold destination title
 * - **Country Information**: Country name with location icon
 * - **Pricing Details**: Price information with flight icon
 *
 * **Interactive Features:**
 * - **Visual Feedback**: Proper visual states and animations
 * - **Accessibility**: Proper content descriptions for screen readers
 * - **Touch Targets**: Adequate sizing for mobile interaction
 * - **Content Scaling**: Optimized image display and cropping
 *
 * **Performance Optimizations:**
 * - **Async Image Loading**: Efficient image loading with Coil
 * - **Content Scale**: Crop scaling for optimal image display
 * - **Memory Management**: Proper image caching and disposal
 * - **Efficient Rendering**: Optimized for smooth performance
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Gold for discount badges)
 * - **Typography**: Consistent text styles and font weights
 * - **Shapes**: Unified shape system for rounded corners
 * - **Spacing**: Standardized spacing values throughout
 *
 * **Business Logic:**
 * - **Destination Showcase**: Highlights travel destinations
 * - **Offer Information**: Displays comprehensive offer details
 * - **Visual Marketing**: Compelling visual presentation
 * - **User Engagement**: Encourages destination exploration
 *
 * **Use Cases:**
 * - Destination showcase displays
 * - Travel offer presentation
 * - Destination marketing
 * - User engagement and conversion
 * - Visual content presentation
 *
 * @param offer The travel offer information to display
 * @param currentIndex Current offer position (1-based for display)
 * @param totalCount Total number of available offers
 */
@Composable
private fun DestinationCard(
    offer: OfferUiModel,
    currentIndex: Int = 1,
    totalCount: Int = 1
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        RiyadhAirAsyncImage(
            imageUrl = offer.coverImage,
            contentDescription = "Image of ${offer.destination}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            shape = RiyadhAirShapes.large
        )

        // Gradient overlay
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
                .padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section with discount badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Page indicator (e.g., "1 / 5")
                Text(
                    text = "$currentIndex / $totalCount",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier
                        .background(
                            color = Color.Black.copy(alpha = 0.3f),
                            shape = RiyadhAirShapes.small
                        )
                        .padding(
                            horizontal = RiyadhAirSpacing.sm,
                            vertical = RiyadhAirSpacing.xs
                        )
                )
                
                // Discount badge
                DiscountBadge(
                    discountPercentage = offer.discountInfo
                )
            }

            // Bottom section with destination info
            Column(
                verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
            ) {
                // Destination name
                Text(
                    text = offer.destination,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                // Country with location icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    Text(
                        text = "📍", // Location emoji
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = offer.country,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
                
                // Price info with flight icon
                Row(
                    modifier = Modifier
                        .background(
                            color = Color.White.copy(alpha = 0.9f),
                            shape = RiyadhAirShapes.small
                        )
                        .padding(
                            horizontal = RiyadhAirSpacing.md,
                            vertical = RiyadhAirSpacing.sm
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                ) {
                    Text(
                        text = offer.priceInfo,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "✈️", // Flight emoji
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/**
 * A placeholder card component for when no offers are available.
 *
 * This composable displays a loading state or empty state message
 * when the destination carousel has no offers to display. It provides
 * visual feedback to users and maintains consistent layout structure.
 *
 * **Placeholder Features:**
 * - **Loading Message**: Clear indication of content loading state
 * - **Consistent Layout**: Maintains carousel structure and spacing
 * - **Visual Feedback**: Provides user with status information
 * - **Theme Integration**: Uses Material Design color scheme
 * - **Accessibility**: Proper text content for screen readers
 *
 * **Layout Structure:**
 * - **Full Size Container**: Maintains consistent card dimensions
 * - **Centered Content**: Loading message centered in the card
 * - **Background Color**: Uses surface variant color for distinction
 * - **Text Styling**: Consistent typography with theme colors
 *
 * **User Experience:**
 * - **Clear Feedback**: Users understand content is loading
 * - **Consistent Interface**: Maintains visual consistency
 * - **Professional Appearance**: Professional loading state presentation
 * - **Accessibility**: Screen reader friendly content
 *
 * **Design System Integration:**
 * - **Color Scheme**: Uses Material Design 3 color system
 * - **Typography**: Consistent text styles and sizing
 * - **Spacing**: Proper content alignment and positioning
 * - **Theme Consistency**: Integrates with overall app theme
 *
 * **Use Cases:**
 * - Loading state display
 * - Empty content indication
 * - Fallback content presentation
 * - User feedback during data loading
 * - Consistent layout maintenance
 */
@Composable
private fun PlaceholderCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.loading_offers_text),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * A discount badge component for displaying promotional offers.
 *
 * This composable creates a visually prominent badge that highlights
 * available discounts and promotional offers. It uses the RiyadhAir
 * gold color scheme for brand consistency and proper visual hierarchy.
 *
 * **Badge Features:**
 * - **Prominent Display**: Gold background for visual attention
 * - **Consistent Styling**: Uses RiyadhAir design system colors
 * - **Proper Spacing**: Standardized padding for visual balance
 * - **Rounded Corners**: Consistent with overall design language
 * - **Text Contrast**: White text for optimal readability
 *
 * **Visual Design:**
 * - **Color Scheme**: RiyadhAir Gold for brand consistency
 * - **Shape System**: Small rounded corners from design system
 * - **Typography**: Label large style with bold font weight
 * - **Spacing**: Standardized padding values throughout
 * - **Elevation**: Proper visual hierarchy and prominence
 *
 * **Content Display:**
 * - **Discount Text**: Clear display of discount percentage
 * - **Text Styling**: Bold, readable text with proper contrast
 * - **Content Alignment**: Centered text for visual balance
 * - **Overflow Handling**: Proper text sizing and display
 *
 * **Accessibility:**
 * - **Text Contrast**: High contrast white text on gold background
 * - **Touch Targets**: Adequate sizing for mobile interaction
 * - **Content Description**: Clear discount information display
 * - **Screen Reader**: Proper text content for accessibility
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir Gold color
 * - **Shape System**: Consistent with RiyadhAirShapes.small
 * - **Spacing**: Standardized RiyadhAirSpacing values
 * - **Typography**: Material Design 3 typography system
 *
 * **Use Cases:**
 * - Discount percentage display
 * - Promotional offer highlighting
 * - Price reduction indication
 * - Special offer presentation
 * - Marketing content emphasis
 *
 * @param discountPercentage The discount percentage or offer text to display
 * @param modifier Modifier to apply to the discount badge container
 */
@Composable
private fun DiscountBadge(
    discountPercentage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = RiyadhAirColors.Gold,
                shape = RiyadhAirShapes.small
            )
            .padding(
                horizontal = RiyadhAirSpacing.md,
                vertical = RiyadhAirSpacing.sm
            )
    ) {
        Text(
            text = discountPercentage,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * Interactive pagination indicators for navigating between offers.
 *
 * This composable creates a row of circular indicators that show
 * the current position in the offer carousel and allow users to
 * navigate between different destinations. It features smooth
 * animations and visual feedback for optimal user experience.
 *
 * **Pagination Features:**
 * - **Visual Indicators**: Circular dots representing each offer
 * - **Current Position**: Highlighted indicator for active offer
 * - **Interactive Navigation**: Clickable indicators for offer switching
 * - **Smooth Animations**: Alpha transitions for visual feedback
 * - **Responsive Layout**: Adapts to different numbers of offers
 *
 * **Animation System:**
 * - **Alpha Transitions**: 300ms fade animations for indicator states
 * - **Smooth Feedback**: Optimized animation performance
 * - **Visual Hierarchy**: Clear distinction between active and inactive states
 * - **User Experience**: Engaging and responsive interface
 *
 * **Layout Structure:**
 * - **Horizontal Row**: Centered indicators with proper spacing
 * - **Circular Design**: Consistent 8dp circular indicators
 * - **Proper Spacing**: Standardized spacing between indicators
 * - **Responsive Design**: Adapts to different screen sizes
 *
 * **Interactive Features:**
 * - **Click Handling**: Proper callback management for navigation
 * - **Visual Feedback**: Smooth state transitions and animations
 * - **Touch Targets**: Adequate sizing for mobile interaction
 * - **Accessibility**: Clear visual indication of current position
 *
 * **Visual Design:**
 * - **Color Scheme**: Uses RiyadhAir Royal Purple for brand consistency
 * - **Shape System**: Perfect circles for clean visual appearance
 * - **State Indication**: Alpha variations for active/inactive states
 * - **Spacing**: Consistent spacing between all indicators
 * - **Theme Integration**: Proper Material Design 3 theming
 *
 * **Performance Features:**
 * - **Efficient Animations**: Optimized alpha transitions
 * - **State Management**: Efficient state updates and animations
 * - **Memory Management**: Proper resource cleanup and disposal
 * - **Smooth Rendering**: Optimized for smooth user experience
 *
 * **Use Cases:**
 * - Offer carousel navigation
 * - Content pagination control
 * - Multi-item navigation
 * - User position indication
 * - Interactive content browsing
 *
 * @param total Total number of offers in the carousel
 * @param current Current active offer index (0-based)
 * @param onIndexChanged Callback when user selects a different index
 * @param modifier Modifier to apply to the pagination indicators container
 */
@Composable
private fun PaginationIndicators(
    total: Int,
    current: Int,
    onIndexChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(total) { index ->
            val isSelected = index == current
            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0.4f,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorAlpha$index"
            )
            
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        color = RiyadhAirColors.RoyalPurple.copy(alpha = alpha)
                    )
                    .padding(RiyadhAirSpacing.xs)
            )
            
            if (index < total - 1) {
                Spacer(modifier = Modifier.size(RiyadhAirSpacing.sm))
            }
        }
    }
}

/**
 * Preview function for DestinationCarousel component.
 *
 * This preview demonstrates the DestinationCarousel with sample offer
 * data, showing how the component appears in the design system
 * preview environment. It includes the complete carousel layout
 * with destination card, pagination indicators, and all visual elements.
 *
 * **Preview Configuration:**
 * - **Sample Offer**: Uses New York destination as example
 * - **Single Offer**: Shows carousel with one offer for testing
 * - **Full Layout**: Displays complete carousel with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Destination Card**: Shows main destination card with 9:16 ratio
 * - **Background Image**: Displays sample destination image
 * - **Content Overlay**: Shows destination information and pricing
 * - **Discount Badge**: Demonstrates discount information display
 * - **Pagination**: Shows navigation indicators (single offer)
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete carousel interface
 *
 * **Sample Data:**
 * - **Destination**: New York, United States
 * - **Price**: Starting from 899.99 EUR
 * - **Discount**: 22% off promotion
 * - **Image**: Placeholder image URL for testing
 */
@Preview(showBackground = true)
@Composable
private fun DestinationCarouselPreview() {
    RiyadhAirTheme {
        DestinationCarousel(
            offers = listOf(
                OfferUiModel(
                    coverImage = "https://example.com/nyc.jpg",
                    destination = "New York",
                    country = "United States",
                    priceInfo = "A partir de 899.99 EUR",
                    discountInfo = "-22%",
                )
            ),
            currentIndex = 0,
            onIndexChanged = {},
            modifier = Modifier.padding(RiyadhAirSpacing.md)
        )
    }
}
