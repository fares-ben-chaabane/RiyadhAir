package fr.benchaabane.riyadhair.data.partners.mappers

import fr.benchaabane.riyadhair.data.partners.api.PartnerDto
import fr.benchaabane.riyadhair.data.partners.dao.PartnerEntity
import fr.benchaabane.riyadhair.domain.partners.models.Partner
import fr.benchaabane.riyadhair.domain.partners.models.PartnerCategory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Maps a PartnerEntity to a domain Partner model.
 *
 * This extension function converts a database entity to a domain model,
 * parsing the category string into a PartnerCategory enum value for
 * type-safe business logic operations.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, name, image URL, description, discount, website, active status
 * - **Category Conversion**: String category to PartnerCategory enum
 * - **Data Integrity**: Preserves all entity information
 * - **Type Safety**: Ensures proper domain model structure
 *
 * **Field Mapping:**
 * - **Entity Fields**: `category` (String)
 * - **Domain Fields**: `category` (PartnerCategory enum)
 * - **Data Transformation**: String → Enum parsing
 * - **Value Preservation**: All entity data is maintained
 *
 * **Category Handling:**
 * - **Enum Parsing**: Uses `PartnerCategory.valueOf(category)`
 * - **String Matching**: Expects exact category string values
 * - **Type Safety**: Ensures valid category values
 * - **Business Logic**: Enables category-based operations
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting cached entities to domain models
 * - **Data Retrieval**: Providing domain objects for business logic
 * - **Cache to Domain**: Bridging data and domain layers
 * - **Offline Support**: Converting stored data to usable models
 *
 * @return Domain Partner model with parsed category enum
 */
internal fun PartnerEntity.toDomain(): Partner = Partner(
    id = id,
    name = name,
    category = PartnerCategory.valueOf(category),
    imageUrl = imageUrl,
    description = description,
    discountPercentage = discountPercentage,
    websiteUrl = websiteUrl,
    isActive = isActive
)

/**
 * Maps a domain Partner model to a PartnerEntity.
 *
 * This extension function converts a domain model to a database entity,
 * converting the PartnerCategory enum back to a string for database storage.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, name, image URL, description, discount, website, active status
 * - **Category Conversion**: PartnerCategory enum to String
 * - **Data Persistence**: Prepares model for database storage
 * - **Storage Optimization**: String-based category storage
 *
 * **Field Mapping:**
 * - **Domain Fields**: `category` (PartnerCategory enum)
 * - **Entity Fields**: `category` (String)
 * - **Data Transformation**: Enum → String conversion
 * - **Value Preservation**: All domain data is maintained
 *
 * **Category Handling:**
 * - **String Conversion**: Uses `category.name` for enum to string
 * - **Storage Format**: Stores category as string in database
 * - **Data Consistency**: Maintains category information integrity
 * - **Query Support**: Enables string-based category filtering
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting domain models to entities for storage
 * - **Data Persistence**: Saving domain objects to local database
 * - **Cache Management**: Storing fresh data from API responses
 * - **Data Synchronization**: Updating local cache with remote data
 *
 * @return PartnerEntity ready for database storage
 */
internal fun Partner.toEntity(): PartnerEntity = PartnerEntity(
    id = id,
    name = name,
    category = category.name,
    imageUrl = imageUrl,
    description = description,
    discountPercentage = discountPercentage,
    websiteUrl = websiteUrl,
    isActive = isActive
)

/**
 * Maps a PartnerDto to a domain Partner model.
 *
 * This extension function converts an API DTO to a domain model,
 * handling nullable fields with safe defaults and parsing the category
 * string into a PartnerCategory enum value.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, discount percentage
 * - **Nullable Fields**: Name, category, image URL, description, website, active status
 * - **Category Conversion**: String category to PartnerCategory enum with uppercase conversion
 * - **Null Safety**: Provides default values for nullable fields
 *
 * **Field Mapping:**
 * - **DTO Fields**: `name?`, `category?`, `imageUrl?`, etc.
 * - **Domain Fields**: `name`, `category`, `imageUrl`, etc.
 * - **Default Values**: Empty strings for nullable text fields, false for active status
 * - **Category Mapping**: Handles nullable category with uppercase conversion
 *
 * **Null Handling:**
 * - **Text Fields**: Default to empty strings when null
 * - **Active Status**: Defaults to false when null
 * - **Category**: Handles nullable category with safe parsing
 * - **Data Integrity**: Prevents null values from reaching domain layer
 *
 * **Category Processing:**
 * - **Uppercase Conversion**: Converts category to uppercase before parsing
 * - **Enum Parsing**: Uses `PartnerCategory.valueOf()` for type safety
 * - **Null Safety**: Handles null category gracefully
 * - **Business Logic**: Ensures consistent category handling
 *
 * **Usage Context:**
 * - **API Integration**: Converting remote data to domain models
 * - **Data Processing**: Handling API responses in repositories
 * - **Network to Domain**: Bridging external and internal data
 * - **Data Transformation**: Preparing API data for business logic
 *
 * @return Domain Partner model with safe default values and parsed category
 */
internal fun PartnerDto.toDomain(): Partner = Partner(
    id = id,
    name = name.orEmpty(),
    category = PartnerCategory.valueOf(category?.uppercase().orEmpty()),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    discountPercentage = discountPercentage,
    websiteUrl = websiteUrl.orEmpty(),
    isActive = isActive ?: false
)

/**
 * Maps a PartnerDto to a PartnerEntity.
 *
 * This extension function converts an API DTO directly to a database entity,
 * handling nullable fields with safe defaults and preparing the data
 * for immediate database storage.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, discount percentage
 * - **Nullable Fields**: Name, category, image URL, description, website, active status
 * - **Category Processing**: Handles nullable category with uppercase conversion
 * - **Storage Preparation**: Creates entity ready for database storage
 *
 * **Field Mapping:**
 * - **DTO Fields**: `name?`, `category?`, `imageUrl?`, etc.
 * - **Entity Fields**: `name`, `category`, `imageUrl`, etc.
 * - **Default Values**: Empty strings for nullable text fields, false for active status
 * - **Direct Storage**: Enables immediate database persistence
 *
 * **Null Handling:**
 * - **Text Fields**: Default to empty strings when null
 * - **Active Status**: Defaults to false when null
 * - **Category**: Handles nullable category with safe processing
 * - **Data Consistency**: Ensures all fields have valid values
 *
 * **Category Processing:**
 * - **Uppercase Conversion**: Converts category to uppercase for consistency
 * - **Null Safety**: Handles null category gracefully
 * - **Storage Format**: Stores category as string in database
 * - **Data Integrity**: Maintains category information
 *
 * **Usage Context:**
 * - **Cache Management**: Storing API responses directly to database
 * - **Data Synchronization**: Updating local cache with remote data
 * - **Offline Preparation**: Ensuring all data is available locally
 * - **Performance**: Avoiding intermediate domain model conversion
 *
 * @return PartnerEntity ready for immediate database storage
 */
internal fun PartnerDto.toEntity(): PartnerEntity = PartnerEntity(
    id = id,
    name = name.orEmpty(),
    category = category?.uppercase().orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    discountPercentage = discountPercentage,
    websiteUrl = websiteUrl.orEmpty(),
    isActive = isActive ?: false
)
