package fr.benchaabane.riyadhair.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.benchaabane.riyadhair.data.account.repositories.AccountRepositoryImpl
import fr.benchaabane.riyadhair.data.flights.repositories.FlightRepositoryImpl
import fr.benchaabane.riyadhair.data.offers.repositories.OffersRepositoryImpl
import fr.benchaabane.riyadhair.data.partners.repositories.PartnerRepositoryImpl
import fr.benchaabane.riyadhair.data.reservations.repositories.ReservationRepositoryImpl
import fr.benchaabane.riyadhair.domain.account.repositories.AccountRepository
import fr.benchaabane.riyadhair.domain.flights.repositories.FlightRepository
import fr.benchaabane.riyadhair.domain.offers.repositories.OffersRepository
import fr.benchaabane.riyadhair.domain.partners.repositories.PartnerRepository
import fr.benchaabane.riyadhair.domain.reservations.repositories.ReservationRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    abstract fun bindFlightRepository(
        flightRepositoryImpl: FlightRepositoryImpl
    ): FlightRepository
    
    @Binds
    abstract fun bindReservationRepository(
        reservationRepositoryImpl: ReservationRepositoryImpl
    ): ReservationRepository
    
    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ): AccountRepository
    
    @Binds
    abstract fun bindOffersRepository(
        offersRepositoryImpl: OffersRepositoryImpl
    ): OffersRepository
    
    @Binds
    abstract fun bindPartnerRepository(
        partnerRepositoryImpl: PartnerRepositoryImpl
    ): PartnerRepository
}
