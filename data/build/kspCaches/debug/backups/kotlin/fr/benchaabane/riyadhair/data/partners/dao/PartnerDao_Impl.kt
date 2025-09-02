package fr.benchaabane.riyadhair.`data`.partners.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class PartnerDao_Impl(
  __db: RoomDatabase,
) : PartnerDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfPartnerEntity: EntityUpsertAdapter<PartnerEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfPartnerEntity = EntityUpsertAdapter<PartnerEntity>(object :
        EntityInsertAdapter<PartnerEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `partners` (`id`,`name`,`category`,`imageUrl`,`description`,`discountPercentage`,`websiteUrl`,`isActive`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PartnerEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.imageUrl)
        statement.bindText(5, entity.description)
        val _tmpDiscountPercentage: Int? = entity.discountPercentage
        if (_tmpDiscountPercentage == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpDiscountPercentage.toLong())
        }
        statement.bindText(7, entity.websiteUrl)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(8, _tmp.toLong())
      }
    }, object : EntityDeleteOrUpdateAdapter<PartnerEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `partners` SET `id` = ?,`name` = ?,`category` = ?,`imageUrl` = ?,`description` = ?,`discountPercentage` = ?,`websiteUrl` = ?,`isActive` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PartnerEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.category)
        statement.bindText(4, entity.imageUrl)
        statement.bindText(5, entity.description)
        val _tmpDiscountPercentage: Int? = entity.discountPercentage
        if (_tmpDiscountPercentage == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpDiscountPercentage.toLong())
        }
        statement.bindText(7, entity.websiteUrl)
        val _tmp: Int = if (entity.isActive) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindText(9, entity.id)
      }
    })
  }

  public override suspend fun upsertAll(partners: List<PartnerEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfPartnerEntity.upsert(_connection, partners)
  }

  public override suspend fun getPartners(): List<PartnerEntity> {
    val _sql: String = "SELECT * FROM partners WHERE isActive = 1 ORDER BY name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDiscountPercentage: Int = getColumnIndexOrThrow(_stmt,
            "discountPercentage")
        val _columnIndexOfWebsiteUrl: Int = getColumnIndexOrThrow(_stmt, "websiteUrl")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "isActive")
        val _result: MutableList<PartnerEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PartnerEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDiscountPercentage: Int?
          if (_stmt.isNull(_columnIndexOfDiscountPercentage)) {
            _tmpDiscountPercentage = null
          } else {
            _tmpDiscountPercentage = _stmt.getLong(_columnIndexOfDiscountPercentage).toInt()
          }
          val _tmpWebsiteUrl: String
          _tmpWebsiteUrl = _stmt.getText(_columnIndexOfWebsiteUrl)
          val _tmpIsActive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp != 0
          _item =
              PartnerEntity(_tmpId,_tmpName,_tmpCategory,_tmpImageUrl,_tmpDescription,_tmpDiscountPercentage,_tmpWebsiteUrl,_tmpIsActive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAll() {
    val _sql: String = "DELETE FROM partners"
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
