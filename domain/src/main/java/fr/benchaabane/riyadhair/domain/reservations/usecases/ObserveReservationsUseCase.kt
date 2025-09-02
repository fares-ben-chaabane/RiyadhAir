package fr.benchaabane.riyadhair.domain.reservations.usecases

import fr.benchaabane.riyadhair.domain.reservations.models.Reservation
import fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository
import kotlinx.coroutines.flow.Flow

class ObserveReservationsUseCase(private val reservationRepository: ReservationRepository) {
    operator fun invoke(): Flow<List<Reservation>> =
        reservationRepository.observeReservations()
}