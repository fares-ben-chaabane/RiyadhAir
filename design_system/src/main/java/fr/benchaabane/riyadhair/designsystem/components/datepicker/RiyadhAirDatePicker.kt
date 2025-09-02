package fr.benchaabane.riyadhair.designsystem.components.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import fr.benchaabane.riyadhair.designsystem.components.textfields.RiyadhAirTextField
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A customizable date picker component that follows RiyadhAir design system guidelines.
 *
 * This component provides a text field interface for date selection with a popup
 * calendar picker. It integrates seamlessly with the design system's text field
 * components and provides a consistent user experience for date input.
 *
 * **Features:**
 * - **Text Field Interface**: Displays selected date in formatted text
 * - **Popup Calendar**: Material 3 date picker in a custom popup
 * - **Custom Formatting**: Configurable date format with locale support
 * - **Icon Integration**: Date range icon for visual identification
 * - **State Management**: Handles date selection and display state
 *
 * **User Interaction Flow:**
 * 1. User sees formatted date in text field (or placeholder if no date selected)
 * 2. User clicks on date range icon or text field
 * 3. Popup calendar appears with current selection
 * 4. User selects new date and confirms with OK button
 * 5. Text field updates with newly formatted date
 *
 * **Date Formatting:**
 * - Default format: "MMM dd, yyyy" (e.g., "Jan 15, 2024")
 * - Customizable through dateFormat parameter
 * - Automatically adapts to system locale
 * - Handles null dates gracefully
 *
 * **Usage Examples:**
 * ```kotlin
 * var selectedDate by remember { mutableStateOf<Long?>(null) }
 * 
 * RiyadhAirDatePicker(
 *     selectedDate = selectedDate,
 *     onDateSelected = { selectedDate = it },
 *     label = "Birth Date",
 *     placeholder = "Select your birth date",
 *     dateFormat = "dd/MM/yyyy"
 * )
 * ```
 *
 * **Design Features:**
 * - Material 3 date picker integration
 * - Custom popup with shadow and elevation
 * - Consistent spacing and typography
 * - Responsive layout with proper alignment
 * - Accessible text field with read-only behavior
 *
 * @param selectedDate The currently selected date in milliseconds, or null if no date selected
 * @param onDateSelected Callback invoked when a new date is selected
 * @param modifier Modifier to apply to the date picker container
 * @param label Optional label text for the text field
 * @param placeholder Optional placeholder text when no date is selected
 * @param enabled Whether the date picker is interactive
 * @param dateFormat The format string for displaying the selected date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiyadhAirDatePicker(
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    dateFormat: String = "MMM dd, yyyy"
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
    )
    
    val formattedDate by remember(selectedDate) {
        derivedStateOf {
            selectedDate?.let {
                SimpleDateFormat(dateFormat, Locale.getDefault()).format(Date(it))
            } ?: ""
        }
    }
    
    Box(modifier = modifier.fillMaxWidth()) {
        RiyadhAirTextField(
            value = formattedDate,
            onValueChange = { },
            label = label,
            placeholder = placeholder,
            readOnly = true,
            enabled = enabled,
            trailingIcon = RiyadhAirIcons.DateRange,
            onTrailingIconClick = { showDatePicker = !showDatePicker }
        )
        
        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp
                ) {
                    Column {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false,
                            title = null,
                            headline = null
                        )
                        
                        // Action buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(RiyadhAirSpacing.lg),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = { showDatePicker = false }
                            ) {
                                Text("Cancel")
                            }
                            
                            TextButton(
                                onClick = {
                                    onDateSelected(datePickerState.selectedDateMillis)
                                    showDatePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * A dialog-based date picker component for modal date selection.
 *
 * This component provides a full-screen dialog interface for date selection,
 * using the Material 3 DatePickerDialog. It's suitable for scenarios where
 * a modal approach is preferred over the popup style.
 *
 * **Features:**
 * - **Modal Dialog**: Full-screen dialog with backdrop
 * - **Material 3 Integration**: Native Material 3 date picker dialog
 * - **Action Buttons**: Built-in OK and Cancel buttons
 * - **Custom Title**: Configurable dialog title
 * - **State Management**: Handles date selection and dialog state
 *
 * **User Interaction Flow:**
 * 1. Dialog appears with current date selection
 * 2. User navigates calendar to select desired date
 * 3. User confirms selection with OK button
 * 4. Dialog dismisses and callback is invoked
 * 5. User can cancel at any time with Cancel button
 *
 * **Use Cases:**
 * - Modal date selection workflows
 * - Full-screen date picker experiences
 * - Consistent with Material 3 design patterns
 * - Better accessibility for complex date selection
 *
 * **Usage Example:**
 * ```kotlin
 * var showDialog by remember { mutableStateOf(false) }
 * var selectedDate by remember { mutableStateOf<Long?>(null) }
 * 
 * RiyadhAirDatePickerDialog(
 *     selectedDate = selectedDate,
 *     onDateSelected = { selectedDate = it },
 *     onDismiss = { showDialog = false },
 *     title = "Choose Your Travel Date"
 * )
 * ```
 *
 * **Design Features:**
 * - Material 3 dialog styling
 * - Consistent button placement and styling
 * - Proper title formatting and spacing
 * - Responsive layout for different screen sizes
 *
 * @param selectedDate The currently selected date in milliseconds, or null if no date selected
 * @param onDateSelected Callback invoked when a new date is confirmed
 * @param onDismiss Callback invoked when the dialog is dismissed
 * @param modifier Modifier to apply to the dialog
 * @param title The title text displayed in the dialog header
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiyadhAirDatePickerDialog(
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Select Date"
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
    )
    
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        modifier = modifier
    ) {
        DatePicker(
            state = datePickerState,
            title = {
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
    }
}

/**
 * A specialized date picker component for flight booking scenarios.
 *
 * This component provides a convenient way to select both departure and return
 * dates for flight bookings. It automatically handles the round-trip vs one-way
 * logic and provides consistent labeling and placeholder text.
 *
 * **Features:**
 * - **Dual Date Selection**: Departure and return date pickers
 * - **Round-Trip Logic**: Conditionally shows return date picker
 * - **Consistent Styling**: Uses RiyadhAirDatePicker for both fields
 * - **Proper Spacing**: Applies design system spacing between fields
 * - **Flexible Configuration**: Supports both round-trip and one-way flights
 *
 * **Layout Structure:**
 * - **Departure Date**: Always visible with flight-specific labeling
 * - **Return Date**: Conditionally visible based on isRoundTrip parameter
 * - **Vertical Arrangement**: Fields stacked with consistent spacing
 * - **Responsive Design**: Adapts to different screen sizes
 *
 * **Use Cases:**
 * - Flight search interfaces
 * - Travel booking forms
 * - Itinerary planning tools
 * - Multi-date selection workflows
 *
 * **Usage Examples:**
 * ```kotlin
 * var departureDate by remember { mutableStateOf<Long?>(null) }
 * var returnDate by remember { mutableStateOf<Long?>(null) }
 * 
 * FlightDatePicker(
 *     departureDate = departureDate,
 *     returnDate = returnDate,
 *     onDepartureDateSelected = { departureDate = it },
 *     onReturnDateSelected = { returnDate = it },
 *     isRoundTrip = true
 * )
 * 
 * // For one-way flights
 * FlightDatePicker(
 *     departureDate = departureDate,
 *     returnDate = null,
 *     onDepartureDateSelected = { departureDate = it },
 *     onReturnDateSelected = { /* Not used for one-way */ },
 *     isRoundTrip = false
 * )
 * ```
 *
 * **Design Considerations:**
 * - Consistent field labeling for flight context
 * - Appropriate placeholder text for user guidance
 * - Proper spacing between related fields
 * - Responsive layout for mobile and tablet use
 *
 * @param departureDate The selected departure date in milliseconds
 * @param returnDate The selected return date in milliseconds (for round-trip)
 * @param onDepartureDateSelected Callback for departure date selection
 * @param onReturnDateSelected Callback for return date selection
 * @param modifier Modifier to apply to the date picker container
 * @param isRoundTrip Whether to show the return date picker
 */
@Composable
fun FlightDatePicker(
    departureDate: Long?,
    returnDate: Long?,
    onDepartureDateSelected: (Long?) -> Unit,
    onReturnDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    isRoundTrip: Boolean = true
) {
            Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
        RiyadhAirDatePicker(
            selectedDate = departureDate,
            onDateSelected = onDepartureDateSelected,
            label = "Departure Date",
            placeholder = "Select departure date"
        )
        
        if (isRoundTrip) {
            RiyadhAirDatePicker(
                selectedDate = returnDate,
                onDateSelected = onReturnDateSelected,
                label = "Return Date",
                placeholder = "Select return date"
            )
        }
    }
}

/**
 * Preview function for RiyadhAirDatePicker components.
 *
 * This preview demonstrates both the standard date picker and the specialized
 * flight date picker. It provides a comprehensive view of the date picker
 * components for designers and developers during the design process.
 *
 * **Preview Content:**
 * - **Standard Date Picker**: Single date selection with label and placeholder
 * - **Flight Date Picker**: Dual date selection for departure and return
 * - **Interactive State**: Shows how components appear with and without selections
 *
 * **Preview Configuration:**
 * - Uses RiyadhAir theme for consistent styling
 * - Demonstrates different use cases and configurations
 * - Shows proper spacing and layout relationships
 * - Includes state management examples
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirDatePickerPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            var selectedDate by remember { mutableStateOf<Long?>(null) }
            RiyadhAirDatePicker(
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it },
                label = "Travel Date",
                placeholder = "Select your travel date"
            )
            
            var departureDate by remember { mutableStateOf<Long?>(null) }
            var returnDate by remember { mutableStateOf<Long?>(null) }
            FlightDatePicker(
                departureDate = departureDate,
                returnDate = returnDate,
                onDepartureDateSelected = { departureDate = it },
                onReturnDateSelected = { returnDate = it }
            )
        }
    }
}
