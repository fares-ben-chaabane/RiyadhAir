package fr.benchaabane.riyadhair.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme
import fr.benchaabane.riyadhair.presentation.navigation.AppNavHost

/**
 * Main activity of the RiyadhAir application.
 *
 * This activity serves as the entry point for the application and sets up
 * the main UI structure using Jetpack Compose. It integrates with Hilt
 * for dependency injection and applies the RiyadhAir design system theme.
 *
 * **Application Features:**
 * - **Hilt Integration**: Dependency injection setup for the entire app
 * - **Compose UI**: Modern declarative UI framework
 * - **Design System**: RiyadhAir theme and styling
 * - **Navigation**: App-wide navigation setup through AppNavHost
 *
 * **Architecture Role:**
 * - **Entry Point**: Main activity that launches the application
 * - **Theme Application**: Applies consistent design system
 * - **Navigation Setup**: Initializes the navigation structure
 * - **Dependency Injection**: Hilt integration point
 *
 * **UI Structure:**
 * - RiyadhAir theme wrapper for consistent styling
 * - Material 3 surface with background color
 * - Navigation host for screen management
 * - Responsive layout support
 *
 * **Technical Implementation:**
 * - Extends ComponentActivity for Compose support
 * - Uses setContent for Compose UI setup
 * - Integrates with Hilt for dependency management
 * - Follows Material 3 design principles
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Initializes the main activity and sets up the application UI.
     *
     * This method is called when the activity is first created and performs
     * the initial setup of the application, including UI composition and
     * theme application.
     *
     * **Setup Process:**
     * 1. **Activity Initialization**: Standard Android activity setup
     * 2. **Compose Content**: Sets up the main UI using setContent
     * 3. **Theme Application**: Applies RiyadhAir design system
     * 4. **Navigation Setup**: Initializes the app navigation structure
     *
     * **UI Composition:**
     * - RiyadhAir theme wrapper for consistent styling
     * - Material 3 surface with proper background color
     * - AppNavHost for centralized navigation management
     * - Responsive and adaptive layout support
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RiyadhAirTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost()
                }
            }
        }
    }
}
