package fr.benchaabane.riyadhair.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.benchaabane.riyadhair.data.account.api.AccountService
import fr.benchaabane.riyadhair.data.flights.api.FlightService
import fr.benchaabane.riyadhair.data.offers.api.OffersService
import fr.benchaabane.riyadhair.data.partners.api.PartnerService
import fr.benchaabane.riyadhair.data.reservations.api.ReservationService
import fr.benchaabane.riyadhair.network.MockInterceptor
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(MockInterceptor(context))
        .build()
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.riyadhair.com/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    
    @Provides
    @Singleton
    fun provideFlightService(retrofit: Retrofit): FlightService =
        retrofit.create(FlightService::class.java)
    
    @Provides
    @Singleton
    fun provideReservationService(retrofit: Retrofit): ReservationService =
        retrofit.create(ReservationService::class.java)
    
    @Provides
    @Singleton
    fun provideAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)
    
    @Provides
    @Singleton
    fun provideOffersService(retrofit: Retrofit): OffersService =
        retrofit.create(OffersService::class.java)
    
    @Provides
    @Singleton
    fun providePartnerService(retrofit: Retrofit): PartnerService =
        retrofit.create(PartnerService::class.java)
}
