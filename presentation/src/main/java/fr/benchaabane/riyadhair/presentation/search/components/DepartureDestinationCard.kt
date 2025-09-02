package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.core.ui.rtlAware
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * A comprehensive card component for displaying and managing flight departure and destination information.
 *
 * This composable provides an intuitive interface for viewing and modifying flight route details,
 * featuring interactive departure and destination sections with a convenient swap functionality.
 * It's designed to be the central element in flight search interfaces, clearly presenting
 * the journey from origin to destination.
 *
 * **Route Information Display:**
 * - **Departure Section**: Shows departure airport with city name and IATA code
 * - **Destination Section**: Displays destination airport with city name and IATA code
 * - **Visual Icons**: Location icons to distinguish departure and arrival
 * - **Typography Hierarchy**: Clear distinction between city names and airport codes
 * - **Color Coding**: Sky blue accents for location indicators
 *
 * **Interactive Features:**
 * - **Departure Selection**: Clickable departure section for airport modification
 * - **Destination Selection**: Clickable destination section for airport modification
 * - **Route Swap**: One-click button to exchange departure and destination
 * - **Touch Targets**: Adequate sizing for mobile interaction
 * - **Visual Feedback**: Clear indication of interactive areas
 *
 * **Layout Structure:**
 * - **Three-Column Layout**: Departure, swap button, destination
 * - **Balanced Distribution**: Equal weight for departure and destination sections
 * - **Center Swap Button**: Prominently placed for easy access
 * - **Responsive Design**: Adapts to different screen sizes and content lengths
 * - **Proper Spacing**: Consistent RiyadhAir spacing system throughout
 *
 * **Visual Design:**
 * - **Card Container**: Material Design 3 card with elevation and border
 * - **Icon Integration**: Location and refresh icons for clear visual communication
 * - **Color Scheme**: Sky blue for location elements, royal purple for swap action
 * - **Shape Consistency**: Uses RiyadhAir shape system for rounded corners
 * - **Typography System**: Consistent text styles and font weights
 *
 * **User Experience Features:**
 * - **Clear Information**: Easy-to-read airport and city details
 * - **Intuitive Interaction**: Familiar clickable patterns for modification
 * - **Quick Actions**: Instant route swapping for return trip planning
 * - **Visual Consistency**: Matches overall RiyadhAir design language
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Use Cases:**
 * - Flight search interface main route display
 * - Booking modification for existing reservations
 * - Route planning and comparison
 * - Return trip configuration
 * - Multi-city trip planning
 *
 * **Localization Support:**
 * - **French Language**: Primary language support
 * - **Dynamic Strings**: Resource-based text management
 * - **Cultural Adaptation**: Language-specific airport and city names
 * - **RTL Support**: Compatible with right-to-left languages
 *
 * **Business Logic:**
 * - **Airport Validation**: Displays valid airport codes and city names
 * - **Route Management**: Handles departure and destination as separate entities
 * - **Swap Functionality**: Enables quick route reversal for return trips
 * - **Modification Support**: Allows users to change either endpoint
 *
 * @param departureAirportCode The IATA code of the departure airport (e.g., "PAR")
 * @param destinationAirportCode The IATA code of the destination airport (e.g., "DXB")
 * @param departureAirportCity The city name of the departure airport (e.g., "Paris")
 * @param destinationAirportCity The city name of the destination airport (e.g., "Dubai")
 * @param onDepartureClick Callback when the departure section is tapped
 * @param onDestinationClick Callback when the destination section is tapped
 * @param onSwapClick Callback when the swap button is pressed
 * @param modifier Modifier to apply to the card container
 */
@Composable
fun DepartureDestinationCard(
    departureAirportCode: String,
    destinationAirportCode: String,
    departureAirportCity: String,
    destinationAirportCity: String,
    onDepartureClick: () -> Unit,
    onDestinationClick: () -> Unit,
    onSwapClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                // Departure
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDepartureClick() }
                        .padding(RiyadhAirSpacing.sm)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                    ) {
                        Icon(
                            imageVector = RiyadhAirIcons.LocationOn,
                            contentDescription = "Départ",
                            tint = RiyadhAirColors.SkyBlue
                        )
                        Text(
                            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.departure_from),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))
                    
                    Text(
                        text = departureAirportCity,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = departureAirportCode,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                // Swap Button
                IconButton(
                    onClick = onSwapClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = RiyadhAirIcons.Refresh,
                        contentDescription = stringResource(fr.benchaabane.riyadhair.presentation.R.string.swap_destinations),
                        tint = RiyadhAirColors.RoyalPurple
                    )
                }
                
                // Destination
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDestinationClick() }
                        .padding(RiyadhAirSpacing.sm)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                    ) {
                        Icon(
                            imageVector = RiyadhAirIcons.Place,
                            contentDescription = stringResource(fr.benchaabane.riyadhair.presentation.R.string.cd_arrival),
                            tint = RiyadhAirColors.SkyBlue
                        )
                        Text(
                            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.destination_to),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))
                    
                    Text(
                        text = destinationAirportCity,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = destinationAirportCode,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Preview function for DepartureDestinationCard component.
 *
 * This preview demonstrates the DepartureDestinationCard with sample
 * airport data, showing how the component appears in the design system
 * preview environment. It includes a realistic route from Paris to Dubai
 * to showcase the complete card layout and styling.
 *
 * **Preview Configuration:**
 * - **Sample Route**: Paris (PAR) to Dubai (DXB) flight route
 * - **Full Layout**: Displays complete card with all sections
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Departure Section**: Shows Paris departure with location icon
 * - **Swap Button**: Displays refresh icon for route reversal
 * - **Destination Section**: Shows Dubai destination with place icon
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete route display interface
 *
 * **Sample Data:**
 * - **Departure**: Paris (PAR) - Major European hub
 * - **Destination**: Dubai (DXB) - Major Middle Eastern hub
 * - **Route Type**: International long-haul flight
 * - **Airline Context**: Typical RiyadhAir route example
 */
@Preview(showBackground = true)
@Composable
private fun DepartureDestinationCardPreview() {
    RiyadhAirTheme {
        DepartureDestinationCard(
            departureAirportCode = "PAR",
            departureAirportCity = "Paris",
            destinationAirportCode = "DXB",
            destinationAirportCity = "Dubai",
            onDepartureClick = {},
            onDestinationClick = {},
            onSwapClick = {},
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
