package fr.benchaabane.riyadhair.designsystem.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.components.images.RiyadhAirAsyncImage
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * A card component with a blurred background image and gradient overlay.
 *
 * This component creates a visually appealing card with a blurred background
 * image and a dark gradient overlay to ensure content readability. It's
 * particularly useful for displaying content over images while maintaining
 * text legibility.
 *
 * **Visual Features:**
 * - **Blurred Background**: Background image with 8dp blur radius for depth
 * - **Gradient Overlay**: Dark gradient from 40% to 70% black opacity
 * - **Elevated Design**: 8dp elevation for material design depth
 * - **Rounded Corners**: Large shape radius for modern appearance
 *
 * **Layout Structure:**
 * 1. **Background Layer**: Blurred image (if provided)
 * 2. **Overlay Layer**: Dark gradient for text contrast
 * 3. **Content Layer**: User-provided content with padding
 *
 * **Use Cases:**
 * - Hero sections with background images
 * - Content cards over scenic backgrounds
 * - Profile cards with background photos
 * - Feature highlights with visual context
 *
 * **Usage Example:**
 * ```kotlin
 * BlurredBackgroundCard(
 *     backgroundImageUrl = "https://example.com/airplane.jpg",
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .height(300.dp)
 * ) {
 *     Column {
 *         Text(
 *             text = "Welcome to RiyadhAir",
 *             style = MaterialTheme.typography.headlineMedium,
 *             color = Color.White
 *         )
 *         Text(
 *             text = "Your journey begins here",
 *             style = MaterialTheme.typography.bodyLarge,
 *             color = Color.White.copy(alpha = 0.8f)
 *         )
 *     }
 * }
 * ```
 *
 * **Design Considerations:**
 * - Content should use light colors for contrast against dark overlay
 * - Background images work best with high contrast content
 * - The blur effect adds depth without compromising readability
 * - Gradient overlay ensures consistent text visibility
 *
 * @param backgroundImageUrl URL of the background image to display (optional)
 * @param modifier Modifier to apply to the card
 * @param contentDescription Accessibility description for the background image
 * @param content The content to display over the blurred background
 */
@Composable
fun BlurredBackgroundCard(
    backgroundImageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image with blur
            if (!backgroundImageUrl.isNullOrBlank()) {
                RiyadhAirAsyncImage(
                    imageUrl = backgroundImageUrl,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(radius = 8.dp),
                    contentScale = ContentScale.Crop,
                    shape = RiyadhAirShapes.large
                )
            }
            
            // Gradient overlay for better text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )
            
            // Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(RiyadhAirSpacing.xl)
            ) {
                content()
            }
        }
    }
}

/**
 * Preview function for BlurredBackgroundCard component.
 *
 * This preview demonstrates the BlurredBackgroundCard with a sample
 * background image URL and dimensions. It shows how the component
 * appears in the design system preview environment.
 *
 * **Preview Configuration:**
 * - Sample background image URL
 * - Full width with 200dp height
 * - Applied padding for visual separation
 * - Uses RiyadhAir theme for consistent styling
 *
 * **Note:** The preview shows the card structure but the actual
 * background image won't load in the preview environment.
 */
@Preview(showBackground = true)
@Composable
private fun BlurredBackgroundCardPreview() {
    RiyadhAirTheme {
        BlurredBackgroundCard(
            backgroundImageUrl = "https://example.com/background.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(RiyadhAirSpacing.lg)
        ) {
            // Preview content would go here
        }
    }
}
