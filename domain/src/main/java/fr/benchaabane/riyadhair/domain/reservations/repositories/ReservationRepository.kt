package fr.benchaabane.riyadhair.domain.reservations.repositories

import fr.benchaabane.riyadhair.domain.reservations.models.Reservation
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    fun observeReservations(): Flow<List<Reservation>>
    suspend fun saveReservation(reservation: Reservation)
    suspend fun refreshReservations()
}