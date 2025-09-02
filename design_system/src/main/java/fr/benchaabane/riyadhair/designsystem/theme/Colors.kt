package fr.benchaabane.riyadhair.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * RiyadhAir brand color palette and design system colors.
 *
 * This object defines the complete color system for the RiyadhAir application,
 * including brand colors, semantic colors, and loyalty tier colors. The color
 * palette is inspired by Saudi heritage, aviation themes, and modern design
 * principles to create a distinctive and culturally relevant brand identity.
 *
 * **Color Philosophy:**
 * - **Royal Purple**: Represents luxury, heritage, and premium service
 * - **Gold**: Traditional Saudi color symbolizing wealth and hospitality
 * - **Sky Blue**: Aviation theme connecting to air travel and technology
 * - **Neutral Colors**: Professional and accessible design foundation
 *
 * **Brand Colors:**
 * - **Primary**: Royal Purple for main actions and branding
 * - **Secondary**: Gold for accents and highlights
 * - **Tertiary**: Sky Blue for aviation-specific elements
 *
 * **Semantic Colors:**
 * - **Success**: Green for positive actions and confirmations
 * - **Warning**: Amber for caution and attention
 * - **Error**: Red for errors and critical actions
 *
 * **Loyalty Tier Colors:**
 * - **Bronze**: Entry-level loyalty tier
 * - **Silver**: Mid-tier loyalty status
 * - **Loyalty Gold**: Premium loyalty tier
 * - **Platinum**: Elite loyalty status
 * - **Diamond**: Highest loyalty tier
 *
 * **Usage Guidelines:**
 * - Use primary colors for main actions and branding
 * - Use secondary colors for supporting elements
 * - Use semantic colors consistently across the application
 * - Use loyalty colors only for loyalty-related features
 * - Ensure proper contrast ratios for accessibility
 */
object RiyadhAirColors {
    // Primary - Deep Royal Purple (inspired by Saudi heritage and luxury)
    val RoyalPurple = Color(0xFF6B46C1)
    val RoyalPurpleLight = Color(0xFF8B5CF6)
    val RoyalPurpleDark = Color(0xFF553C9A)

    // Secondary - Gold (traditional Saudi color)
    val Gold = Color(0xFFD4AF37)
    val GoldLight = Color(0xFFE5C547)
    val GoldDark = Color(0xFFB8941F)

    // Accent - Sky Blue (aviation theme)
    val SkyBlue = Color(0xFF0EA5E9)
    val SkyBlueLight = Color(0xFF38BDF8)
    val SkyBlueDark = Color(0xFF0284C7)

    // Neutral colors
    val CloudWhite = Color(0xFFFAFAFA)
    val StormGray = Color(0xFF64748B)
    val MidnightBlue = Color(0xFF1E293B)

    // Semantic colors
    val Success = Color(0xFF10B981)
    val Warning = Color(0xFFF59E0B)
    val Error = Color(0xFFEF4444)

    // Loyalty tier colors
    val Bronze = Color(0xFFCD7F32)
    val Silver = Color(0xFFC0C0C0)
    val LoyaltyGold = Color(0xFFFFD700)
    val Platinum = Color(0xFFE5E4E2)
    val Diamond = Color(0xFFB9F2FF)

    val yellow = Color(0xFFF1F5F9)
    val grey = Color(0xFFCBD5E1)
    val blue = Color(0xFF0F172A)
}

/**
 * Light theme color scheme for RiyadhAir application.
 *
 * This color scheme provides a bright, accessible design that emphasizes
 * readability and user comfort. It uses the brand colors with appropriate
 * contrast ratios and follows Material 3 design principles for light themes.
 *
 * **Color Mapping:**
 * - **Primary**: Royal Purple for main actions and branding
 * - **Secondary**: Gold for supporting elements and highlights
 * - **Tertiary**: Sky Blue for aviation-specific features
 * - **Background**: Cloud White for clean, professional appearance
 * - **Surface**: White for content areas and cards
 * - **Text**: Midnight Blue for optimal readability
 *
 * **Contrast Considerations:**
 * - High contrast between text and backgrounds
 * - Accessible color combinations for all users
 * - Consistent visual hierarchy through color
 * - Professional appearance suitable for business use
 *
 * **Use Cases:**
 * - Default theme for most users
 * - High-contrast environments
 * - Professional and business contexts
 * - Accessibility-focused design
 */
val RiyadhAirLightColors = lightColorScheme(
    primary = RiyadhAirColors.RoyalPurple,
    onPrimary = Color.White,
    primaryContainer = RiyadhAirColors.RoyalPurpleLight,
    onPrimaryContainer = Color.White,

    secondary = RiyadhAirColors.Gold,
    onSecondary = RiyadhAirColors.MidnightBlue,
    secondaryContainer = RiyadhAirColors.GoldLight,
    onSecondaryContainer = RiyadhAirColors.MidnightBlue,

    tertiary = RiyadhAirColors.SkyBlue,
    onTertiary = Color.White,
    tertiaryContainer = RiyadhAirColors.SkyBlueLight,
    onTertiaryContainer = Color.White,

    background = RiyadhAirColors.CloudWhite,
    onBackground = RiyadhAirColors.MidnightBlue,
    surface = Color.White,
    onSurface = RiyadhAirColors.MidnightBlue,
    surfaceVariant = RiyadhAirColors.yellow,
    onSurfaceVariant = RiyadhAirColors.StormGray,

    outline = RiyadhAirColors.StormGray,
    outlineVariant = RiyadhAirColors.grey
)

/**
 * Dark theme color scheme for RiyadhAir application.
 *
 * This color scheme provides a sophisticated, modern design that reduces
 * eye strain in low-light environments. It maintains the brand identity
 * while providing excellent contrast and readability in dark conditions.
 *
 * **Color Mapping:**
 * - **Primary**: Light Royal Purple for better visibility on dark backgrounds
 * - **Secondary**: Light Gold for supporting elements
 * - **Tertiary**: Light Sky Blue for aviation features
 * - **Background**: Deep Blue for rich, immersive experience
 * - **Surface**: Midnight Blue for content areas and cards
 * - **Text**: White for optimal contrast and readability
 *
 * **Dark Theme Benefits:**
 * - Reduced eye strain in low-light conditions
 * - Modern, sophisticated appearance
 * - Better battery life on OLED displays
 * - Enhanced focus on content
 *
 * **Use Cases:**
 * - Low-light environments
 * - Night-time usage
 * - Modern, sophisticated user preferences
 * - OLED display optimization
 */
val RiyadhAirDarkColors = darkColorScheme(
    primary = RiyadhAirColors.RoyalPurpleLight,
    onPrimary = RiyadhAirColors.MidnightBlue,
    primaryContainer = RiyadhAirColors.RoyalPurpleDark,
    onPrimaryContainer = Color.White,

    secondary = RiyadhAirColors.GoldLight,
    onSecondary = RiyadhAirColors.MidnightBlue,
    secondaryContainer = RiyadhAirColors.GoldDark,
    onSecondaryContainer = Color.White,

    tertiary = RiyadhAirColors.SkyBlueLight,
    onTertiary = RiyadhAirColors.MidnightBlue,
    tertiaryContainer = RiyadhAirColors.SkyBlueDark,
    onTertiaryContainer = Color.White,

    background = RiyadhAirColors.blue,
    onBackground = Color.White,
    surface = RiyadhAirColors.MidnightBlue,
    onSurface = Color.White,
    surfaceVariant = RiyadhAirColors.yellow,
    onSurfaceVariant = RiyadhAirColors.StormGray,

    outline = RiyadhAirColors.StormGray,
    outlineVariant = RiyadhAirColors.grey
)
