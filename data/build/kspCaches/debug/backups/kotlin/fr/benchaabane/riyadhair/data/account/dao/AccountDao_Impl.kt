package fr.benchaabane.riyadhair.`data`.account.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AccountDao_Impl(
  __db: RoomDatabase,
) : AccountDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfAccountEntity: EntityUpsertAdapter<AccountEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfAccountEntity = EntityUpsertAdapter<AccountEntity>(object :
        EntityInsertAdapter<AccountEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `account` (`id`,`firstName`,`lastName`,`email`,`phoneNumber`,`loyaltyLevelName`,`loyaltyTier`,`loyaltyColor`,`milesPoints`,`xpPoints`,`profileImageUrl`,`preferredLanguage`,`currentLocation`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AccountEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.firstName)
        statement.bindText(3, entity.lastName)
        statement.bindText(4, entity.email)
        val _tmpPhoneNumber: String? = entity.phoneNumber
        if (_tmpPhoneNumber == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpPhoneNumber)
        }
        statement.bindText(6, entity.loyaltyLevelName)
        statement.bindText(7, entity.loyaltyTier)
        statement.bindText(8, entity.loyaltyColor)
        statement.bindLong(9, entity.milesPoints.toLong())
        statement.bindLong(10, entity.xpPoints.toLong())
        val _tmpProfileImageUrl: String? = entity.profileImageUrl
        if (_tmpProfileImageUrl == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpProfileImageUrl)
        }
        statement.bindText(12, entity.preferredLanguage)
        val _tmpCurrentLocation: String? = entity.currentLocation
        if (_tmpCurrentLocation == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpCurrentLocation)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<AccountEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `account` SET `id` = ?,`firstName` = ?,`lastName` = ?,`email` = ?,`phoneNumber` = ?,`loyaltyLevelName` = ?,`loyaltyTier` = ?,`loyaltyColor` = ?,`milesPoints` = ?,`xpPoints` = ?,`profileImageUrl` = ?,`preferredLanguage` = ?,`currentLocation` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AccountEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.firstName)
        statement.bindText(3, entity.lastName)
        statement.bindText(4, entity.email)
        val _tmpPhoneNumber: String? = entity.phoneNumber
        if (_tmpPhoneNumber == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpPhoneNumber)
        }
        statement.bindText(6, entity.loyaltyLevelName)
        statement.bindText(7, entity.loyaltyTier)
        statement.bindText(8, entity.loyaltyColor)
        statement.bindLong(9, entity.milesPoints.toLong())
        statement.bindLong(10, entity.xpPoints.toLong())
        val _tmpProfileImageUrl: String? = entity.profileImageUrl
        if (_tmpProfileImageUrl == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpProfileImageUrl)
        }
        statement.bindText(12, entity.preferredLanguage)
        val _tmpCurrentLocation: String? = entity.currentLocation
        if (_tmpCurrentLocation == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpCurrentLocation)
        }
        statement.bindText(14, entity.id)
      }
    })
  }

  public override suspend fun upsertAccount(account: AccountEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfAccountEntity.upsert(_connection, account)
  }

  public override suspend fun getAccount(): AccountEntity? {
    val _sql: String = "SELECT * FROM account LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "phoneNumber")
        val _columnIndexOfLoyaltyLevelName: Int = getColumnIndexOrThrow(_stmt, "loyaltyLevelName")
        val _columnIndexOfLoyaltyTier: Int = getColumnIndexOrThrow(_stmt, "loyaltyTier")
        val _columnIndexOfLoyaltyColor: Int = getColumnIndexOrThrow(_stmt, "loyaltyColor")
        val _columnIndexOfMilesPoints: Int = getColumnIndexOrThrow(_stmt, "milesPoints")
        val _columnIndexOfXpPoints: Int = getColumnIndexOrThrow(_stmt, "xpPoints")
        val _columnIndexOfProfileImageUrl: Int = getColumnIndexOrThrow(_stmt, "profileImageUrl")
        val _columnIndexOfPreferredLanguage: Int = getColumnIndexOrThrow(_stmt, "preferredLanguage")
        val _columnIndexOfCurrentLocation: Int = getColumnIndexOrThrow(_stmt, "currentLocation")
        val _result: AccountEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfPhoneNumber)) {
            _tmpPhoneNumber = null
          } else {
            _tmpPhoneNumber = _stmt.getText(_columnIndexOfPhoneNumber)
          }
          val _tmpLoyaltyLevelName: String
          _tmpLoyaltyLevelName = _stmt.getText(_columnIndexOfLoyaltyLevelName)
          val _tmpLoyaltyTier: String
          _tmpLoyaltyTier = _stmt.getText(_columnIndexOfLoyaltyTier)
          val _tmpLoyaltyColor: String
          _tmpLoyaltyColor = _stmt.getText(_columnIndexOfLoyaltyColor)
          val _tmpMilesPoints: Int
          _tmpMilesPoints = _stmt.getLong(_columnIndexOfMilesPoints).toInt()
          val _tmpXpPoints: Int
          _tmpXpPoints = _stmt.getLong(_columnIndexOfXpPoints).toInt()
          val _tmpProfileImageUrl: String?
          if (_stmt.isNull(_columnIndexOfProfileImageUrl)) {
            _tmpProfileImageUrl = null
          } else {
            _tmpProfileImageUrl = _stmt.getText(_columnIndexOfProfileImageUrl)
          }
          val _tmpPreferredLanguage: String
          _tmpPreferredLanguage = _stmt.getText(_columnIndexOfPreferredLanguage)
          val _tmpCurrentLocation: String?
          if (_stmt.isNull(_columnIndexOfCurrentLocation)) {
            _tmpCurrentLocation = null
          } else {
            _tmpCurrentLocation = _stmt.getText(_columnIndexOfCurrentLocation)
          }
          _result =
              AccountEntity(_tmpId,_tmpFirstName,_tmpLastName,_tmpEmail,_tmpPhoneNumber,_tmpLoyaltyLevelName,_tmpLoyaltyTier,_tmpLoyaltyColor,_tmpMilesPoints,_tmpXpPoints,_tmpProfileImageUrl,_tmpPreferredLanguage,_tmpCurrentLocation)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAccount() {
    val _sql: String = "DELETE FROM account"
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
