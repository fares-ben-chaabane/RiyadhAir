package fr.benchaabane.riyadhair.data.offers.mappers

import fr.benchaabane.riyadhair.data.offers.api.DestinationDto
import fr.benchaabane.riyadhair.data.offers.api.OfferDto
import fr.benchaabane.riyadhair.data.offers.dao.OfferEntity
import fr.benchaabane.riyadhair.domain.offers.models.Destination
import fr.benchaabane.riyadhair.domain.offers.models.Offer

/**
 * Maps an OfferEntity to a domain Offer model.
 *
 * This extension function converts a database entity to a domain model,
 * extracting destination information and creating a proper Destination
 * object from the flattened entity fields.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, prices, discount, validity, descriptions
 * - **Destination Construction**: Builds Destination object from entity fields
 * - **Data Integrity**: Preserves all entity information
 * - **Type Safety**: Ensures proper domain model structure
 *
 * **Field Mapping:**
 * - **Entity Fields**: `destinationId`, `destinationName`, etc.
 * - **Domain Fields**: `destination.id`, `destination.name`, etc.
 * - **Data Transformation**: Flattened → Structured destination
 * - **Value Preservation**: All entity data is maintained
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting cached entities to domain models
 * - **Data Retrieval**: Providing domain objects for business logic
 * - **Cache to Domain**: Bridging data and domain layers
 * - **Offline Support**: Converting stored data to usable models
 *
 * @return Domain Offer model with structured destination information
 */
internal fun OfferEntity.toDomain(): Offer = Offer(
    id = id,
    destination = Destination(
        id = destinationId,
        name = destinationName,
        cityName = destinationCityName,
        countryName = destinationCountryName,
        airportCode = destinationAirportCode,
        imageUrl = destinationImageUrl,
        description = destinationDescription,
        averageTemperature = destinationAverageTemperature,
        timeZone = destinationTimeZone
    ),
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    discountPercentage = discountPercentage,
    validUntil = validUntil,
    description = description,
    termsAndConditions = termsAndConditions
)

/**
 * Maps a domain Offer model to an OfferEntity.
 *
 * This extension function converts a domain model to a database entity,
 * flattening the destination information into individual entity fields
 * for efficient database storage and querying.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, prices, discount, validity, descriptions
 * - **Destination Flattening**: Extracts destination properties to entity fields
 * - **Data Persistence**: Prepares model for database storage
 * - **Storage Optimization**: Flattened structure for efficient queries
 *
 * **Field Mapping:**
 * - **Domain Fields**: `destination.id`, `destination.name`, etc.
 * - **Entity Fields**: `destinationId`, `destinationName`, etc.
 * - **Data Transformation**: Structured destination → Flattened fields
 * - **Value Preservation**: All domain data is maintained
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting domain models to entities for storage
 * - **Data Persistence**: Saving domain objects to local database
 * - **Cache Management**: Storing fresh data from API responses
 * - **Data Synchronization**: Updating local cache with remote data
 *
 * @return OfferEntity ready for database storage
 */
internal fun Offer.toEntity(): OfferEntity = OfferEntity(
    id = id,
    destinationId = destination.id,
    destinationName = destination.name,
    destinationCityName = destination.cityName,
    destinationCountryName = destination.countryName,
    destinationAirportCode = destination.airportCode,
    destinationImageUrl = destination.imageUrl,
    destinationDescription = destination.description,
    destinationAverageTemperature = destination.averageTemperature,
    destinationTimeZone = destination.timeZone,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    discountPercentage = discountPercentage,
    validUntil = validUntil,
    description = description,
    termsAndConditions = termsAndConditions
)

/**
 * Maps an OfferDto to a domain Offer model.
 *
 * This extension function converts an API DTO to a domain model,
 * handling nullable fields with safe defaults and converting the
 * nested destination DTO to a domain Destination object.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, prices, discount, validity, descriptions
 * - **Destination Conversion**: Maps DestinationDto to domain Destination
 * - **Null Safety**: Provides default values for nullable fields
 * - **Data Validation**: Ensures required fields have meaningful defaults
 *
 * **Field Mapping:**
 * - **DTO Fields**: `originalPrice?`, `discountedPrice?`, etc.
 * - **Domain Fields**: `originalPrice`, `discountedPrice`, etc.
 * - **Default Values**: `.0` for prices, `0` for discount, empty strings for text
 * - **Destination Mapping**: Delegates to `DestinationDto.toDomain()`
 *
 * **Null Handling:**
 * - **Prices**: Default to `.0` when null
 * - **Discount**: Default to `0` when null
 * - **Text Fields**: Default to empty strings when null
 * - **Destination**: Handled by dedicated mapper function
 *
 * **Usage Context:**
 * - **API Integration**: Converting remote data to domain models
 * - **Data Processing**: Handling API responses in repositories
 * - **Network to Domain**: Bridging external and internal data
 * - **Data Transformation**: Preparing API data for business logic
 *
 * @return Domain Offer model with safe default values for nullable fields
 */
internal fun OfferDto.toDomain(): Offer = Offer(
    id = id,
    destination = destination.toDomain(),
    originalPrice = originalPrice ?: .0,
    discountedPrice = discountedPrice ?: .0,
    discountPercentage = discountPercentage ?: 0,
    validUntil = validUntil.orEmpty(),
    description = description.orEmpty(),
    termsAndConditions = termsAndConditions.orEmpty()
)

/**
 * Maps an OfferDto to an OfferEntity.
 *
 * This extension function converts an API DTO directly to a database entity,
 * flattening the destination information and providing safe defaults for
 * nullable fields. This enables direct storage of API responses.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, prices, discount, validity, descriptions
 * - **Destination Flattening**: Extracts destination properties to entity fields
 * - **Null Safety**: Provides default values for nullable fields
 * - **Storage Preparation**: Creates entity ready for database storage
 *
 * **Field Mapping:**
 * - **DTO Fields**: `destination.name?`, `destination.cityName?`, etc.
 * - **Entity Fields**: `destinationName`, `destinationCityName`, etc.
 * - **Default Values**: Empty strings for nullable text fields
 * - **Direct Storage**: Enables immediate database persistence
 *
 * **Null Handling:**
 * - **Text Fields**: Default to empty strings when null
 * - **Prices**: Default to `.0` when null
 * - **Discount**: Default to `0` when null
 * - **Destination Fields**: All nullable fields get empty string defaults
 *
 * **Usage Context:**
 * - **Cache Management**: Storing API responses directly to database
 * - **Data Synchronization**: Updating local cache with remote data
 * - **Offline Preparation**: Ensuring all data is available locally
 * - **Performance**: Avoiding intermediate domain model conversion
 *
 * @return OfferEntity ready for immediate database storage
 */
internal fun OfferDto.toEntity(): OfferEntity = OfferEntity(
    id = id,
    destinationId = destination.id,
    destinationName = destination.name.orEmpty(),
    destinationCityName = destination.cityName.orEmpty(),
    destinationCountryName = destination.countryName.orEmpty(),
    destinationAirportCode = destination.airportCode.orEmpty(),
    destinationImageUrl = destination.imageUrl.orEmpty(),
    destinationDescription = destination.description.orEmpty(),
    destinationAverageTemperature = destination.averageTemperature,
    destinationTimeZone = destination.timeZone,
    originalPrice = originalPrice ?: .0,
    discountedPrice = discountedPrice ?: .0,
    discountPercentage = discountPercentage ?: 0,
    validUntil = validUntil.orEmpty(),
    description = description.orEmpty(),
    termsAndConditions = termsAndConditions.orEmpty()
)

/**
 * Maps a DestinationDto to a domain Destination model.
 *
 * This extension function converts an API destination DTO to a domain model,
 * handling nullable fields with safe defaults to ensure data integrity
 * and prevent null pointer exceptions in the domain layer.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, time zone, average temperature
 * - **Nullable Fields**: Name, city, country, airport code, image, description
 * - **Default Values**: Empty strings for nullable text fields
 * - **Data Safety**: Ensures all fields have meaningful values
 *
 * **Field Mapping:**
 * - **DTO Fields**: `name?`, `cityName?`, `countryName?`, etc.
 * - **Domain Fields**: `name`, `cityName`, `countryName`, etc.
 * - **Default Values**: Empty strings for all nullable text fields
 * - **Non-null Fields**: ID, time zone, and temperature are preserved as-is
 *
 * **Null Handling:**
 * - **Text Fields**: All nullable text fields default to empty strings
 * - **Required Fields**: ID, time zone, and temperature are assumed non-null
 * - **Data Integrity**: Prevents null values from reaching domain layer
 * - **User Experience**: Ensures UI always has displayable values
 *
 * **Usage Context:**
 * - **API Integration**: Converting remote destination data to domain models
 * - **Data Processing**: Handling destination information in offer mapping
 * - **Null Safety**: Ensuring domain models are always valid
 * - **Data Transformation**: Preparing API data for business logic
 *
 * @return Domain Destination model with safe default values for nullable fields
 */
internal fun DestinationDto.toDomain(): Destination = Destination(
    id = id,
    name = name.orEmpty(),
    cityName = cityName.orEmpty(),
    countryName = countryName.orEmpty(),
    airportCode = airportCode.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    averageTemperature = averageTemperature,
    timeZone = timeZone
)
