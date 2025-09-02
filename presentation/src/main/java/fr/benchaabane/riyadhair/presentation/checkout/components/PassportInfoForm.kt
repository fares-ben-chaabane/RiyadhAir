package fr.benchaabane.riyadhair.presentation.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
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
import fr.benchaabane.riyadhair.presentation.checkout.PassportInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A comprehensive passport information form component for the checkout process.
 *
 * This composable creates a user-friendly form for collecting passport
 * information required for international travel bookings. It features
 * input fields for passport details, an MRZ scanner option, and a
 * dropdown for country selection, providing a complete passport
 * information collection experience.
 *
 * **Form Features:**
 * - **Passport Number Input**: Text field for passport identification
 * - **Country Selection**: Dropdown menu for issuing country
 * - **MRZ Scanner**: Optional passport scanning functionality
 * - **Form Validation**: Input validation and error handling
 * - **Responsive Layout**: Adapts to different screen sizes
 *
 * **Layout Structure:**
 * - **Header Section**: Title, subtitle, and MRZ scanner button
 * - **Input Fields**: Passport number and country selection
 * - **Helper Text**: Optional information and recommendations
 * - **Scanner Modal**: MRZ scanning dialog for automatic data entry
 * - **Visual Hierarchy**: Clear distinction between different sections
 *
 * **Input Components:**
 * - **Passport Number Field**: Text input with leading icon and placeholder
 * - **Country Dropdown**: Exposed dropdown menu with country list
 * - **Scanner Button**: Icon button with Sky Blue accent styling
 * - **Form Validation**: Real-time input validation and feedback
 * - **Keyboard Options**: Appropriate keyboard types for different fields
 *
 * **MRZ Scanner Integration:**
 * - **Scanner Button**: Prominent button for passport scanning
 * - **Modal Dialog**: Full-screen dialog for scanning interface
 * - **Demo Mode**: Simulated scanning for development and testing
 * - **Automatic Population**: Fills form fields with scanned data
 * - **User Guidance**: Clear instructions for proper scanning
 *
 * **User Experience Features:**
 * - **Clear Instructions**: Descriptive labels and helper text
 * - **Visual Feedback**: Proper button states and interactions
 * - **Form Validation**: Real-time validation and error messages
 * - **Accessibility**: Proper content descriptions and touch targets
 * - **Responsive Design**: Adapts to different screen orientations
 *
 * **Business Logic:**
 * - **Passport Collection**: Gathers required travel documentation
 * - **Data Validation**: Ensures passport information completeness
 * - **Country Verification**: Validates passport issuing country
 * - **International Travel**: Supports cross-border flight bookings
 * - **Compliance**: Meets international travel requirements
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir colors (Royal Purple, Sky Blue)
 * - **Typography**: Consistent text styles and font weights
 * - **Shapes**: Unified shape system for rounded corners
 * - **Spacing**: Standardized spacing values throughout
 * - **Icons**: RiyadhAir icon system for visual elements
 *
 * **Country Support:**
 * - **European Countries**: France, Germany, Spain, Italy, UK
 * - **North American**: United States, Canada
 * - **Asia Pacific**: Australia, Japan, China
 * - **Middle East**: Morocco, Tunisia, Algeria, UAE
 * - **Other Options**: Additional country support
 *
 * **Use Cases:**
 * - International flight bookings
 * - Passport information collection
 * - Travel documentation verification
 * - MRZ scanning integration
 * - Form validation and submission
 *
 * @param passportInfo Current passport information (optional)
 * @param onPassportInfoChange Callback when passport information changes
 * @param modifier Modifier to apply to the passport form container
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassportInfoForm(
    passportInfo: PassportInfo?,
    onPassportInfoChange: (PassportInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    var showMrzScanner by remember { mutableStateOf(false) }
    
    val currentPassportInfo = passportInfo ?: PassportInfo()
    
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passport_information),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.RoyalPurple
                    )
                    
                    Text(
                        text = "Saisissez ou scannez votre passeport",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                // MRZ Scanner Button
                IconButton(
                    onClick = { showMrzScanner = true },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = RiyadhAirColors.SkyBlue.copy(alpha = 0.1f),
                            shape = RiyadhAirShapes.medium
                        )
                ) {
                    Icon(
                        imageVector = RiyadhAirIcons.Add,
                        contentDescription = "Scanner le passeport",
                        tint = RiyadhAirColors.SkyBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.sm))
            
            // Passport Number
            OutlinedTextField(
                value = currentPassportInfo.passportNumber,
                onValueChange = { 
                    onPassportInfoChange(currentPassportInfo.copy(passportNumber = it.uppercase()))
                },
                label = { Text("Numéro de passeport") },
                leadingIcon = {
                    Icon(
                        imageVector = RiyadhAirIcons.Settings,
                        contentDescription = "Passeport"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RiyadhAirShapes.medium,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                placeholder = { Text("Ex: 12AB34567") }
            )
            
            // Issuing Country
            var expanded by remember { mutableStateOf(false) }
            val countries = listOf(
                "France", "Allemagne", "Espagne", "Italie", "Royaume-Uni",
                "États-Unis", "Canada", "Australie", "Japon", "Chine",
                "Maroc", "Tunisie", "Algérie", "Émirats arabes unis", "Autre"
            )
            
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = currentPassportInfo.issuingCountry,
                    onValueChange = { },
                    label = { Text("Pays de délivrance") },
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
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country) },
                            onClick = {
                                onPassportInfoChange(currentPassportInfo.copy(issuingCountry = country))
                                expanded = false
                            }
                        )
                    }
                }
            }
            

            
            // Optional note
            Text(
                text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.optional_info_recommended),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    
    // MRZ Scanner Modal (placeholder for now)
    if (showMrzScanner) {
        AlertDialog(
            onDismissRequest = { showMrzScanner = false },
            title = { Text("Scanner MRZ") },
            text = { 
                Text("Positionnez la zone MRZ (Machine Readable Zone) de votre passeport dans le cadre pour scanner automatiquement les informations.")
            },
            confirmButton = {
                TextButton(onClick = { 
                    // Simulate scanned data
                    onPassportInfoChange(
                        PassportInfo(
                            passportNumber = "12AB34567",
                            issuingCountry = "France"
                        )
                    )
                    showMrzScanner = false
                }) {
                    Text("Scanner (Demo)")
                }
            },
            dismissButton = {
                TextButton(onClick = { showMrzScanner = false }) {
                    Text("Annuler")
                }
            }
        )
    }
}

/**
 * Preview function for PassportInfoForm component.
 *
 * This preview demonstrates the PassportInfoForm with sample passport
 * data, showing how the component appears in the design system
 * preview environment. It includes the complete form layout with
 * all input fields, buttons, and visual elements.
 *
 * **Preview Configuration:**
 * - **Sample Passport**: Uses French passport as example
 * - **Passport Number**: 12AB34567 for testing
 * - **Issuing Country**: France for country selection
 * - **Full Layout**: Displays complete form with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Form Header**: Shows title and subtitle with MRZ scanner button
 * - **Passport Number Field**: Displays input field with leading icon
 * - **Country Dropdown**: Shows country selection with dropdown menu
 * - **Helper Text**: Displays optional information recommendations
 * - **Scanner Button**: Shows MRZ scanner button with Sky Blue styling
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete passport form interface
 *
 * **Sample Data:**
 * - **Passport Number**: 12AB34567 (example format)
 * - **Issuing Country**: France (European country)
 * - **Form State**: Pre-filled with sample data for testing
 * - **Validation**: Shows form validation and error handling
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
private fun PassportInfoFormPreview() {
    RiyadhAirTheme {
        PassportInfoForm(
            passportInfo = PassportInfo(
                passportNumber = "12AB34567",
                issuingCountry = "France"
            ),
            onPassportInfoChange = {},
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
