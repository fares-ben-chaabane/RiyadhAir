package fr.benchaabane.riyadhair.presentation.offers

import fr.benchaabane.riyadhair.domain.offers.models.Offer

/**
 * Maps a domain Offer model to its corresponding UI model.
 *
 * This extension function transforms the domain Offer entity into
 * a presentation-layer OfferUiModel, extracting and formatting
 * the necessary information for display in the user interface.
 *
 * **Mapping Transformations:**
 * - **Cover Image**: Extracts destination image URL for visual display
 * - **Discount Information**: Formats discount percentage with minus sign
 * - **Destination Name**: Uses destination name for primary identification
 * - **Price Information**: Formats discounted price with currency and "From" prefix
 * - **Country**: Extracts country name for geographic context
 *
 * **Data Formatting:**
 * - **Discount Display**: Adds minus sign for clear discount indication
 * - **Price Formatting**: Converts to integer and adds "From" prefix
 * - **Currency Display**: Shows prices in EUR for consistency
 * - **Text Formatting**: Ensures proper text presentation for UI
 *
 * **Use Cases:**
 * - Home screen offers display
 * - Offers list presentation
 * - Offer detail screens
 * - Marketing content display
 * - Price comparison interfaces
 *
 * **UI Integration:**
 * - **Image Loading**: Provides URL for Coil image loading
 * - **Text Display**: Formatted strings for immediate UI use
 * - **Layout Consistency**: Ensures uniform data presentation
 * - **User Experience**: Clear and readable information display
 *
 * **Performance Considerations:**
 * - **Lightweight Transformation**: Minimal computational overhead
 * - **Memory Efficient**: No unnecessary object creation
 * - **Immediate Use**: Ready for direct UI consumption
 * - **Caching Friendly**: Can be cached for repeated use
 *
 * @receiver The domain Offer model to transform
 * @return OfferUiModel containing formatted data for UI presentation
 */
internal fun Offer.toUi() = OfferUiModel(
    coverImage = destination.imageUrl,
    discountInfo = "-$discountPercentage%",
    destination = destination.name,
    priceInfo = "From ${discountedPrice.toInt()} EUR",
    country = destination.countryName
)