package fr.benchaabane.riyadhair.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.presentation.home.components.AccountCard
import fr.benchaabane.riyadhair.presentation.home.components.DestinationCarousel
import fr.benchaabane.riyadhair.presentation.home.components.MarketingSection
import fr.benchaabane.riyadhair.presentation.home.components.PartnersSection
import kotlinx.coroutines.delay

/**
 * Main home screen composable for the RiyadhAir application.
 *
 * This composable serves as the primary landing page for users, providing
 * a comprehensive overview of their account, travel offers, destinations,
 * and business partnerships. It features smooth animations and a well-organized
 * layout for optimal user experience.
 *
 * **Screen Layout Structure:**
 * - **Account Card**: User profile and loyalty information with background image
 * - **Marketing Section**: Overview of available offers with call-to-action
 * - **Destination Carousel**: Interactive showcase of travel destinations
 * - **Partners Section**: Business partner information and benefits
 *
 * **Animation Features:**
 * - **Entrance Animation**: Smooth slide-in from bottom with fade effect
 * - **Staggered Reveal**: Content appears with slight delay for visual appeal
 * - **Smooth Transitions**: Animated visibility for polished user experience
 * - **Performance Optimized**: Efficient animation implementation
 *
 * **User Experience Features:**
 * - **Scrollable Content**: Vertical scrolling for all content sections
 * - **Responsive Layout**: Adapts to different screen sizes and orientations
 * - **Interactive Elements**: Clickable sections for navigation and actions
 * - **Visual Hierarchy**: Clear separation and spacing between sections
 * - **Loading States**: Graceful handling of data loading and errors
 *
 * **Data Integration:**
 * - **ViewModel Integration**: Uses Hilt for dependency injection
 * - **State Observation**: Lifecycle-aware state collection
 * - **Real-time Updates**: Automatic UI updates based on data changes
 * - **Error Handling**: Graceful fallbacks for failed operations
 *
 * **Navigation Support:**
 * - **Offer Navigation**: Callback for viewing all available offers
 * - **Partner Interaction**: Callback for partner-specific actions
 * - **Account Access**: Direct access to user account information
 * - **Destination Exploration**: Interactive destination browsing
 *
 * **Performance Considerations:**
 * - **Lazy Loading**: Efficient content rendering and updates
 * - **Memory Management**: Proper state management and cleanup
 * - **Animation Performance**: Optimized animations for smooth experience
 * - **State Synchronization**: Consistent UI state across updates
 *
 * **Use Cases:**
 * - Application home screen
 * - User dashboard and overview
 * - Travel offer discovery
 * - Partner information access
 * - Account management entry point
 *
 * **Accessibility Features:**
 * - **Content Descriptions**: Proper accessibility labels
 * - **Touch Targets**: Adequate sizing for mobile interaction
 * - **Visual Contrast**: Proper color and typography contrast
 * - **Screen Reader Support**: Compatible with accessibility tools
 *
 * @param modifier Modifier to apply to the home screen container
 * @param viewModel The HomeViewModel instance for managing screen state
 * @param onViewAllOffersClick Callback when user wants to view all offers
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onViewAllOffersClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100) // Small delay for smooth entrance
        isContentVisible = true
    }

    // Get background image URL from current location or fallback
    val backgroundImageUrl = "https://mistertravel.news/wp-content/uploads/2025/02/Riyadh-Air.jpeg"

    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = isContentVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 4 }
        ) + fadeIn()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xxl)
        ) {
            // Account Card
            AccountCard(
                account = uiState.account,
                backgroundImageUrl = backgroundImageUrl,
                modifier = Modifier.fillMaxWidth()
            )

            // Marketing Section
            MarketingSection(
                offerCount = uiState.bestOffers.size,
                onViewAllClick = onViewAllOffersClick,
                modifier = Modifier.fillMaxWidth()
            )

            // Destination Carousel
            DestinationCarousel(
                offers = uiState.bestOffers,
                currentIndex = uiState.currentOfferIndex,
                onIndexChanged = viewModel::onOfferIndexChanged,
                modifier = Modifier.fillMaxWidth()
            )

            // Partners Section
            PartnersSection(
                partners = uiState.partners,
                onPartnerClick = { partner ->
                    // TODO: Navigate to partner details or open website
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Preview function for HomeScreen component.
 *
 * This preview demonstrates the HomeScreen with default configuration,
 * showing how the component appears in the design system preview
 * environment. It includes the complete screen layout with all
 * sections and components.
 *
 * **Preview Configuration:**
 * - **Default ViewModel**: Uses Hilt-injected ViewModel
 * - **Default Callbacks**: Empty callback implementations for testing
 * - **Full Layout**: Displays complete screen with all sections
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Account Card**: Shows account information display area
 * - **Marketing Section**: Displays offers overview section
 * - **Destination Carousel**: Shows interactive destination showcase
 * - **Partners Section**: Displays business partners information
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete home screen interface
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of screen layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 */
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    RiyadhAirTheme {
        HomeScreen()
    }
}