package fr.benchaabane.riyadhair.presentation.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.core.ui.rtlAware
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.presentation.search.FlightUiModel

/**
 * A comprehensive journey summary card for the checkout process.
 *
 * This composable creates a detailed summary of the user's flight booking,
 * displaying both outbound and return flight information, total price,
 * trip type, and cabin class details. It provides a clear overview
 * of the booking before final confirmation.
 *
 * **Card Features:**
 * - **Journey Summary Header**: Clear title with total price display
 * - **Flight Details**: Comprehensive outbound and return flight information
 * - **Price Information**: Prominent total price with Sky Blue accent
 * - **Trip Classification**: One-way vs round-trip indication
 * - **Cabin Class Details**: Selected cabin class information
 * - **Visual Flight Path**: Graphical representation of flight routes
 *
 * **Layout Structure:**
 * - **Header Section**: Title and total price with Sky Blue accent
 * - **Flight Rows**: Individual flight information with visual elements
 * - **Trip Details**: Trip type and cabin class summary
 * - **Visual Separators**: Dividers for clear content organization
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **Visual Design:**
 * - **Sky Blue Border**: 2dp border with RiyadhAir brand color
 * - **Card Elevation**: 8dp elevation for depth perception
 * - **Color Accents**: Sky Blue for price and flight path elements
 * - **Typography Hierarchy**: Clear distinction between information levels
 * - **Consistent Spacing**: Standardized spacing throughout
 *
 * **Flight Information Display:**
 * - **Departure Details**: Time, airport code, and city
 * - **Flight Path Visualization**: Duration, route line, and flight number
 * - **Arrival Details**: Time, airport code, and city
 * - **Airline Information**: Company name and aircraft type
 * - **Price Breakdown**: Individual flight pricing
 *
 * **User Experience Features:**
 * - **Clear Information**: Well-organized flight details
 * - **Visual Hierarchy**: Logical flow from header to details
 * - **Price Prominence**: Clear total price display
 * - **Flight Visualization**: Intuitive flight path representation
 * - **Accessibility**: Proper content descriptions and contrast
 *
 * **Business Logic:**
 * - **Booking Summary**: Provides complete booking overview
 * - **Price Transparency**: Clear display of total cost
 * - **Flight Details**: Comprehensive flight information
 * - **Trip Classification**: Clear indication of journey type
 * - **Cabin Information**: Selected service level details
 *
 * **RTL Support:**
 * - **Layout Adaptation**: Proper alignment for RTL languages
 * - **Text Alignment**: Context-aware text positioning
 * - **Visual Elements**: Consistent visual representation
 * - **Cultural Adaptation**: Proper language support
 *
 * **Use Cases:**
 * - Checkout process booking summary
 * - Flight confirmation display
 * - Booking review and verification
 * - Price transparency and breakdown
 * - Flight itinerary presentation
 *
 * @param outBoundFlight The outbound flight information (required)
 * @param returnFlight The return flight information (optional for one-way trips)
 * @param modifier Modifier to apply to the journey summary card container
 */
@Composable
fun JourneySummaryCard(
    outBoundFlight: FlightUiModel?,
    returnFlight: FlightUiModel?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RiyadhAirShapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = RiyadhAirColors.SkyBlue
        )
    ) {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.journey_summary),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Surface(
                    shape = RiyadhAirShapes.medium,
                    color = RiyadhAirColors.SkyBlue.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = "1248 EUR",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.SkyBlue,
                        modifier = Modifier.padding(
                            horizontal = RiyadhAirSpacing.md,
                            vertical = RiyadhAirSpacing.sm
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))

            // Outbound flight
            outBoundFlight?.let {
                FlightSummaryRow(
                    flight = outBoundFlight,
                    label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.outbound_flight),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Return flight (if exists)

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))

            returnFlight?.let {
                FlightSummaryRow(
                    flight = returnFlight,
                    label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.return_flight_label),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))

            // Trip details
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

            Spacer(modifier = Modifier.height(RiyadhAirSpacing.md))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.trip_type_text),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (returnFlight != null) stringResource(fr.benchaabane.riyadhair.presentation.R.string.round_trip_text) else stringResource(fr.benchaabane.riyadhair.presentation.R.string.one_way_text),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.cabin_class_text),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = outBoundFlight?.cabin.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

/**
 * A detailed flight summary row component for displaying individual flight information.
 *
 * This composable creates a comprehensive flight information display with
 * departure and arrival details, flight path visualization, and pricing
 * information. It provides a clear and organized view of each flight
 * segment in the journey.
 *
 * **Row Features:**
 * - **Flight Label**: Clear indication of flight type (outbound/return)
 * - **Departure Information**: Time, airport code, and city details
 * - **Flight Path Visualization**: Duration, route line, and flight number
 * - **Arrival Information**: Time, airport code, and city details
 * - **Price Breakdown**: Individual flight pricing and airline details
 *
 * **Layout Structure:**
 * - **Three-Column Layout**: Departure, flight path, and arrival sections
 * - **Equal Weight Distribution**: Balanced visual layout
 * - **Proper Spacing**: Consistent spacing between elements
 * - **Visual Hierarchy**: Clear distinction between information levels
 * - **RTL Support**: Proper alignment for different language directions
 *
 * **Flight Path Visualization:**
 * - **Duration Display**: Flight duration prominently shown
 * - **Route Line**: Visual connection between departure and arrival
 * - **Flight Icon**: Arrow indicating flight direction
 * - **Connection Points**: Circular indicators for airports
 * - **Flight Number**: Aircraft identification information
 *
 * **Information Display:**
 * - **Time Information**: Bold departure and arrival times
 * - **Airport Codes**: Prominent airport identifier display
 * - **City Names**: Secondary city information with proper styling
 * - **Airline Details**: Company name and aircraft type
 * - **Price Information**: Individual flight cost display
 *
 * **Visual Design:**
 * - **Color Scheme**: Sky Blue accents for flight path elements
 * - **Typography Hierarchy**: Clear distinction between information levels
 * - **Icon Integration**: Proper icon sizing and coloring
 * - **Spacing Consistency**: Standardized spacing values
 * - **Visual Balance**: Proper alignment and distribution
 *
 * **RTL Support:**
 * - **Layout Adaptation**: Proper alignment for RTL languages
 * - **Text Positioning**: Context-aware text alignment
 * - **Visual Consistency**: Maintains visual hierarchy
 * - **Cultural Adaptation**: Proper language support
 *
 * **Accessibility:**
 * - **Content Descriptions**: Proper descriptions for screen readers
 * - **Text Contrast**: Adequate contrast for readability
 * - **Touch Targets**: Proper sizing for mobile interaction
 * - **Information Hierarchy**: Clear content organization
 *
 * **Business Logic:**
 * - **Flight Details**: Comprehensive flight information display
 * - **Route Visualization**: Clear flight path representation
 * - **Pricing Transparency**: Individual flight cost breakdown
 * - **Airline Information**: Company and aircraft details
 * - **Time Information**: Accurate departure and arrival times
 *
 * **Use Cases:**
 * - Flight summary display
 * - Journey overview presentation
 * - Booking confirmation details
 * - Flight itinerary review
 * - Price breakdown analysis
 *
 * @param flight The flight information to display
 * @param label The label for the flight type (e.g., "Outbound Flight")
 * @param modifier Modifier to apply to the flight summary row container
 */
@Composable
private fun FlightSummaryRow(
    flight: FlightUiModel,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Label
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = RiyadhAirColors.RoyalPurple
        )

        Spacer(modifier = Modifier.height(RiyadhAirSpacing.sm))

        // Flight info
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Departure
            Column(
                horizontalAlignment = rtlAware(
                    ltrValue = Alignment.Start,
                    rtlValue = Alignment.End
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = flight.departureTime,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = flight.departureAirportCode,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = flight.departureCity,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Flight visualization
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = flight.duration,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(RiyadhAirColors.SkyBlue)
                    )

                    HorizontalDivider(
                        modifier = Modifier.width(20.dp),
                        thickness = 1.dp,
                        color = RiyadhAirColors.SkyBlue
                    )

                    Icon(
                                                    imageVector = RiyadhAirIcons.ArrowForward,
                        contentDescription = "Vol",
                        tint = RiyadhAirColors.SkyBlue,
                        modifier = Modifier.size(12.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.width(20.dp),
                        thickness = 1.dp,
                        color = RiyadhAirColors.SkyBlue
                    )

                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(RiyadhAirColors.SkyBlue)
                    )
                }

                Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))

                Text(
                    text = flight.flightNumber,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arrival
            Column(
                horizontalAlignment = rtlAware(
                    ltrValue = Alignment.End,
                    rtlValue = Alignment.Start
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = flight.arrivalTime,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = flight.arrivalAirportCode,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = flight.arrivalCity,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = rtlAware(
                        ltrValue = TextAlign.End,
                        rtlValue = TextAlign.Start
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(RiyadhAirSpacing.sm))

        // Price breakdown
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${flight.companyName} • ${flight.aircraftType}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = flight.price,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = RiyadhAirColors.SkyBlue,
                textAlign = rtlAware(
                    ltrValue = TextAlign.End,
                    rtlValue = TextAlign.Start
                )
            )
        }
    }
}

/**
 * Preview function for JourneySummaryCard component.
 *
 * This preview demonstrates the JourneySummaryCard with sample flight
 * data, showing how the component appears in the design system
 * preview environment. It includes the complete journey summary layout
 * with outbound and return flight information and all visual elements.
 *
 * **Preview Configuration:**
 * - **Sample Flights**: Uses Air France flight as example for both directions
 * - **Round Trip**: Shows both outbound and return flights for testing
 * - **Full Layout**: Displays complete card with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Journey Summary Header**: Shows title and total price display
 * - **Outbound Flight**: Displays departure flight information
 * - **Return Flight**: Shows return flight details
 * - **Flight Visualization**: Demonstrates flight path representation
 * - **Trip Details**: Shows trip type and cabin class information
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete journey summary interface
 *
 * **Sample Data:**
 * - **Flight Number**: AF1234 (Air France)
 * - **Route**: Paris (CDG) to New York (JFK)
 * - **Duration**: 8h45 flight time
 * - **Price**: 599.0 EUR per flight
 * - **Cabin**: Economy class
 * - **Aircraft**: Boeing 777
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of card layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 * - **Content Testing**: Validates flight information display
 */
@Preview(showBackground = true)
@Composable
private fun JourneySummaryCardPreview() {
    RiyadhAirTheme {
        val mockOutbound = FlightUiModel(
            flightNumber = "AF1234",
            companyName = "Air France",
            duration = "8h45",
            price = "599.0 EUR",
            cabin = CabinClass.ECONOMY.displayName,
            availableSeats = 23,
            aircraftType = "Boeing 777",
            departureAirportCode = "CDG",
            departureCity = "Paris",
            arrivalAirportCode = "JFK",
            arrivalCity = "New York",
            departureTime = "08:00",
            arrivalTime = "12:00"
        )

        JourneySummaryCard(
            outBoundFlight = mockOutbound,
            returnFlight = mockOutbound,
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
