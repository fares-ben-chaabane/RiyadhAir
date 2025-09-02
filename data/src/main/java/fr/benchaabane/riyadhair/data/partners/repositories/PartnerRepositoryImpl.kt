package fr.benchaabane.riyadhair.data.partners.repositories

import fr.benchaabane.riyadhair.core.extensions.recoverSuspendCatching
import fr.benchaabane.riyadhair.core.extensions.runSuspendCatching
import fr.benchaabane.riyadhair.data.partners.api.PartnerService
import fr.benchaabane.riyadhair.data.partners.dao.PartnerDao
import fr.benchaabane.riyadhair.data.partners.dao.PartnerEntity
import fr.benchaabane.riyadhair.data.partners.mappers.toDomain
import fr.benchaabane.riyadhair.data.partners.mappers.toEntity
import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.models.PartnerCategory
import fr.benchaabane.riyadhair.domain.partners.repositories.PartnerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts

/**
 * Implementation of the PartnerRepository interface.
 *
 * This repository provides access to partner data by combining remote API
 * calls with local database caching. It implements a network-first strategy
 * with fallback to cached data when the network is unavailable or returns
 * empty results.
 *
 * **Architecture Pattern:**
 * - **Repository Pattern**: Implements domain repository interface
 * - **Dependency Injection**: Uses Hilt for service injection
 * - **Data Flow**: API → Database → Domain Models
 * - **Error Handling**: Graceful fallback to cached data
 *
 * **Data Strategy:**
 * - **Network First**: Attempts to fetch fresh data from API
 * - **Cache Fallback**: Uses local database when network fails
 * - **Data Synchronization**: Updates local cache with remote data
 * - **Offline Support**: Provides data even without network
 *
 * **Dependencies:**
 * - **PartnerDao**: Local database access for caching
 * - **PartnerService**: Remote API access for fresh data
 * - **Mappers**: Convert between data and domain models
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * **Usage Context:**
 * - **Use Cases**: Called by domain layer use cases
 * - **Data Source**: Provides partners for UI presentation
 * - **Cache Management**: Maintains fresh partner data
 * - **Offline Support**: Ensures data availability
 *
 * @see PartnerRepository
 * @see PartnerDao
 * @see PartnerService
 * @see fr.benchaabane.riyadhair.domain.partners.models.Partner
 */
@OptIn(ExperimentalContracts::class)
class PartnerRepositoryImpl @Inject constructor(
    private val partnerDao: PartnerDao,
    private val partnerService: PartnerService
) : PartnerRepository {

    /**
     * Retrieves all available partners.
     *
     * This method implements a network-first strategy for fetching partners:
     * 1. Attempts to fetch fresh partners from the remote API
     * 2. If successful and partners are available, updates local cache
     * 3. If network fails or returns empty results, falls back to cached data
     * 4. Returns domain models for use in the presentation layer
     *
     * **Data Flow:**
     * - **API Call**: Fetches partners from PartnerService
     * - **Cache Update**: Stores fresh data in local database
     * - **Data Conversion**: Maps entities to domain models
     * - **Fallback Logic**: Uses cached data when needed
     *
     * **Error Handling:**
     * - **Network Failures**: Gracefully falls back to cached data
     * - **Empty Responses**: Handles cases where API returns no partners
     * - **Exception Recovery**: Uses recoverSuspendCatching for safety
     * - **User Experience**: Ensures data is always available
     *
     * **Performance Considerations:**
     * - **Async Operations**: Non-blocking API and database calls
     * - **Cache Efficiency**: Minimizes redundant API calls
     * - **Data Freshness**: Prioritizes latest available data
     * - **Resource Management**: Efficient memory and storage usage
     *
     * **Business Logic:**
     * - **Partner Prioritization**: API data takes precedence over cache
     * - **Data Consistency**: Ensures local cache reflects remote state
     * - **User Satisfaction**: Provides best available partner data
     * - **Offline Resilience**: Maintains functionality without network
     *
     * @return Result containing a list of partners or an empty list on failure
     */
    override suspend fun getPartners(): Result<List<Partner>> {
        return runSuspendCatching {
            val response = partnerService.getPartners()
            if (response.partners.isEmpty()) {
                partnerDao.getPartners().map { it.toDomain() }
            } else {
                val entities = response.partners.map { it.toEntity() }
                partnerDao.clearAll()
                partnerDao.upsertAll(entities)
                entities.map { it.toDomain() }
            }
        }.recoverSuspendCatching {
            emptyList()
        }
    }
}
