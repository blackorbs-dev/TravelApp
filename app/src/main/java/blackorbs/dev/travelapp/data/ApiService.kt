package blackorbs.dev.travelapp.data

import blackorbs.dev.travelapp.data.entities.Location
import blackorbs.dev.travelapp.data.entities.Trip
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/trips")
    fun getAllTrips(): RequestHandler<List<Trip>>

    @GET("/api/trips/{id}")
    fun getTrip(@Path("id") id: Int): RequestHandler<Trip>

    @POST("/api/trips")
    fun createTrip(@Body trip: Trip): RequestHandler<Trip>

    @GET("https://nominatim.openstreetmap.org/search")
    fun getLocations(
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("limit") limit: Int = 20
    ): RequestHandler<List<Location>>
}