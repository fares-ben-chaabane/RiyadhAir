package fr.benchaabane.riyadhair.presentation.navigation

/**
 * Centralized navigation routes for the RiyadhAir application.
 *
 * This object contains all the navigation route constants used throughout
 * the application, providing a single source of truth for navigation
 * destinations and ensuring consistency across the navigation system.
 *
 * **Navigation Structure:**
 * - **Core Screens**: Primary application screens for main functionality
 * - **Feature Screens**: Specialized screens for specific features
 * - **Flow Screens**: Screens that are part of multi-step processes
 * - **User Screens**: Screens related to user account and preferences
 *
 * **Route Organization:**
 * - **HOME**: Main landing page and dashboard
 * - **OFFERS**: Travel offers and promotional content
 * - **SEARCH**: Flight search interface
 * - **SEARCH_RESULTS**: Flight search results display
 * - **CHECKOUT**: Booking and payment process
 * - **RESERVATIONS**: User's flight reservations
 * - **ACCOUNT**: User account management
 *
 * **Navigation Benefits:**
 * - **Type Safety**: Compile-time route validation
 * - **Centralized Management**: Single location for all routes
 * - **Consistency**: Uniform route naming across the app
 * - **Maintainability**: Easy to update and modify routes
 * - **Documentation**: Clear overview of available destinations
 *
 * **Usage Patterns:**
 * - **Navigation Calls**: Used with NavController.navigate()
 * - **Deep Linking**: Support for external app navigation
 * - **Route Parameters**: Base routes for parameterized navigation
 * - **Navigation Graphs**: Building blocks for navigation structure
 *
 * **Implementation Details:**
 * - **Object Declaration**: Singleton pattern for global access
 * - **Constant Values**: Immutable string constants
 * - **Naming Convention**: Descriptive and intuitive route names
 * - **Route Hierarchy**: Logical organization of navigation flow
 *
 * **Navigation Flow:**
 * - **Home → Offers**: Browse available travel offers
 * - **Home → Search**: Initiate flight search process
 * - **Search → Results**: View search results
 * - **Results → Checkout**: Proceed with booking
 * - **Checkout → Reservations**: View booking confirmation
 * - **Any → Account**: Access user account from anywhere
 *
 * **Future Considerations:**
 * - **Route Parameters**: Support for dynamic route parameters
 * - **Nested Routes**: Complex navigation hierarchies
 * - **Deep Links**: External app navigation support
 * - **Analytics**: Route tracking for user behavior analysis
 *
 * @property HOME Route to the main home screen and dashboard
 * @property OFFERS Route to the travel offers browsing screen
 * @property SEARCH Route to the flight search interface
 * @property SEARCH_RESULTS Route to the flight search results screen
 * @property CHECKOUT Route to the booking and payment process
 * @property RESERVATIONS Route to the user's reservations screen
 * @property ACCOUNT Route to the user account management screen
 */
object NavigationRoutes {
    /** Route to the main home screen and dashboard */
    const val HOME = "home"
    
    /** Route to the travel offers browsing screen */
    const val OFFERS = "offers"
    
    /** Route to the flight search interface */
    const val SEARCH = "search"
    
    /** Route to the flight search results screen */
    const val SEARCH_RESULTS = "search results"
    
    /** Route to the booking and payment process */
    const val CHECKOUT = "checkout"
    
    /** Route to the user's reservations screen */
    const val RESERVATIONS = "reservations"
    
    /** Route to the user account management screen */
    const val ACCOUNT = "account"
}

