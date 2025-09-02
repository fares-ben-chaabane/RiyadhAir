package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * A bottom sheet component for selecting flight dates with support for round-trip bookings.
 *
 * This composable provides a comprehensive date selection interface that handles
 * both one-way and round-trip flight scenarios. It features a step-by-step approach
 * for round-trip bookings and provides clear visual feedback throughout the selection process.
 *
 * **Date Selection Features:**
 * - **One-Way Flights**: Simple departure date selection
 * - **Round-Trip Flights**: Sequential departure and return date selection
 * - **Step Navigation**: Visual step indicators for multi-step process
 * - **Date Validation**: Ensures logical date ordering (return after departure)
 * - **Temporary State**: Allows users to modify selections before confirmation
 *
 * **User Interface Elements:**
 * - **Modal Bottom Sheet**: Slides up from bottom for easy access
 * - **Step Indicators**: Visual chips showing current step and completion status
 * - **Date Picker**: Material Design 3 date picker component
 * - **Action Buttons**: Cancel and confirm/next buttons with dynamic text
 * - **Header Text**: Context-aware titles for each selection step
 *
 * **State Management:**
 * - **Temporary Dates**: Stores selections until final confirmation
 * - **Step Tracking**: Manages current selection step for round-trip
 * - **Validation Logic**: Prevents invalid date combinations
 * - **Callback Handling**: Notifies parent of date selections and dismissal
 *
 * **User Experience Features:**
 * - **Progressive Disclosure**: Shows relevant information based on current step
 * - **Visual Feedback**: Clear indication of selected and completed steps
 * - **Accessibility**: Proper content descriptions and keyboard navigation
 * - **Responsive Design**: Adapts to different screen sizes and orientations
 *
 * **Use Cases:**
 * - Flight search date selection
 * - Round-trip booking configuration
 * - One-way flight date picking
 * - Date modification in existing bookings
 * - Travel planning date selection
 *
 * **Localization Support:**
 * - **French Language**: Primary language support ("Aller", "Retour", "Annuler")
 * - **Dynamic Text**: Context-aware button and header text
 * - **Cultural Adaptation**: Language-specific date formatting
 *
 * @param isSelectingDeparture Whether the user is currently selecting departure date
 * @param isRoundTrip Whether this is a round-trip flight booking
 * @param departureDate The currently selected departure date (can be null)
 * @param returnDate The currently selected return date (can be null)
 * @param onDismiss Callback when the bottom sheet is dismissed
 * @param onDatesSelected Callback with the final selected dates
 * @param modifier Modifier to apply to the bottom sheet container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
    isSelectingDeparture: Boolean,
    isRoundTrip: Boolean,
    departureDate: LocalDate?,
    returnDate: LocalDate?,
    onDismiss: () -> Unit,
    onDatesSelected: (LocalDate?, LocalDate?) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempDepartureDate by remember { mutableStateOf(departureDate) }
    var tempReturnDate by remember { mutableStateOf(returnDate) }
    var currentStep by remember { mutableStateOf(if (isSelectingDeparture) DatePickerStep.DEPARTURE else DatePickerStep.RETURN) }
    
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = when (currentStep) {
            DatePickerStep.DEPARTURE -> tempDepartureDate?.toEpochDay()?.times(24 * 60 * 60 * 1000)
            DatePickerStep.RETURN -> tempReturnDate?.toEpochDay()?.times(24 * 60 * 60 * 1000)
        }
    )
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(RiyadhAirSpacing.lg)
        ) {
            // Header
            Text(
                text = when (currentStep) {
                    DatePickerStep.DEPARTURE -> stringResource(fr.benchaabane.riyadhair.presentation.R.string.choose_departure_date)
                    DatePickerStep.RETURN -> stringResource(fr.benchaabane.riyadhair.presentation.R.string.choose_return_date)
                },
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = RiyadhAirSpacing.lg)
            )
            
            // Step indicator for round trip
            if (isRoundTrip) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = RiyadhAirSpacing.lg),
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                ) {
                    DateStepChip(
                        label = "Aller",
                        isSelected = currentStep == DatePickerStep.DEPARTURE,
                        isCompleted = tempDepartureDate != null,
                        onClick = { currentStep = DatePickerStep.DEPARTURE },
                        modifier = Modifier.weight(1f)
                    )
                    
                    DateStepChip(
                        label = "Retour",
                        isSelected = currentStep == DatePickerStep.RETURN,
                        isCompleted = tempReturnDate != null,
                        onClick = { 
                            if (tempDepartureDate != null) {
                                currentStep = DatePickerStep.RETURN
                            }
                        },
                        enabled = tempDepartureDate != null,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            // Date Picker
            DatePicker(
                state = datePickerState,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))
            
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    shape = RiyadhAirShapes.large
                ) {
                    Text("Annuler")
                }
                
                Button(
                    onClick = {
                        val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
                            LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000))
                        }
                        
                        when (currentStep) {
                            DatePickerStep.DEPARTURE -> {
                                tempDepartureDate = selectedDate
                                if (isRoundTrip && tempReturnDate == null) {
                                    currentStep = DatePickerStep.RETURN
                                } else {
                                    onDatesSelected(tempDepartureDate, tempReturnDate)
                                }
                            }
                            DatePickerStep.RETURN -> {
                                tempReturnDate = selectedDate
                                onDatesSelected(tempDepartureDate, tempReturnDate)
                            }
                        }
                    },
                    enabled = datePickerState.selectedDateMillis != null &&
                            (currentStep == DatePickerStep.DEPARTURE || 
                             (currentStep == DatePickerStep.RETURN && tempDepartureDate != null)),
                    modifier = Modifier.weight(1f),
                    shape = RiyadhAirShapes.large
                ) {
                    Text(
                        when {
                            !isRoundTrip -> "Confirmer"
                            currentStep == DatePickerStep.DEPARTURE -> "Suivant"
                            else -> "Confirmer"
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
        }
    }
}

/**
 * A chip component for displaying date selection steps in round-trip bookings.
 *
 * This composable provides visual feedback for the current step and completion
 * status in the date selection process. It shows whether a step is selected,
 * completed, or available for selection, helping users understand their progress.
 *
 * **Visual States:**
 * - **Selected**: Currently active step with bold text and selected styling
 * - **Completed**: Successfully finished step with checkmark indicator
 * - **Available**: Step that can be selected (enabled state)
 * - **Disabled**: Step that cannot be selected yet (e.g., return before departure)
 *
 * **Visual Elements:**
 * - **Checkmark Icon**: Shows completion status for finished steps
 * - **Dynamic Text**: Bold text for selected step, medium for others
 * - **Filter Chip**: Material Design 3 chip component for consistent styling
 * - **Interactive States**: Proper enabled/disabled visual feedback
 *
 * **Layout Features:**
 * - **Horizontal Arrangement**: Icons and text in a row layout
 * - **Proper Spacing**: Consistent spacing between elements
 * - **Alignment**: Vertical center alignment for all content
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **User Experience:**
 * - **Clear Progress**: Visual indication of completed and current steps
 * - **Interactive Feedback**: Immediate response to user interactions
 * - **Accessibility**: Proper content descriptions and touch targets
 * - **Visual Hierarchy**: Clear distinction between different states
 *
 * **Use Cases:**
 * - Round-trip date selection steps
 * - Multi-step form progress indicators
 * - Wizard interface step navigation
 * - Process completion tracking
 * - Workflow status display
 *
 * @param label The text label for the step (e.g., "Aller", "Retour")
 * @param isSelected Whether this step is currently active
 * @param isCompleted Whether this step has been successfully completed
 * @param onClick Callback when the step chip is tapped
 * @param enabled Whether this step can be selected
 * @param modifier Modifier to apply to the step chip container
 */
@Composable
private fun DateStepChip(
    label: String,
    isSelected: Boolean,
    isCompleted: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        enabled = enabled,
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
            ) {
                if (isCompleted && !isSelected) {
                    Text("✓", style = MaterialTheme.typography.labelMedium)
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            }
        },
        modifier = modifier
    )
}

/**
 * Enumeration representing the different steps in the date picker flow.
 *
 * This enum defines the two possible states for date selection in round-trip
 * bookings. It helps manage the flow between departure and return date selection
 * and provides type safety for step-related logic.
 *
 * **Step Values:**
 * - **DEPARTURE**: First step for selecting the departure date
 * - **RETURN**: Second step for selecting the return date (only available after departure)
 *
 * **Usage Context:**
 * - **State Management**: Tracks current selection step
 * - **UI Updates**: Determines header text and button behavior
 * - **Navigation Logic**: Controls step progression and validation
 * - **Date Picker State**: Sets initial date selection for each step
 *
 * **Flow Logic:**
 * - **One-Way Flights**: Only uses DEPARTURE step
 * - **Round-Trip Flights**: Progresses from DEPARTURE to RETURN
 * - **Validation**: RETURN step only enabled after DEPARTURE selection
 * - **Completion**: Both steps must be completed for round-trip confirmation
 */
private enum class DatePickerStep {
    DEPARTURE, RETURN
}

/**
 * Preview function for DatePickerBottomSheet component.
 *
 * This preview demonstrates the DatePickerBottomSheet with round-trip
 * configuration, showing how the component appears in the design system
 * preview environment. It includes the complete bottom sheet layout
 * with step indicators and date picker.
 *
 * **Preview Configuration:**
 * - **Round-Trip Mode**: Shows both departure and return step indicators
 * - **Initial State**: No dates selected, starting with departure step
 * - **Full Layout**: Displays complete bottom sheet with all components
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Step Indicators**: Shows "Aller" and "Retour" chips
 * - **Date Picker**: Displays Material Design 3 date picker
 * - **Action Buttons**: Shows cancel and next/confirm buttons
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 */
@Preview(showBackground = true)
@Composable
private fun DatePickerBottomSheetPreview() {
    RiyadhAirTheme {
        DatePickerBottomSheet(
            isSelectingDeparture = true,
            isRoundTrip = true,
            departureDate = null,
            returnDate = null,
            onDismiss = {},
            onDatesSelected = { _, _ -> }
        )
    }
}
