package fr.benchaabane.riyadhair.data.partners.api

import retrofit2.http.GET

/**
 * Retrofit service interface for partner-related API operations.
 *
 * This service defines the API endpoints for retrieving partner information
 * from the RiyadhAir backend. It provides access to business partners,
 * affiliates, and service providers that offer complementary travel services.
 *
 * **API Endpoints:**
 * - **Partners List**: Retrieves all available partners
 * - **Data Format**: JSON responses with partner information
 * - **Authentication**: May require user authentication for access
 * - **Rate Limiting**: Subject to API rate limiting policies
 *
 * **Partner Categories:**
 * - **Travel Services**: Hotels, car rentals, travel insurance
 * - **Lifestyle**: Restaurants, entertainment, shopping
 * - **Business**: Corporate services, meeting facilities
 * - **Local Partners**: Destination-specific services
 *
 * **Service Features:**
 * - **Suspending Functions**: Designed for coroutine-based async operations
 * - **Retrofit Integration**: Uses Retrofit for HTTP communication
 * - **Type Safety**: Strongly typed responses and error handling
 * - **Network Layer**: Part of the data layer's network infrastructure
 *
 * **Usage Context:**
 * - **Repository Layer**: Called by PartnerRepositoryImpl
 * - **Data Synchronization**: Fetching fresh partner data
 * - **Offline Support**: Updating local partner cache
 * - **User Experience**: Providing partner information and deals
 *
 * @see PartnersResponse
 * @see PartnerDto
 * @see fr.benchaabane.riyadhair.domain.partners.repositories.PartnerRepository
 */
interface PartnerService {
    /**
     * Retrieves all available partners from the API.
     *
     * This method fetches the complete list of business partners,
     * affiliates, and service providers that collaborate with RiyadhAir.
     * The response includes partner details such as names, categories,
     * descriptions, and any special offers or discounts.
     *
     * **API Details:**
     * - **Endpoint**: `GET /partners`
     * - **Response**: Complete list of all partners
     * - **Authentication**: May require valid user session
     * - **Caching**: Response can be cached for offline use
     *
     * **Data Content:**
     * - **Partner Information**: Names, categories, descriptions
     * - **Visual Assets**: Logo and image URLs
     * - **Business Details**: Website URLs, active status
     * - **Special Offers**: Discount percentages and promotions
     *
     * **Error Handling:**
     * - **Network Failures**: Handled by calling repository
     * - **Authentication Errors**: May require user re-login
     * - **Rate Limiting**: Subject to API throttling
     * - **Data Validation**: Ensures response integrity
     *
     * **Performance Considerations:**
     * - **Async Operation**: Non-blocking network call
     * - **Data Size**: Response may contain many partners
     * - **Caching Strategy**: Local storage for offline access
     * - **User Experience**: Provides partner information quickly
     *
     * @return PartnersResponse containing the list of all available partners
     */
    @GET("partners")
    suspend fun getPartners(): PartnersResponse
}
