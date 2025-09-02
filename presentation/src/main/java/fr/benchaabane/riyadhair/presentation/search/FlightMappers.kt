package fr.benchaabane.riyadhair.presentation.search

import fr.benchaabane.riyadhair.domain.flights.models.Flight

/**
 * Maps domain Flight model to presentation FlightUiModel.
 *
 * This extension function transforms the domain layer Flight model into
 * a UI-optimized FlightUiModel for presentation layer consumption.
 * It handles data formatting, time conversion, and provides user-friendly
 * representations of flight information.
 *
 * **Mapping Transformations:**
 * - **Airline Information**: Maps airline name and flight number
 * - **Time Formatting**: Converts time objects to readable string format
 * - **Airport Details**: Extracts airport codes and city names
 * - **Price Formatting**: Adds currency symbol and formatting
 * - **Cabin Class**: Maps cabin class to display name
 *
 * **Data Formatting:**
 * - **Time Display**: Converts LocalTime to "HH:MM" format
 * - **Price Format**: Adds "EUR" currency suffix
 * - **Airport Codes**: Extracts IATA codes for quick identification
 * - **City Names**: Provides user-friendly city names
 * - **Duration**: Preserves duration in readable format
 *
 * **UI Optimization:**
 * - **Readable Format**: Human-friendly time and price displays
 * - **Consistent Structure**: Uniform data presentation
 * - **Quick Identification**: Airport codes for rapid recognition
 * - **User Experience**: Clear and understandable information
 *
 * **Use Cases:**
 * - Search results data preparation
 * - Flight comparison interface
 * - Flight selection display
 * - Domain to UI data transformation
 * - Consistent flight information presentation
 *
 * **Architecture Benefits:**
 * - **Separation of Concerns**: Keeps domain and UI models separate
 * - **Data Consistency**: Ensures uniform flight presentation
 * - **Maintainability**: Centralized mapping logic
 * - **Testability**: Isolated transformation logic
 *
 * @return FlightUiModel formatted for presentation layer consumption
 */
internal fun Flight.toUi() = FlightUiModel(
    companyName = airline,
    flightNumber = flightNumber,
    departureTime = "${departureTime.hour}:${departureTime.minute}",
    arrivalTime = "${arrivalTime.hour}:${arrivalTime.minute}",
    duration = duration,
    departureAirportCode = departureAirport.code,
    arrivalAirportCode = arrivalAirport.code,
    departureCity = departureAirport.city,
    arrivalCity = arrivalAirport.city,
    price = "$price EUR",
    cabin = cabinClass.displayName,
    availableSeats = availableSeats,
    aircraftType = aircraft
)