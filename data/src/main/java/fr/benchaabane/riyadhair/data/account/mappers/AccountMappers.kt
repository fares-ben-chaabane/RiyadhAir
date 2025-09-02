package fr.benchaabane.riyadhair.data.account.mappers

import fr.benchaabane.riyadhair.data.account.api.AccountDto
import fr.benchaabane.riyadhair.data.account.api.LoyaltyLevelDto
import fr.benchaabane.riyadhair.data.account.dao.AccountEntity
import fr.benchaabane.riyadhair.domain.account.models.Account
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyLevel
import fr.benchaabane.riyadhair.domain.account.models.LoyaltyTier
import kotlin.text.orEmpty

/**
 * Maps an AccountEntity to a domain Account model.
 *
 * This function converts database entity data to the domain model,
 * ensuring proper data transformation and type safety. It handles
 * the conversion of loyalty level information and maintains data
 * integrity across the mapping process.
 *
 * **Mapping Details:**
 * - **Entity Fields**: Maps all database entity fields to domain properties
 * - **Loyalty Level**: Converts loyalty tier string to enum value
 * - **Data Safety**: Uses safe string operations with fallback values
 * - **Type Conversion**: Ensures proper enum parsing for loyalty tiers
 *
 * **Usage Context:**
 * - **Database Operations**: Converting stored data to domain models
 * - **Repository Layer**: Providing domain models to use cases
 * - **Data Flow**: Database → Entity → Domain → Use Case
 *
 * **Data Transformation:**
 * - **Loyalty Tier**: String to LoyaltyTier enum conversion
 * - **Nullable Fields**: Proper handling of optional database fields
 * - **String Safety**: Fallback to empty strings for null values
 *
 * @param this The AccountEntity to convert to domain model
 * @return Domain Account model with properly mapped data
 */
internal fun AccountEntity.toDomain(): Account = Account(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNumber,
    loyaltyLevel = LoyaltyLevel(
        name = loyaltyLevelName,
        tier = LoyaltyTier.valueOf(loyaltyLevelName.uppercase()),
        color = loyaltyColor
    ),
    milesPoints = milesPoints,
    xpPoints = xpPoints,
    profileImageUrl = profileImageUrl,
    preferredLanguage = preferredLanguage,
    currentLocation = currentLocation
)

/**
 * Maps a domain Account model to an AccountEntity.
 *
 * This function converts domain model data to database entity format,
 * preparing it for local storage operations. It handles the conversion
 * of loyalty level information and ensures data consistency.
 *
 * **Mapping Details:**
 * - **Domain Fields**: Maps all domain properties to entity fields
 * - **Loyalty Level**: Extracts loyalty information for database storage
 * - **Data Safety**: Handles nullable domain properties safely
 * - **String Conversion**: Converts enum values to database strings
 *
 * **Usage Context:**
 * - **Data Persistence**: Converting domain models for database storage
 * - **Repository Operations**: Preparing data for local database operations
 * - **Data Flow**: Use Case → Domain → Entity → Database
 *
 * **Data Transformation:**
 * - **Loyalty Information**: Extracts loyalty level details for storage
 * - **Nullable Handling**: Safely converts optional domain properties
 * - **String Storage**: Converts enum values to database-compatible strings
 *
 * @param this The domain Account model to convert to entity
 * @return AccountEntity ready for database operations
 */
internal fun Account.toEntity(): AccountEntity = AccountEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNumber,
    loyaltyLevelName = loyaltyLevel?.name.orEmpty(),
    loyaltyTier = loyaltyLevel?.tier?.name.orEmpty(),
    loyaltyColor = loyaltyLevel?.color.orEmpty(),
    milesPoints = milesPoints,
    xpPoints = xpPoints,
    profileImageUrl = profileImageUrl,
    preferredLanguage = preferredLanguage,
    currentLocation = currentLocation
)

/**
 * Maps an AccountDto to a domain Account model.
 *
 * This function converts API response data to the domain model,
 * handling nullable API fields and ensuring data consistency.
 * It provides safe fallbacks for missing or null API data.
 *
 * **Mapping Details:**
 * - **API Fields**: Maps all DTO properties to domain model
 * - **Nullable Handling**: Provides safe defaults for missing API data
 * - **Loyalty Level**: Converts DTO loyalty information to domain model
 * - **Data Safety**: Uses fallback values for null API fields
 *
 * **Usage Context:**
 * - **API Integration**: Converting network responses to domain models
 * - **Repository Layer**: Processing external data for local use
 * - **Data Flow**: API → DTO → Domain → Use Case
 *
 * **Data Transformation:**
 * - **String Safety**: Fallback to empty strings for null values
 * - **Integer Safety**: Fallback to 0 for missing numeric values
 * - **Loyalty Mapping**: Converts DTO loyalty data to domain format
 *
 * @param this The AccountDto to convert to domain model
 * @return Domain Account model with API data safely mapped
 */
internal fun AccountDto.toDomain(): Account = Account(
    id = id,
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    email = email.orEmpty(),
    phoneNumber = phoneNumber,
    loyaltyLevel = loyaltyLevel?.toDomain(),
    milesPoints = milesPoints ?: 0,
    xpPoints = xpPoints ?: 0,
    profileImageUrl = profileImageUrl,
    preferredLanguage = preferredLanguage.orEmpty(),
    currentLocation = currentLocation
)

/**
 * Maps an AccountDto to an AccountEntity.
 *
 * This function converts API response data directly to database entity
 * format, enabling efficient data storage without intermediate domain
 * model conversion. It handles nullable API fields safely.
 *
 * **Mapping Details:**
 * - **Direct Conversion**: API DTO to database entity mapping
 * - **Nullable Handling**: Safe conversion of optional API fields
 * - **Loyalty Information**: Extracts loyalty data for database storage
 * - **Data Safety**: Provides fallback values for missing data
 *
 * **Usage Context:**
 * - **Direct Storage**: Converting API data for immediate database storage
 * - **Efficiency**: Bypassing domain model for direct persistence
 * - **Data Flow**: API → DTO → Entity → Database
 *
 * **Data Transformation:**
 * - **String Safety**: Fallback to empty strings for null values
 * - **Integer Safety**: Fallback to 0 for missing numeric values
 * - **Loyalty Storage**: Converts loyalty data to database format
 *
 * @param this The AccountDto to convert to entity
 * @return AccountEntity ready for database storage
 */
internal fun AccountDto.toEntity(): AccountEntity = AccountEntity(
    id = id,
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    email = email.orEmpty(),
    phoneNumber = phoneNumber,
    loyaltyLevelName = loyaltyLevel?.name.orEmpty(),
    loyaltyTier = loyaltyLevel?.tier.orEmpty(),
    loyaltyColor = loyaltyLevel?.color.orEmpty(),
    milesPoints = milesPoints ?: 0,
    xpPoints = xpPoints ?: 0,
    profileImageUrl = profileImageUrl,
    preferredLanguage = preferredLanguage.orEmpty(),
    currentLocation = currentLocation
)

/**
 * Maps a LoyaltyLevelDto to a domain LoyaltyLevel model.
 *
 * This function converts API loyalty level data to the domain model,
 * handling string-to-enum conversion and ensuring data consistency.
 *
 * **Mapping Details:**
 * - **API Fields**: Maps DTO loyalty properties to domain model
 * - **Enum Conversion**: Converts tier string to LoyaltyTier enum
 * - **String Safety**: Handles nullable API fields safely
 * - **Data Consistency**: Ensures proper enum value parsing
 *
 * **Usage Context:**
 * - **Loyalty Processing**: Converting API loyalty data to domain format
 * - **Account Mapping**: Supporting account data transformation
 * - **Data Flow**: API → DTO → Domain → Use Case
 *
 * **Data Transformation:**
 * - **Tier Conversion**: String to LoyaltyTier enum mapping
 * - **Case Handling**: Proper uppercase conversion for enum parsing
 * - **String Safety**: Safe handling of nullable API fields
 *
 * @param this The LoyaltyLevelDto to convert to domain model
 * @return Domain LoyaltyLevel model with properly mapped data
 */
internal fun LoyaltyLevelDto.toDomain(): LoyaltyLevel = LoyaltyLevel(
    name = name,
    tier = LoyaltyTier.valueOf(tier.uppercase()),
    color = color,
)

/**
 * Maps a domain Account model to an AccountDto.
 *
 * This function converts domain model data to API request format,
 * preparing it for network operations. It handles enum-to-string
 * conversion and ensures API compatibility.
 *
 * **Mapping Details:**
 * - **Domain Fields**: Maps all domain properties to DTO format
 * - **Loyalty Conversion**: Converts loyalty enum to API string format
 * - **Data Safety**: Handles nullable domain properties safely
 * - **API Compatibility**: Ensures proper API request format
 *
 * **Usage Context:**
 * - **API Requests**: Converting domain data for network operations
 * - **Data Synchronization**: Preparing local data for server updates
 * - **Data Flow**: Use Case → Domain → DTO → API
 *
 * **Data Transformation:**
 * - **Enum Conversion**: LoyaltyTier enum to lowercase string
 * - **Nullable Handling**: Safe conversion of optional domain properties
 * - **API Format**: Ensures proper API request structure
 *
 * @param this The domain Account model to convert to DTO
 * @return AccountDto ready for API operations
 */
internal fun Account.toDto(): AccountDto = AccountDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNumber,
    loyaltyLevel = LoyaltyLevelDto(
        name = loyaltyLevel?.name.orEmpty(),
        tier = loyaltyLevel?.tier?.name.orEmpty().lowercase(),
        color = loyaltyLevel?.color.orEmpty(),
    ),
    milesPoints = milesPoints,
    xpPoints = xpPoints,
    profileImageUrl = profileImageUrl,
    preferredLanguage = preferredLanguage,
    currentLocation = currentLocation
)
