package fr.benchaabane.riyadhair.domain.flights.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Flight(
    val id: String,
    val flightNumber: String,
    val airline: String,
    val departureAirport: Airport,
    val arrivalAirport: Airport,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val duration: String,
    val price: Double,
    val currency: String = "EUR",
    val cabinClass: CabinClass,
    val availableSeats: Int,
    val aircraft: String,
    val stops: List<Airport> = emptyList()
)

data class Airport(
    val code: String,
    val name: String,
    val city: String,
    val country: String,
    val timezone: String
)

data class FlightSearch(
    val departureAirportCode: String,
    val arrivalAirportCode: String,
    val departureDate: LocalDate,
    val returnDate: LocalDate? = null,
    val passengers: PassengerCount,
    val cabinClass: CabinClass,
    val isRoundTrip: Boolean = returnDate != null
)

data class PassengerCount(
    val adults: Int = 1,
    val children: Int = 0,
    val infants: Int = 0
) {
    val total: Int get() = adults + children + infants
}

enum class CabinClass(val displayName: String) {
    ECONOMY("Economy"),
    PREMIUM_ECONOMY("Premium Economy"),
    BUSINESS("Business"),
    FIRST("First Class")
}

data class FlightSearchResult(
    val outboundFlights: List<Flight>,
    val returnFlights: List<Flight> = emptyList(),
    val searchQuery: FlightSearch,
    val totalResults: Int,
    val page: Int,
    val hasNextPage: Boolean
)

data class SelectedFlights(
    val outboundFlight: Flight?,
    val returnFlight: Flight? = null,
    val totalPrice: Double = (outboundFlight?.price ?: 0.0) + (returnFlight?.price ?: 0.0)
)