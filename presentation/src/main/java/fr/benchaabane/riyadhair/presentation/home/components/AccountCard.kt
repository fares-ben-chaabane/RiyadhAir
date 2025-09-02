package fr.benchaabane.riyadhair.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import fr.benchaabane.riyadhair.designsystem.components.cards.BlurredBackgroundCard
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import fr.benchaabane.riyadhair.presentation.R
import fr.benchaabane.riyadhair.presentation.account.AccountUiModel

/**
 * A comprehensive account information card component for the home screen.
 *
 * This composable creates an engaging account summary card that displays
 * user information, loyalty status, and rewards points. It features a
 * blurred background image, smooth content animations, and comprehensive
 * account details presentation for optimal user experience.
 *
 * **Card Features:**
 * - **Blurred Background**: Beautiful background image with blur effect
 * - **Animated Content**: Smooth alpha transitions for content visibility
 * - **Account Information**: User name, loyalty level, and rewards display
 * - **Loyalty Badge**: Visual representation of user's loyalty tier
 * - **Responsive Layout**: Adapts to different screen sizes and content
 *
 * **Animation System:**
 * - **Content Alpha**: 300ms fade-in effect for content visibility
 * - **Conditional Animation**: Different alpha values based on account state
 * - **Smooth Transitions**: Optimized animation performance
 * - **Visual Hierarchy**: Creates engaging user experience
 *
 * **Layout Structure:**
 * - **Fixed Height**: 180dp height for consistent card sizing
 * - **Content Sections**: Welcome message, loyalty info, and rewards display
 * - **Visual Hierarchy**: Clear distinction between different information levels
 * - **Responsive Design**: Adapts to different content lengths
 *
 * **Content Display:**
 * - **Welcome Message**: Personalized greeting with user's name
 * - **Loyalty Information**: Visual loyalty tier indicator and badge
 * - **Rewards Points**: Miles and XP points with proper labeling
 * - **Background Image**: Default or custom background with blur effect
 * - **Content Organization**: Logical flow from greeting to rewards
 *
 * **User Experience Features:**
 * - **Visual Appeal**: Engaging background and content design
 * - **Personal Information**: Clear display of user account details
 * - **Loyalty Recognition**: Prominent loyalty status presentation
 * - **Rewards Visibility**: Easy-to-read points and miles display
 * - **Accessibility**: Proper content descriptions and contrast
 *
 * **Business Logic:**
 * - **Account Summary**: Provides comprehensive account overview
 * - **Loyalty Display**: Shows user's current loyalty tier and benefits
 * - **Rewards Tracking**: Displays accumulated miles and experience points
 * - **User Engagement**: Encourages continued loyalty program participation
 * - **Brand Recognition**: Reinforces RiyadhAir brand identity
 *
 * **Performance Features:**
 * - **Efficient Animations**: Optimized alpha transitions
 * - **Image Loading**: Background image with blur processing
 * - **State Management**: Efficient state updates and animations
 * - **Memory Management**: Proper resource cleanup and disposal
 *
 * **Design System Integration:**
 * - **BlurredBackgroundCard**: Uses specialized card component
 * - **Color Palette**: Uses RiyadhAir colors for loyalty tiers
 * - **Typography**: Consistent text styles and font weights
 * - **Spacing**: Standardized spacing values throughout
 * - **Theme Integration**: Proper Material Design 3 theming
 *
 * **Use Cases:**
 * - Home screen account summary
 * - User profile overview
 * - Loyalty status display
 * - Rewards points showcase
 * - Account information presentation
 *
 * @param modifier Modifier to apply to the account card container
 * @param account The user account information to display
 * @param backgroundImageUrl Custom background image URL (optional)
 */
@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    account: AccountUiModel?,
    backgroundImageUrl: String? = null,
) {
    val contentAlpha by animateFloatAsState(
        targetValue = if (account != null) 1f else 0.3f,
        animationSpec = tween(durationMillis = 300),
        label = "accountContentAlpha"
    )

    BlurredBackgroundCard(
        backgroundImageUrl = backgroundImageUrl
            ?: "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=800&h=400&fit=crop",
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        contentDescription = stringResource(R.string.cd_account_background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(contentAlpha),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Welcome message
            Column {
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Text(
                    text = account?.accountName ?: "Utilisateur",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Loyalty information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Loyalty level badge
                if (account?.accountLoyaltyLevel != null) {
                    LoyaltyBadge(
                        loyaltyLevel = account.accountLoyaltyLevel,
                        modifier = Modifier
                    )
                }

                // Miles and XP
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                    ) {
                        Text(
                            text = "${account?.accountMiles ?: 0}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.miles),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
                    ) {
                        Text(
                            text = "${account?.accountXpPoints ?: 0}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.xp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }
    }
}

/**
 * A loyalty badge component for displaying user loyalty tier information.
 *
 * This composable creates a visual representation of the user's loyalty
 * status, featuring a colored circle indicator and tier name. It provides
 * clear visual feedback about the user's current loyalty level and
 * encourages continued program participation.
 *
 * **Badge Features:**
 * - **Tier Indicator**: Colored circle representing loyalty tier
 * - **Tier Name**: Text display of loyalty level name
 * - **Color Coding**: Different colors for each loyalty tier
 * - **Visual Hierarchy**: Clear distinction between tier levels
 * - **Consistent Styling**: Unified design with overall card theme
 *
 * **Visual Design:**
 * - **Color System**: Tier-specific colors from RiyadhAir palette
 * - **Circle Indicator**: 12dp circular indicator for tier representation
 * - **Typography**: Label medium style with bold font weight
 * - **Spacing**: Standardized spacing values throughout
 * - **Layout**: Horizontal arrangement with proper alignment
 *
 * **Loyalty Tiers:**
 * - **Bronze**: Basic loyalty tier with bronze color
 * - **Silver**: Intermediate tier with silver color
 * - **Gold**: Advanced tier with loyalty gold color
 * - **Platinum**: Premium tier with platinum color
 * - **Diamond**: Elite tier with diamond color
 *
 * **Content Display:**
 * - **Tier Circle**: Visual color indicator for loyalty level
 * - **Tier Name**: Uppercase text display of loyalty tier
 * - **Proper Spacing**: Consistent spacing between elements
 * - **Alignment**: Vertical center alignment for visual balance
 *
 * **Accessibility:**
 * - **Color Contrast**: High contrast white text for readability
 * - **Visual Indicators**: Clear visual distinction between tiers
 * - **Text Content**: Descriptive tier names for screen readers
 * - **Touch Targets**: Adequate sizing for mobile interaction
 *
 * **Design System Integration:**
 * - **Color Palette**: Uses RiyadhAir loyalty tier colors
 * - **Typography**: Consistent text styles and font weights
 * - **Spacing**: Standardized spacing values throughout
 * - **Theme Consistency**: Integrates with overall app theme
 *
 * **Business Logic:**
 * - **Loyalty Recognition**: Visual acknowledgment of user status
 * - **Tier Progression**: Clear indication of current level
 * - **User Motivation**: Encourages continued program participation
 * - **Status Display**: Shows user's loyalty program standing
 *
 * **Use Cases:**
 * - Loyalty tier display
 * - User status indication
 * - Program participation motivation
 * - Visual loyalty recognition
 * - Status badge presentation
 *
 * @param loyaltyLevel The loyalty level information to display
 * @param modifier Modifier to apply to the loyalty badge container
 */
@Composable
private fun LoyaltyBadge(
    loyaltyLevel: LoyaltyLevel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(RiyadhAirSpacing.sm)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.sm)
        ) {
            // Loyalty tier indicator
            androidx.compose.foundation.Canvas(
                modifier = Modifier.size(12.dp)
            ) {
                drawCircle(
                    color = when (loyaltyLevel.tier) {
                        LoyaltyTier.BRONZE -> RiyadhAirColors.Bronze
                        LoyaltyTier.SILVER -> RiyadhAirColors.Silver
                        LoyaltyTier.GOLD -> RiyadhAirColors.LoyaltyGold
                        LoyaltyTier.PLATINUM -> RiyadhAirColors.Platinum
                        LoyaltyTier.DIAMOND -> RiyadhAirColors.Diamond
                    }
                )
            }

            Text(
                text = loyaltyLevel.name.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

/**
 * Preview function for AccountCard component.
 *
 * This preview demonstrates the AccountCard with sample account
 * data, showing how the component appears in the design system
 * preview environment. It includes the complete account layout
 * with loyalty information, rewards display, and all visual elements.
 *
 * **Preview Configuration:**
 * - **Sample Account**: Uses "Fares Ben Chaabane" as example user
 * - **Loyalty Tier**: Silver tier with appropriate color coding
 * - **Rewards Data**: 9,355 miles and 96 XP points for testing
 * - **Full Layout**: Displays complete card with all components
 * - **Applied Padding**: Shows proper spacing for visual separation
 * - **Theme Integration**: Uses RiyadhAir theme for consistent styling
 *
 * **Preview Features:**
 * - **Account Card**: Shows main account card with blurred background
 * - **Welcome Message**: Displays personalized greeting
 * - **Loyalty Badge**: Shows Silver tier indicator and badge
 * - **Rewards Display**: Demonstrates miles and XP points
 * - **Background Image**: Shows default background with blur effect
 * - **Responsive Design**: Demonstrates layout adaptation
 * - **Visual Hierarchy**: Shows proper spacing and typography
 *
 * **Preview Purpose:**
 * - **Design Validation**: Ensures proper visual appearance
 * - **Layout Testing**: Verifies component spacing and alignment
 * - **Theme Consistency**: Shows RiyadhAir design system integration
 * - **Component Integration**: Demonstrates how all parts work together
 * - **User Experience**: Shows the complete account interface
 *
 * **Sample Data:**
 * - **User Name**: Fares Ben Chaabane
 * - **Loyalty Tier**: Silver with silver color indicator
 * - **Miles**: 9,355 accumulated miles
 * - **XP Points**: 96 experience points
 * - **Phone**: Empty string for testing
 *
 * **Preview Context:**
 * - **Development Testing**: Quick visual feedback during development
 * - **Design Review**: Easy review of card layout and styling
 * - **Component Validation**: Ensures all sections render correctly
 * - **Theme Verification**: Confirms design system consistency
 * - **Content Testing**: Validates account information display
 */
@Preview(showBackground = true)
@Composable
private fun AccountCardPreview() {
    RiyadhAirTheme {
        AccountCard(
            account = AccountUiModel(
                accountName = "Fares Ben Chaabane",
                accountLoyaltyLevel = LoyaltyLevel(
                    name = "Silver",
                    tier = LoyaltyTier.SILVER,
                    color = "#C0C0C0",
                ),
                accountMiles = 9355,
                accountXpPoints = 96,
                phoneNumber = ""
            ),
            modifier = Modifier.padding(RiyadhAirSpacing.lg)
        )
    }
}
