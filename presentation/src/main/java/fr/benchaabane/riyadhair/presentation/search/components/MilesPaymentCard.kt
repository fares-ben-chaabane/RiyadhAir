package fr.benchaabane.riyadhair.presentation.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * A card component for managing miles-based payment options.
 *
 * This composable provides a user interface for enabling or disabling
 * miles-based payment for flight bookings. It displays the available
 * miles balance and allows users to toggle between cash and miles
 * payment methods.
 *
 * **Payment Options:**
 * - **Cash Payment**: Standard currency-based payment
 * - **Miles Payment**: Loyalty program miles redemption
 * - **Toggle Control**: Switch between payment methods
 * - **Balance Display**: Shows available miles for redemption
 *
 * **UI Features:**
 * - **Brand Integration**: FlyingBlue loyalty program branding
 * - **Visual Design**: Sky blue background with purple accents
 * - **Interactive Switch**: Toggle control for payment method
 * - **Information Display**: Clear miles balance and payment option
 *
 * **Loyalty Program Integration:**
 * - **FlyingBlue**: Air France-KLM loyalty program
 * - **Miles Redemption**: Convert accumulated miles to flight discounts
 * - **Balance Tracking**: Real-time miles availability display
 * - **Payment Flexibility**: Seamless switching between payment methods
 *
 * **User Experience:**
 * - **Clear Information**: Easy-to-understand payment options
 * - **Visual Feedback**: Switch state changes provide immediate feedback
 * - **Brand Recognition**: Familiar FlyingBlue program identity
 * - **Accessibility**: Clear text and visual indicators
 *
 * **Use Cases:**
 * - Flight booking payment selection
 * - Loyalty program miles redemption
 * - Payment method preference setting
 * - Miles balance awareness
 * - Booking cost optimization
 *
 * @param milesAvailable The number of miles available for redemption
 * @param onTogglePayWithMiles Callback when miles payment is toggled
 * @param modifier Modifier to apply to the card container
 */
@Composable
fun MilesPaymentCard(
    milesAvailable: Int,
    onTogglePayWithMiles: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPayWithMiles by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RiyadhAirShapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = RiyadhAirSpacing.xs),
        colors = CardDefaults.cardColors(
            containerColor = RiyadhAirColors.SkyBlue.copy(alpha = 0.05f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(RiyadhAirSpacing.lg),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    Text(
                        text = "✈️",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "flyingblue",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.RoyalPurple
                    )
                }
                
                Spacer(modifier = Modifier.height(RiyadhAirSpacing.xs))
                
                Text(
                    text = "Payer avec mes $milesAvailable Miles",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            Switch(
                checked = isPayWithMiles,
                onCheckedChange = { 
                    isPayWithMiles = it
                    onTogglePayWithMiles(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = RiyadhAirColors.RoyalPurple,
                    checkedTrackColor = RiyadhAirColors.RoyalPurple.copy(alpha = 0.5f)
                )
            )
        }
    }
}

/**
 * Preview function for MilesPaymentCard component.
 *
 * This preview demonstrates the MilesPaymentCard with sample miles
 * balance and demonstrates how the component appears in the design
 * system preview environment.
 *
 * **Preview Configuration:**
 * - Sample miles balance of 9,355
 * - Applied padding for visual separation
 * - Uses RiyadhAir theme for consistent styling
 * - Shows both payment method options
 *
 * **Preview Features:**
 * - **FlyingBlue Branding**: Loyalty program identity display
 * - **Miles Balance**: Sample miles availability
 * - **Toggle Switch**: Interactive payment method selection
 * - **Design System**: Consistent RiyadhAir styling
 */
@Preview(showBackground = true)
@Composable
private fun MilesPaymentCardPreview() {
    RiyadhAirTheme {
        MilesPaymentCard(
            milesAvailable = 9355,
            onTogglePayWithMiles = {},
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
