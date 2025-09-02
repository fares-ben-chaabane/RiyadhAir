package fr.benchaabane.riyadhair.`data`.offers.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class OfferDao_Impl(
  __db: RoomDatabase,
) : OfferDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfOfferEntity: EntityUpsertAdapter<OfferEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfOfferEntity = EntityUpsertAdapter<OfferEntity>(object :
        EntityInsertAdapter<OfferEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `offers` (`id`,`destinationId`,`destinationName`,`destinationCityName`,`destinationCountryName`,`destinationAirportCode`,`destinationImageUrl`,`destinationDescription`,`destinationAverageTemperature`,`destinationTimeZone`,`originalPrice`,`discountedPrice`,`discountPercentage`,`validUntil`,`description`,`termsAndConditions`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: OfferEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.destinationId)
        statement.bindText(3, entity.destinationName)
        statement.bindText(4, entity.destinationCityName)
        statement.bindText(5, entity.destinationCountryName)
        statement.bindText(6, entity.destinationAirportCode)
        statement.bindText(7, entity.destinationImageUrl)
        statement.bindText(8, entity.destinationDescription)
        val _tmpDestinationAverageTemperature: String? = entity.destinationAverageTemperature
        if (_tmpDestinationAverageTemperature == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDestinationAverageTemperature)
        }
        val _tmpDestinationTimeZone: String? = entity.destinationTimeZone
        if (_tmpDestinationTimeZone == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpDestinationTimeZone)
        }
        statement.bindDouble(11, entity.originalPrice)
        statement.bindDouble(12, entity.discountedPrice)
        statement.bindLong(13, entity.discountPercentage.toLong())
        statement.bindText(14, entity.validUntil)
        statement.bindText(15, entity.description)
        statement.bindText(16, entity.termsAndConditions)
      }
    }, object : EntityDeleteOrUpdateAdapter<OfferEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `offers` SET `id` = ?,`destinationId` = ?,`destinationName` = ?,`destinationCityName` = ?,`destinationCountryName` = ?,`destinationAirportCode` = ?,`destinationImageUrl` = ?,`destinationDescription` = ?,`destinationAverageTemperature` = ?,`destinationTimeZone` = ?,`originalPrice` = ?,`discountedPrice` = ?,`discountPercentage` = ?,`validUntil` = ?,`description` = ?,`termsAndConditions` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: OfferEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.destinationId)
        statement.bindText(3, entity.destinationName)
        statement.bindText(4, entity.destinationCityName)
        statement.bindText(5, entity.destinationCountryName)
        statement.bindText(6, entity.destinationAirportCode)
        statement.bindText(7, entity.destinationImageUrl)
        statement.bindText(8, entity.destinationDescription)
        val _tmpDestinationAverageTemperature: String? = entity.destinationAverageTemperature
        if (_tmpDestinationAverageTemperature == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpDestinationAverageTemperature)
        }
        val _tmpDestinationTimeZone: String? = entity.destinationTimeZone
        if (_tmpDestinationTimeZone == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpDestinationTimeZone)
        }
        statement.bindDouble(11, entity.originalPrice)
        statement.bindDouble(12, entity.discountedPrice)
        statement.bindLong(13, entity.discountPercentage.toLong())
        statement.bindText(14, entity.validUntil)
        statement.bindText(15, entity.description)
        statement.bindText(16, entity.termsAndConditions)
        statement.bindText(17, entity.id)
      }
    })
  }

  public override suspend fun upsertAll(offers: List<OfferEntity>): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfOfferEntity.upsert(_connection, offers)
  }

  public override suspend fun getOffers(): List<OfferEntity> {
    val _sql: String = "SELECT * FROM offers ORDER BY discountPercentage DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDestinationId: Int = getColumnIndexOrThrow(_stmt, "destinationId")
        val _columnIndexOfDestinationName: Int = getColumnIndexOrThrow(_stmt, "destinationName")
        val _columnIndexOfDestinationCityName: Int = getColumnIndexOrThrow(_stmt,
            "destinationCityName")
        val _columnIndexOfDestinationCountryName: Int = getColumnIndexOrThrow(_stmt,
            "destinationCountryName")
        val _columnIndexOfDestinationAirportCode: Int = getColumnIndexOrThrow(_stmt,
            "destinationAirportCode")
        val _columnIndexOfDestinationImageUrl: Int = getColumnIndexOrThrow(_stmt,
            "destinationImageUrl")
        val _columnIndexOfDestinationDescription: Int = getColumnIndexOrThrow(_stmt,
            "destinationDescription")
        val _columnIndexOfDestinationAverageTemperature: Int = getColumnIndexOrThrow(_stmt,
            "destinationAverageTemperature")
        val _columnIndexOfDestinationTimeZone: Int = getColumnIndexOrThrow(_stmt,
            "destinationTimeZone")
        val _columnIndexOfOriginalPrice: Int = getColumnIndexOrThrow(_stmt, "originalPrice")
        val _columnIndexOfDiscountedPrice: Int = getColumnIndexOrThrow(_stmt, "discountedPrice")
        val _columnIndexOfDiscountPercentage: Int = getColumnIndexOrThrow(_stmt,
            "discountPercentage")
        val _columnIndexOfValidUntil: Int = getColumnIndexOrThrow(_stmt, "validUntil")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfTermsAndConditions: Int = getColumnIndexOrThrow(_stmt,
            "termsAndConditions")
        val _result: MutableList<OfferEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: OfferEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpDestinationId: String
          _tmpDestinationId = _stmt.getText(_columnIndexOfDestinationId)
          val _tmpDestinationName: String
          _tmpDestinationName = _stmt.getText(_columnIndexOfDestinationName)
          val _tmpDestinationCityName: String
          _tmpDestinationCityName = _stmt.getText(_columnIndexOfDestinationCityName)
          val _tmpDestinationCountryName: String
          _tmpDestinationCountryName = _stmt.getText(_columnIndexOfDestinationCountryName)
          val _tmpDestinationAirportCode: String
          _tmpDestinationAirportCode = _stmt.getText(_columnIndexOfDestinationAirportCode)
          val _tmpDestinationImageUrl: String
          _tmpDestinationImageUrl = _stmt.getText(_columnIndexOfDestinationImageUrl)
          val _tmpDestinationDescription: String
          _tmpDestinationDescription = _stmt.getText(_columnIndexOfDestinationDescription)
          val _tmpDestinationAverageTemperature: String?
          if (_stmt.isNull(_columnIndexOfDestinationAverageTemperature)) {
            _tmpDestinationAverageTemperature = null
          } else {
            _tmpDestinationAverageTemperature =
                _stmt.getText(_columnIndexOfDestinationAverageTemperature)
          }
          val _tmpDestinationTimeZone: String?
          if (_stmt.isNull(_columnIndexOfDestinationTimeZone)) {
            _tmpDestinationTimeZone = null
          } else {
            _tmpDestinationTimeZone = _stmt.getText(_columnIndexOfDestinationTimeZone)
          }
          val _tmpOriginalPrice: Double
          _tmpOriginalPrice = _stmt.getDouble(_columnIndexOfOriginalPrice)
          val _tmpDiscountedPrice: Double
          _tmpDiscountedPrice = _stmt.getDouble(_columnIndexOfDiscountedPrice)
          val _tmpDiscountPercentage: Int
          _tmpDiscountPercentage = _stmt.getLong(_columnIndexOfDiscountPercentage).toInt()
          val _tmpValidUntil: String
          _tmpValidUntil = _stmt.getText(_columnIndexOfValidUntil)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpTermsAndConditions: String
          _tmpTermsAndConditions = _stmt.getText(_columnIndexOfTermsAndConditions)
          _item =
              OfferEntity(_tmpId,_tmpDestinationId,_tmpDestinationName,_tmpDestinationCityName,_tmpDestinationCountryName,_tmpDestinationAirportCode,_tmpDestinationImageUrl,_tmpDestinationDescription,_tmpDestinationAverageTemperature,_tmpDestinationTimeZone,_tmpOriginalPrice,_tmpDiscountedPrice,_tmpDiscountPercentage,_tmpValidUntil,_tmpDescription,_tmpTermsAndConditions)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAll() {
    val _sql: String = "DELETE FROM offers"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
