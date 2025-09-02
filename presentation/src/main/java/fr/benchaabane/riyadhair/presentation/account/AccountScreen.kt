package fr.benchaabane.riyadhair.presentation.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.presentation.account.components.CompactMilesCard
import fr.benchaabane.riyadhair.presentation.account.components.Enhanced3DMemberCard
import fr.benchaabane.riyadhair.presentation.account.components.HorizontalXpTimeline
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        isContentVisible = true
    }

    AnimatedVisibility(
        modifier = modifier.fillMaxSize(),
        visible = isContentVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 4 }
        ) + fadeIn()
    ) {
        if (uiState.isLoading && uiState.account == null) {
            // Loading state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(RiyadhAirSpacing.lg),
                verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
            ) {
                // Enhanced 3D Member Card
                Enhanced3DMemberCard(
                    account = uiState.account,
                    enableRotation = true
                )

                // Compact Miles Card
                uiState.account?.let {
                    CompactMilesCard(
                        miles = it.accountMiles,
                        expirationDate = uiState.milesExpirationDate
                    )
                }

                // Horizontal XP Timeline
                uiState.account?.let {
                    HorizontalXpTimeline(
                        currentXp = it.accountXpPoints,
                        currentTier = it.accountLoyaltyLevel?.tier ?: LoyaltyTier.BRONZE
                    )
                }

                // Add some bottom padding for better scrolling
                Spacer(modifier = Modifier.height(RiyadhAirSpacing.xl))
            }
        }
    }

    // Error handling
    uiState.error?.let { error ->
        LaunchedEffect(error) {
            // Show error snackbar or handle error display
            // For now, just clear the error after showing
            delay(3000)
            viewModel.clearError()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountScreenPreview() {
    RiyadhAirTheme {
        // Mock preview with sample data
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            val mockAccount = Account(
                id = "user123",
                firstName = "Fares",
                lastName = "Ben Chaabane",
                email = "fares@riyadhair.com",
                phoneNumber = "1130 496 016",
                loyaltyLevel = LoyaltyLevel(
                    name = "Silver",
                    tier = LoyaltyTier.SILVER,
                    color = "#C0C0C0",
                ),
                milesPoints = 9355,
                xpPoints = 96,
                profileImageUrl = null,
                preferredLanguage = "en",
                currentLocation = "Paris"
            )

            Enhanced3DMemberCard(account = AccountUiModel(
                accountName = "Fares Ben Chaabane",
                accountLoyaltyLevel = LoyaltyLevel(
                    name = "Silver",
                    tier = LoyaltyTier.SILVER,
                    color = "#C0C0C0",
                ),
                accountMiles = 9355,
                accountXpPoints = 96,
                phoneNumber = "0629741524"
            ))
            CompactMilesCard(miles = 9355, expirationDate = "12/08/2027")
            HorizontalXpTimeline(currentXp = 96, currentTier = LoyaltyTier.SILVER)
        }
    }
}
