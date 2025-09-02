package fr.benchaabane.riyadhair.presentation.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.benchaabane.riyadhair.designsystem.components.images.RiyadhAirAsyncImage
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.presentation.home.HomeViewModel

/**
 * A comprehensive screen for displaying all available travel offers in a list format.
 *
 * This composable provides a full-screen interface for browsing travel offers,
 * presenting them in an organized, scrollable list with rich visual cards.
 * It integrates with the HomeViewModel to access offer data and provides
 * an immersive browsing experience for users.
 *
 * **Screen Features:**
 * - **Full-Screen Layout**: Maximizes screen real estate for offer browsing
 * - **Scrollable Content**: LazyColumn for efficient rendering of large offer lists
 * - **Rich Visual Cards**: High-quality offer presentation with images and overlays
 * - **Responsive Design**: Adapts to different screen sizes and orientations
 * - **Performance Optimized**: Efficient rendering with lazy loading
 *
 * **Data Integration:**
 * - **ViewModel Integration**: Uses Hilt-injected HomeViewModel
 * - **State Observation**: Lifecycle-aware state collection for offers
 * - **Real-time Updates**: Automatic UI updates based on data changes
 * - **Data Consistency**: Ensures UI reflects current offer availability
 *
 * **Layout Structure:**
 * - **Content Padding**: Consistent spacing around the entire list
 * - **Vertical Arrangement**: Proper spacing between offer cards
 * - **Card Dimensions**: 16:9 aspect ratio for optimal visual presentation
 * - **Responsive Spacing**: Uses RiyadhAir spacing system throughout
 *
 * **User Experience:**
 * - **Smooth Scrolling**: Optimized scrolling performance for large lists
 * - **Visual Hierarchy**: Clear organization of offer information
 * - **Interactive Elements**: Clickable cards for offer exploration
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Use Cases:**
 * - Complete offers browsing experience
 * - Travel inspiration and discovery
 * - Price comparison across destinations
 * - Marketing content presentation
 * - User engagement and conversion
 *
 * **Navigation Support:**
 * - **Offer Details**: Callback for navigating to offer details (TODO)
 * - **Screen Integration**: Seamless integration with navigation system
 * - **Back Navigation**: Standard Android back button support
 *
 * @param modifier Modifier to apply to the offers list screen container
 * @param viewModel The HomeViewModel instance for accessing offer data
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OffersListScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(RiyadhAirSpacing.lg),
        verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
    ) {
        items(uiState.bestOffers) { offer ->
            OfferCard(
                offer = offer,
                onClick = { /* TODO: Navigate to offer details */ }
            )
        }
    }
}

/**
 * A visually rich card component for displaying individual travel offers.
 *
 * This composable creates an immersive offer presentation with background
 * images, gradient overlays, and well-organized content layout. It provides
 * a compelling visual experience that encourages user engagement and
 * decision-making.
 *
 * **Visual Design Features:**
 * - **Background Image**: High-quality destination images for visual appeal
 * - **Gradient Overlay**: Dark gradient for text readability over images
 * - **Card Elevation**: Material Design elevation for depth perception
 * - **Rounded Corners**: Consistent with RiyadhAir design system
 * - **Aspect Ratio**: 16:9 ratio for optimal visual presentation
 *
 * **Content Layout:**
 * - **Discount Badge**: Prominent display of savings at the top
 * - **Destination Information**: Clear hierarchy of location details
 * - **Price Display**: Highlighted pricing information at the bottom
 * - **Text Contrast**: White text with proper opacity for readability
 * - **Spacing System**: Consistent RiyadhAir spacing throughout
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
 * - **Lazy Rendering**: Only renders visible cards for performance
 * - **Memory Management**: Proper image caching and disposal
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Gold for highlights)
 * - **Typography**: Consistent text styles and font weights
 * - **Shapes**: Unified shape system for rounded corners
 * - **Spacing**: Standardized spacing values throughout
 *
 * **Use Cases:**
 * - Offer list displays
 * - Marketing content presentation
 * - Destination browsing
 * - Price comparison interfaces
 * - User engagement and conversion
 *
 * @param offer The offer data to display in the card
 * @param onClick Callback when the offer card is tapped
 * @param modifier Modifier to apply to the offer card container
 */
@Composable
private fun OfferCard(
    offer: OfferUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
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
                            )
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
                // Discount badge
                Box(
                    modifier = Modifier
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
                        text = offer.discountInfo,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Destination info
                Column(
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    Text(
                        text = offer.destination,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = offer.country,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Text(
                        text = offer.priceInfo,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.Gold
                    )
                }
            }
        }
    }
}

/**
 * Preview function for OffersListScreen component.
 *
 * This preview demonstrates the OffersListScreen with default configuration,
 * showing how the component appears in the design system preview
 * environment. It includes the complete screen layout with offer cards.
 *
 * **Preview Configuration:**
 * - **Default ViewModel**: Uses Hilt-injected HomeViewModel
 * - **Sample Data**: Shows offers from ViewModel state
 * - **Full Layout**: Displays complete screen with all components
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Screen Layout**: Shows full-screen offers list
 * - **Offer Cards**: Displays sample offer cards with images
 * - **Visual Design**: Demonstrates card styling and layout
 * - **Responsive Design**: Shows how the screen adapts to content
 * - **Design System**: Confirms RiyadhAir theme integration
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete offers browsing interface
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of screen layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 */
@Preview(showBackground = true)
@Composable
private fun OffersListScreenPreview() {
    RiyadhAirTheme {
        OffersListScreen()
    }
}
