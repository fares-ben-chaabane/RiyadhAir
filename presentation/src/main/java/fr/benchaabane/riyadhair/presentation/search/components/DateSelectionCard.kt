package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * A comprehensive card component for selecting and displaying flight travel dates.
 *
 * This composable provides an intuitive interface for managing flight date selection,
 * supporting both one-way and round-trip scenarios. It features a clean card design
 * with integrated date picker functionality and clear visual representation of
 * selected dates.
 *
 * **Date Selection Features:**
 * - **One-Way Flights**: Simple departure date selection
 * - **Round-Trip Flights**: Departure and return date selection
 * - **Date Range Picker**: Material Design 3 date range picker dialog
 * - **Flexible Selection**: Optional return date for one-way trips
 * - **Date Validation**: Ensures logical date ordering
 *
 * **User Interface Elements:**
 * - **Interactive Card**: Clickable surface to open date picker
 * - **Date Display**: Clear presentation of selected dates
 * - **Visual Icons**: Date range icon and navigation arrow
 * - **Typography Hierarchy**: Clear distinction between labels and values
 * - **Color Coding**: Sky blue accents for interactive elements
 *
 * **Date Display Modes:**
 * - **No Dates Selected**: Shows placeholder text for date selection
 * - **Departure Only**: Displays outbound date with option for return
 * - **Round Trip**: Shows both departure and return dates with arrow separator
 * - **French Formatting**: Uses French locale for date display
 * - **Dynamic Content**: Adapts layout based on selection state
 *
 * **Date Picker Integration:**
 * - **Modal Dialog**: Full-screen date range picker for easy selection
 * - **Range Selection**: Supports start and end date selection
 * - **Confirmation Flow**: Clear confirm/cancel actions
 * - **State Management**: Handles temporary and final date selections
 * - **Accessibility**: Proper content descriptions and keyboard navigation
 *
 * **Layout Structure:**
 * - **Header Section**: Icon, title, and navigation arrow
 * - **Date Display**: Dynamic content based on selection state
 * - **Responsive Design**: Adapts to different content lengths
 * - **Proper Spacing**: Consistent RiyadhAir spacing system
 * - **Touch Targets**: Adequate sizing for mobile interaction
 *
 * **Visual Design:**
 * - **Card Container**: Material Design 3 card with elevation and border
 * - **Icon Integration**: Date range icon for clear visual communication
 * - **Color Scheme**: Sky blue for primary elements, proper contrast for text
 * - **Shape Consistency**: Uses RiyadhAir shape system for rounded corners
 * - **Typography System**: Consistent text styles and font weights
 *
 * **User Experience Features:**
 * - **Clear Information**: Easy-to-read date information
 * - **Intuitive Interaction**: Familiar clickable card pattern
 * - **Visual Feedback**: Clear indication of interactive areas
 * - **Date Formatting**: User-friendly date display format
 * - **Accessibility**: Proper content descriptions and touch targets
 *
 * **Use Cases:**
 * - Flight search date selection
 * - Round-trip booking configuration
 * - One-way flight date picking
 * - Date modification in existing bookings
 * - Travel planning date selection
 *
 * **Localization Support:**
 * - **French Language**: Primary language support with French locale
 * - **Dynamic Strings**: Resource-based text management
 * - **Date Formatting**: French date format (e.g., "lun 15 mar")
 * - **Cultural Adaptation**: Language-specific date patterns
 *
 * **Business Logic:**
 * - **Date Validation**: Prevents invalid date combinations
 * - **Flexible Selection**: Supports both one-way and round-trip scenarios
 * - **State Management**: Handles temporary and confirmed selections
 * - **Callback Handling**: Notifies parent of final date selections
 *
 * @param departureDate The selected departure date (can be null)
 * @param returnDate The selected return date (can be null for one-way trips)
 * @param onDateRangeSelected Callback with the final selected date range
 * @param modifier Modifier to apply to the date selection card container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionCard(
    departureDate: LocalDate?,
    returnDate: LocalDate?,
    onDateRangeSelected: (LocalDate?, LocalDate?) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDateRangePicker by remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ofPattern("EEE dd MMM", Locale.FRENCH)
    
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDateRangePicker = true }
                .padding(RiyadhAirSpacing.lg)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
                ) {
                    Icon(
                        imageVector = RiyadhAirIcons.DateRange,
                        contentDescription = "Sélectionner les dates",
                        tint = RiyadhAirColors.SkyBlue
                    )
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.travel_dates),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                                            imageVector = RiyadhAirIcons.ArrowRight,
                    contentDescription = "Ouvrir le calendrier",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.md))
            
            // Date Display
            if (departureDate != null || returnDate != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Departure Date
                    Column {
                        Text(
                            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.outbound),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = departureDate?.format(dateFormatter) ?: "Non sélectionné",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    if (returnDate != null) {
                        Text(
                            text = "→",
                            style = MaterialTheme.typography.headlineSmall,
                            color = RiyadhAirColors.SkyBlue
                        )
                        
                        // Return Date
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.return_flight),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = returnDate.format(dateFormatter),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.select_travel_dates),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
    
    // Date Range Picker Dialog
    if (showDateRangePicker) {
        val dateRangePickerState = rememberDateRangePickerState()
        
        DatePickerDialog(
            onDismissRequest = { showDateRangePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val startDate = dateRangePickerState.selectedStartDateMillis?.let { millis ->
                            LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000L))
                        }
                        val endDate = dateRangePickerState.selectedEndDateMillis?.let { millis ->
                            LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000L))
                        }
                        onDateRangeSelected(startDate, endDate)
                        showDateRangePicker = false
                    },
                    enabled = dateRangePickerState.selectedStartDateMillis != null
                ) {
                    Text(stringResource(fr.benchaabane.riyadhair.presentation.R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDateRangePicker = false }) {
                    Text(stringResource(fr.benchaabane.riyadhair.presentation.R.string.cancel))
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                title = {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.select_your_dates),
                        modifier = Modifier.padding(RiyadhAirSpacing.lg)
                    )
                },
                headline = {
                    Text(
                        text = if (dateRangePickerState.selectedEndDateMillis != null) 
                            stringResource(fr.benchaabane.riyadhair.presentation.R.string.outbound_and_return) 
                        else 
                            stringResource(fr.benchaabane.riyadhair.presentation.R.string.outbound_date_optional_return),
                        modifier = Modifier.padding(horizontal = RiyadhAirSpacing.lg)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Preview function for DateSelectionCard component.
 *
 * This preview demonstrates the DateSelectionCard in various states,
 * showing how the component appears in the design system preview
 * environment. It includes multiple scenarios to showcase different
 * date selection states and layouts.
 *
 * **Preview Configuration:**
 * - **Multiple States**: Shows three different date selection scenarios
 * - **Sample Dates**: Uses realistic dates for testing (March 2024)
 * - **Full Layout**: Displays complete cards with all components
 * - **Applied Spacing**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Scenarios:**
 * - **No Dates Selected**: Empty state with placeholder text
 * - **Departure Only**: Single date selection for one-way trips
 * - **Round Trip**: Both departure and return dates with arrow separator
 *
 * **Preview Features:**
 * - **State Variations**: Demonstrates all possible date selection states
 * - **Layout Adaptation**: Shows how the card adapts to different content
 * - **Visual Hierarchy**: Displays proper spacing and typography
 * - **Interactive Elements**: Shows clickable card design
 * - **Date Formatting**: Demonstrates French date format display
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance in all states
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **State Management**: Shows how different date combinations are handled
 * - **Theme Consistency**: Demonstrates RiyadhAir design system integration
 * - **User Experience**: Shows the complete date selection interface flow
 *
 * **Sample Data:**
 * - **Departure Date**: March 15, 2024 (Friday)
 * - **Return Date**: March 22, 2024 (Friday)
 * - **Date Format**: French locale (e.g., "ven 15 mar")
 * - **Trip Duration**: 7-day round trip example
 */
@Preview(showBackground = true)
@Composable
private fun DateSelectionCardPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            DateSelectionCard(
                departureDate = null,
                returnDate = null,
                onDateRangeSelected = { _, _ -> }
            )
            
            DateSelectionCard(
                departureDate = LocalDate.of(2024, 3, 15),
                returnDate = null,
                onDateRangeSelected = { _, _ -> }
            )
            
            DateSelectionCard(
                departureDate = LocalDate.of(2024, 3, 15),
                returnDate = LocalDate.of(2024, 3, 22),
                onDateRangeSelected = { _, _ -> }
            )
        }
    }
}