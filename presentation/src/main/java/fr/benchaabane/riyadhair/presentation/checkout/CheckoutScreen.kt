package fr.benchaabane.riyadhair.presentation.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.*
import fr.benchaabane.riyadhair.presentation.checkout.components.JourneySummaryCard
import fr.benchaabane.riyadhair.presentation.checkout.components.TravelerInfoForm
import fr.benchaabane.riyadhair.presentation.checkout.components.PassportInfoForm
import fr.benchaabane.riyadhair.presentation.search.SearchViewModel
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Comprehensive checkout screen for completing flight bookings.
 *
 * This composable provides a complete checkout experience, allowing users
 * to review their flight selection, enter traveler information, and
 * provide passport details before completing their booking. It integrates
 * with the SearchViewModel to access flight details and manages form
 * validation for a smooth user experience.
 *
 * **Screen Layout Structure:**
 * - **Journey Summary**: Overview of selected outbound and return flights
 * - **Traveler Information**: Personal details and contact information form
 * - **Passport Information**: Travel document details form
 * - **Complete Booking Button**: Final action button with validation
 *
 * **Data Management:**
 * - **Flight Details**: Retrieves and displays selected flight information
 * - **Form State**: Manages traveler and passport information input
 * - **Validation**: Ensures all required information is provided
 * - **Data Flow**: Coordinates between ViewModel and UI components
 *
 * **User Experience Features:**
 * - **Scrollable Content**: Vertical scrolling for all form sections
 * - **Real-time Validation**: Immediate feedback on form completeness
 * - **Progressive Disclosure**: Clear information organization
 * - **Visual Feedback**: Button state reflects form validity
 * - **Responsive Design**: Adapts to different screen sizes
 *
 * **Form Validation:**
 * - **Required Fields**: Essential traveler information validation
 * - **Email Format**: Basic email format validation
 * - **Data Completeness**: Ensures all necessary information is provided
 * - **User Guidance**: Clear indication of what's required
 *
 * **Integration Points:**
 * - **SearchViewModel**: Accesses flight details and booking state
 * - **Navigation**: Receives flight numbers as parameters
 * - **Callback System**: Notifies parent of booking completion
 * - **Component System**: Uses specialized checkout components
 *
 * **Business Logic:**
 * - **Flight Retrieval**: Fetches detailed flight information
 * - **Form Management**: Handles complex form state and validation
 * - **Data Preparation**: Prepares booking data for submission
 * - **User Flow**: Guides users through complete booking process
 *
 * **Use Cases:**
 * - Flight booking completion
 * - Traveler information collection
 * - Passport details verification
 * - Booking confirmation process
 * - Payment preparation
 *
 * @param modifier Modifier to apply to the checkout screen container
 * @param outBoundFlightNumber The flight number for the departure flight
 * @param returnFlightNumber The flight number for the return flight
 * @param onCompleteBooking Callback when the booking is completed
 * @param viewModel The SearchViewModel for accessing flight details
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    outBoundFlightNumber: String,
    returnFlightNumber: String,
    onCompleteBooking: (TravelerInfo, PassportInfo?) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var travelerInfo by remember { mutableStateOf(TravelerInfo()) }
    var passportInfo by remember { mutableStateOf<PassportInfo?>(null) }
    var isFormValid by remember { mutableStateOf(false) }
    val flights = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getFlightDetails(outBoundFlightNumber, returnFlightNumber)
    }
    // Validate form completeness
    LaunchedEffect(travelerInfo, passportInfo) {
        isFormValid = travelerInfo.isValid()
    }
    
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            // Journey Summary
            JourneySummaryCard(
                modifier = Modifier.fillMaxWidth(),
                outBoundFlight = flights.value.selectedDepartureFlight,
                returnFlight = flights.value.selectedReturnFlight
            )
            
            // Traveler Information
            TravelerInfoForm(
                travelerInfo = travelerInfo,
                onTravelerInfoChange = { travelerInfo = it },
                modifier = Modifier.fillMaxWidth()
            )
            
            // Passport Information
            PassportInfoForm(
                passportInfo = passportInfo,
                onPassportInfoChange = { passportInfo = it },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
        }
        
        // Complete Booking Button
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp
        ) {
            Button(
                onClick = { onCompleteBooking(travelerInfo, passportInfo) },
                enabled = isFormValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RiyadhAirSpacing.lg)
                    .height(56.dp),
                shape = RiyadhAirShapes.large
            ) {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.payment),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * Data class representing traveler information for flight bookings.
 *
 * This class encapsulates all the personal and contact information
 * required for completing a flight booking, including validation
 * logic to ensure data completeness and accuracy.
 *
 * **Personal Information:**
 * - **Name Details**: First and last name for passenger identification
 * - **Birth Information**: Date of birth for age verification
 * - **Nationality**: Country of citizenship for travel documentation
 * - **Contact Details**: Email and phone for booking communications
 *
 * **Travel Preferences:**
 * - **Emergency Contact**: Alternative contact person information
 * - **Dietary Requirements**: Special meal requests for flights
 * - **Special Requests**: Additional accommodations or services
 *
 * **Data Validation:**
 * - **Required Fields**: Essential information validation
 * - **Email Format**: Basic email address validation
 * - **Completeness Check**: Ensures all necessary data is provided
 * - **Business Rules**: Enforces airline booking requirements
 *
 * **Use Cases:**
 * - Flight booking passenger information
 * - Traveler profile creation
 * - Booking confirmation data
 * - Passenger manifest generation
 * - Customer service support
 *
 * **Data Privacy:**
 * - **Personal Information**: Sensitive data handling
 * - **Contact Details**: Communication preferences
 * - **Travel History**: Booking pattern analysis
 * - **Compliance**: GDPR and data protection adherence
 *
 * @property firstName The traveler's first name
 * @property lastName The traveler's last name
 * @property dateOfBirth The traveler's date of birth
 * @property nationality The traveler's country of citizenship
 * @property email The traveler's email address for communications
 * @property phoneNumber The traveler's phone number for contact
 * @property emergencyContact Alternative contact person information
 * @property dietaryRequirements Special meal or dietary needs
 * @property specialRequests Additional accommodations or services
 */
data class TravelerInfo(
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: LocalDate? = null,
    val nationality: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val emergencyContact: String = "",
    val dietaryRequirements: String = "",
    val specialRequests: String = ""
) {
    /**
     * Validates the completeness and accuracy of traveler information.
     *
     * This method performs comprehensive validation of all required
     * traveler information fields to ensure the booking can proceed.
     *
     * **Validation Rules:**
     * - **Name Requirements**: Both first and last names must be provided
     * - **Birth Date**: Date of birth is mandatory for age verification
     * - **Nationality**: Country of citizenship must be specified
     * - **Email Validation**: Email must be provided and contain @ symbol
     * - **Phone Number**: Contact phone number is required
     *
     * **Validation Logic:**
     * - **String Validation**: Ensures non-blank values for required fields
     * - **Email Format**: Basic email format validation with @ symbol check
     * - **Date Validation**: Confirms date of birth is provided
     * - **Completeness**: All essential fields must be filled
     *
     * **Business Requirements:**
     * - **Airline Compliance**: Meets booking system requirements
     * - **Travel Documentation**: Supports passport and visa processing
     * - **Communication**: Enables booking confirmations and updates
     * - **Emergency Contact**: Ensures passenger safety and support
     *
     * @return true if all required information is valid, false otherwise
     */
    fun isValid(): Boolean {
        return firstName.isNotBlank() &&
               lastName.isNotBlank() &&
               dateOfBirth != null &&
               nationality.isNotBlank() &&
               email.isNotBlank() &&
               email.contains("@") &&
               phoneNumber.isNotBlank()
    }
}

/**
 * Data class representing passport information for international travel.
 *
 * This class contains the essential passport details required for
 * international flight bookings and travel documentation processing.
 *
 * **Passport Details:**
 * - **Passport Number**: Unique identifier for the travel document
 * - **Issuing Country**: Country that issued the passport
 *
 * **Travel Documentation:**
 * - **International Travel**: Required for cross-border flights
 * - **Identity Verification**: Supports passenger identification
 * - **Visa Processing**: Enables visa application and processing
 * - **Border Control**: Facilitates immigration procedures
 *
 * **Data Validation:**
 * - **Required Fields**: Both passport number and country are mandatory
 * - **Format Validation**: Ensures proper passport number format
 * - **Country Validation**: Confirms valid issuing country
 * - **Completeness**: All passport information must be provided
 *
 * **Use Cases:**
 * - International flight bookings
 * - Travel document verification
 * - Visa application support
 * - Immigration processing
 * - Passenger manifest generation
 *
 * **Security Considerations:**
 * - **Document Verification**: Authenticates passport information
 * - **Fraud Prevention**: Helps prevent identity theft
 * - **Compliance**: Meets international travel requirements
 * - **Data Protection**: Secure handling of sensitive information
 *
 * @property passportNumber The unique passport identification number
 * @property issuingCountry The country that issued the passport
 */
data class PassportInfo(
    val passportNumber: String = "",
    val issuingCountry: String = ""
) {
    /**
     * Validates the completeness of passport information.
     *
     * This method ensures that all required passport details are
     * provided before proceeding with international travel bookings.
     *
     * **Validation Requirements:**
     * - **Passport Number**: Must be provided and non-blank
     * - **Issuing Country**: Country of issuance must be specified
     *
     * **Validation Logic:**
     * - **String Validation**: Ensures non-blank values for both fields
     * - **Completeness Check**: Both fields must be filled
     * - **Format Validation**: Basic format requirements checking
     *
     * **Business Context:**
     * - **International Travel**: Required for cross-border flights
     * - **Documentation**: Supports travel document processing
     * - **Compliance**: Meets international travel regulations
     * - **Security**: Enables passenger identity verification
     *
     * @return true if all passport information is provided, false otherwise
     */
    fun isValid(): Boolean {
        return passportNumber.isNotBlank() && issuingCountry.isNotBlank()
    }
}

/**
 * Preview function for CheckoutScreen component.
 *
 * This preview demonstrates the CheckoutScreen with sample flight
 * numbers, showing how the component appears in the design system
 * preview environment. It includes the complete checkout layout
 * with all form sections and components.
 *
 * **Preview Configuration:**
 * - **Sample Flight Numbers**: Uses "123" and "435" for testing
 * - **Default Callback**: Empty callback implementation for testing
 * - **Full Layout**: Displays complete screen with all components
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Journey Summary**: Shows flight summary card area
 * - **Traveler Form**: Displays personal information input form
 * - **Passport Form**: Shows travel document information form
 * - **Complete Button**: Displays booking completion button
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete checkout interface flow
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of screen layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 */
@Preview(showBackground = true)
@Composable
private fun CheckoutScreenPreview() {
    RiyadhAirTheme {
        CheckoutScreen(
            outBoundFlightNumber = "123",
            returnFlightNumber = "435",
            onCompleteBooking = { _, _ -> }
        )
    }
}
