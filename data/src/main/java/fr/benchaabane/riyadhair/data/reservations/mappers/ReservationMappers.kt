package fr.benchaabane.riyadhair.data.reservations.mappers

import fr.benchaabane.riyadhair.data.reservations.api.ReservationDto
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationEntity
import fr.benchaabane.riyadhair.domain.reservations.models.Reservation

/**
 * Maps a ReservationEntity to a domain Reservation model.
 *
 * This extension function converts a database entity to a domain model,
 * preserving all reservation information for use in business logic
 * and presentation layers.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, flight ID, passenger name, seat, status
 * - **Data Integrity**: Preserves all entity information
 * - **Type Safety**: Ensures proper domain model structure
 * - **Simple Mapping**: Direct field-to-field assignment
 *
 * **Field Mapping:**
 * - **Entity Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Domain Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Data Transformation**: Database entity → Domain model
 * - **Value Preservation**: All entity data is maintained
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting cached entities to domain models
 * - **Data Retrieval**: Providing domain objects for business logic
 * - **Cache to Domain**: Bridging data and domain layers
 * - **Offline Support**: Converting stored data to usable models
 *
 * @return Domain Reservation model with all entity data preserved
 */
internal fun ReservationEntity.toDomain(): Reservation = Reservation(
    id = id,
    flightId = flightId,
    passengerName = passengerName,
    seat = seat,
    status = status
)

/**
 * Maps a domain Reservation model to a ReservationEntity.
 *
 * This extension function converts a domain model to a database entity,
 * preparing the data for local storage and caching operations.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, flight ID, passenger name, seat, status
 * - **Data Persistence**: Prepares model for database storage
 * - **Storage Optimization**: Creates entity ready for Room operations
 * - **Simple Mapping**: Direct field-to-field assignment
 *
 * **Field Mapping:**
 * - **Domain Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Entity Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Data Transformation**: Domain model → Database entity
 * - **Value Preservation**: All domain data is maintained
 *
 * **Usage Context:**
 * - **Repository Layer**: Converting domain models to entities for storage
 * - **Data Persistence**: Saving domain objects to local database
 * - **Cache Management**: Storing fresh data from API responses
 * - **Data Synchronization**: Updating local cache with remote data
 *
 * @return ReservationEntity ready for database storage
 */
internal fun Reservation.toEntity(): ReservationEntity = ReservationEntity(
    id = id,
    flightId = flightId,
    passengerName = passengerName,
    seat = seat,
    status = status
)

/**
 * Maps a ReservationDto to a ReservationEntity.
 *
 * This extension function converts an API DTO directly to a database entity,
 * enabling immediate storage of API responses without intermediate
 * domain model conversion.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, flight ID, passenger name, seat, status
 * - **Storage Preparation**: Creates entity ready for database storage
 * - **API Integration**: Direct conversion from remote data
 * - **Simple Mapping**: Direct field-to-field assignment
 *
 * **Field Mapping:**
 * - **DTO Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Entity Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Data Transformation**: API DTO → Database entity
 * - **Value Preservation**: All DTO data is maintained
 *
 * **Usage Context:**
 * - **Cache Management**: Storing API responses directly to database
 * - **Data Synchronization**: Updating local cache with remote data
 * - **Offline Preparation**: Ensuring all data is available locally
 * - **Performance**: Avoiding intermediate domain model conversion
 *
 * @return ReservationEntity ready for immediate database storage
 */
internal fun ReservationDto.toEntity(): ReservationEntity = ReservationEntity(
    id = id,
    flightId = flightId,
    passengerName = passengerName,
    seat = seat,
    status = status
)

/**
 * Maps a ReservationDto to a domain Reservation model.
 *
 * This extension function converts an API DTO to a domain model,
 * preparing remote data for use in business logic and presentation
 * layers.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, flight ID, passenger name, seat, status
 * - **Data Validation**: Ensures required fields are present
 * - **API Integration**: Converts remote data to domain format
 * - **Simple Mapping**: Direct field-to-field assignment
 *
 * **Field Mapping:**
 * - **DTO Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Domain Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Data Transformation**: API DTO → Domain model
 * - **Value Preservation**: All DTO data is maintained
 *
 * **Usage Context:**
 * - **API Integration**: Converting remote data to domain models
 * - **Data Processing**: Handling API responses in repositories
 * - **Network to Domain**: Bridging external and internal data
 * - **Data Transformation**: Preparing API data for business logic
 *
 * @return Domain Reservation model with all DTO data preserved
 */
internal fun ReservationDto.toDomain(): Reservation = Reservation(
    id = id,
    flightId = flightId,
    passengerName = passengerName,
    seat = seat,
    status = status
)

/**
 * Maps a domain Reservation model to a ReservationDto.
 *
 * This extension function converts a domain model to an API DTO,
 * preparing data for network requests such as creating or updating
 * reservations on the server.
 *
 * **Mapping Details:**
 * - **Direct Fields**: ID, flight ID, passenger name, seat, status
 * - **API Preparation**: Creates DTO ready for network requests
 * - **Request Format**: Prepares data for server communication
 * - **Simple Mapping**: Direct field-to-field assignment
 *
 * **Field Mapping:**
 * - **Domain Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **DTO Fields**: `id`, `flightId`, `passengerName`, `seat`, `status`
 * - **Data Transformation**: Domain model → API DTO
 * - **Value Preservation**: All domain data is maintained
 *
 * **Usage Context:**
 * - **API Requests**: Preparing data for server communication
 * - **Data Submission**: Sending reservation data to backend
 * - **Network Operations**: Creating or updating remote reservations
 * - **Data Synchronization**: Ensuring server has current reservation data
 *
 * @return ReservationDto ready for API requests
 */
internal fun Reservation.toDto(): ReservationDto = ReservationDto(
    id = id,
    flightId = flightId,
    passengerName = passengerName,
    seat = seat,
    status = status
)
