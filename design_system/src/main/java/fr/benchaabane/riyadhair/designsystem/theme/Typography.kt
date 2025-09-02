package fr.benchaabane.riyadhair.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * RiyadhAir typography system that provides consistent text styling throughout the application.
 *
 * This object defines a comprehensive set of text styles that follow Material 3
 * typography principles. The typography system provides progressive text scales
 * for different content types and hierarchy levels, ensuring consistent readability
 * and visual hierarchy across all components.
 *
 * **Typography Philosophy:**
 * - **Material 3 Compliance**: Follows Material 3 typography guidelines
 * - **Progressive Scale**: Logical size progression for different content types
 * - **Readability Focus**: Optimized for legibility across devices and contexts
 * - **Hierarchy Clarity**: Clear visual distinction between content levels
 * - **Accessibility**: Proper contrast ratios and readable font sizes
 *
 * **Text Style Categories:**
 * - **Display Styles**: Large, prominent text for hero sections and major headings
 * - **Headline Styles**: Section headers and important content titles
 * - **Title Styles**: Card titles, prominent text, and content headers
 * - **Body Styles**: Main content text and readable paragraphs
 * - **Label Styles**: Form labels, buttons, and small interface text
 *
 * **Display Styles (Hero & Major Headings):**
 * - **Display Large (57sp)**: Primary hero text, major page titles
 * - **Display Medium (45sp)**: Secondary hero text, section hero titles
 * - **Display Small (36sp)**: Tertiary hero text, major section headers
 *
 * **Headline Styles (Section Headers):**
 * - **Headline Large (32sp)**: Primary section headers, major content titles
 * - **Headline Medium (28sp)**: Secondary section headers, content section titles
 * - **Headline Small (24sp)**: Tertiary section headers, subsection titles
 *
 * **Title Styles (Content Headers):**
 * - **Title Large (22sp)**: Card titles, prominent content headers
 * - **Title Medium (16sp)**: Standard content titles, form section headers
 * - **Title Small (14sp)**: Compact titles, small content headers
 *
 * **Body Styles (Main Content):**
 * - **Body Large (16sp)**: Primary content text, main paragraphs
 * - **Body Medium (14sp)**: Secondary content text, supporting paragraphs
 * - **Body Small (12sp)**: Compact content text, captions, notes
 *
 * **Label Styles (Interface Text):**
 * - **Label Large (14sp)**: Button text, form labels, navigation items
 * - **Label Medium (12sp)**: Small buttons, compact form labels
 * - **Label Small (11sp)**: Minimal interface text, micro labels
 *
 * **Usage Guidelines:**
 * - Use display styles sparingly for maximum impact
 * - Reserve headline styles for true section divisions
 * - Use title styles for content organization and hierarchy
 * - Apply body styles for readable content and descriptions
 * - Use label styles for interactive elements and small text
 *
 * **Implementation Examples:**
 * ```kotlin
 * // Hero section with display text
 * Text(
 *     text = "Welcome to RiyadhAir",
 *     style = MaterialTheme.typography.displayLarge
 * )
 *
 * // Section header
 * Text(
 *     text = "Flight Search",
 *     style = MaterialTheme.typography.headlineMedium
 * )
 *
 * // Card title
 * Text(
 *     text = "Flight Details",
 *     style = MaterialTheme.typography.titleLarge
 * )
 *
 * // Main content
 * Text(
 *     text = "Your journey begins with our comprehensive flight search.",
 *     style = MaterialTheme.typography.bodyLarge
 * )
 *
 * // Button text
 * Text(
 *     text = "Search Flights",
 *     style = MaterialTheme.typography.labelLarge
 * )
 * ```
 *
 * **Design Benefits:**
 * - **Consistency**: Unified text styling across all components
 * - **Hierarchy**: Clear visual organization of content importance
 * - **Readability**: Optimized for different reading contexts
 * - **Accessibility**: Proper text sizing and contrast ratios
 * - **Brand Identity**: Distinctive RiyadhAir typography style
 *
 * **Accessibility Considerations:**
 * - Minimum font size of 11sp for readability
 * - Proper line height ratios for comfortable reading
 * - Consistent letter spacing for text clarity
 * - Font weight variations for emphasis and hierarchy
 */
val RiyadhAirTypography = Typography(
    // Display styles - for hero sections and major headings
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    
    // Headline styles - for section headers
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    
    // Title styles - for card titles and prominent text
    titleLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    
    // Body styles - for main content
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    
    // Label styles - for buttons and form labels
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
