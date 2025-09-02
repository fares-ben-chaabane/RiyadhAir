package fr.benchaabane.riyadhair.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.benchaabane.riyadhair.domain.account.repositories.AccountRepository
import fr.benchaabane.riyadhair.domain.account.usecases.GetAccountUseCase
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository
import fr.benchaabane.riyadhair.domain.flights.usecases.GetFlightDetailsUseCase
import fr.benchaabane.riyadhair.domain.flights.usecases.SearchFlightsUseCase
import fr.benchaabane.riyadhair.domain.offers.repositories.OffersRepository
import fr.benchaabane.riyadhair.domain.offers.usecases.GetBestOffersUseCase
import fr.benchaabane.riyadhair.domain.partners.repositories.PartnerRepository
import fr.benchaabane.riyadhair.domain.partners.usecases.GetPartnersUseCase
import fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository
import fr.benchaabane.riyadhair.domain.reservations.usecases.ObserveReservationsUseCase
import fr.benchaabane.riyadhair.domain.reservations.usecases.SaveReservationUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSearchFlightsUseCase(
        repository: FlightRepository
    ): SearchFlightsUseCase = SearchFlightsUseCase(repository)

    @Provides
    fun provideObserveReservationsUseCase(
        repository: ReservationRepository
    ): ObserveReservationsUseCase = ObserveReservationsUseCase(repository)

    @Provides
    fun provideSaveReservationUseCase(
        repository: ReservationRepository
    ): SaveReservationUseCase = SaveReservationUseCase(repository)

    @Provides
    fun provideGetAccountUseCase(
        repository: AccountRepository
    ): GetAccountUseCase = GetAccountUseCase(repository)

    @Provides
    fun provideGetBestOffersUseCase(
        repository: OffersRepository
    ): GetBestOffersUseCase = GetBestOffersUseCase(repository)

    @Provides
    fun provideGetPartnersUseCase(
        repository: PartnerRepository
    ): GetPartnersUseCase = GetPartnersUseCase(repository)

    @Provides
    fun provideGetFlightDetailsUseCase(
        repository: FlightRepository
    ): GetFlightDetailsUseCase = GetFlightDetailsUseCase(repository)
}
