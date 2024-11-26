package blackorbs.dev.travelapp.dependency

import blackorbs.dev.travelapp.data.ApiService
import blackorbs.dev.travelapp.data.RequestAdapterFactory
import blackorbs.dev.travelapp.repository.BaseTripRepository
import blackorbs.dev.travelapp.repository.TripRepository
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Singleton
    @Binds
    abstract fun provideRepository(repository: TripRepository): BaseTripRepository
}

@Module
@InstallIn(SingletonComponent::class)
object MainModule{

    @Singleton
    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl("https://cad0944d1ad035acb8af.free.beeceptor.com/") /*TODO: Change the api server base url*/
            .addCallAdapterFactory(RequestAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            ))
            .build().create(ApiService::class.java)
}