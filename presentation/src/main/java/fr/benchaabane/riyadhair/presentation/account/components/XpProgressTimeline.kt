package fr.benchaabane.riyadhair.presentation.account.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import kotlinx.coroutines.delay

data class XpLevel(
    val tier: LoyaltyTier,
    val name: String,
    val xpRequired: Int,
    val color: Color,
    val isUnlocked: Boolean = false
)

@Composable
fun XpProgressTimeline(
    currentXp: Int,
    currentTier: LoyaltyTier,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    
    val levels = remember {
        listOf(
            XpLevel(LoyaltyTier.BRONZE, "Bronze", 0, RiyadhAirColors.Bronze, true),
            XpLevel(LoyaltyTier.SILVER, "Silver", 100, RiyadhAirColors.Silver, currentXp >= 100),
            XpLevel(LoyaltyTier.GOLD, "Gold", 180, RiyadhAirColors.LoyaltyGold, currentXp >= 180),
            XpLevel(LoyaltyTier.PLATINUM, "Platinum", 400, RiyadhAirColors.Platinum, currentXp >= 400)
        )
    }
    
    val currentLevelIndex = levels.indexOfFirst { it.tier == currentTier }
    val nextLevel = levels.getOrNull(currentLevelIndex + 1)
    
    LaunchedEffect(Unit) {
        delay(600) // Delay for staggered animation
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
            modifier = modifier.fillMaxWidth(),
            shape = RiyadhAirShapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RiyadhAirSpacing.xl),
                verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
            ) {
                // Header
                Text(
                    text = "Progression du statut",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                // Current XP display
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                ) {
                    Text(
                        text = "Solde de XP",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Text(
                        text = "$currentXp XP",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.RoyalPurple
                    )
                }
                
                // Progress Timeline
                XpTimelineProgress(
                    levels = levels,
                    currentXp = currentXp,
                    currentLevelIndex = currentLevelIndex
                )
                
                // Next level info
                if (nextLevel != null) {
                    val xpNeeded = nextLevel.xpRequired - currentXp
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RiyadhAirShapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(RiyadhAirSpacing.lg),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                        ) {
                            Text(
                                text = "Pour passer ${nextLevel.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            
                            Text(
                                text = "$xpNeeded XP",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = nextLevel.color,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun XpTimelineProgress(
    levels: List<XpLevel>,
    currentXp: Int,
    currentLevelIndex: Int,
    modifier: Modifier = Modifier
) {
    var animationProgress by remember { mutableFloatStateOf(0f) }
    
    val animatedProgress by animateFloatAsState(
        targetValue = animationProgress,
        animationSpec = tween(
            durationMillis = 2000,
            easing = EaseOutCubic
        ),
        label = "xp_progress"
    )
    
    LaunchedEffect(Unit) {
        delay(300) // Small delay before starting progress animation
        animationProgress = 1f
    }
    
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
    ) {
        levels.forEachIndexed { index, level ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
            ) {
                // Level indicator
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (level.isUnlocked) level.color else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (level.isUnlocked) {
                        Text(
                            text = "✓",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                        )
                    }
                }
                
                // Level info and progress
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = level.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = if (index == currentLevelIndex) FontWeight.Bold else FontWeight.Medium,
                            color = if (level.isUnlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = "${level.xpRequired} XP",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    // Progress bar for current level
                    if (index == currentLevelIndex && index < levels.size - 1) {
                        val nextLevelXp = levels[index + 1].xpRequired
                        val currentLevelXp = level.xpRequired
                        val progress = ((currentXp - currentLevelXp).toFloat() / (nextLevelXp - currentLevelXp)).coerceIn(0f, 1f)
                        
                        LinearProgressIndicator(
                            progress = { progress * animatedProgress },
                            modifier = Modifier.fillMaxWidth(),
                            color = level.color,
                            trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        )
                    }
                }
            }
            
            // Connection line to next level
            if (index < levels.size - 1) {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(2.dp)
                        .height(16.dp)
                        .background(
                            color = if (level.isUnlocked) level.color.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun XpProgressTimelinePreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            XpProgressTimeline(
                currentXp = 96,
                currentTier = LoyaltyTier.SILVER
            )
        }
    }
}
