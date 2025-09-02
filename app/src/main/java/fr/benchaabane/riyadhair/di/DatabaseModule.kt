package fr.benchaabane.riyadhair.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.benchaabane.riyadhair.data.account.dao.AccountDao
import fr.benchaabane.riyadhair.data.db.AppDatabase
import fr.benchaabane.riyadhair.data.offers.dao.OfferDao
import fr.benchaabane.riyadhair.data.partners.dao.PartnerDao
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    )
        .addMigrations(AppDatabase.MIGRATION_2_3)
        .fallbackToDestructiveMigration(false) // Fallback for development - remove in production
        .build()


    @Provides
    fun provideReservationDao(database: AppDatabase): ReservationDao = database.reservationDao()

    @Provides
    fun provideAccountDao(database: AppDatabase): AccountDao = database.accountDao()

    @Provides
    fun provideOfferDao(database: AppDatabase): OfferDao = database.offerDao()

    @Provides
    fun providePartnerDao(database: AppDatabase): PartnerDao = database.partnerDao()
}
