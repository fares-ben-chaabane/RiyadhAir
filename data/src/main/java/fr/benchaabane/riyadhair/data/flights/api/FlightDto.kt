package fr.benchaabane.riyadhair.data.flights.api

import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for flight information from the API.
 *
 * This DTO represents a flight received from the RiyadhAir backend,
 * including route details, timing information, pricing, and aircraft details.
 * It serves as the data structure for API communication when searching
 * and retrieving flight information.
 *
 * **Flight Components:**
 * - **Route Information**: Origin and destination airports
 * - **Timing Details**: Departure and arrival times
 * - **Pricing Information**: Base price and cabin class options
 * - **Aircraft Details**: Flight number and aircraft type
 *
 * **Data Structure:**
 * - **Core Fields**: ID, route, timing, pricing, aircraft details
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Data Validation**: Ensures required fields are present
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete flight data.
 *
 * @property id Unique identifier for the flight
 * @property flightNumber Flight number (e.g., "SA123")
 * @property departureAirport Origin airport information
 * @property arrivalAirport Destination airport information
 * @property departureTime Scheduled departure time (ISO 8601 format)
 * @property arrivalTime Scheduled arrival time (ISO 8601 format)
 * @property price Base price for the flight
 * @property cabinClass Available cabin classes (e.g., "economy", "business", "first")
 * @property aircraft Aircraft type for this flight
 *
 * @see AirportDto
 * @see FlightsResponse
 * @see fr.benchaabane.riyadhair.domain.flights.models.Flight
 */
@Serializable
data class FlightDto(
    val id: String,
    val flightNumber: String,
    val departureAirport: AirportDto,
    val arrivalAirport: AirportDto,
    val airline: String?,
    val departureTime: String?,
    val arrivalTime: String?,
    val duration: String?,
    val price: Double?,
    val cabinClass: String?,
    val availableSeats: Int?,
    val aircraft: String?,
    val stops: List<AirportDto>? = emptyList()
)

/**
 * Data Transfer Object for airport information from the API.
 *
 * This DTO represents airport details including location information,
 * codes, and descriptive names. It's used within flight DTOs to
 * provide comprehensive route information.
 *
 * **Airport Information:**
 * - **Location Details**: City, country, and geographic coordinates
 * - **Identification**: IATA and ICAO codes
 * - **Descriptive Names**: Human-readable airport and city names
 * - **Geographic Data**: Latitude and longitude coordinates
 *
 * **Data Structure:**
 * - **Core Fields**: ID, codes, names, location coordinates
 * - **API Integration**: Designed for JSON serialization/deserialization
 * - **Geographic Support**: Provides location data for mapping
 * - **Type Safety**: Strongly typed for reliable data handling
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * All fields are required to ensure complete airport data.
 *
 * @property code IATA airport code (e.g., "CDG", "JFK")
 * @property name Airport name (e.g., "Charles de Gaulle Airport")
 * @property city City name where the airport is located
 * @property country Country name where the airport is located
 *
 * @see fr.benchaabane.riyadhair.domain.flights.models.Airport
 */
@Serializable
data class AirportDto(
    val code: String,
    val name: String,
    val city: String,
    val country: String,
    val timezone: String
)

/**
 * Data Transfer Object for flight search response from the API.
 *
 * This DTO wraps a list of flights returned from the flight search API endpoint.
 * It provides a structured response format for bulk flight data retrieval,
 * typically used when searching for available flights between airports.
 *
 * **Response Structure:**
 * - **Flights List**: Collection of FlightDto objects
 * - **Bulk Data**: Efficient retrieval of multiple flights
 * - **API Consistency**: Standard response format
 * - **Data Organization**: Structured collection for easy processing
 *
 * **Usage Context:**
 * - **API Responses**: Wrapping flight search results
 * - **Data Processing**: Bulk flight data handling
 * - **Repository Layer**: Converting API responses to domain models
 * - **Cache Management**: Storing multiple flights efficiently
 *
 * **Serialization:**
 * Uses Kotlinx Serialization for JSON parsing from API responses.
 * Designed to handle API response structures consistently.
 *
 * @property flights List of available flights matching search criteria
 *
 * @see FlightDto
 * @see fr.benchaabane.riyadhair.domain.flights.models.Flight
 */
@Serializable
data class FlightsResponse(
    val flights: List<FlightDto>
)
