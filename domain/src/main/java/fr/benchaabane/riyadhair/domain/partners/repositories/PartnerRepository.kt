package fr.benchaabane.riyadhair.domain.partners.repositories

import fr.benchaabane.riyadhair.domain.partners.models.Partner
import kotlinx.coroutines.flow.Flow

interface PartnerRepository {
    suspend fun getPartners(): Result<List<Partner>>
}
