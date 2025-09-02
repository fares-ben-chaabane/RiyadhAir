package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * Data class representing airport information for destination selection.
 *
 * This class encapsulates all the essential information needed to display
 * and identify airports in the destination selection interface. It provides
 * a structured way to represent airport data with clear, descriptive fields.
 *
 * **Airport Information Fields:**
 * - **Code**: International airport code (IATA format)
 * - **Name**: Full airport name for identification
 * - **City**: City where the airport is located
 * - **Country**: Country of the airport location
 *
 * **Data Structure:**
 * - **Immutable Design**: All properties are read-only for data integrity
 * - **Structured Format**: Organized information for easy access
 * - **Search Support**: Fields optimized for text-based filtering
 * - **Display Ready**: Formatted for direct use in UI components
 *
 * **Usage Context:**
 * - **Destination Selection**: Primary use in airport picker interfaces
 * - **Search Functionality**: All fields support text-based filtering
 * - **UI Display**: Structured data for consistent presentation
 * - **Data Management**: Clean data model for airport collections
 *
 * **Search Capabilities:**
 * - **Multi-field Search**: Supports searching across all properties
 * - **Case Insensitive**: Search works regardless of text case
 * - **Partial Matching**: Finds airports with partial text matches
 * - **Flexible Queries**: Users can search by any airport attribute
 *
 * @param code The three-letter IATA airport code (e.g., "CDG", "LHR")
 * @param name The full name of the airport (e.g., "Charles de Gaulle")
 * @param city The city where the airport is located (e.g., "Paris")
 * @param country The country of the airport location (e.g., "France")
 */
data class AirportInfo(
    val code: String,
    val name: String,
    val city: String,
    val country: String
)

/**
 * A comprehensive bottom sheet for selecting flight departure and destination airports.
 *
 * This composable provides a complete airport selection interface with search
 * functionality and a curated list of major international airports. It supports
 * both departure and destination selection modes with context-aware behavior.
 *
 * **Airport Selection Features:**
 * - **Dual Mode Support**: Handles both departure and destination selection
 * - **Comprehensive Airport List**: Includes major international airports
 * - **Smart Search**: Real-time filtering across multiple airport attributes
 * - **Geographic Coverage**: Airports from Europe, Middle East, and beyond
 * - **Context-Aware Headers**: Dynamic titles based on selection mode
 *
 * **Search Functionality:**
 * - **Real-time Filtering**: Instant results as user types
 * - **Multi-field Search**: Searches across code, name, city, and country
 * - **Case Insensitive**: Finds matches regardless of text case
 * - **Partial Matching**: Supports incomplete search queries
 * - **Search Icon**: Visual indicator for search functionality
 *
 * **Airport Database:**
 * - **European Airports**: Major hubs in France, UK, Germany, Italy, Spain
 * - **Middle Eastern Airports**: Key airports in UAE, Qatar, Saudi Arabia
 * - **International Coverage**: Airports from Turkey, Egypt, Switzerland
 * - **Major Airlines**: Focus on airports served by major carriers
 * - **Tourist Destinations**: Popular travel destinations included
 *
 * **User Interface Elements:**
 * - **Modal Bottom Sheet**: Full-height interface for comfortable browsing
 * - **Search Field**: Prominent search input with placeholder text
 * - **Airport List**: Scrollable list with clear airport information
 * - **Dynamic Headers**: Context-aware titles for user guidance
 * - **Responsive Layout**: Adapts to different screen sizes
 *
 * **User Experience Features:**
 * - **Intuitive Search**: Familiar search interface pattern
 * - **Clear Information**: Well-organized airport details
 * - **Visual Hierarchy**: Proper typography and spacing
 * - **Interactive Elements**: Clickable airport items
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Use Cases:**
 * - Flight search departure selection
 * - Flight search destination selection
 * - Airport information browsing
 * - Travel planning airport research
 * - Multi-city trip planning
 *
 * **Localization Support:**
 * - **French Language**: Primary language support
 * - **Dynamic Strings**: Resource-based text management
 * - **Cultural Adaptation**: Language-specific airport names and locations
 *
 * @param isSelectingDeparture Whether this is for departure (true) or destination (false) selection
 * @param onDismiss Callback when the bottom sheet is dismissed
 * @param onAirportSelected Callback with the selected airport code
 * @param modifier Modifier to apply to the bottom sheet container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationBottomSheet(
    isSelectingDeparture: Boolean,
    onDismiss: () -> Unit,
    onAirportSelected: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    
    val airports = remember {
        listOf(
            AirportInfo("CDG", "Charles de Gaulle", "Paris", "France"),
            AirportInfo("ORY", "Orly", "Paris", "France"),
            AirportInfo("NCE", "Côte d'Azur", "Nice", "France"),
            AirportInfo("LYS", "Lyon-Saint Exupéry", "Lyon", "France"),
            AirportInfo("MRS", "Marseille Provence", "Marseille", "France"),
            AirportInfo("DXB", "Dubai International", "Dubaï", "Émirats arabes unis"),
            AirportInfo("AUH", "Abu Dhabi International", "Abu Dhabi", "Émirats arabes unis"),
            AirportInfo("DOH", "Hamad International", "Doha", "Qatar"),
            AirportInfo("RUH", "King Khalid International", "Riyadh", "Arabie saoudite"),
            AirportInfo("JED", "King Abdulaziz International", "Jeddah", "Arabie saoudite"),
            AirportInfo("CAI", "Cairo International", "Le Caire", "Égypte"),
            AirportInfo("IST", "Istanbul Airport", "Istanbul", "Turquie"),
            AirportInfo("LHR", "Heathrow", "Londres", "Royaume-Uni"),
            AirportInfo("FCO", "Leonardo da Vinci", "Rome", "Italie"),
            AirportInfo("BCN", "Barcelona-El Prat", "Barcelone", "Espagne"),
            AirportInfo("MAD", "Adolfo Suárez Madrid-Barajas", "Madrid", "Espagne"),
            AirportInfo("FRA", "Frankfurt am Main", "Francfort", "Allemagne"),
            AirportInfo("AMS", "Amsterdam Airport Schiphol", "Amsterdam", "Pays-Bas"),
            AirportInfo("BRU", "Brussels Airport", "Bruxelles", "Belgique"),
            AirportInfo("ZUR", "Zurich Airport", "Zurich", "Suisse")
        )
    }
    
    val filteredAirports = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            airports
        } else {
            airports.filter { airport ->
                airport.city.contains(searchQuery, ignoreCase = true) ||
                airport.country.contains(searchQuery, ignoreCase = true) ||
                airport.code.contains(searchQuery, ignoreCase = true) ||
                airport.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = RiyadhAirSpacing.lg)
        ) {
            // Header
            Text(
                text = if (isSelectingDeparture) stringResource(fr.benchaabane.riyadhair.presentation.R.string.choose_departure) else stringResource(fr.benchaabane.riyadhair.presentation.R.string.choose_destination),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = RiyadhAirSpacing.lg)
            )
            
            // Search Field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(stringResource(fr.benchaabane.riyadhair.presentation.R.string.search_destination)) },
                leadingIcon = {
                    Icon(
                        imageVector = RiyadhAirIcons.Search,
                        contentDescription = "Rechercher"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = RiyadhAirSpacing.md),
                shape = RiyadhAirShapes.medium,
                singleLine = true
            )
            
            // Airport List
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
            ) {
                items(filteredAirports) { airport ->
                    AirportItem(
                        airport = airport,
                        onClick = {
                            onAirportSelected(airport.code, airport.city)
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
        }
    }
}

/**
 * A clickable item component for displaying airport information in the selection list.
 *
 * This composable provides a consistent and interactive way to display airport
 * details in the destination selection interface. It presents airport information
 * in a clear, organized layout with visual hierarchy and proper touch targets.
 *
 * **Information Display:**
 * - **City Name**: Primary identifier displayed prominently
 * - **Airport Details**: Full name and country in secondary text
 * - **Airport Code**: IATA code displayed prominently on the right
 * - **Visual Hierarchy**: Clear distinction between primary and secondary information
 *
 * **Layout Structure:**
 * - **Left Section**: City name and airport details in a column
 * - **Right Section**: Airport code with sky blue accent color
 * - **Horizontal Arrangement**: Balanced layout with proper spacing
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **Interactive Features:**
 * - **Clickable Surface**: Entire item is interactive for easy selection
 * - **Visual Feedback**: Proper touch target sizing for mobile interaction
 * - **Callback Handling**: Notifies parent of airport selection
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Visual Design:**
 * - **Typography Hierarchy**: Clear distinction between information levels
 * - **Color Coding**: Sky blue accent for airport codes
 * - **Shape Consistency**: Uses RiyadhAir shape system
 * - **Spacing System**: Consistent spacing throughout the component
 * - **Surface Styling**: Material Design surface for proper elevation
 *
 * **User Experience:**
 * - **Clear Information**: Easy-to-read airport details
 * - **Intuitive Interaction**: Familiar clickable list item pattern
 * - **Visual Consistency**: Matches overall design system
 * - **Touch Friendly**: Adequate sizing for mobile interaction
 * - **Information Density**: Efficient use of available space
 *
 * **Use Cases:**
 * - Airport selection in destination picker
 * - Airport information display in lists
 * - Interactive airport browsing
 * - Flight search interface
 * - Travel planning tools
 *
 * @param airport The airport information to display
 * @param onClick Callback when the airport item is selected
 * @param modifier Modifier to apply to the airport item container
 */
@Composable
private fun AirportItem(
    airport: AirportInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RiyadhAirShapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(RiyadhAirSpacing.md),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = airport.city,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Text(
                    text = "${airport.name}, ${airport.country}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = airport.code,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = RiyadhAirColors.SkyBlue
            )
        }
    }
}

/**
 * Preview function for DestinationBottomSheet component.
 *
 * This preview demonstrates the DestinationBottomSheet with departure
 * selection mode, showing how the component appears in the design system
 * preview environment. It includes the complete bottom sheet layout
 * with search field and airport list.
 *
 * **Preview Configuration:**
 * - **Departure Mode**: Shows departure airport selection interface
 * - **Full Layout**: Displays complete bottom sheet with all components
 * - **Sample Data**: Shows airport list with realistic information
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Header Text**: Shows departure selection title
 * - **Search Field**: Displays search input with placeholder
 * - **Airport List**: Shows scrollable list of airports
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete airport selection flow
 */
@Preview(showBackground = true)
@Composable
private fun DestinationBottomSheetPreview() {
    RiyadhAirTheme {
        DestinationBottomSheet(
            isSelectingDeparture = true,
            onDismiss = {},
            onAirportSelected = {_,_ ->}
        )
    }
}
