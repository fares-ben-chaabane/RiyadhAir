package fr.benchaabane.riyadhair.data.flights.paging

/*import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import fr.benchaabane.riyadhair.data.db.AppDatabase
import fr.benchaabane.riyadhair.data.flights.api.FlightService
import fr.benchaabane.riyadhair.data.flights.dao.FlightEntity
import fr.benchaabane.riyadhair.data.flights.mappers.toEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FlightRemoteMediator @Inject constructor(
    private val flightService: FlightService,
    private val database: AppDatabase,
    private val origin: String,
    private val destination: String
) : RemoteMediator<Int, FlightEntity>() {

    private val flightDao = database.flightDao()

    override suspend fun initialize(): InitializeAction {
        // Always refresh data on app start for fresh flight information
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FlightEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        // Calculate page based on current items count
                        (state.pages.sumOf { it.data.size } / state.config.pageSize) + 1
                    }
                }
            }

            val response = flightService.searchFlights(
                origin = origin,
                destination = destination,
                page = page,
                limit = state.config.pageSize
            )

            val flights = response.flights.map { it.toEntity() }
            val endOfPaginationReached = flights.isEmpty()

            database.withTransaction {
                // Clear all flights for this route if refreshing
                if (loadType == LoadType.REFRESH) {
                    flightDao.clearFlightsByRoute(origin, destination)
                }
                
                // Insert new flights
                flightDao.upsertAll(flights)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}
*/