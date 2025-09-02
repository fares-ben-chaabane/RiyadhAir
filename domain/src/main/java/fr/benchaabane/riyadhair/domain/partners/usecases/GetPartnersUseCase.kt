package fr.benchaabane.riyadhair.domain.partners.usecases

import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.repositories.PartnerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving available partners.
 * 
 * This use case follows the command pattern and provides a single responsibility
 * for fetching partner information from the repository layer.
 * 
 * @property repository The partner repository interface for data access
 */
class GetPartnersUseCase @Inject constructor(
    private val repository: PartnerRepository
) {
    /**
     * Retrieves the list of available partners.
     * 
     * @return A [Result] containing a list of available partners.
     *         The result is wrapped in a [Result] to handle potential errors gracefully.
     */
    suspend operator fun invoke(): Result<List<Partner>> = repository.getPartners()
}
