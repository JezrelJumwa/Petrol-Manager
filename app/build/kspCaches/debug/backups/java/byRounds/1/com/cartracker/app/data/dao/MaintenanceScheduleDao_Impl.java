package com.cartracker.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cartracker.app.data.model.MaintenanceScheduleEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MaintenanceScheduleDao_Impl implements MaintenanceScheduleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MaintenanceScheduleEntity> __insertionAdapterOfMaintenanceScheduleEntity;

  private final EntityDeletionOrUpdateAdapter<MaintenanceScheduleEntity> __deletionAdapterOfMaintenanceScheduleEntity;

  private final EntityDeletionOrUpdateAdapter<MaintenanceScheduleEntity> __updateAdapterOfMaintenanceScheduleEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSchedulesForVehicle;

  public MaintenanceScheduleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMaintenanceScheduleEntity = new EntityInsertionAdapter<MaintenanceScheduleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `maintenance_schedules` (`id`,`vehicleId`,`maintenanceType`,`intervalMiles`,`intervalMonths`,`lastServiceDate`,`lastServiceMileage`,`nextDueDate`,`nextDueMileage`,`isActive`,`reminderEnabled`,`reminderAdvanceMiles`,`reminderAdvanceDays`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceScheduleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindString(3, entity.getMaintenanceType());
        if (entity.getIntervalMiles() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getIntervalMiles());
        }
        if (entity.getIntervalMonths() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getIntervalMonths());
        }
        if (entity.getLastServiceDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getLastServiceDate());
        }
        if (entity.getLastServiceMileage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getLastServiceMileage());
        }
        if (entity.getNextDueDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getNextDueDate());
        }
        if (entity.getNextDueMileage() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextDueMileage());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getReminderAdvanceMiles());
        statement.bindLong(13, entity.getReminderAdvanceDays());
        statement.bindLong(14, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfMaintenanceScheduleEntity = new EntityDeletionOrUpdateAdapter<MaintenanceScheduleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `maintenance_schedules` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceScheduleEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMaintenanceScheduleEntity = new EntityDeletionOrUpdateAdapter<MaintenanceScheduleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `maintenance_schedules` SET `id` = ?,`vehicleId` = ?,`maintenanceType` = ?,`intervalMiles` = ?,`intervalMonths` = ?,`lastServiceDate` = ?,`lastServiceMileage` = ?,`nextDueDate` = ?,`nextDueMileage` = ?,`isActive` = ?,`reminderEnabled` = ?,`reminderAdvanceMiles` = ?,`reminderAdvanceDays` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceScheduleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindString(3, entity.getMaintenanceType());
        if (entity.getIntervalMiles() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getIntervalMiles());
        }
        if (entity.getIntervalMonths() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getIntervalMonths());
        }
        if (entity.getLastServiceDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getLastServiceDate());
        }
        if (entity.getLastServiceMileage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getLastServiceMileage());
        }
        if (entity.getNextDueDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getNextDueDate());
        }
        if (entity.getNextDueMileage() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextDueMileage());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp);
        final int _tmp_1 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getReminderAdvanceMiles());
        statement.bindLong(13, entity.getReminderAdvanceDays());
        statement.bindLong(14, entity.getCreatedAt());
        statement.bindLong(15, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllSchedulesForVehicle = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM maintenance_schedules WHERE vehicleId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSchedule(final MaintenanceScheduleEntity schedule,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMaintenanceScheduleEntity.insertAndReturnId(schedule);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSchedule(final MaintenanceScheduleEntity schedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMaintenanceScheduleEntity.handle(schedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSchedule(final MaintenanceScheduleEntity schedule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMaintenanceScheduleEntity.handle(schedule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllSchedulesForVehicle(final long vehicleId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSchedulesForVehicle.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, vehicleId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllSchedulesForVehicle.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MaintenanceScheduleEntity>> getActiveSchedulesByVehicle(final long vehicleId) {
    final String _sql = "SELECT * FROM maintenance_schedules WHERE vehicleId = ? AND isActive = 1 ORDER BY maintenanceType";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_schedules"}, new Callable<List<MaintenanceScheduleEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMaintenanceType = CursorUtil.getColumnIndexOrThrow(_cursor, "maintenanceType");
          final int _cursorIndexOfIntervalMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMiles");
          final int _cursorIndexOfIntervalMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMonths");
          final int _cursorIndexOfLastServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceDate");
          final int _cursorIndexOfLastServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceMileage");
          final int _cursorIndexOfNextDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueDate");
          final int _cursorIndexOfNextDueMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueMileage");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderAdvanceMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceMiles");
          final int _cursorIndexOfReminderAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceDays");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MaintenanceScheduleEntity> _result = new ArrayList<MaintenanceScheduleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpMaintenanceType;
            _tmpMaintenanceType = _cursor.getString(_cursorIndexOfMaintenanceType);
            final Integer _tmpIntervalMiles;
            if (_cursor.isNull(_cursorIndexOfIntervalMiles)) {
              _tmpIntervalMiles = null;
            } else {
              _tmpIntervalMiles = _cursor.getInt(_cursorIndexOfIntervalMiles);
            }
            final Integer _tmpIntervalMonths;
            if (_cursor.isNull(_cursorIndexOfIntervalMonths)) {
              _tmpIntervalMonths = null;
            } else {
              _tmpIntervalMonths = _cursor.getInt(_cursorIndexOfIntervalMonths);
            }
            final Long _tmpLastServiceDate;
            if (_cursor.isNull(_cursorIndexOfLastServiceDate)) {
              _tmpLastServiceDate = null;
            } else {
              _tmpLastServiceDate = _cursor.getLong(_cursorIndexOfLastServiceDate);
            }
            final Integer _tmpLastServiceMileage;
            if (_cursor.isNull(_cursorIndexOfLastServiceMileage)) {
              _tmpLastServiceMileage = null;
            } else {
              _tmpLastServiceMileage = _cursor.getInt(_cursorIndexOfLastServiceMileage);
            }
            final Long _tmpNextDueDate;
            if (_cursor.isNull(_cursorIndexOfNextDueDate)) {
              _tmpNextDueDate = null;
            } else {
              _tmpNextDueDate = _cursor.getLong(_cursorIndexOfNextDueDate);
            }
            final Integer _tmpNextDueMileage;
            if (_cursor.isNull(_cursorIndexOfNextDueMileage)) {
              _tmpNextDueMileage = null;
            } else {
              _tmpNextDueMileage = _cursor.getInt(_cursorIndexOfNextDueMileage);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpReminderEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_1 != 0;
            final int _tmpReminderAdvanceMiles;
            _tmpReminderAdvanceMiles = _cursor.getInt(_cursorIndexOfReminderAdvanceMiles);
            final int _tmpReminderAdvanceDays;
            _tmpReminderAdvanceDays = _cursor.getInt(_cursorIndexOfReminderAdvanceDays);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MaintenanceScheduleEntity(_tmpId,_tmpVehicleId,_tmpMaintenanceType,_tmpIntervalMiles,_tmpIntervalMonths,_tmpLastServiceDate,_tmpLastServiceMileage,_tmpNextDueDate,_tmpNextDueMileage,_tmpIsActive,_tmpReminderEnabled,_tmpReminderAdvanceMiles,_tmpReminderAdvanceDays,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<MaintenanceScheduleEntity>> getAllSchedulesByVehicle(final long vehicleId) {
    final String _sql = "SELECT * FROM maintenance_schedules WHERE vehicleId = ? ORDER BY maintenanceType";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_schedules"}, new Callable<List<MaintenanceScheduleEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMaintenanceType = CursorUtil.getColumnIndexOrThrow(_cursor, "maintenanceType");
          final int _cursorIndexOfIntervalMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMiles");
          final int _cursorIndexOfIntervalMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMonths");
          final int _cursorIndexOfLastServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceDate");
          final int _cursorIndexOfLastServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceMileage");
          final int _cursorIndexOfNextDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueDate");
          final int _cursorIndexOfNextDueMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueMileage");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderAdvanceMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceMiles");
          final int _cursorIndexOfReminderAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceDays");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MaintenanceScheduleEntity> _result = new ArrayList<MaintenanceScheduleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpMaintenanceType;
            _tmpMaintenanceType = _cursor.getString(_cursorIndexOfMaintenanceType);
            final Integer _tmpIntervalMiles;
            if (_cursor.isNull(_cursorIndexOfIntervalMiles)) {
              _tmpIntervalMiles = null;
            } else {
              _tmpIntervalMiles = _cursor.getInt(_cursorIndexOfIntervalMiles);
            }
            final Integer _tmpIntervalMonths;
            if (_cursor.isNull(_cursorIndexOfIntervalMonths)) {
              _tmpIntervalMonths = null;
            } else {
              _tmpIntervalMonths = _cursor.getInt(_cursorIndexOfIntervalMonths);
            }
            final Long _tmpLastServiceDate;
            if (_cursor.isNull(_cursorIndexOfLastServiceDate)) {
              _tmpLastServiceDate = null;
            } else {
              _tmpLastServiceDate = _cursor.getLong(_cursorIndexOfLastServiceDate);
            }
            final Integer _tmpLastServiceMileage;
            if (_cursor.isNull(_cursorIndexOfLastServiceMileage)) {
              _tmpLastServiceMileage = null;
            } else {
              _tmpLastServiceMileage = _cursor.getInt(_cursorIndexOfLastServiceMileage);
            }
            final Long _tmpNextDueDate;
            if (_cursor.isNull(_cursorIndexOfNextDueDate)) {
              _tmpNextDueDate = null;
            } else {
              _tmpNextDueDate = _cursor.getLong(_cursorIndexOfNextDueDate);
            }
            final Integer _tmpNextDueMileage;
            if (_cursor.isNull(_cursorIndexOfNextDueMileage)) {
              _tmpNextDueMileage = null;
            } else {
              _tmpNextDueMileage = _cursor.getInt(_cursorIndexOfNextDueMileage);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpReminderEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_1 != 0;
            final int _tmpReminderAdvanceMiles;
            _tmpReminderAdvanceMiles = _cursor.getInt(_cursorIndexOfReminderAdvanceMiles);
            final int _tmpReminderAdvanceDays;
            _tmpReminderAdvanceDays = _cursor.getInt(_cursorIndexOfReminderAdvanceDays);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MaintenanceScheduleEntity(_tmpId,_tmpVehicleId,_tmpMaintenanceType,_tmpIntervalMiles,_tmpIntervalMonths,_tmpLastServiceDate,_tmpLastServiceMileage,_tmpNextDueDate,_tmpNextDueMileage,_tmpIsActive,_tmpReminderEnabled,_tmpReminderAdvanceMiles,_tmpReminderAdvanceDays,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<MaintenanceScheduleEntity> getScheduleById(final long scheduleId) {
    final String _sql = "SELECT * FROM maintenance_schedules WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, scheduleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_schedules"}, new Callable<MaintenanceScheduleEntity>() {
      @Override
      @Nullable
      public MaintenanceScheduleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMaintenanceType = CursorUtil.getColumnIndexOrThrow(_cursor, "maintenanceType");
          final int _cursorIndexOfIntervalMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMiles");
          final int _cursorIndexOfIntervalMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMonths");
          final int _cursorIndexOfLastServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceDate");
          final int _cursorIndexOfLastServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceMileage");
          final int _cursorIndexOfNextDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueDate");
          final int _cursorIndexOfNextDueMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueMileage");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderAdvanceMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceMiles");
          final int _cursorIndexOfReminderAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceDays");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final MaintenanceScheduleEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpMaintenanceType;
            _tmpMaintenanceType = _cursor.getString(_cursorIndexOfMaintenanceType);
            final Integer _tmpIntervalMiles;
            if (_cursor.isNull(_cursorIndexOfIntervalMiles)) {
              _tmpIntervalMiles = null;
            } else {
              _tmpIntervalMiles = _cursor.getInt(_cursorIndexOfIntervalMiles);
            }
            final Integer _tmpIntervalMonths;
            if (_cursor.isNull(_cursorIndexOfIntervalMonths)) {
              _tmpIntervalMonths = null;
            } else {
              _tmpIntervalMonths = _cursor.getInt(_cursorIndexOfIntervalMonths);
            }
            final Long _tmpLastServiceDate;
            if (_cursor.isNull(_cursorIndexOfLastServiceDate)) {
              _tmpLastServiceDate = null;
            } else {
              _tmpLastServiceDate = _cursor.getLong(_cursorIndexOfLastServiceDate);
            }
            final Integer _tmpLastServiceMileage;
            if (_cursor.isNull(_cursorIndexOfLastServiceMileage)) {
              _tmpLastServiceMileage = null;
            } else {
              _tmpLastServiceMileage = _cursor.getInt(_cursorIndexOfLastServiceMileage);
            }
            final Long _tmpNextDueDate;
            if (_cursor.isNull(_cursorIndexOfNextDueDate)) {
              _tmpNextDueDate = null;
            } else {
              _tmpNextDueDate = _cursor.getLong(_cursorIndexOfNextDueDate);
            }
            final Integer _tmpNextDueMileage;
            if (_cursor.isNull(_cursorIndexOfNextDueMileage)) {
              _tmpNextDueMileage = null;
            } else {
              _tmpNextDueMileage = _cursor.getInt(_cursorIndexOfNextDueMileage);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpReminderEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_1 != 0;
            final int _tmpReminderAdvanceMiles;
            _tmpReminderAdvanceMiles = _cursor.getInt(_cursorIndexOfReminderAdvanceMiles);
            final int _tmpReminderAdvanceDays;
            _tmpReminderAdvanceDays = _cursor.getInt(_cursorIndexOfReminderAdvanceDays);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new MaintenanceScheduleEntity(_tmpId,_tmpVehicleId,_tmpMaintenanceType,_tmpIntervalMiles,_tmpIntervalMonths,_tmpLastServiceDate,_tmpLastServiceMileage,_tmpNextDueDate,_tmpNextDueMileage,_tmpIsActive,_tmpReminderEnabled,_tmpReminderAdvanceMiles,_tmpReminderAdvanceDays,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<MaintenanceScheduleEntity>> getUpcomingMaintenance(final long vehicleId,
      final int currentMileage, final long currentDate) {
    final String _sql = "\n"
            + "        SELECT * FROM maintenance_schedules \n"
            + "        WHERE vehicleId = ? \n"
            + "        AND isActive = 1 \n"
            + "        AND reminderEnabled = 1\n"
            + "        AND (\n"
            + "            (nextDueMileage IS NOT NULL AND nextDueMileage <= ? + reminderAdvanceMiles)\n"
            + "            OR (nextDueDate IS NOT NULL AND nextDueDate <= ? + (reminderAdvanceDays * 86400000))\n"
            + "        )\n"
            + "        ORDER BY COALESCE(nextDueDate, 9999999999999), COALESCE(nextDueMileage, 999999999)\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentMileage);
    _argIndex = 3;
    _statement.bindLong(_argIndex, currentDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_schedules"}, new Callable<List<MaintenanceScheduleEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMaintenanceType = CursorUtil.getColumnIndexOrThrow(_cursor, "maintenanceType");
          final int _cursorIndexOfIntervalMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMiles");
          final int _cursorIndexOfIntervalMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMonths");
          final int _cursorIndexOfLastServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceDate");
          final int _cursorIndexOfLastServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceMileage");
          final int _cursorIndexOfNextDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueDate");
          final int _cursorIndexOfNextDueMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueMileage");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderAdvanceMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceMiles");
          final int _cursorIndexOfReminderAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceDays");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MaintenanceScheduleEntity> _result = new ArrayList<MaintenanceScheduleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpMaintenanceType;
            _tmpMaintenanceType = _cursor.getString(_cursorIndexOfMaintenanceType);
            final Integer _tmpIntervalMiles;
            if (_cursor.isNull(_cursorIndexOfIntervalMiles)) {
              _tmpIntervalMiles = null;
            } else {
              _tmpIntervalMiles = _cursor.getInt(_cursorIndexOfIntervalMiles);
            }
            final Integer _tmpIntervalMonths;
            if (_cursor.isNull(_cursorIndexOfIntervalMonths)) {
              _tmpIntervalMonths = null;
            } else {
              _tmpIntervalMonths = _cursor.getInt(_cursorIndexOfIntervalMonths);
            }
            final Long _tmpLastServiceDate;
            if (_cursor.isNull(_cursorIndexOfLastServiceDate)) {
              _tmpLastServiceDate = null;
            } else {
              _tmpLastServiceDate = _cursor.getLong(_cursorIndexOfLastServiceDate);
            }
            final Integer _tmpLastServiceMileage;
            if (_cursor.isNull(_cursorIndexOfLastServiceMileage)) {
              _tmpLastServiceMileage = null;
            } else {
              _tmpLastServiceMileage = _cursor.getInt(_cursorIndexOfLastServiceMileage);
            }
            final Long _tmpNextDueDate;
            if (_cursor.isNull(_cursorIndexOfNextDueDate)) {
              _tmpNextDueDate = null;
            } else {
              _tmpNextDueDate = _cursor.getLong(_cursorIndexOfNextDueDate);
            }
            final Integer _tmpNextDueMileage;
            if (_cursor.isNull(_cursorIndexOfNextDueMileage)) {
              _tmpNextDueMileage = null;
            } else {
              _tmpNextDueMileage = _cursor.getInt(_cursorIndexOfNextDueMileage);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpReminderEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_1 != 0;
            final int _tmpReminderAdvanceMiles;
            _tmpReminderAdvanceMiles = _cursor.getInt(_cursorIndexOfReminderAdvanceMiles);
            final int _tmpReminderAdvanceDays;
            _tmpReminderAdvanceDays = _cursor.getInt(_cursorIndexOfReminderAdvanceDays);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MaintenanceScheduleEntity(_tmpId,_tmpVehicleId,_tmpMaintenanceType,_tmpIntervalMiles,_tmpIntervalMonths,_tmpLastServiceDate,_tmpLastServiceMileage,_tmpNextDueDate,_tmpNextDueMileage,_tmpIsActive,_tmpReminderEnabled,_tmpReminderAdvanceMiles,_tmpReminderAdvanceDays,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllActiveSchedulesWithReminders(
      final Continuation<? super List<MaintenanceScheduleEntity>> $completion) {
    final String _sql = "SELECT * FROM maintenance_schedules WHERE isActive = 1 AND reminderEnabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MaintenanceScheduleEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMaintenanceType = CursorUtil.getColumnIndexOrThrow(_cursor, "maintenanceType");
          final int _cursorIndexOfIntervalMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMiles");
          final int _cursorIndexOfIntervalMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "intervalMonths");
          final int _cursorIndexOfLastServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceDate");
          final int _cursorIndexOfLastServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastServiceMileage");
          final int _cursorIndexOfNextDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueDate");
          final int _cursorIndexOfNextDueMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDueMileage");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderAdvanceMiles = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceMiles");
          final int _cursorIndexOfReminderAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderAdvanceDays");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MaintenanceScheduleEntity> _result = new ArrayList<MaintenanceScheduleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpMaintenanceType;
            _tmpMaintenanceType = _cursor.getString(_cursorIndexOfMaintenanceType);
            final Integer _tmpIntervalMiles;
            if (_cursor.isNull(_cursorIndexOfIntervalMiles)) {
              _tmpIntervalMiles = null;
            } else {
              _tmpIntervalMiles = _cursor.getInt(_cursorIndexOfIntervalMiles);
            }
            final Integer _tmpIntervalMonths;
            if (_cursor.isNull(_cursorIndexOfIntervalMonths)) {
              _tmpIntervalMonths = null;
            } else {
              _tmpIntervalMonths = _cursor.getInt(_cursorIndexOfIntervalMonths);
            }
            final Long _tmpLastServiceDate;
            if (_cursor.isNull(_cursorIndexOfLastServiceDate)) {
              _tmpLastServiceDate = null;
            } else {
              _tmpLastServiceDate = _cursor.getLong(_cursorIndexOfLastServiceDate);
            }
            final Integer _tmpLastServiceMileage;
            if (_cursor.isNull(_cursorIndexOfLastServiceMileage)) {
              _tmpLastServiceMileage = null;
            } else {
              _tmpLastServiceMileage = _cursor.getInt(_cursorIndexOfLastServiceMileage);
            }
            final Long _tmpNextDueDate;
            if (_cursor.isNull(_cursorIndexOfNextDueDate)) {
              _tmpNextDueDate = null;
            } else {
              _tmpNextDueDate = _cursor.getLong(_cursorIndexOfNextDueDate);
            }
            final Integer _tmpNextDueMileage;
            if (_cursor.isNull(_cursorIndexOfNextDueMileage)) {
              _tmpNextDueMileage = null;
            } else {
              _tmpNextDueMileage = _cursor.getInt(_cursorIndexOfNextDueMileage);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpReminderEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_1 != 0;
            final int _tmpReminderAdvanceMiles;
            _tmpReminderAdvanceMiles = _cursor.getInt(_cursorIndexOfReminderAdvanceMiles);
            final int _tmpReminderAdvanceDays;
            _tmpReminderAdvanceDays = _cursor.getInt(_cursorIndexOfReminderAdvanceDays);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MaintenanceScheduleEntity(_tmpId,_tmpVehicleId,_tmpMaintenanceType,_tmpIntervalMiles,_tmpIntervalMonths,_tmpLastServiceDate,_tmpLastServiceMileage,_tmpNextDueDate,_tmpNextDueMileage,_tmpIsActive,_tmpReminderEnabled,_tmpReminderAdvanceMiles,_tmpReminderAdvanceDays,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
