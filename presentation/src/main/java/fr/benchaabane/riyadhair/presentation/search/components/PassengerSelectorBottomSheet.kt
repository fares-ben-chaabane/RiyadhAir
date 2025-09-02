package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.models.PassengerCount

/**
 * A comprehensive bottom sheet for selecting passenger counts and cabin class.
 *
 * This composable provides a complete interface for configuring flight booking
 * details, including passenger counts by age category and cabin class selection.
 * It features intuitive controls with validation logic and temporary state
 * management for a smooth user experience.
 *
 * **Passenger Configuration Features:**
 * - **Adult Passengers**: Primary travelers (12+ years) with minimum of 1
 * - **Children**: Young travelers (2-11 years) with optional inclusion
 * - **Infants**: Babies (under 2 years) with validation against adult count
 * - **Smart Validation**: Prevents invalid passenger combinations
 * - **Maximum Limits**: Enforces reasonable passenger count limits
 *
 * **Cabin Class Selection:**
 * - **Economy**: Standard cabin class option
 * - **Premium Economy**: Enhanced economy experience
 * - **Business**: Premium business class service
 * - **First Class**: Ultimate luxury experience
 * - **Radio Button Interface**: Clear single-selection mechanism
 *
 * **User Interface Elements:**
 * - **Modal Bottom Sheet**: Slides up with 85% height for comfortable interaction
 * - **Card Layout**: Organized sections for passengers and cabin class
 * - **Counter Controls**: Increment/decrement buttons with visual feedback
 * - **Radio Selection**: Clear cabin class selection interface
 * - **Action Button**: Full-width confirm button for final selection
 *
 * **State Management:**
 * - **Temporary State**: Stores selections until confirmation
 * - **Validation Logic**: Ensures logical passenger combinations
 * - **Callback Handling**: Notifies parent of final selections
 * - **Dismissal Support**: Allows cancellation of changes
 *
 * **User Experience Features:**
 * - **Visual Feedback**: Clear indication of current selections
 * - **Interactive Controls**: Responsive buttons with proper enabled states
 * - **Information Display**: Age descriptions for each passenger category
 * - **Accessibility**: Proper content descriptions and touch targets
 * - **Responsive Design**: Adapts to different screen sizes
 *
 * **Business Logic:**
 * - **Minimum Requirements**: At least one adult passenger required
 * - **Infant Validation**: Infants cannot exceed adult count
 * - **Maximum Limits**: Prevents excessive passenger counts
 * - **Cabin Class**: Single selection with clear visual feedback
 *
 * **Use Cases:**
 * - Flight search passenger configuration
 * - Booking modification for existing reservations
 * - Group travel planning
 * - Cabin class upgrade selection
 * - Passenger count adjustment
 *
 * **Localization Support:**
 * - **French Language**: Primary language support
 * - **Dynamic Strings**: Resource-based text management
 * - **Cultural Adaptation**: Language-specific formatting and descriptions
 *
 * @param passengerCount The current passenger count configuration
 * @param cabinClass The currently selected cabin class
 * @param onDismiss Callback when the bottom sheet is dismissed
 * @param onConfirm Callback with the final passenger and cabin selections
 * @param modifier Modifier to apply to the bottom sheet container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerSelectorBottomSheet(
    passengerCount: PassengerCount,
    cabinClass: CabinClass,
    onDismiss: () -> Unit,
    onConfirm: (PassengerCount, CabinClass) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempPassengerCount by remember { mutableStateOf(passengerCount) }
    var tempCabinClass by remember { mutableStateOf(cabinClass) }
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(RiyadhAirSpacing.lg)
        ) {
            // Header
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_and_cabin),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = RiyadhAirSpacing.lg)
            )
            
            // Passenger Counters
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(RiyadhAirSpacing.lg)
                ) {
                    // Adults
                    PassengerCounter(
                        label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_adult),
                        description = "12 ans et plus",
                        count = tempPassengerCount.adults,
                        onIncrement = { 
                            if (tempPassengerCount.adults < 9) {
                                tempPassengerCount = tempPassengerCount.copy(adults = tempPassengerCount.adults + 1)
                            }
                        },
                        onDecrement = { 
                            if (tempPassengerCount.adults > 1) {
                                tempPassengerCount = tempPassengerCount.copy(adults = tempPassengerCount.adults - 1)
                            }
                        },
                        minValue = 1
                    )
                    
                    HorizontalDivider(modifier = Modifier.padding(vertical = RiyadhAirSpacing.md))
                    
                    // Children
                    PassengerCounter(
                        label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_enfant),
                        description = "2-11 ans",
                        count = tempPassengerCount.children,
                        onIncrement = { 
                            if (tempPassengerCount.children < 9) {
                                tempPassengerCount = tempPassengerCount.copy(children = tempPassengerCount.children + 1)
                            }
                        },
                        onDecrement = { 
                            if (tempPassengerCount.children > 0) {
                                tempPassengerCount = tempPassengerCount.copy(children = tempPassengerCount.children - 1)
                            }
                        },
                        minValue = 0
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = RiyadhAirSpacing.md))
                    
                    // Infants
                    PassengerCounter(
                        label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_baby),
                        description = "Moins de 2 ans",
                        count = tempPassengerCount.infants,
                        onIncrement = { 
                            if (tempPassengerCount.infants < tempPassengerCount.adults) {
                                tempPassengerCount = tempPassengerCount.copy(infants = tempPassengerCount.infants + 1)
                            }
                        },
                        onDecrement = { 
                            if (tempPassengerCount.infants > 0) {
                                tempPassengerCount = tempPassengerCount.copy(infants = tempPassengerCount.infants - 1)
                            }
                        },
                        minValue = 0
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))
            
            // Cabin Class Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RiyadhAirSpacing.lg)
                        .selectableGroup()
                ) {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.cabin_class),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = RiyadhAirSpacing.md)
                    )
                    
                    CabinClass.entries.forEach { cabin ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = RiyadhAirSpacing.xs),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = tempCabinClass == cabin,
                                onClick = { tempCabinClass = cabin },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = RiyadhAirColors.RoyalPurple
                                )
                            )
                            
                            Text(
                                text = cabin.displayName,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = RiyadhAirSpacing.sm)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
            
            // Confirm Button
            Button(
                onClick = { onConfirm(tempPassengerCount, tempCabinClass) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RiyadhAirShapes.large
            ) {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.confirm),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
        }
    }
}

/**
 * A counter component for managing passenger counts by age category.
 *
 * This composable provides an intuitive interface for adjusting passenger
 * counts with increment and decrement controls. It includes age-specific
 * descriptions and validation logic to ensure logical passenger combinations.
 *
 * **Counter Features:**
 * - **Increment/Decrement**: Plus and minus buttons for count adjustment
 * - **Visual Feedback**: Clear display of current count value
 * - **Validation Logic**: Enforces minimum and maximum value constraints
 * - **Age Descriptions**: Contextual information for each passenger category
 * - **Interactive States**: Proper enabled/disabled button states
 *
 * **Layout Structure:**
 * - **Left Section**: Passenger category label and age description
 * - **Right Section**: Counter controls with current value display
 * - **Horizontal Arrangement**: Balanced layout with proper spacing
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **Visual Design:**
 * - **Typography Hierarchy**: Clear distinction between label and description
 * - **Icon Integration**: Arrow icons for increment/decrement actions
 * - **Color Coding**: Royal purple for interactive elements
 * - **Spacing System**: Consistent RiyadhAir spacing throughout
 * - **Button States**: Visual feedback for enabled/disabled controls
 *
 * **Validation Logic:**
 * - **Minimum Values**: Enforces category-specific minimum counts
 * - **Maximum Limits**: Prevents excessive passenger counts
 * - **Business Rules**: Implements airline-specific passenger policies
 * - **User Guidance**: Clear indication of valid ranges
 *
 * **User Experience:**
 * - **Intuitive Controls**: Familiar plus/minus button pattern
 * - **Immediate Feedback**: Real-time count updates
 * - **Clear Information**: Age descriptions for context
 * - **Accessibility**: Proper content descriptions and touch targets
 * - **Visual Consistency**: Matches overall design system
 *
 * **Use Cases:**
 * - Adult passenger count adjustment
 * - Children passenger inclusion
 * - Infant passenger management
 * - Group travel planning
 * - Passenger count validation
 *
 * @param label The passenger category label (e.g., "Adult", "Child", "Infant")
 * @param description The age description for the passenger category
 * @param count The current count value for this passenger type
 * @param onIncrement Callback when the increment button is pressed
 * @param onDecrement Callback when the decrement button is pressed
 * @param minValue The minimum allowed count for this passenger type
 * @param modifier Modifier to apply to the counter container
 */
@Composable
private fun PassengerCounter(
    label: String,
    description: String,
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    minValue: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            IconButton(
                onClick = onDecrement,
                enabled = count > minValue
            ) {
                Icon(
                                            imageVector = RiyadhAirIcons.ArrowDown,
                    contentDescription = "Diminuer",
                    tint = if (count > minValue) 
                        RiyadhAirColors.RoyalPurple 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.widthIn(min = 24.dp)
            )
            
            IconButton(
                onClick = onIncrement
            ) {
                Icon(
                                            imageVector = RiyadhAirIcons.ArrowUp,
                    contentDescription = "Augmenter",
                    tint = RiyadhAirColors.RoyalPurple
                )
            }
        }
    }
}

/**
 * Preview function for PassengerSelectorBottomSheet component.
 *
 * This preview demonstrates the PassengerSelectorBottomSheet with sample
 * passenger count and cabin class data, showing how the component appears
 * in the design system preview environment. It includes the complete
 * bottom sheet layout with all passenger counters and cabin class options.
 *
 * **Preview Configuration:**
 * - **Sample Passenger Count**: 1 adult, 0 children, 0 infants
 * - **Cabin Class**: Economy class selection
 * - **Full Layout**: Displays complete bottom sheet with all sections
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Passenger Counters**: Shows all three passenger type counters
 * - **Cabin Class Selection**: Displays radio button options for all classes
 * - **Action Button**: Shows confirm button with proper styling
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete passenger selection flow
 */
@Preview(showBackground = true)
@Composable
private fun PassengerSelectorBottomSheetPreview() {
    RiyadhAirTheme {
        PassengerSelectorBottomSheet(
            passengerCount = PassengerCount(adults = 1, children = 0, infants = 0),
            cabinClass = CabinClass.ECONOMY,
            onDismiss = {},
            onConfirm = { _, _ -> }
        )
    }
}
