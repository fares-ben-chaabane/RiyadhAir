package fr.benchaabane.riyadhair.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import fr.benchaabane.riyadhair.presentation.account.AccountScreen
import fr.benchaabane.riyadhair.presentation.checkout.CheckoutScreen
import fr.benchaabane.riyadhair.presentation.home.HomeScreen
import fr.benchaabane.riyadhair.presentation.offers.OffersListScreen
import fr.benchaabane.riyadhair.presentation.reservations.ReservationsScreen
import fr.benchaabane.riyadhair.presentation.search.SearchResultsScreen
import fr.benchaabane.riyadhair.presentation.search.SearchScreen
import fr.benchaabane.riyadhair.presentation.navigation.NavigationRoutes

/**
 * Main navigation host for the RiyadhAir application.
 *
 * This composable serves as the central navigation hub, managing all screen
 * transitions, navigation state, and the overall app structure. It provides
 * a comprehensive navigation experience with bottom navigation, top app bar,
 * and deep linking support.
 *
 * **Navigation Architecture:**
 * - **Bottom Navigation**: Primary navigation for main app sections
 * - **Top App Bar**: Context-aware header with navigation controls
 * - **Navigation Graph**: Complete routing structure for all screens
 * - **Deep Linking**: Support for parameterized navigation
 * - **Back Stack Management**: Proper navigation history handling
 *
 * **Navigation Structure:**
 * - **HOME**: Main landing page and dashboard
 * - **SEARCH**: Flight search interface
 * - **RESERVATIONS**: User's flight bookings
 * - **ACCOUNT**: User account management
 * - **OFFERS**: Travel offers browsing
 * - **SEARCH_RESULTS**: Flight search results with parameters
 * - **CHECKOUT**: Booking process with flight details
 *
 * **UI Components:**
 * - **Scaffold**: Material Design 3 layout structure
 * - **Top App Bar**: Dynamic title and navigation controls
 * - **Bottom Navigation**: Main app section navigation
 * - **Content Area**: Screen content with proper padding
 * - **Navigation Icons**: Visual indicators for navigation items
 *
 * **Navigation Features:**
 * - **Route Parameters**: Dynamic navigation with flight details
 * - **Back Navigation**: Proper back stack management
 * - **Conditional UI**: Bottom bar shows/hides based on context
 * - **Navigation State**: Tracks current route for UI updates
 * - **Deep Linking**: Support for complex navigation scenarios
 *
 * **User Experience:**
 * - **Intuitive Navigation**: Clear visual hierarchy and feedback
 * - **Consistent Layout**: Uniform navigation patterns throughout
 * - **Smooth Transitions**: Seamless screen-to-screen navigation
 * - **Context Awareness**: UI adapts to current navigation state
 * - **Accessibility**: Proper content descriptions and navigation
 *
 * **State Management:**
 * - **Navigation State**: Tracks current route and back stack
 * - **UI State**: Controls visibility of navigation elements
 * - **Parameter State**: Manages dynamic route parameters
 * - **Navigation Logic**: Handles complex navigation scenarios
 *
 * **Performance Considerations:**
 * - **Lazy Navigation**: Only loads screens when needed
 * - **Efficient Routing**: Optimized navigation graph structure
 * - **Memory Management**: Proper back stack cleanup
 * - **State Optimization**: Minimal recomposition during navigation
 *
 * **Use Cases:**
 * - Application navigation hub
 * - Screen-to-screen transitions
 * - Deep linking and external navigation
 * - Navigation state management
 * - User flow orchestration
 *
 * @param navController The navigation controller for managing navigation state
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        NavigationItem(NavigationRoutes.HOME, "Home", RiyadhAirIcons.Home),
        NavigationItem(NavigationRoutes.SEARCH, "Search", RiyadhAirIcons.Search),
        NavigationItem(NavigationRoutes.RESERVATIONS, "Reservations", RiyadhAirIcons.Reservations),
        NavigationItem(NavigationRoutes.ACCOUNT, "Account", RiyadhAirIcons.Account)
    )

    val backStack by navController.currentBackStackEntryAsState()
    val route = backStack?.destination?.route
    val showBottomBar = route in listOf(NavigationRoutes.HOME, NavigationRoutes.SEARCH, NavigationRoutes.RESERVATIONS, NavigationRoutes.ACCOUNT)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = route.orEmpty().substringBefore("/"),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    if (showBottomBar.not()) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = route == item.route,
                            onClick = {
                                if (route != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(NavigationRoutes.HOME) { inclusive = false }
                                    }
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationRoutes.HOME) {
                HomeScreen(
                    onViewAllOffersClick = {
                        navController.navigate(NavigationRoutes.OFFERS)
                    }
                )
            }
            composable(NavigationRoutes.OFFERS) {
                OffersListScreen()
            }
            composable(NavigationRoutes.SEARCH) {
                SearchScreen(
                    onShowResult = { departure, arrival ->
                        navController.navigate("${NavigationRoutes.SEARCH_RESULTS}/$departure/$arrival")
                    }
                )
            }
            composable(
                "${NavigationRoutes.SEARCH_RESULTS}/{departure}/{arrival}",
                arguments = listOf(
                    navArgument("departure") { type = NavType.StringType },
                    navArgument("arrival") { type = NavType.StringType }
                )) { backStackEntry ->
                val departure = backStackEntry.arguments?.getString("departure") ?: ""
                val arrival = backStackEntry.arguments?.getString("arrival") ?: ""
                SearchResultsScreen(
                    onProceedToCheckout = { outBoundFlightNumber, returnFlightNumber ->
                        navController.navigate("checkout/$outBoundFlightNumber/$returnFlightNumber")
                    },
                    departure = departure,
                    arrival = arrival
                )
            }
            composable(
                "${NavigationRoutes.CHECKOUT}/{outBoundFlightNumber}/{returnFlightNumber}",
                arguments = listOf(
                    navArgument("outBoundFlightNumber") { type = NavType.StringType },
                    navArgument("returnFlightNumber") { type = NavType.StringType }
                )) { backStackEntry ->

                CheckoutScreen(
                    outBoundFlightNumber = backStackEntry.arguments?.getString("outBoundFlightNumber")
                        ?: "",
                    returnFlightNumber = backStackEntry.arguments?.getString("returnFlightNumber")
                        ?: "",
                    onCompleteBooking = { travelerInfo, passportInfo ->
                        // Handle booking completion
                        navController.navigate("reservations") {
                            popUpTo(NavigationRoutes.HOME) { inclusive = false }
                        }
                    }
                )
            }
            composable(NavigationRoutes.RESERVATIONS) { ReservationsScreen() }
            composable(NavigationRoutes.ACCOUNT) { AccountScreen() }
        }
    }
}

/**
 * Data class representing a navigation item in the bottom navigation bar.
 *
 * This class encapsulates all the information needed to display and
 * manage a navigation item, including its route, display title,
 * and associated icon for visual representation.
 *
 * **Navigation Item Properties:**
 * - **Route**: Navigation destination identifier
 * - **Title**: Human-readable display name
 * - **Icon**: Visual representation for the navigation item
 *
 * **UI Integration:**
 * - **Bottom Navigation**: Used in NavigationBar for main app sections
 * - **Icon Display**: Provides visual identity for navigation items
 * - **Text Labels**: Shows descriptive names for accessibility
 * - **Selection State**: Tracks current navigation selection
 *
 * **Navigation Behavior:**
 * - **Route Mapping**: Links to specific navigation destinations
 * - **Click Handling**: Triggers navigation when tapped
 * - **Selection Feedback**: Visual indication of current section
 * - **State Management**: Integrates with navigation controller
 *
 * **Design System Integration:**
 * - **Icon Consistency**: Uses RiyadhAir icon system
 * - **Typography**: Consistent text styling and naming
 * - **Visual Hierarchy**: Clear navigation structure
 * - **Accessibility**: Proper content descriptions
 *
 * **Use Cases:**
 * - Bottom navigation bar items
 * - Navigation menu items
 * - Tab-based navigation
 * - Section navigation
 * - App structure organization
 *
 * @property route The navigation route identifier for this item
 * @property title The human-readable display name for this navigation item
 * @property icon The visual icon representation for this navigation item
 */
data class NavigationItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)


