package fr.benchaabane.riyadhair.designsystem.components.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.components.textfields.RiyadhAirTextField
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * A customizable dropdown selection component that follows RiyadhAir design system guidelines.
 *
 * This component provides a text field interface with a dropdown menu for selecting
 * from a list of options. It integrates seamlessly with the design system's text
 * field components and provides a consistent user experience for selection input.
 *
 * **Features:**
 * - **Text Field Interface**: Displays selected value in a read-only text field
 * - **Dropdown Menu**: Material 3 dropdown with option list
 * - **Generic Type Support**: Works with any data type through displayText function
 * - **Icon Integration**: Dropdown arrow icon for visual identification
 * - **State Management**: Handles selection state and menu expansion
 *
 * **User Interaction Flow:**
 * 1. User sees selected value in text field (or placeholder if no selection)
 * 2. User clicks on dropdown arrow or text field
 * 3. Dropdown menu appears with available options
 * 4. User selects an option from the list
 * 5. Menu closes and text field updates with selection
 *
 * **Generic Type Support:**
 * - Works with any data type (String, Int, custom objects, etc.)
 * - Customizable text display through displayText parameter
 * - Type-safe value handling and change callbacks
 *
 * **Usage Examples:**
 * ```kotlin
 * // String-based dropdown
 * var selectedCountry by remember { mutableStateOf<String?>(null) }
 * RiyadhAirDropdown(
 *     value = selectedCountry,
 *     onValueChange = { selectedCountry = it },
 *     options = listOf("USA", "UK", "Germany", "France"),
 *     label = "Country",
 *     placeholder = "Select a country"
 * )
 *
 * // Custom object dropdown with custom display
 * data class User(val id: Int, val name: String)
 * var selectedUser by remember { mutableStateOf<User?>(null) }
 * RiyadhAirDropdown(
 *     value = selectedUser,
 *     onValueChange = { selectedUser = it },
 *     options = userList,
 *     displayText = { user -> user.name },
 *     label = "User"
 * )
 * ```
 *
 * **Design Features:**
 * - Material 3 dropdown menu styling
 * - Consistent with RiyadhAir text field design
 * - Proper spacing and typography
 * - Accessible text field with read-only behavior
 * - Visual feedback for interactive elements
 *
 * @param T The type of values in the dropdown options
 * @param value The currently selected value, or null if no selection
 * @param onValueChange Callback invoked when a new value is selected
 * @param options The list of available options to choose from
 * @param modifier Modifier to apply to the dropdown container
 * @param label Optional label text for the text field
 * @param placeholder Optional placeholder text when no value is selected
 * @param enabled Whether the dropdown is interactive
 * @param displayText Function to convert option values to display text
 */
@Composable
fun <T> RiyadhAirDropdown(
    value: T?,
    onValueChange: (T) -> Unit,
    options: List<T>,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    displayText: (T) -> String = { it.toString() }
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(modifier = modifier) {
        RiyadhAirTextField(
            value = value?.let(displayText) ?: "",
            onValueChange = { },
            label = label,
            placeholder = placeholder,
            readOnly = true,
            enabled = enabled,
            trailingIcon = RiyadhAirIcons.ArrowDropDown,
            onTrailingIconClick = { expanded = !expanded }
        )
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(displayText(option)) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

/**
 * A specialized dropdown component for airport selection.
 *
 * This component provides a pre-configured dropdown specifically designed
 * for airport selection in flight booking scenarios. It includes a curated
 * list of major airports with their IATA codes and city names for easy
 * identification.
 *
 * **Airport List Coverage:**
 * - **Gulf Region**: RUH (Riyadh), JED (Jeddah), DXB (Dubai), DOH (Doha)
 * - **Middle East**: KWI (Kuwait), BAH (Bahrain), AUH (Abu Dhabi)
 * - **North Africa**: CAI (Cairo), AMM (Amman), BEY (Beirut)
 * - **Europe**: LHR (London), CDG (Paris), FRA (Frankfurt), IST (Istanbul)
 * - **North America**: JFK (New York)
 *
 * **Format**: "IATA - City Name" (e.g., "RUH - Riyadh")
 * **Total Airports**: 17 major international airports
 *
 * **Use Cases:**
 * - Flight search forms
 * - Departure/arrival airport selection
 * - Travel booking interfaces
 * - Airport code lookup
 *
 * **Usage Example:**
 * ```kotlin
 * var departureAirport by remember { mutableStateOf<String?>(null) }
 * 
 * AirportDropdown(
 *     selectedAirport = departureAirport,
 *     onAirportChange = { departureAirport = it },
 *     label = "From",
 *     placeholder = "Select departure airport"
 * )
 * ```
 *
 * **Design Features:**
 * - Consistent with RiyadhAir dropdown styling
 * - Airport codes for quick identification
 * - City names for user-friendly selection
 * - Proper labeling for form contexts
 *
 * @param selectedAirport The currently selected airport code, or null if no selection
 * @param onAirportChange Callback invoked when a new airport is selected
 * @param modifier Modifier to apply to the dropdown container
 * @param label Optional label text for the dropdown
 * @param placeholder Optional placeholder text when no airport is selected
 */
@Composable
fun AirportDropdown(
    selectedAirport: String?,
    onAirportChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null
) {
    val airports = listOf(
        "RUH - Riyadh",
        "JED - Jeddah", 
        "DXB - Dubai",
        "DOH - Doha",
        "KWI - Kuwait",
        "BAH - Bahrain",
        "AUH - Abu Dhabi",
        "SHJ - Sharjah",
        "MCT - Muscat",
        "CAI - Cairo",
        "AMM - Amman",
        "BEY - Beirut",
        "LHR - London Heathrow",
        "CDG - Paris Charles de Gaulle",
        "FRA - Frankfurt",
        "IST - Istanbul",
        "JFK - New York JFK"
    )
    
    RiyadhAirDropdown(
        value = selectedAirport,
        onValueChange = onAirportChange,
        options = airports,
        modifier = modifier,
        label = label,
        placeholder = placeholder
    )
}

/**
 * A specialized dropdown component for passenger count selection.
 *
 * This component provides a pre-configured dropdown specifically designed
 * for selecting the number of passengers in flight booking scenarios.
 * It includes passenger counts from 1 to 9 with proper singular/plural
 * text formatting.
 *
 * **Passenger Range**: 1 to 9 passengers
 * **Text Formatting**: 
 * - Single passenger: "1 Passenger"
 * - Multiple passengers: "X Passengers"
 *
 * **Use Cases:**
 * - Flight booking forms
 * - Passenger count selection
 * - Group travel planning
 * - Reservation management
 *
 * **Usage Example:**
 * ```kotlin
 * var passengerCount by remember { mutableStateOf(1) }
 * 
 * PassengerCountDropdown(
 *     passengerCount = passengerCount,
 *     onPassengerCountChange = { passengerCount = it },
 *     label = "Number of Passengers"
 * )
 * ```
 *
 * **Design Features:**
 * - Consistent with RiyadhAir dropdown styling
 * - Proper singular/plural text handling
 * - Clear labeling for passenger selection
 * - Intuitive number-based options
 *
 * @param passengerCount The currently selected number of passengers
 * @param onPassengerCountChange Callback invoked when passenger count changes
 * @param modifier Modifier to apply to the dropdown container
 * @param label The label text for the passenger count dropdown
 */
@Composable
fun PassengerCountDropdown(
    passengerCount: Int,
    onPassengerCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Passengers"
) {
    val passengerOptions = (1..9).toList()
    
    RiyadhAirDropdown(
        value = passengerCount,
        onValueChange = onPassengerCountChange,
        options = passengerOptions,
        modifier = modifier,
        label = label,
        displayText = { count ->
            when (count) {
                1 -> "1 Passenger"
                else -> "$count Passengers"
            }
        }
    )
}

/**
 * Preview function for RiyadhAirDropdown components.
 *
 * This preview demonstrates all dropdown variants including the generic
 * dropdown, specialized airport dropdown, and passenger count dropdown.
 * It provides a comprehensive view of the selection components for
 * designers and developers during the design process.
 *
 * **Preview Content:**
 * - **Airport Dropdown**: Airport selection with IATA codes and city names
 * - **Passenger Count Dropdown**: Passenger count selection (1-9)
 * - **Generic Dropdown**: Travel class selection with custom options
 *
 * **Preview Configuration:**
 * - Uses RiyadhAir theme for consistent styling
 * - Demonstrates different use cases and configurations
 * - Shows proper spacing and layout relationships
 * - Includes state management examples for all dropdowns
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirDropdownPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            var selectedAirport by remember { mutableStateOf<String?>(null) }
            AirportDropdown(
                selectedAirport = selectedAirport,
                onAirportChange = { selectedAirport = it },
                label = "From",
                placeholder = "Select departure airport"
            )
            
            var passengerCount by remember { mutableStateOf(1) }
            PassengerCountDropdown(
                passengerCount = passengerCount,
                onPassengerCountChange = { passengerCount = it }
            )
            
            var travelClass by remember { mutableStateOf<String?>(null) }
            RiyadhAirDropdown(
                value = travelClass,
                onValueChange = { travelClass = it },
                options = listOf("Economy", "Premium Economy", "Business", "First Class"),
                label = "Travel Class",
                placeholder = "Select class"
            )
        }
    }
}
