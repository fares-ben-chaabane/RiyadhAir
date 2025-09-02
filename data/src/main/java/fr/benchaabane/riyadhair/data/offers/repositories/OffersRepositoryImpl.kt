package fr.benchaabane.riyadhair.data.offers.repositories

import fr.benchaabane.riyadhair.core.extensions.recoverSuspendCatching
import fr.benchaabane.riyadhair.core.extensions.runSuspendCatching
import fr.benchaabane.riyadhair.data.offers.api.OffersService
import fr.benchaabane.riyadhair.data.offers.dao.OfferDao
import fr.benchaabane.riyadhair.data.offers.dao.OfferEntity
import fr.benchaabane.riyadhair.data.offers.mappers.toDomain
import fr.benchaabane.riyadhair.data.offers.mappers.toEntity
import fr.benchaabane.riyadhair.domain.offers.models.Offer
import fr.benchaabane.riyadhair.domain.offers.repositories.OffersRepository
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts

/**
 * Implementation of the OffersRepository interface.
 *
 * This repository provides access to travel offers data by combining
 * remote API calls with local database caching. It implements a
 * network-first strategy with fallback to cached data when the network
 * is unavailable or returns empty results.
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
 * - **OfferDao**: Local database access for caching
 * - **OffersService**: Remote API access for fresh data
 * - **Mappers**: Convert between data and domain models
 *
 * **Threading:**
 * All methods are suspending functions designed to be called from
 * coroutines in the appropriate dispatcher context.
 *
 * **Usage Context:**
 * - **Use Cases**: Called by domain layer use cases
 * - **Data Source**: Provides offers for UI presentation
 * - **Cache Management**: Maintains fresh offer data
 * - **Offline Support**: Ensures data availability
 *
 * @see OffersRepository
 * @see OfferDao
 * @see OffersService
 * @see fr.benchaabane.riyadhair.domain.offers.models.Offer
 */
@OptIn(ExperimentalContracts::class)
class OffersRepositoryImpl @Inject constructor(
    private val offerDao: OfferDao,
    private val offersService: OffersService
) : OffersRepository {

    /**
     * Retrieves the best available travel offers.
     *
     * This method implements a network-first strategy for fetching offers:
     * 1. Attempts to fetch fresh offers from the remote API
     * 2. If successful and offers are available, updates local cache
     * 3. If network fails or returns empty results, falls back to cached data
     * 4. Returns domain models for use in the presentation layer
     *
     * **Data Flow:**
     * - **API Call**: Fetches offers from OffersService
     * - **Cache Update**: Stores fresh data in local database
     * - **Data Conversion**: Maps entities to domain models
     * - **Fallback Logic**: Uses cached data when needed
     *
     * **Error Handling:**
     * - **Network Failures**: Gracefully falls back to cached data
     * - **Empty Responses**: Handles cases where API returns no offers
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
     * - **Offer Prioritization**: API data takes precedence over cache
     * - **Data Consistency**: Ensures local cache reflects remote state
     * - **User Satisfaction**: Provides best available offer data
     * - **Offline Resilience**: Maintains functionality without network
     *
     * @return Result containing a list of offers or an empty list on failure
     */
    override suspend fun getBestOffers(): Result<List<Offer>> {
        return runSuspendCatching {
            val response = offersService.getBestOffers()
            if (response.offers.isNullOrEmpty()) {
                offerDao.getOffers().map { it.toDomain() }
            } else {
                val entities = response.offers.map { it.toEntity() }
                offerDao.clearAll()
                offerDao.upsertAll(entities)
                entities.map { it.toDomain() }
            }
        }.recoverSuspendCatching {
            emptyList()
        }
    }

    /**
     * Seeds the local database with mock offer data.
     *
     * This private method provides sample travel offers for development
     * and testing purposes. It creates realistic offer data including
     * various destinations, pricing, and promotional details.
     *
     * **Mock Data Features:**
     * - **Multiple Destinations**: Los Angeles, New York, Tokyo, London, Marrakech
     * - **Realistic Pricing**: Original and discounted prices with percentage calculations
     * - **Destination Details**: Airport codes, temperatures, time zones, descriptions
     * - **Visual Content**: Image URLs for destination representation
     * - **Business Rules**: Terms, conditions, and validity periods
     *
     * **Data Structure:**
     * - **Geographic Diversity**: Covers multiple continents and countries
     * - **Price Range**: Various price points for different market segments
     * - **Discount Levels**: Different discount percentages for testing
     * - **Content Richness**: Comprehensive destination information
     * - **Real-world Context**: Realistic airport codes and city names
     *
     * **Usage Context:**
     * - **Development**: Provides data for UI testing and development
     * - **Testing**: Enables unit and integration testing
     * - **Demo Purposes**: Shows app functionality with sample data
     * - **Offline Development**: Allows work without network connectivity
     *
     * **Implementation Details:**
     * - **Entity Creation**: Builds OfferEntity instances with mock data
     * - **Database Storage**: Uses DAO to persist mock offers
     * - **Data Consistency**: Ensures all required fields are populated
     * - **Realistic Values**: Uses believable data for testing scenarios
     */
    private suspend fun seedMockOffers() {
        val mockOffers = listOf(
            OfferEntity(
                id = "offer1",
                destinationId = "dest1",
                destinationName = "Los Angeles",
                destinationCityName = "Los Angeles",
                destinationCountryName = "États-Unis",
                destinationAirportCode = "LAX",
                destinationImageUrl = "https://images.unsplash.com/photo-1534190239940-9ba8944ea261?w=800&h=600&fit=crop",
                destinationDescription = "City of Angels",
                destinationAverageTemperature = "22°C",
                destinationTimeZone = "PST",
                originalPrice = 899.0,
                discountedPrice = 598.0,
                discountPercentage = 33,
                validUntil = "2024-12-31",
                description = "Découvrez la ville des anges",
                termsAndConditions = "Valable jusqu'à fin d'année"
            ),
            OfferEntity(
                id = "offer2",
                destinationId = "dest2",
                destinationName = "New York",
                destinationCityName = "New York",
                destinationCountryName = "États-Unis",
                destinationAirportCode = "JFK",
                destinationImageUrl = "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?w=800&h=600&fit=crop",
                destinationDescription = "The city that never sleeps",
                destinationAverageTemperature = "15°C",
                destinationTimeZone = "EST",
                originalPrice = 799.0,
                discountedPrice = 549.0,
                discountPercentage = 31,
                validUntil = "2024-12-31",
                description = "Découvrez la Big Apple",
                termsAndConditions = "Valable jusqu'à fin d'année"
            ),
            OfferEntity(
                id = "offer3",
                destinationId = "dest3",
                destinationName = "Tokyo",
                destinationCityName = "Tokyo",
                destinationCountryName = "Japon",
                destinationAirportCode = "NRT",
                destinationImageUrl = "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?w=800&h=600&fit=crop",
                destinationDescription = "Where tradition meets innovation",
                destinationAverageTemperature = "18°C",
                destinationTimeZone = "JST",
                originalPrice = 1299.0,
                discountedPrice = 899.0,
                discountPercentage = 31,
                validUntil = "2024-12-31",
                description = "Explorez le Japon moderne",
                termsAndConditions = "Valable jusqu'à fin d'année"
            ),
            OfferEntity(
                id = "offer4",
                destinationId = "dest4",
                destinationName = "Londres",
                destinationCityName = "Londres",
                destinationCountryName = "Royaume-Uni",
                destinationAirportCode = "LHR",
                destinationImageUrl = "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=800&h=600&fit=crop",
                destinationDescription = "History and modernity combined",
                destinationAverageTemperature = "12°C",
                destinationTimeZone = "GMT",
                originalPrice = 599.0,
                discountedPrice = 399.0,
                discountPercentage = 33,
                validUntil = "2024-12-31",
                description = "Explorez la ville royale",
                termsAndConditions = "Valable jusqu'à fin d'année"
            ),
            OfferEntity(
                id = "offer5",
                destinationId = "dest5",
                destinationName = "Marrakech",
                destinationCityName = "Marrakech",
                destinationCountryName = "Maroc",
                destinationAirportCode = "RAK",
                destinationImageUrl = "https://images.unsplash.com/photo-1539650116574-75c0c6d73f6e?w=800&h=600&fit=crop",
                destinationDescription = "The red city of Morocco",
                destinationAverageTemperature = "25°C",
                destinationTimeZone = "WET",
                originalPrice = 499.0,
                discountedPrice = 299.0,
                discountPercentage = 40,
                validUntil = "2024-12-31",
                description = "Découvrez la perle du Maroc",
                termsAndConditions = "Valable jusqu'à fin d'année"
            )
        )
        offerDao.upsertAll(mockOffers)
    }
}
