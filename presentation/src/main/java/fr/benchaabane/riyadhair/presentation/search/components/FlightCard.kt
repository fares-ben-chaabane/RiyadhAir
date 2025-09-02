package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.core.ui.rtlAware
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.Airport
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.models.Flight
import fr.benchaabane.riyadhair.presentation.search.FlightUiModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A comprehensive flight card component for displaying flight information in search results.
 *
 * This composable provides a detailed view of flight options, including airline information,
 * departure/arrival details, pricing, and availability. It features smooth animations and
 * interactive selection states to enhance the user experience.
 *
 * **Flight Information Display:**
 * - **Airline Details**: Company name and flight number
 * - **Route Information**: Departure and arrival times, airports, and cities
 * - **Flight Duration**: Total travel time with visual representation
 * - **Pricing**: Cost display with cabin class information
 * - **Availability**: Seat count with low availability warnings
 * - **Aircraft Details**: Aircraft type and technical information
 *
 * **Interactive Features:**
 * - **Selection State**: Visual feedback when flight is selected
 * - **Click Handling**: Callback for flight selection
 * - **Animated Transitions**: Smooth elevation, border, and scale animations
 * - **Staggered Animation**: Sequential appearance with configurable delays
 *
 * **Visual Design:**
 * - **Card Layout**: Material Design 3 card with elevation
 * - **Color Coding**: Sky blue accents for interactive elements
 * - **Typography Hierarchy**: Clear information hierarchy with proper font weights
 * - **Icon Integration**: Visual indicators for flight details
 * - **Responsive Design**: Adapts to different screen sizes
 *
 * **Animation System:**
 * - **Entrance Animation**: Slide and fade in from bottom
 * - **Selection Animation**: Spring-based elevation and scale changes
 * - **Border Animation**: Dynamic border width based on selection state
 * - **Performance Optimized**: Uses appropriate animation specs for smooth performance
 *
 * **User Experience:**
 * - **Clear Information**: Easy-to-read flight details
 * - **Visual Feedback**: Immediate response to user interactions
 * - **Accessibility**: Proper content descriptions and text contrast
 * - **Touch Targets**: Adequate sizing for mobile interaction
 *
 * **Use Cases:**
 * - Flight search results display
 * - Flight comparison interfaces
 * - Booking flow flight selection
 * - Flight itinerary review
 * - Search result filtering
 *
 * @param modifier Modifier to apply to the card container
 * @param flight The flight information to display
 * @param onSelectFlight Callback when the flight card is selected
 * @param isSelected Whether this flight is currently selected
 * @param animationDelay Delay in milliseconds before the card appears (for staggered animations)
 */
@Composable
fun FlightCard(
    modifier: Modifier = Modifier,
    flight: FlightUiModel,
    onSelectFlight: (FlightUiModel) -> Unit,
    isSelected: Boolean = false,
    animationDelay: Int = 0
) {
    var isVisible by remember { mutableStateOf(false) }
    
    // Animated properties for selection
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 4.dp,
        animationSpec = spring(dampingRatio = 0.6f),
        label = "elevation"
    )
    
    val animatedBorderWidth by animateDpAsState(
        targetValue = if (isSelected) 3.dp else 1.dp,
        animationSpec = spring(dampingRatio = 0.7f),
        label = "borderWidth"
    )
    
    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.02f else 1f,
        animationSpec = spring(dampingRatio = 0.8f),
        label = "scale"
    )
    
    LaunchedEffect(flight.flightNumber) {
        delay(animationDelay.toLong())
        isVisible = true
    }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(600)
        ) + fadeIn(animationSpec = tween(600))
    ) {
        Card(
            onClick = { onSelectFlight(flight) },
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer(
                    scaleX = animatedScale,
                    scaleY = animatedScale
                ),
            shape = RiyadhAirShapes.large,
            elevation = CardDefaults.cardElevation(
                defaultElevation = animatedElevation
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = androidx.compose.foundation.BorderStroke(
                width = animatedBorderWidth, 
                color = if (isSelected) 
                    RiyadhAirColors.SkyBlue 
                else 
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
        ) {
            Column(
                modifier = Modifier.padding(RiyadhAirSpacing.lg)
            ) {
                // Header with airline and flight number
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = flight.companyName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.RoyalPurple
                    )
                    
                    Surface(
                        shape = RiyadhAirShapes.small,
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = flight.flightNumber,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(
                                horizontal = RiyadhAirSpacing.sm,
                                vertical = RiyadhAirSpacing.xs
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))
                
                // Flight route and times
                FlightRouteSection(
                    flight = flight,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(RiyadhAirSpacing.lg))
                
                // Flight details and price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    // Flight details
                    Column {
                        FlightDetailRow(
                                                          icon = RiyadhAirIcons.Info,
                            label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.duration),
                            value = flight.duration
                        )
                        
                        Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))
                        
                        FlightDetailRow(
                                                          icon = RiyadhAirIcons.ArrowForward,
                            label = stringResource(fr.benchaabane.riyadhair.presentation.R.string.aircraft),
                            value = flight.aircraftType
                        )
                    }
                    
                    // Price section
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = flight.price,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = RiyadhAirColors.SkyBlue,
                            textAlign = TextAlign.End
                        )
                        
                        Text(
                            text = flight.cabin,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.End
                        )
                        
                        Text(
                            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.available_seats, flight.availableSeats),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (flight.availableSeats < 10) 
                                MaterialTheme.colorScheme.error 
                            else 
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}

/**
 * A section component for displaying flight route information.
 *
 * This composable shows the departure and arrival details in a visually appealing
 * layout with a flight path visualization between them. It provides a clear
 * representation of the flight journey from origin to destination.
 *
 * **Route Information Display:**
 * - **Departure Details**: Time, airport code, and city name
 * - **Flight Path Visualization**: Visual representation of the journey
 * - **Arrival Details**: Time, airport code, and city name
 * - **Duration Display**: Total flight time in the center
 *
 * **Visual Elements:**
 * - **Departure Column**: Left-aligned departure information
 * - **Center Path**: Visual flight route with dots and arrows
 * - **Arrival Column**: Right-aligned arrival information
 * - **Typography Hierarchy**: Clear distinction between time, airport, and city
 *
 * **Layout Features:**
 * - **Three-Column Layout**: Departure, path visualization, arrival
 * - **Equal Weight Distribution**: Balanced spacing across the section
 * - **Responsive Design**: Adapts to different content lengths
 * - **Alignment Consistency**: Proper text alignment for each column
 *
 * **Design System Integration:**
 * - **Sky Blue Accents**: Consistent color scheme for flight elements
 * - **Proper Spacing**: Uses RiyadhAir spacing system
 * - **Icon Integration**: Arrow icon for flight direction
 * - **Shape Consistency**: Circular dots for route endpoints
 *
 * @param flight The flight information containing route details
 * @param modifier Modifier to apply to the route section container
 */
@Composable
private fun FlightRouteSection(
    flight: FlightUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Departure
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = flight.departureTime,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = flight.departureAirportCode,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = flight.departureCity,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        // Flight path visualization
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
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(RiyadhAirColors.SkyBlue)
                )
                
                HorizontalDivider(
                    modifier = Modifier.width(20.dp),
                    thickness = 2.dp,
                    color = RiyadhAirColors.SkyBlue
                )
                
                Icon(
                    imageVector = RiyadhAirIcons.ArrowForward,
                    contentDescription = stringResource(fr.benchaabane.riyadhair.presentation.R.string.cd_flight),
                    tint = RiyadhAirColors.SkyBlue,
                    modifier = Modifier.size(16.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.width(20.dp),
                    thickness = 2.dp,
                    color = RiyadhAirColors.SkyBlue
                )
                
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(RiyadhAirColors.SkyBlue)
                )
            }
        }
        
        // Arrival
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = flight.arrivalTime,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = flight.arrivalAirportCode,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = flight.arrivalCity,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End
            )
        }
    }
}

/**
 * A row component for displaying flight detail information with an icon and label.
 *
 * This composable provides a consistent way to display flight-related information
 * such as duration, aircraft type, or other technical details. It combines
 * an icon, label, and value in a horizontal layout for easy reading.
 *
 * **Information Display:**
 * - **Icon**: Visual indicator for the information type
 * - **Label**: Descriptive text for the information category
 * - **Value**: The actual data value to display
 * - **Formatting**: Consistent text formatting and spacing
 *
 * **Layout Features:**
 * - **Horizontal Arrangement**: Icon, label, and value in a row
 * - **Proper Spacing**: Consistent spacing between elements
 * - **Alignment**: Vertical center alignment for all elements
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **Visual Design:**
 * - **Icon Styling**: Consistent icon size and color
 * - **Typography**: Appropriate text styles for readability
 * - **Color Scheme**: Uses Material Design color system
 * - **Spacing System**: Integrates with RiyadhAir spacing
 *
 * **Use Cases:**
 * - Flight duration display
 * - Aircraft type information
 * - Baggage allowance details
 * - Meal service information
 * - Technical flight specifications
 *
 * @param icon The icon to display for the information type
 * @param label The descriptive label for the information
 * @param value The actual value to display
 * @param modifier Modifier to apply to the detail row container
 */
@Composable
private fun FlightDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
        
        Text(
            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.flight_info_format, label, value),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Preview function for FlightCard component.
 *
 * This preview demonstrates the FlightCard with sample flight data,
 * showing how the component appears in the design system preview
 * environment. It includes a mock flight with realistic information
 * to showcase the complete card layout and styling.
 *
 * **Preview Configuration:**
 * - **Mock Flight Data**: Realistic flight information for testing
 * - **Multiple Cards**: Shows multiple flight options for comparison
 * - **Proper Spacing**: Applied padding for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Complete Layout**: Shows all card sections and components
 * - **Typography Display**: Demonstrates text hierarchy and styling
 * - **Color Scheme**: Shows RiyadhAir color palette integration
 * - **Interactive Elements**: Displays selection states and animations
 * - **Responsive Design**: Shows how the card adapts to content
 *
 * **Mock Data Includes:**
 * - **Flight Details**: AF1234, Air France, CDG to DBX
 * - **Timing**: 08:10 departure, 15:00 arrival, 8h45 duration
 * - **Pricing**: 599.0 EUR with economy cabin
 * - **Availability**: 23 available seats
 * - **Aircraft**: Boeing 777
 * - **Cities**: Paris to Dubai
 */
@Preview(showBackground = true)
@Composable
private fun FlightCardPreview() {
    RiyadhAirTheme {
        val mockFlight = FlightUiModel(
            flightNumber = "AF1234",
            companyName = "Air France",
            departureAirportCode = "CDG",
            arrivalAirportCode = "DBX",
            departureTime = "08:10",
            arrivalTime = "15:00",
            duration = "8h45",
            price = "599.0 EUR",
            cabin = CabinClass.ECONOMY.displayName,
            availableSeats = 23,
            aircraftType = "Boeing 777",
            departureCity = "Paris",
            arrivalCity = "Dubai"
        )
        
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            FlightCard(
                flight = mockFlight,
                onSelectFlight = {},
                isSelected = false
            )
        }
    }
}
