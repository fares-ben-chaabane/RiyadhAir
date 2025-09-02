package fr.benchaabane.riyadhair.`data`.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import fr.benchaabane.riyadhair.`data`.account.dao.AccountDao
import fr.benchaabane.riyadhair.`data`.account.dao.AccountDao_Impl
import fr.benchaabane.riyadhair.`data`.offers.dao.OfferDao
import fr.benchaabane.riyadhair.`data`.offers.dao.OfferDao_Impl
import fr.benchaabane.riyadhair.`data`.partners.dao.PartnerDao
import fr.benchaabane.riyadhair.`data`.partners.dao.PartnerDao_Impl
import fr.benchaabane.riyadhair.`data`.reservations.dao.ReservationDao
import fr.benchaabane.riyadhair.`data`.reservations.dao.ReservationDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _reservationDao: Lazy<ReservationDao> = lazy {
    ReservationDao_Impl(this)
  }

  private val _accountDao: Lazy<AccountDao> = lazy {
    AccountDao_Impl(this)
  }

  private val _offerDao: Lazy<OfferDao> = lazy {
    OfferDao_Impl(this)
  }

  private val _partnerDao: Lazy<PartnerDao> = lazy {
    PartnerDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(3,
        "9a2153ffa84495735f37b7a281d95129", "fc861c4f51a72f49888a97eae0f2cf70") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `reservations` (`id` TEXT NOT NULL, `flightId` TEXT NOT NULL, `passengerName` TEXT NOT NULL, `seat` TEXT NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `account` (`id` TEXT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `email` TEXT NOT NULL, `phoneNumber` TEXT, `loyaltyLevelName` TEXT NOT NULL, `loyaltyTier` TEXT NOT NULL, `loyaltyColor` TEXT NOT NULL, `milesPoints` INTEGER NOT NULL, `xpPoints` INTEGER NOT NULL, `profileImageUrl` TEXT, `preferredLanguage` TEXT NOT NULL, `currentLocation` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `offers` (`id` TEXT NOT NULL, `destinationId` TEXT NOT NULL, `destinationName` TEXT NOT NULL, `destinationCityName` TEXT NOT NULL, `destinationCountryName` TEXT NOT NULL, `destinationAirportCode` TEXT NOT NULL, `destinationImageUrl` TEXT NOT NULL, `destinationDescription` TEXT NOT NULL, `destinationAverageTemperature` TEXT, `destinationTimeZone` TEXT, `originalPrice` REAL NOT NULL, `discountedPrice` REAL NOT NULL, `discountPercentage` INTEGER NOT NULL, `validUntil` TEXT NOT NULL, `description` TEXT NOT NULL, `termsAndConditions` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `partners` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `description` TEXT NOT NULL, `discountPercentage` INTEGER, `websiteUrl` TEXT NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9a2153ffa84495735f37b7a281d95129')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `reservations`")
        connection.execSQL("DROP TABLE IF EXISTS `account`")
        connection.execSQL("DROP TABLE IF EXISTS `offers`")
        connection.execSQL("DROP TABLE IF EXISTS `partners`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsReservations: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsReservations.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsReservations.put("flightId", TableInfo.Column("flightId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsReservations.put("passengerName", TableInfo.Column("passengerName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReservations.put("seat", TableInfo.Column("seat", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsReservations.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysReservations: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesReservations: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoReservations: TableInfo = TableInfo("reservations", _columnsReservations,
            _foreignKeysReservations, _indicesReservations)
        val _existingReservations: TableInfo = read(connection, "reservations")
        if (!_infoReservations.equals(_existingReservations)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |reservations(fr.benchaabane.riyadhair.data.reservations.dao.ReservationEntity).
              | Expected:
              |""".trimMargin() + _infoReservations + """
              |
              | Found:
              |""".trimMargin() + _existingReservations)
        }
        val _columnsAccount: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAccount.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("firstName", TableInfo.Column("firstName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("lastName", TableInfo.Column("lastName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("phoneNumber", TableInfo.Column("phoneNumber", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("loyaltyLevelName", TableInfo.Column("loyaltyLevelName", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("loyaltyTier", TableInfo.Column("loyaltyTier", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("loyaltyColor", TableInfo.Column("loyaltyColor", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("milesPoints", TableInfo.Column("milesPoints", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("xpPoints", TableInfo.Column("xpPoints", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("profileImageUrl", TableInfo.Column("profileImageUrl", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("preferredLanguage", TableInfo.Column("preferredLanguage", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccount.put("currentLocation", TableInfo.Column("currentLocation", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAccount: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAccount: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAccount: TableInfo = TableInfo("account", _columnsAccount, _foreignKeysAccount,
            _indicesAccount)
        val _existingAccount: TableInfo = read(connection, "account")
        if (!_infoAccount.equals(_existingAccount)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |account(fr.benchaabane.riyadhair.data.account.dao.AccountEntity).
              | Expected:
              |""".trimMargin() + _infoAccount + """
              |
              | Found:
              |""".trimMargin() + _existingAccount)
        }
        val _columnsOffers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsOffers.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationId", TableInfo.Column("destinationId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationName", TableInfo.Column("destinationName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationCityName", TableInfo.Column("destinationCityName", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationCountryName", TableInfo.Column("destinationCountryName",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationAirportCode", TableInfo.Column("destinationAirportCode",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationImageUrl", TableInfo.Column("destinationImageUrl", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationDescription", TableInfo.Column("destinationDescription",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationAverageTemperature",
            TableInfo.Column("destinationAverageTemperature", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("destinationTimeZone", TableInfo.Column("destinationTimeZone", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("originalPrice", TableInfo.Column("originalPrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("discountedPrice", TableInfo.Column("discountedPrice", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("discountPercentage", TableInfo.Column("discountPercentage", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("validUntil", TableInfo.Column("validUntil", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOffers.put("termsAndConditions", TableInfo.Column("termsAndConditions", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysOffers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesOffers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoOffers: TableInfo = TableInfo("offers", _columnsOffers, _foreignKeysOffers,
            _indicesOffers)
        val _existingOffers: TableInfo = read(connection, "offers")
        if (!_infoOffers.equals(_existingOffers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |offers(fr.benchaabane.riyadhair.data.offers.dao.OfferEntity).
              | Expected:
              |""".trimMargin() + _infoOffers + """
              |
              | Found:
              |""".trimMargin() + _existingOffers)
        }
        val _columnsPartners: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPartners.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("discountPercentage", TableInfo.Column("discountPercentage", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("websiteUrl", TableInfo.Column("websiteUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPartners.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPartners: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPartners: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPartners: TableInfo = TableInfo("partners", _columnsPartners, _foreignKeysPartners,
            _indicesPartners)
        val _existingPartners: TableInfo = read(connection, "partners")
        if (!_infoPartners.equals(_existingPartners)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |partners(fr.benchaabane.riyadhair.data.partners.dao.PartnerEntity).
              | Expected:
              |""".trimMargin() + _infoPartners + """
              |
              | Found:
              |""".trimMargin() + _existingPartners)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "reservations", "account",
        "offers", "partners")
  }

  public override fun clearAllTables() {
    super.performClear(false, "reservations", "account", "offers", "partners")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ReservationDao::class, ReservationDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AccountDao::class, AccountDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(OfferDao::class, OfferDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PartnerDao::class, PartnerDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun reservationDao(): ReservationDao = _reservationDao.value

  public override fun accountDao(): AccountDao = _accountDao.value

  public override fun offerDao(): OfferDao = _offerDao.value

  public override fun partnerDao(): PartnerDao = _partnerDao.value
}
