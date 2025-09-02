package fr.benchaabane.riyadhair.presentation.account.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirShapes
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.presentation.account.AccountUiModel
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Enhanced3DMemberCard(
    account: AccountUiModel?,
    modifier: Modifier = Modifier,
    enableRotation: Boolean = true
) {
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var isBeingDragged by remember { mutableStateOf(false) }
    var dragStartPosition by remember { mutableStateOf(Offset.Zero) }

    val density = LocalDensity.current

    // Auto-rotation when not being dragged
    LaunchedEffect(isBeingDragged) {
        while (!isBeingDragged && enableRotation) {
            delay(16) // 60 FPS
            rotationY += 0.005f
            if (rotationY > 2 * PI) rotationY = 0f
        }
    }

    // Smooth return to center when drag ends
    val animatedRotationX by animateFloatAsState(
        targetValue = if (isBeingDragged) rotationX else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "rotationX"
    )

    val animatedRotationY by animateFloatAsState(
        targetValue = rotationY,
        animationSpec = if (isBeingDragged) tween(0) else spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "rotationY"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.6f) // Credit card ratio
            .padding(RiyadhAirSpacing.md)
    ) {
        if (account != null) {
            // Enhanced 3D card with realistic shadows and perspective
            Card3DView(
                account = account,
                rotationX = animatedRotationX,
                rotationY = animatedRotationY,
                onDragStart = { offset ->
                    isBeingDragged = true
                    dragStartPosition = offset
                },
                onDragEnd = {
                    isBeingDragged = false
                },
                onDrag = { dragAmount ->
                    if (enableRotation) {
                        rotationX = (rotationX - dragAmount.y * 0.01f).coerceIn(
                            -PI.toFloat() / 4,
                            PI.toFloat() / 4
                        )
                        rotationY += dragAmount.x * 0.01f
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Loading state
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RiyadhAirShapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun Card3DView(
    account: AccountUiModel,
    rotationX: Float,
    rotationY: Float,
    onDragStart: (Offset) -> Unit,
    onDragEnd: () -> Unit,
    onDrag: (Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    val loyaltyColor = when (account.accountLoyaltyLevel?.tier) {
        LoyaltyTier.BRONZE -> RiyadhAirColors.Bronze
        LoyaltyTier.SILVER -> RiyadhAirColors.Silver
                        LoyaltyTier.GOLD -> RiyadhAirColors.LoyaltyGold
        LoyaltyTier.PLATINUM -> RiyadhAirColors.Platinum
        LoyaltyTier.DIAMOND -> RiyadhAirColors.Diamond
        else -> RiyadhAirColors.Bronze
    }

    val density = LocalDensity.current

    // Calculate perspective effects
    val perspectiveScale = 1f - abs(sin(rotationY)) * 0.1f
    val shadowOffsetX = sin(rotationY) * 20f
    val shadowOffsetY = sin(rotationX) * 10f + 10f
    val shadowBlur = (10f + abs(sin(rotationY)) * 15f).dp

    Box(
        modifier = modifier
            .graphicsLayer {
                this.rotationX = rotationX * 180f / PI.toFloat()
                this.rotationY = rotationY * 180f / PI.toFloat()
                scaleX = perspectiveScale
                scaleY = perspectiveScale
                cameraDistance = 12f * density.density
                translationX = shadowOffsetX * 0.5f
                translationY = -abs(shadowOffsetY) * 0.3f
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> onDragStart(offset) },
                    onDragEnd = { onDragEnd() },
                    onDrag = { _, dragAmount -> onDrag(dragAmount) }
                )
            }
    ) {
        // Dynamic shadow based on rotation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = shadowOffsetX.dp, y = shadowOffsetY.dp)
                .shadow(
                    elevation = shadowBlur,
                    shape = RiyadhAirShapes.large,
                    ambientColor = Color.Black.copy(alpha = 0.3f),
                    spotColor = Color.Black.copy(alpha = 0.5f)
                )
                .background(
                    color = Color.Transparent,
                    shape = RiyadhAirShapes.large
                )
        )

        // Main card
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RiyadhAirShapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // We handle shadow manually
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                loyaltyColor.copy(alpha = 0.95f),
                                loyaltyColor.copy(alpha = 0.8f),
                                loyaltyColor.copy(alpha = 0.95f)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 1000f)
                        )
                    )
            ) {
                // Holographic effect overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.2f),
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.1f)
                                ),
                                radius = 800f,
                                center = Offset(
                                    300f + sin(rotationY) * 200f,
                                    200f + cos(rotationX) * 100f
                                )
                            )
                        )
                )

                // Card content
                CardContent(account = account, loyaltyColor = loyaltyColor)
            }
        }
    }
}

@Composable
private fun CardContent(
    account: AccountUiModel,
    loyaltyColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(RiyadhAirSpacing.lg),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header with Flying Blue logo and airline
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "flyingblue",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "AIRFRANCE",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Text(
                    text = "KLM",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        // Loyalty tier - center
        Text(
            text = account.accountLoyaltyLevel?.tier?.name.orEmpty(),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // User info - bottom
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = account.accountName.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = account.phoneNumber,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "10/2025",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.xs)
                ) {
                    Text(
                        text = "QR",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.9f),
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Text(
                            text = "⬜",
                            modifier = Modifier.align(Alignment.Center),
                            color = loyaltyColor,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Enhanced3DMemberCardPreview() {
    RiyadhAirTheme {
        Enhanced3DMemberCard(
            account = AccountUiModel(
                accountName = "Fares Ben Chaabane",
                accountLoyaltyLevel = LoyaltyLevel(
                    name = "Silver",
                    tier = LoyaltyTier.SILVER,
                    color = "#C0C0C0",
                ),
                accountMiles = 9355,
                accountXpPoints = 96,
                phoneNumber = "0629164668"
            ),
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
