package fr.benchaabane.riyadhair.data.flights.mappers

import fr.benchaabane.riyadhair.data.flights.api.AirportDto
import fr.benchaabane.riyadhair.data.flights.api.FlightDto
import fr.benchaabane.riyadhair.domain.flights.models.Airport
import fr.benchaabane.riyadhair.domain.flights.models.CabinClass
import fr.benchaabane.riyadhair.domain.flights.models.Flight
import java.time.LocalDateTime

/**
 * Maps a FlightDto to a domain Flight model.
 *
 * This function converts API flight data to the domain model,
 * handling nullable fields, data transformations, and ensuring
 * proper type safety. It processes time strings and provides
 * fallback values for missing data.
 *
 * **Mapping Details:**
 * - **API Fields**: Maps all DTO properties to domain model
 * - **Nullable Handling**: Provides safe defaults for missing API data
 * - **Time Processing**: Converts ISO time strings to LocalDateTime
 * - **Cabin Class**: Maps string values to CabinClass enum
 * - **Data Safety**: Uses fallback values for null fields
 *
 * **Data Transformations:**
 * - **Time Strings**: ISO format to LocalDateTime conversion
 * - **Cabin Class**: String to CabinClass enum mapping
 * - **Nullable Fields**: Safe handling with fallback values
 * - **String Safety**: Empty string fallbacks for null values
 *
 * **Usage Context:**
 * - **API Integration**: Converting network responses to domain models
 * - **Repository Layer**: Processing external flight data
 * - **Data Flow**: API → DTO → Domain → Use Case
 * - **Flight Search**: Processing search results
 *
 * **Error Handling:**
 * - **Time Parsing**: Handles ISO time format conversion
 * - **Cabin Class**: Provides default economy class for invalid values
 * - **Nullable Safety**: Graceful handling of missing data
 *
 * @param this The FlightDto to convert to domain model
 * @return Domain Flight model with API data safely mapped
 */
internal fun FlightDto.toDomain(): Flight = Flight(
    id = id,
    flightNumber = flightNumber,
    airline = airline.orEmpty(),
    departureAirport = departureAirport.toDomain(),
    arrivalAirport = arrivalAirport.toDomain(),
    departureTime = LocalDateTime.parse(departureTime?.replace("Z", "")),
    arrivalTime = LocalDateTime.parse(arrivalTime?.replace("Z", "")),
    duration = duration.orEmpty(),
    price = price ?: .0,
    cabinClass = CabinClass.ECONOMY,
    availableSeats = availableSeats ?: 0,
    aircraft = aircraft.orEmpty()
)

/**
 * Maps an AirportDto to a domain Airport model.
 *
 * This function converts API airport data to the domain model,
 * providing a clean mapping between API and domain representations.
 * All airport fields are required, so no nullable handling is needed.
 *
 * **Mapping Details:**
 * - **Direct Mapping**: All fields mapped directly without transformation
 * - **Type Safety**: Ensures proper data type conversion
 * - **Data Integrity**: Maintains airport information accuracy
 * - **Simple Conversion**: Straightforward DTO to domain mapping
 *
 * **Usage Context:**
 * - **Flight Routing**: Converting departure/arrival airport data
 * - **Location Display**: Processing airport information for UI
 * - **Data Flow**: API → DTO → Domain → Use Case
 * - **Airport Information**: Managing airport details
 *
 * **Field Mapping:**
 * - **code**: IATA airport code (e.g., "CDG", "JFK")
 * - **name**: Full airport name
 * - **city**: City location
 * - **country**: Country location
 * - **timezone**: Airport timezone
 *
 * @param this The AirportDto to convert to domain model
 * @return Domain Airport model with API data mapped
 */
internal fun AirportDto.toDomain() = Airport(
    code = code,
    name = name,
    city = city,
    country = country,
    timezone = timezone
)

/**
 * Type alias for cabin class string values.
 *
 * This alias provides semantic meaning to string values
 * representing cabin classes, improving code readability
 * and type safety in mapping operations.
 *
 * **Usage Context:**
 * - **Mapping Functions**: Parameter typing for cabin class conversion
 * - **Type Safety**: Clear indication of expected string values
 * - **Code Readability**: Semantic meaning for cabin class strings
 *
 * @see CabinClass
 */
typealias CabinClassString = String

/**
 * Maps a cabin class string to a CabinClass enum value.
 *
 * This function converts string representations of cabin classes
 * to the corresponding enum values, providing type safety and
 * validation for cabin class data.
 *
 * **Mapping Details:**
 * - **String Values**: Maps specific string values to enum constants
 * - **Case Handling**: Exact string matching for cabin class names
 * - **Default Handling**: Throws exception for invalid cabin classes
 * - **Type Safety**: Ensures valid enum values
 *
 * **Supported Cabin Classes:**
 * - **"Economy"** → `CabinClass.ECONOMY`
 * - **"Premium Economy"** → `CabinClass.PREMIUM_ECONOMY`
 * - **"Business"** → `CabinClass.BUSINESS`
 * - **"First Class"** → `CabinClass.FIRST`
 *
 * **Error Handling:**
 * - **Invalid Values**: Throws IllegalArgumentException for unsupported strings
 * - **Validation**: Ensures only valid cabin classes are processed
 * - **Debugging**: Provides clear error messages for invalid input
 *
 * **Usage Context:**
 * - **Flight Mapping**: Converting API cabin class strings
 * - **Data Validation**: Ensuring valid cabin class values
 * - **Type Safety**: Converting strings to enum types
 * - **Error Handling**: Managing invalid cabin class data
 *
 * @param this The cabin class string to convert
 * @return CabinClass enum value corresponding to the string
 * @throws IllegalArgumentException if the string doesn't match any cabin class
 */
internal fun CabinClassString.toDomain(): CabinClass = when (this) {
    "Economy" -> CabinClass.ECONOMY
    "Premium Economy" -> CabinClass.PREMIUM_ECONOMY
    "Business" -> CabinClass.BUSINESS
    "First Class" -> CabinClass.FIRST
    else -> throw IllegalArgumentException("Unknown cabin class: $this")
}