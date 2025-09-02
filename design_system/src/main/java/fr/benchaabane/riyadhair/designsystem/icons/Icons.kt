package fr.benchaabane.riyadhair.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * RiyadhAir icon system that provides consistent icon usage throughout the application.
 *
 * This object defines a curated collection of Material Design icons that are
 * commonly used across the RiyadhAir application. It provides semantic icon
 * names and ensures consistent icon usage patterns while leveraging Material
 * Design's comprehensive icon library.
 *
 * **Icon Philosophy:**
 * - **Material Design Foundation**: Built on Material Design icon system
 * - **Semantic Naming**: Descriptive names for clear understanding
 * - **Consistent Usage**: Unified icon patterns across components
 * - **RTL Support**: Auto-mirrored icons for international users
 * - **Accessibility**: Proper content descriptions and semantic meaning
 *
 * **Icon Categories:**
 * - **Navigation Icons**: Primary navigation and app structure
 * - **Common Action Icons**: Frequently used interactive elements
 * - **Content Icons**: Content-related and informational elements
 *
 * **Navigation Icons:**
 * - **Home**: Main dashboard and landing page navigation
 * - **Search**: Flight search and content discovery
 * - **Reservations**: User's flight reservations and bookings
 * - **Account**: User profile and account management
 *
 * **Common Action Icons:**
 * - **Add**: Create new items, add to lists
 * - **ArrowForward**: Next step, forward navigation
 * - **ArrowDropDown**: Expandable content, dropdown menus
 * - **ArrowDropUp**: Collapsible content, accordion sections
 * - **ArrowDown**: Downward navigation, scroll down
 * - **ArrowUp**: Upward navigation, scroll up
 * - **ArrowRight**: Rightward navigation, next item
 * - **Back**: Previous step, backward navigation
 *
 * **Content Icons:**
 * - **Person**: User-related content, profile information
 * - **Info**: Information display, help content
 * - **Settings**: Configuration and preferences
 * - **DateRange**: Date selection, calendar content
 * - **LocationOn**: Location-based content, destinations
 * - **Place**: Geographic locations, points of interest
 * - **Refresh**: Update content, reload data
 * - **Email**: Email-related content and actions
 * - **Phone**: Phone contact information
 * - **Visibility**: Show content, reveal information
 * - **VisibilityOff**: Hide content, conceal information
 *
 * **Usage Guidelines:**
 * - Use semantic icon names for clear meaning
 * - Provide appropriate content descriptions for accessibility
 * - Maintain consistent icon sizing within components
 * - Use auto-mirrored icons for RTL language support
 * - Choose icons that clearly represent their function
 *
 * **Implementation Examples:**
 * ```kotlin
 * // Navigation icon
 * Icon(
 *     imageVector = RiyadhAirIcons.Home,
 *     contentDescription = "Navigate to home"
 * )
 *
 * // Action icon with button
 * IconButton(onClick = { /* Handle action */ }) {
 *     Icon(
 *         imageVector = RiyadhAirIcons.Add,
 *         contentDescription = "Add new item"
 *     )
 * }
 *
 * // Content icon in text field
 * RiyadhAirTextField(
 *     trailingIcon = RiyadhAirIcons.Search,
 *     onTrailingIconClick = { /* Handle search */ }
 * )
 * ```
 *
 * **Design Benefits:**
 * - **Consistency**: Unified icon usage across all components
 * - **Clarity**: Semantic names for easy understanding
 * - **Accessibility**: Proper content descriptions and RTL support
 * - **Maintainability**: Centralized icon management
 * - **Brand Identity**: Consistent visual language
 *
 * **Accessibility Features:**
 * - Semantic icon names for screen readers
 * - Auto-mirrored icons for RTL languages
 * - Consistent visual patterns for user recognition
 * - Proper content descriptions for icon meaning
 */
object RiyadhAirIcons {
    // Navigation icons
    val Home: ImageVector = Icons.Default.Home
    val Search: ImageVector = Icons.Default.Search
    val Reservations: ImageVector = Icons.AutoMirrored.Default.List
    val Account: ImageVector = Icons.Default.Person
    
    // Common action icons
    val Add: ImageVector = Icons.Default.Add
    val ArrowForward: ImageVector = Icons.AutoMirrored.Filled.ArrowForward
    val ArrowDropDown: ImageVector = Icons.Default.ArrowDropDown
    val ArrowDropUp: ImageVector = Icons.Default.ArrowDropUp
    val ArrowDown: ImageVector = Icons.Default.KeyboardArrowDown
    val ArrowUp: ImageVector = Icons.Default.KeyboardArrowUp
    val ArrowRight: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight
    val Back: ImageVector = Icons.AutoMirrored.Default.ArrowBack
    
    // Content icons
    val Person: ImageVector = Icons.Default.Person
    val Info: ImageVector = Icons.Default.Info
    val Settings: ImageVector = Icons.Default.Settings
    val DateRange: ImageVector = Icons.Default.DateRange
    val LocationOn: ImageVector = Icons.Default.LocationOn
    val Place: ImageVector = Icons.Default.Place
    val Refresh: ImageVector = Icons.Default.Refresh
    val Email: ImageVector = Icons.Default.Email
    val Phone: ImageVector = Icons.Default.Phone
    val Visibility: ImageVector = Icons.Default.Visibility
    val VisibilityOff: ImageVector = Icons.Default.VisibilityOff
}

