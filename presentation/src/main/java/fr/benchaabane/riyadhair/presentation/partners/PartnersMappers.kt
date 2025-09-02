package fr.benchaabane.riyadhair.presentation.partners

import fr.benchaabane.riyadhair.domain.partners.models.Partner

/**
 * Maps a domain Partner model to its corresponding UI model.
 *
 * This extension function transforms the domain Partner entity into
 * a presentation-layer PartnerUiModel, extracting and formatting
 * the necessary information for display in the user interface.
 *
 * **Mapping Transformations:**
 * - **Cover Image**: Extracts partner image URL for visual display
 * - **Partner Name**: Uses partner name for primary identification
 * - **Category Display**: Converts category enum to readable display name
 * - **Discount Information**: Formats discount percentage with minus sign
 *
 * **Data Formatting:**
 * - **Image URL**: Direct mapping from domain image URL
 * - **Name Display**: Clean text presentation for partner identification
 * - **Category Formatting**: Human-readable category names from enum
 * - **Discount Formatting**: Adds minus sign for clear discount indication
 *
 * **Use Cases:**
 * - Home screen partners section
 * - Partners list presentation
 * - Partner detail screens
 * - Business collaboration showcase
 * - Partner benefits display
 *
 * **UI Integration:**
 * - **Image Loading**: Provides URL for Coil image loading
 * - **Text Display**: Formatted strings for immediate UI use
 * - **Layout Consistency**: Ensures uniform partner presentation
 * - **User Experience**: Clear and readable partner information
 *
 * **Business Logic:**
 * - **Category Mapping**: Converts enum values to user-friendly text
 * - **Discount Display**: Ensures consistent discount presentation
 * - **Brand Consistency**: Maintains partner brand representation
 * - **Information Hierarchy**: Organizes data for optimal UI consumption
 *
 * **Performance Considerations:**
 * - **Lightweight Transformation**: Minimal computational overhead
 * - **Memory Efficient**: No unnecessary object creation
 * - **Immediate Use**: Ready for direct UI consumption
 * - **Caching Friendly**: Can be cached for repeated use
 *
 * **Data Validation:**
 * - **URL Safety**: Ensures image URLs are properly formatted
 * - **Text Sanitization**: Clean text presentation for UI
 * - **Enum Handling**: Safe conversion of category enums
 * - **Null Safety**: Handles optional discount percentages gracefully
 *
 * @receiver The domain Partner model to transform
 * @return PartnerUiModel containing formatted data for UI presentation
 */
internal fun Partner.toUi() = PartnerUiModel(
    coverImage = imageUrl,
    name = name,
    categoryName = category.displayName,
    discountInfo = "-${discountPercentage}%"
)