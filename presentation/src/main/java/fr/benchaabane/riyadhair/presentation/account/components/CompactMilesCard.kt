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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import kotlinx.coroutines.delay

@Composable
fun CompactMilesCard(
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
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp), // Reduced height - much more compact
            shape = RiyadhAirShapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                RiyadhAirColors.SkyBlue.copy(alpha = 0.1f),
                                RiyadhAirColors.RoyalPurple.copy(alpha = 0.05f)
                            )
                        )
                    )
                    .padding(RiyadhAirSpacing.md)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left side - Miles info
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.miles_count),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = "${formatNumber(animatedMiles)} Miles",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = RiyadhAirColors.SkyBlue
                        )
                        
                        if (expirationDate != null) {
                            Text(
                                text = "${stringResource(fr.benchaabane.riyadhair.presentation.R.string.miles_validity)} $expirationDate",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    
                    // Right side - Icon
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = RiyadhAirColors.SkyBlue.copy(alpha = 0.15f),
                                shape = RiyadhAirShapes.medium
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✈️",
                            style = MaterialTheme.typography.headlineMedium
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
private fun CompactMilesCardPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
        ) {
            CompactMilesCard(
                miles = 9355,
                expirationDate = "12/08/2027"
            )
            
            CompactMilesCard(
                miles = 125000,
                expirationDate = null
            )
        }
    }
}
