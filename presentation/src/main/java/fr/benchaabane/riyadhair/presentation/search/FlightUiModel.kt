package fr.benchaabane.riyadhair.presentation.search

import androidx.compose.runtime.Immutable

/**
 * UI model for displaying flight information in the search results.
 *
 * This data class represents flight information specifically formatted
 * for UI presentation in the search results screen. It transforms domain
 * flight models into UI-friendly formats with all necessary information
 * for user decision-making and flight selection.
 *
 * **Flight Information Display:**
 * - **Airline Details**: Company name and flight number identification
 * - **Route Information**: Departure and arrival details with city names
 * - **Timing Details**: Departure, arrival, and flight duration
 * - **Pricing Information**: Cost and cabin class options
 * - **Availability**: Seat availability and aircraft information
 *
 * **UI Presentation Features:**
 * - **Airport Codes**: IATA codes for quick identification (e.g., "RUH", "DXB")
 * - **City Names**: User-friendly city names for clear understanding
 * - **Formatted Times**: Human-readable time formats
 * - **Duration Display**: Flight duration in user-friendly format
 * - **Price Formatting**: Currency and price display
 *
 * **Search and Selection:**
 * - **Flight Comparison**: All necessary details for flight comparison
 * - **User Decision Making**: Comprehensive information for booking decisions
 * - **Filtering Support**: Data structured for search and filter operations
 * - **Sorting Capabilities**: Sortable fields for different criteria
 *
 * **Immutable Design:**
 * - **@Immutable Annotation**: Compose optimization for state changes
 * - **Predictable Updates**: State changes only through proper channels
 * - **Performance**: Efficient Compose recomposition
 * - **Thread Safety**: Safe for concurrent access
 *
 * **Use Cases:**
 * - Search results display
 * - Flight comparison interface
 * - Flight selection and booking
 * - Flight information presentation
 * - Search result filtering and sorting
 *
 * @param companyName Name of the operating airline
 * @param flightNumber Unique flight identifier
 * @param departureTime Scheduled departure time
 * @param arrivalTime Scheduled arrival time
 * @param departureAirportCode IATA code for departure airport
 * @param arrivalAirportCode IATA code for arrival airport
 * @param departureCity Name of departure city
 * @param arrivalCity Name of arrival city
 * @param duration Total flight duration
 * @param price Flight price in local currency
 * @param cabin Cabin class (Economy, Business, First)
 * @param availableSeats Number of seats available for booking
 * @param aircraftType Type of aircraft operating the flight
 */
@Immutable
data class FlightUiModel(
    val companyName: String,
    val flightNumber: String,
    val departureTime: String,
    val arrivalTime: String,
    val departureAirportCode: String,
    val arrivalAirportCode: String,
    val departureCity: String,
    val arrivalCity: String,
    val duration: String,
    val price: String,
    val cabin: String,
    val availableSeats: Int,
    val aircraftType: String,
)