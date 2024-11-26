package blackorbs.dev.travelapp.repository

import blackorbs.dev.travelapp.data.ApiService
import blackorbs.dev.travelapp.data.entities.Location
import blackorbs.dev.travelapp.data.entities.Response
import blackorbs.dev.travelapp.data.entities.Trip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val apiService: ApiService
) : BaseTripRepository {

    override fun getTrip(id: Int): Flow<Response<Trip>> =
        apiService.getTrip(id).execute()

    override fun getAllTrips(): Flow<Response<List<Trip>>> =
        apiService.getAllTrips().execute()

    override fun createTrip(trip: Trip): Flow<Response<Trip>> =
        apiService.createTrip(trip).execute()

    override fun getLocations(query: String): Flow<Response<List<Location>>> =
        apiService.getLocations(query).execute()
}

interface BaseTripRepository {
    fun getTrip(id: Int): Flow<Response<Trip>>

    fun getAllTrips(): Flow<Response<List<Trip>>>

    fun createTrip(trip: Trip): Flow<Response<Trip>>

    fun getLocations(query: String): Flow<Response<List<Location>>>
}