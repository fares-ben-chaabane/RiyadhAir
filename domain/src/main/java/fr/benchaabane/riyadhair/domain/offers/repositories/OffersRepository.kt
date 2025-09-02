package fr.benchaabane.riyadhair.domain.offers.repositories

import fr.benchaabane.riyadhair.domain.offers.models.Offer
import kotlinx.coroutines.flow.Flow

interface OffersRepository {
    suspend fun getBestOffers(): Result<List<Offer>>
}
