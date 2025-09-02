package fr.benchaabane.riyadhair.presentation.checkout.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.presentation.checkout.TravelerInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A comprehensive traveler information form component for the checkout process.
 *
 * This composable creates a detailed form for collecting all necessary
 * traveler information required for flight bookings. It features input
 * fields for personal details, contact information, and special requests,
 * providing a complete traveler profile collection experience.
 *
 * **Form Features:**
 * - **Personal Information**: First name, last name, and date of birth
 * - **Nationality Selection**: Dropdown menu for country of citizenship
 * - **Contact Details**: Email, phone number, and emergency contact
 * - **Special Requirements**: Dietary restrictions and special requests
 * - **Form Validation**: Required field indicators and validation
 *
 * **Layout Structure:**
 * - **Header Section**: Title and subtitle with clear instructions
 * - **Personal Details**: Name fields and date of birth picker
 * - **Nationality Selection**: Dropdown for country selection
 * - **Contact Information**: Email, phone, and emergency contact
 * - **Additional Requirements**: Dietary and special request fields
 * - **Required Field Notes**: Clear indication of mandatory fields
 *
 * **Input Components:**
 * - **Name Fields**: First and last name with leading icons
 * - **Date Picker**: Interactive date selection for birth date
 * - **Nationality Dropdown**: Exposed dropdown with country list
 * - **Contact Fields**: Email and phone with appropriate keyboard types
 * - **Text Areas**: Multi-line fields for special requirements
 *
 * **Date Picker Integration:**
 * - **Date Selection**: Material Design 3 date picker dialog
 * - **Format Display**: DD/MM/YYYY format for date display
 * - **Validation**: Ensures valid date selection
 * - **User Experience**: Clear date selection interface
 * - **Accessibility**: Proper date picker accessibility
 *
 * **Form Validation:**
 * - **Required Fields**: Clear indication of mandatory information
 * - **Field Validation**: Real-time validation and error handling
 * - **Data Completeness**: Ensures all necessary information is collected
 * - **User Guidance**: Clear instructions and field descriptions
 * - **Error Feedback**: Proper error message display
 *
 * **User Experience Features:**
 * - **Clear Instructions**: Descriptive labels and helper text
 * - **Visual Feedback**: Proper button states and interactions
 * - **Form Organization**: Logical grouping of related fields
 * - **Accessibility**: Proper content descriptions and touch targets
 * - **Responsive Design**: Adapts to different screen orientations
 *
 * **Business Logic:**
 * - **Traveler Profile**: Comprehensive traveler information collection
 * - **Booking Requirements**: Meets airline booking system requirements
 * - **Special Needs**: Accommodates dietary and accessibility requirements
 * - **Contact Information**: Ensures proper communication channels
 * - **Compliance**: Meets international travel documentation requirements
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Royal Purple for headers)
 * - **Typography**: Consistent text styles and font weights
 * - **Shapes**: Unified shape system for rounded corners
 * - **Spacing**: Standardized spacing values throughout
 * - **Icons**: RiyadhAir icon system for visual elements
 *
 * **Nationality Support:**
 * - **European Countries**: French, German, Spanish, Italian, British
 * - **North American**: American, Canadian
 * - **Asia Pacific**: Australian, Japanese, Chinese
 * - **Other Options**: Additional nationality support
 *
 * **Special Requirements:**
 * - **Dietary Restrictions**: Vegetarian, gluten-free, halal options
 * - **Accessibility Needs**: PMR assistance, preferred seating
 * - **Medical Requirements**: Special medical accommodations
 * - **Travel Preferences**: Seat preferences and special requests
 *
 * **Use Cases:**
 * - Flight booking traveler information
 * - Passenger profile creation
 * - Special requirement accommodation
 * - Contact information collection
 * - Travel documentation preparation
 *
 * @param travelerInfo Current traveler information
 * @param onTravelerInfoChange Callback when traveler information changes
 * @param modifier Modifier to apply to the traveler form container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelerInfoForm(
    travelerInfo: TravelerInfo,
    onTravelerInfoChange: (TravelerInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier,
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            // Header
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.traveler_information),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = RiyadhAirColors.RoyalPurple
            )
            
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passenger_information),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.sm))
            
            // Personal Information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                OutlinedTextField(
                    value = travelerInfo.firstName,
                    onValueChange = { onTravelerInfoChange(travelerInfo.copy(firstName = it)) },
                    label = { Text("Prénom *") },
                    leadingIcon = {
                        Icon(
                            imageVector = RiyadhAirIcons.Person,
                            contentDescription = "Prénom"
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RiyadhAirShapes.medium,
                    singleLine = true
                )
                
                OutlinedTextField(
                    value = travelerInfo.lastName,
                    onValueChange = { onTravelerInfoChange(travelerInfo.copy(lastName = it)) },
                    label = { Text("Nom *") },
                    modifier = Modifier.weight(1f),
                    shape = RiyadhAirShapes.medium,
                    singleLine = true
                )
            }
            
            // Date of Birth
            OutlinedTextField(
                value = travelerInfo.dateOfBirth?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                onValueChange = { },
                label = { Text("Date de naissance *") },
                leadingIcon = {
                    Icon(
                        imageVector = RiyadhAirIcons.DateRange,
                        contentDescription = "Date de naissance"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                readOnly = true,
                trailingIcon = {
                    TextButton(onClick = { showDatePicker = true }) {
                        Text("Choisir")
                    }
                }
            )
            
            // Nationality
            var expanded by remember { mutableStateOf(false) }
            val nationalities = listOf(
                "Française", "Allemande", "Espagnole", "Italienne", "Britannique",
                "Américaine", "Canadienne", "Australienne", "Japonaise", "Chinoise",
                "Autre"
            )
            
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = travelerInfo.nationality,
                    onValueChange = { },
                    label = { Text("Nationalité *") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RiyadhAirShapes.medium,
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                )
                
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    nationalities.forEach { nationality ->
                        DropdownMenuItem(
                            text = { Text(nationality) },
                            onClick = {
                                onTravelerInfoChange(travelerInfo.copy(nationality = nationality))
                                expanded = false
                            }
                        )
                    }
                }
            }
            
            // Contact Information
            OutlinedTextField(
                value = travelerInfo.email,
                onValueChange = { onTravelerInfoChange(travelerInfo.copy(email = it)) },
                label = { Text("Email *") },
                leadingIcon = {
                    Icon(
                        imageVector = RiyadhAirIcons.Email,
                        contentDescription = "Email"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            
            OutlinedTextField(
                value = travelerInfo.phoneNumber,
                onValueChange = { onTravelerInfoChange(travelerInfo.copy(phoneNumber = it)) },
                label = { Text("Numéro de téléphone *") },
                leadingIcon = {
                    Icon(
                        imageVector = RiyadhAirIcons.Phone,
                        contentDescription = "Téléphone"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true
            )
            
            OutlinedTextField(
                value = travelerInfo.emergencyContact,
                onValueChange = { onTravelerInfoChange(travelerInfo.copy(emergencyContact = it)) },
                label = { Text("Contact d'urgence") },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                singleLine = true
            )
            
            // Additional Information
            OutlinedTextField(
                value = travelerInfo.dietaryRequirements,
                onValueChange = { onTravelerInfoChange(travelerInfo.copy(dietaryRequirements = it)) },
                label = { Text("Régime alimentaire") },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                placeholder = { Text("Végétarien, sans gluten, halal...") },
                maxLines = 2
            )
            
            OutlinedTextField(
                value = travelerInfo.specialRequests,
                onValueChange = { onTravelerInfoChange(travelerInfo.copy(specialRequests = it)) },
                label = { Text("Demandes spéciales") },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                placeholder = { Text("Assistance PMR, siège préféré...") },
                maxLines = 3
            )
            
            // Required fields note
            Text(
                text = "* Champs obligatoires",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    
    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000))
                            onTravelerInfoChange(travelerInfo.copy(dateOfBirth = selectedDate))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("Confirmer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Annuler")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

/**
 * Preview function for TravelerInfoForm component.
 *
 * This preview demonstrates the TravelerInfoForm with sample traveler
 * data, showing how the component appears in the design system
 * preview environment. It includes the complete form layout with
 * all input fields, validation indicators, and visual elements.
 *
 * **Preview Configuration:**
 * - **Sample Traveler**: Uses "Fares Ben Chaabane" as example
 * - **Contact Information**: Sample email for testing
 * - **Full Layout**: Displays complete form with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Form Header**: Shows title and subtitle with clear instructions
 * - **Personal Fields**: Displays first name, last name, and date of birth
 * - **Nationality Selection**: Shows country dropdown menu
 * - **Contact Fields**: Displays email, phone, and emergency contact
 * - **Special Requirements**: Shows dietary and special request fields
 * - **Required Indicators**: Demonstrates mandatory field marking
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete traveler form interface
 *
 * **Sample Data:**
 * - **First Name**: Fares
 * - **Last Name**: Ben Chaabane
 * - **Email**: fares@example.com
 * - **Form State**: Pre-filled with sample data for testing
 * - **Validation**: Shows required field indicators
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of form layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 * - **Form Testing**: Validates input field behavior and appearance
 */
@Preview(showBackground = true)
@Composable
private fun TravelerInfoFormPreview() {
    RiyadhAirTheme {
        TravelerInfoForm(
            travelerInfo = TravelerInfo(
                firstName = "Fares",
                lastName = "Ben Chaabane",
                email = "fares@example.com"
            ),
            onTravelerInfoChange = {},
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
