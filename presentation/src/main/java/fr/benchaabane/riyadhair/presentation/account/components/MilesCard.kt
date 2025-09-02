package fr.benchaabane.riyadhair.presentation.account.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MilesCard(
    miles: Int,
    expirationDate: String? = null,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    var animatedMiles by remember { mutableIntStateOf(0) }
    
    LaunchedEffect(miles) {
        isVisible = true
        delay(300) // Delay for card entrance animation
        
        // Animate miles counter
        val increment = miles / 50
        var current = 0
        while (current < miles) {
            current = (current + increment).coerceAtMost(miles)
            animatedMiles = current
            delay(20)
        }
        animatedMiles = miles
    }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(500)
        ) + fadeIn(animationSpec = tween(500))
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RiyadhAirShapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                RiyadhAirColors.SkyBlue.copy(alpha = 0.1f),
                                RiyadhAirColors.RoyalPurple.copy(alpha = 0.05f)
                            )
                        )
                    )
                    .padding(RiyadhAirSpacing.xl)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
                ) {
                    // Header
                    Text(
                        text = "Solde de Miles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    
                    // Miles count with animation
                    Text(
                        text = "${formatNumber(animatedMiles)} Miles",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.SkyBlue,
                        textAlign = TextAlign.Center
                    )
                    
                    // Expiration info
                    if (expirationDate != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                        ) {
                            Text(
                                text = "${formatNumber(miles)} Miles valable jusqu'au",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        Text(
                            text = expirationDate,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                    
                    // Miles icon or visual element
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = RiyadhAirColors.SkyBlue.copy(alpha = 0.1f),
                                shape = RiyadhAirShapes.medium
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✈️",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

/**
 * Formats number with spaces as thousand separators (French style)
 */
private fun formatNumber(number: Int): String {
    return number.toString().reversed().chunked(3).joinToString(" ").reversed()
}

@Preview(showBackground = true)
@Composable
private fun MilesCardPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            MilesCard(
                miles = 9355,
                expirationDate = "12/08/2027"
            )
            
            MilesCard(
                miles = 125000,
                expirationDate = null
            )
        }
    }
}
