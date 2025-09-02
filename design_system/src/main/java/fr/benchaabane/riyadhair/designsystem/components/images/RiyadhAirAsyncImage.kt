package fr.benchaabane.riyadhair.designsystem.components.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * An asynchronous image loading component that follows RiyadhAir design system guidelines.
 *
 * This component provides a consistent way to load and display images from URLs
 * throughout the application. It integrates with Coil for efficient image loading
 * and provides built-in loading states, error handling, and design system styling.
 *
 * **Features:**
 * - **Asynchronous Loading**: Efficient image loading with Coil integration
 * - **Loading States**: Circular progress indicator during image loading
 * - **Error Handling**: Graceful fallback for failed image loads
 * - **Design System Integration**: Consistent shapes and colors
 * - **Crossfade Animation**: Smooth transitions between image states
 * - **Customizable Styling**: Configurable shapes, content scale, and colors
 *
 * **Image Loading States:**
 * - **Loading**: Shows circular progress indicator on placeholder background
 * - **Success**: Displays the loaded image with applied styling
 * - **Error**: Shows placeholder background when image fails to load
 *
 * **Performance Features:**
 * - **Crossfade**: Smooth transitions between loading states
 * - **Efficient Caching**: Coil handles image caching automatically
 * - **Memory Management**: Proper memory handling for large images
 * - **Network Optimization**: Efficient network requests and retry logic
 *
 * **Usage Examples:**
 * ```kotlin
 * // Basic usage with default styling
 * RiyadhAirAsyncImage(
 *     imageUrl = "https://example.com/airplane.jpg",
 *     contentDescription = "Airplane in flight"
 * )
 *
 * // Custom styling with specific shape and content scale
 * RiyadhAirAsyncImage(
 *     imageUrl = "https://example.com/profile.jpg",
 *     contentDescription = "User profile picture",
 *     shape = RiyadhAirShapes.large,
 *     contentScale = ContentScale.Fit,
 *     modifier = Modifier.size(100.dp)
 * )
 *
 * // Custom placeholder color
 * RiyadhAirAsyncImage(
 *     imageUrl = "https://example.com/banner.jpg",
 *     contentDescription = "Promotional banner",
 *     placeholderColor = MaterialTheme.colorScheme.primaryContainer
 * )
 * ```
 *
 * **Design System Integration:**
 * - Uses RiyadhAirShapes for consistent corner radius
 * - Applies Material 3 color scheme for loading indicators
 * - Maintains consistent spacing and sizing patterns
 * - Follows accessibility guidelines for content descriptions
 *
 * **Accessibility Features:**
 * - Content description support for screen readers
 * - Proper loading state indicators
 * - Error state fallbacks for failed loads
 * - Semantic meaning through visual feedback
 *
 * @param imageUrl The URL of the image to load and display
 * @param contentDescription Accessibility description for the image
 * @param modifier Modifier to apply to the image container
 * @param shape The shape to apply to the image (clipping)
 * @param contentScale How the image should be scaled within its bounds
 * @param placeholderColor The background color for loading and error states
 */
@Composable
fun RiyadhAirAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = RiyadhAirShapes.medium,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier.clip(shape),
        contentScale = contentScale,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(placeholderColor),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(placeholderColor)
            )
        }
    )
}

/**
 * Preview function for RiyadhAirAsyncImage component.
 *
 * This preview demonstrates the RiyadhAirAsyncImage with sample dimensions
 * and a placeholder image URL. It shows how the component appears in the
 * design system preview environment.
 *
 * **Preview Configuration:**
 * - Sample image URL for demonstration
 * - 200dp x 120dp dimensions for visual testing
 * - Sample content description for accessibility
 * - Uses RiyadhAir theme for consistent styling
 *
 * **Note:** The preview shows the component structure but the actual
 * image won't load in the preview environment. The loading state will
 * be visible instead.
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirAsyncImagePreview() {
    RiyadhAirTheme {
        RiyadhAirAsyncImage(
            imageUrl = "https://example.com/image.jpg",
            contentDescription = "Preview image",
            modifier = Modifier.size(200.dp, 120.dp)
        )
    }
}
