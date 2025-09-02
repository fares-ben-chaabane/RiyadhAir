package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.models.PassengerCount

/**
 * A card component for displaying passenger count and cabin class information.
 *
 * This composable provides a comprehensive view of flight booking details,
 * including the number of passengers by category (adults, children, infants)
 * and the selected cabin class. It's designed as an interactive card that
 * allows users to modify these settings.
 *
 * **Passenger Information Display:**
 * - **Adult Passengers**: Primary travelers (18+ years)
 * - **Children**: Young travelers (2-17 years)
 * - **Infants**: Babies (0-1 years)
 * - **Dynamic Text**: Automatically formats passenger counts with proper pluralization
 *
 * **Cabin Class Information:**
 * - **Economy**: Standard cabin class
 * - **Premium Economy**: Enhanced economy experience
 * - **Business**: Premium business class
 * - **First Class**: Ultimate luxury experience
 *
 * **UI Features:**
 * - **Interactive Design**: Clickable card for modification
 * - **Visual Hierarchy**: Clear separation of passenger and cabin information
 * - **Icon Integration**: Person icon for passengers, arrow for navigation
 * - **Responsive Layout**: Adapts to different screen sizes
 * - **Design System**: Consistent with RiyadhAir visual language
 *
 * **User Experience:**
 * - **Clear Information**: Easy-to-read passenger breakdown
 * - **Interactive Feedback**: Clickable area for modifications
 * - **Visual Cues**: Arrow icon indicates expandable content
 * - **Accessibility**: Proper content descriptions and text contrast
 *
 * **Use Cases:**
 * - Flight search passenger configuration
 * - Booking summary display
 * - Passenger count modification
 * - Cabin class selection
 * - Flight booking review
 *
 * **Localization Support:**
 * - **French Language**: Primary language support
 * - **Dynamic Strings**: Resource-based text management
 * - **Pluralization**: Proper grammar for different counts
 * - **Cultural Adaptation**: Language-specific formatting
 *
 * @param passengerCount The breakdown of passengers by age category
 * @param cabinClass The selected cabin class for the flight
 * @param onClick Callback when the card is tapped for modification
 * @param modifier Modifier to apply to the card container
 */
@Composable
fun PassengersCabinCard(
    passengerCount: PassengerCount,
    cabinClass: CabinClass,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(RiyadhAirSpacing.lg),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Passenger section
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                Icon(
                                            imageVector = RiyadhAirIcons.Person,
                    contentDescription = "Passagers",
                    tint = RiyadhAirColors.SkyBlue
                )
                
                Column {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Text(
                        text = buildString {
                            if (passengerCount.adults > 0) {
                                append("${passengerCount.adults} ${stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_adult)}${if (passengerCount.adults > 1) "s" else ""}")
                            }
                            if (passengerCount.children > 0) {
                                if (passengerCount.adults > 0) append(", ")
                                append("${passengerCount.children} ${stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_enfant)}${if (passengerCount.children > 1) "s" else ""}")
                            }
                            if (passengerCount.infants > 0) {
                                if (passengerCount.adults > 0 || passengerCount.children > 0) append(", ")
                                append("${passengerCount.infants} ${stringResource(fr.benchaabane.riyadhair.presentation.R.string.passengers_baby)}${if (passengerCount.infants > 1) "s" else ""}")
                            }
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            // Cabin class section
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.cabin),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text(
                    text = cabinClass.displayName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            Icon(
                                        imageVector = RiyadhAirIcons.ArrowRight,
                contentDescription = "Modifier",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Preview function for PassengersCabinCard component.
 *
 * This preview demonstrates the PassengersCabinCard with sample passenger
 * count and cabin class data. It shows how the component appears in the
 * design system preview environment.
 *
 * **Preview Configuration:**
 * - Single adult passenger
 * - Economy cabin class
 * - Applied padding for visual separation
 * - Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Passenger Display**: Adult passenger count with proper formatting
 * - **Cabin Class**: Economy class selection display
 * - **Interactive Elements**: Clickable card with visual feedback
 * - **Design System**: Consistent RiyadhAir styling and spacing
 */
@Preview(showBackground = true)
@Composable
private fun PassengersCabinCardPreview() {
    RiyadhAirTheme {
        PassengersCabinCard(
            passengerCount = PassengerCount(adults = 1, children = 0, infants = 0),
            cabinClass = CabinClass.ECONOMY,
            onClick = {},
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
