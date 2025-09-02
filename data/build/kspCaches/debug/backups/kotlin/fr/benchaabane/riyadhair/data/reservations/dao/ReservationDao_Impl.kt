package fr.benchaabane.riyadhair.`data`.reservations.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ReservationDao_Impl(
  __db: RoomDatabase,
) : ReservationDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfReservationEntity: EntityUpsertAdapter<ReservationEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfReservationEntity = EntityUpsertAdapter<ReservationEntity>(object :
        EntityInsertAdapter<ReservationEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `reservations` (`id`,`flightId`,`passengerName`,`seat`,`status`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ReservationEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.flightId)
        statement.bindText(3, entity.passengerName)
        statement.bindText(4, entity.seat)
        statement.bindText(5, entity.status)
      }
    }, object : EntityDeleteOrUpdateAdapter<ReservationEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `reservations` SET `id` = ?,`flightId` = ?,`passengerName` = ?,`seat` = ?,`status` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ReservationEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.flightId)
        statement.bindText(3, entity.passengerName)
        statement.bindText(4, entity.seat)
        statement.bindText(5, entity.status)
        statement.bindText(6, entity.id)
      }
    })
  }

  public override suspend fun upsert(reservation: ReservationEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __upsertAdapterOfReservationEntity.upsert(_connection, reservation)
  }

  public override fun observeReservations(): Flow<List<ReservationEntity>> {
    val _sql: String = "SELECT * FROM reservations"
    return createFlow(__db, false, arrayOf("reservations")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFlightId: Int = getColumnIndexOrThrow(_stmt, "flightId")
        val _columnIndexOfPassengerName: Int = getColumnIndexOrThrow(_stmt, "passengerName")
        val _columnIndexOfSeat: Int = getColumnIndexOrThrow(_stmt, "seat")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<ReservationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ReservationEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpFlightId: String
          _tmpFlightId = _stmt.getText(_columnIndexOfFlightId)
          val _tmpPassengerName: String
          _tmpPassengerName = _stmt.getText(_columnIndexOfPassengerName)
          val _tmpSeat: String
          _tmpSeat = _stmt.getText(_columnIndexOfSeat)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _item = ReservationEntity(_tmpId,_tmpFlightId,_tmpPassengerName,_tmpSeat,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getReservationById(id: String): ReservationEntity? {
    val _sql: String = "SELECT * FROM reservations WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFlightId: Int = getColumnIndexOrThrow(_stmt, "flightId")
        val _columnIndexOfPassengerName: Int = getColumnIndexOrThrow(_stmt, "passengerName")
        val _columnIndexOfSeat: Int = getColumnIndexOrThrow(_stmt, "seat")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: ReservationEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpFlightId: String
          _tmpFlightId = _stmt.getText(_columnIndexOfFlightId)
          val _tmpPassengerName: String
          _tmpPassengerName = _stmt.getText(_columnIndexOfPassengerName)
          val _tmpSeat: String
          _tmpSeat = _stmt.getText(_columnIndexOfSeat)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _result = ReservationEntity(_tmpId,_tmpFlightId,_tmpPassengerName,_tmpSeat,_tmpStatus)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: String) {
    val _sql: String = "DELETE FROM reservations WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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
