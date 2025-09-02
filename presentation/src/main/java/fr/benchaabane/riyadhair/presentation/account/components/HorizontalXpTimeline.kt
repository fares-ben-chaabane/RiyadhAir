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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import kotlinx.coroutines.delay

data class HorizontalXpLevel(
    val tier: LoyaltyTier,
    val name: String,
    val xpRequired: Int,
    val color: Color,
    val isUnlocked: Boolean = false
)

@Composable
fun HorizontalXpTimeline(
    currentXp: Int,
    currentTier: LoyaltyTier,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    var animationProgress by remember { mutableFloatStateOf(0f) }
    
    val levels = remember {
        listOf(
            HorizontalXpLevel(LoyaltyTier.BRONZE, "Bronze", 0, RiyadhAirColors.Bronze, true),
            HorizontalXpLevel(LoyaltyTier.SILVER, "Silver", 100, RiyadhAirColors.Silver, currentXp >= 100),
            HorizontalXpLevel(LoyaltyTier.GOLD, "Gold", 180, RiyadhAirColors.LoyaltyGold, currentXp >= 180),
            HorizontalXpLevel(LoyaltyTier.PLATINUM, "Platinum", 400, RiyadhAirColors.Platinum, currentXp >= 400)
        )
    }
    
    val currentLevelIndex = levels.indexOfFirst { it.tier == currentTier }
    val nextLevel = levels.getOrNull(currentLevelIndex + 1)
    
    val animatedProgress by animateFloatAsState(
        targetValue = animationProgress,
        animationSpec = tween(
            durationMillis = 3000,
            easing = EaseOutCubic
        ),
        label = "xp_progress"
    )
    
    LaunchedEffect(Unit) {
        delay(600) // Delay for staggered animation
        isVisible = true
        delay(300)
        animationProgress = 1f
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(fr.benchaabane.riyadhair.presentation.R.string.experience_progression),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = "$currentXp XP",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = RiyadhAirColors.RoyalPurple
                    )
                }

                // Horizontal Progress Timeline
                HorizontalTimelineProgress(
                    levels = levels,
                    currentXp = currentXp,
                    currentLevelIndex = currentLevelIndex,
                    animationProgress = animatedProgress
                )
            }
        }
    }
}

@Composable
private fun HorizontalTimelineProgress(
    levels: List<HorizontalXpLevel>,
    currentXp: Int,
    currentLevelIndex: Int,
    animationProgress: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.md)
    ) {
        // Level indicators row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            levels.forEachIndexed { index, level ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    // Level indicator circle
                    val isAnimated = index <= currentLevelIndex && animationProgress > (index.toFloat() / levels.size)
                    val circleAlpha = if (isAnimated) 1f else 0.3f
                    
                    Box(
                        modifier = Modifier
                            .size(if (index == currentLevelIndex) 48.dp else 40.dp)
                            .clip(CircleShape)
                            .background(
                                color = if (level.isUnlocked) 
                                    level.color.copy(alpha = circleAlpha) 
                                else 
                                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (level.isUnlocked && isAnimated) {
                            Text(
                                text = "✓",
                                style = if (index == currentLevelIndex) 
                                    MaterialTheme.typography.titleMedium 
                                else 
                                    MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        } else if (!level.isUnlocked) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                            )
                        }
                    }
                    
                    // Level name
                    Text(
                        text = level.name,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (index == currentLevelIndex) FontWeight.Bold else FontWeight.Medium,
                        color = if (level.isUnlocked && isAnimated) 
                            MaterialTheme.colorScheme.onSurface 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    
                    // XP requirement
                    Text(
                        text = "${level.xpRequired} XP",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        
        // Connection line with progress
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .padding(horizontal = 20.dp) // Account for circle radius
        ) {
            // Background line
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RiyadhAirShapes.small
                    )
            )
            
            // Progress line - animated
            if (currentLevelIndex < levels.size - 1) {
                val nextLevelXp = levels[currentLevelIndex + 1].xpRequired
                val currentLevelXp = levels[currentLevelIndex].xpRequired
                val levelProgress = ((currentXp - currentLevelXp).toFloat() / (nextLevelXp - currentLevelXp)).coerceIn(0f, 1f)
                
                val totalProgress = (currentLevelIndex.toFloat() + levelProgress) / (levels.size - 1)
                val animatedTotalProgress = totalProgress * animationProgress
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedTotalProgress)
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RiyadhAirShapes.small
                        )
                )
            } else {
                // Full progress for max level
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animationProgress)
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RiyadhAirShapes.small
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalXpTimelinePreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            HorizontalXpTimeline(
                currentXp = 96,
                currentTier = LoyaltyTier.SILVER
            )
        }
    }
}
