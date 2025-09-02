package fr.benchaabane.riyadhair.domain.reservations.usecases

import fr.benchaabane.riyadhair.domain.reservations.models.Reservation
import fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository

/**
 * Use case for saving a new reservation.
 * 
 * This use case follows the command pattern and provides a single responsibility
 * for persisting reservation data to the repository layer.
 * 
 * @property reservationRepository The reservation repository interface for data access
 */
class SaveReservationUseCase(private val reservationRepository: ReservationRepository) {
    /**
     * Saves a new reservation to the system.
     * 
     * @param reservation The reservation object to be saved
     * @return A [Result] indicating success or failure of the save operation.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     */
    suspend operator fun invoke(reservation: Reservation): Result<Unit> =
        runCatching { reservationRepository.saveReservation(reservation) }
}