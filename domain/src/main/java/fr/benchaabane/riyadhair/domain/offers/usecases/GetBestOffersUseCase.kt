package fr.benchaabane.riyadhair.domain.offers.usecases

import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.domain.offers.repositories.OffersRepository
import javax.inject.Inject

/**
 * Use case for retrieving the best available offers.
 * 
 * This use case follows the command pattern and provides a single responsibility
 * for fetching the most attractive offers from the repository layer.
 * 
 * @property repository The offers repository interface for data access
 */
class GetBestOffersUseCase @Inject constructor(
    private val repository: OffersRepository
) {
    /**
     * Retrieves the best available offers.
     * 
     * @return A [Result] containing a list of the best offers available.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     */
    suspend operator fun invoke(): Result<List<Offer>> = repository.getBestOffers()
}
