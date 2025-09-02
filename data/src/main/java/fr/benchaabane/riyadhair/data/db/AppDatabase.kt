package fr.benchaabane.riyadhair.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.benchaabane.riyadhair.data.account.dao.AccountDao
import fr.benchaabane.riyadhair.data.account.dao.AccountEntity
import fr.benchaabane.riyadhair.data.offers.dao.OfferDao
import fr.benchaabane.riyadhair.data.offers.dao.OfferEntity
import fr.benchaabane.riyadhair.data.partners.dao.PartnerDao
import fr.benchaabane.riyadhair.data.partners.dao.PartnerEntity
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationDao
import fr.benchaabane.riyadhair.data.reservations.dao.ReservationEntity

/**
 * Main database class for the RiyadhAir application.
 *
 * This Room database serves as the local data storage for the application,
 * providing offline access to user data, reservations, offers, and partner information.
 * It follows the offline-first approach, ensuring data availability even when
 * network connectivity is limited.
 *
 * **Database Features:**
 * - **Version**: 3 (supports migration from version 2)
 * - **Entities**: Reservations, Accounts, Offers, Partners
 * - **Migration Support**: Automatic schema updates with data preservation
 * - **Offline Capability**: Local storage for all core business data
 *
 * **Architecture Role:**
 * This database is part of the Data layer and provides the persistence
 * mechanism for the Repository pattern implementation.
 *
 * @see RoomDatabase
 * @see ReservationEntity
 * @see AccountEntity
 * @see OfferEntity
 * @see PartnerEntity
 */
@Database(
    entities = [ReservationEntity::class, AccountEntity::class, OfferEntity::class, PartnerEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides access to reservation data operations.
     *
     * @return ReservationDao instance for managing reservation entities
     */
    abstract fun reservationDao(): ReservationDao
    
    /**
     * Provides access to account data operations.
     *
     * @return AccountDao instance for managing account entities
     */
    abstract fun accountDao(): AccountDao
    
    /**
     * Provides access to offer data operations.
     *
     * @return OfferDao instance for managing offer entities
     */
    abstract fun offerDao(): OfferDao
    
    /**
     * Provides access to partner data operations.
     *
     * @return PartnerDao instance for managing partner entities
     */
    abstract fun partnerDao(): PartnerDao

    companion object {
        /**
         * Database name used for Room database creation.
         */
        const val DATABASE_NAME = "riyadhair_database"

        /**
         * Migration from database version 2 to version 3.
         *
         * This migration adds the partners table to support partner management
         * functionality. It creates the table with all necessary columns for
         * storing partner information including discounts and active status.
         *
         * **Migration Details:**
         * - **From Version**: 2
         * - **To Version**: 3
         * - **New Table**: `partners`
         * - **Data Preservation**: All existing data is preserved
         *
         * **Table Schema:**
         * - `id`: Primary key (TEXT)
         * - `name`: Partner name (TEXT)
         * - `category`: Partner category (TEXT)
         * - `imageUrl`: Partner logo/image URL (TEXT)
         * - `description`: Partner description (TEXT)
         * - `discountPercentage`: Discount offered (INTEGER)
         * - `websiteUrl`: Partner website (TEXT)
         * - `isActive`: Active status flag (INTEGER)
         *
         * @see Migration
         */
        val MIGRATION_2_3 = object : Migration(2, 3) {
            /**
             * Executes the migration from version 2 to version 3.
             *
             * This method creates the partners table with the appropriate schema
             * to support partner management functionality. The migration is
             * designed to be safe and non-destructive, preserving all existing data.
             *
             * @param db The database to migrate
             */
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create the partners table
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `partners` (
                        `id` TEXT NOT NULL,
                        `name` TEXT NOT NULL,
                        `category` TEXT NOT NULL,
                        `imageUrl` TEXT NOT NULL,
                        `description` TEXT NOT NULL,
                        `discountPercentage` INTEGER,
                        `websiteUrl` TEXT NOT NULL,
                        `isActive` INTEGER NOT NULL,
                        PRIMARY KEY(`id`)
                    )
                """.trimIndent()
                )
            }
        }
    }
}