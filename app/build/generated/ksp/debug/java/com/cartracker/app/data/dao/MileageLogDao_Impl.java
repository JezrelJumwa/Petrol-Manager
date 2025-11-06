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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cartracker.app.data.model.MileageLogEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class MileageLogDao_Impl implements MileageLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MileageLogEntity> __insertionAdapterOfMileageLogEntity;

  private final EntityDeletionOrUpdateAdapter<MileageLogEntity> __deletionAdapterOfMileageLogEntity;

  private final EntityDeletionOrUpdateAdapter<MileageLogEntity> __updateAdapterOfMileageLogEntity;

  public MileageLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMileageLogEntity = new EntityInsertionAdapter<MileageLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `mileage_logs` (`id`,`vehicleId`,`mileage`,`logDate`,`notes`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MileageLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindLong(3, entity.getMileage());
        statement.bindLong(4, entity.getLogDate());
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfMileageLogEntity = new EntityDeletionOrUpdateAdapter<MileageLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `mileage_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MileageLogEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMileageLogEntity = new EntityDeletionOrUpdateAdapter<MileageLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `mileage_logs` SET `id` = ?,`vehicleId` = ?,`mileage` = ?,`logDate` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MileageLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindLong(3, entity.getMileage());
        statement.bindLong(4, entity.getLogDate());
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNotes());
        }
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertMileageLog(final MileageLogEntity log,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMileageLogEntity.insertAndReturnId(log);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteMileageLog(final MileageLogEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMileageLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMileageLog(final MileageLogEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMileageLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MileageLogEntity>> getMileageLogsByVehicle(final long vehicleId) {
    final String _sql = "SELECT * FROM mileage_logs WHERE vehicleId = ? ORDER BY logDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mileage_logs"}, new Callable<List<MileageLogEntity>>() {
      @Override
      @NonNull
      public List<MileageLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "mileage");
          final int _cursorIndexOfLogDate = CursorUtil.getColumnIndexOrThrow(_cursor, "logDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<MileageLogEntity> _result = new ArrayList<MileageLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MileageLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final int _tmpMileage;
            _tmpMileage = _cursor.getInt(_cursorIndexOfMileage);
            final long _tmpLogDate;
            _tmpLogDate = _cursor.getLong(_cursorIndexOfLogDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new MileageLogEntity(_tmpId,_tmpVehicleId,_tmpMileage,_tmpLogDate,_tmpNotes);
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
  public Object getLatestMileageLog(final long vehicleId,
      final Continuation<? super MileageLogEntity> $completion) {
    final String _sql = "SELECT * FROM mileage_logs WHERE vehicleId = ? ORDER BY logDate DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MileageLogEntity>() {
      @Override
      @Nullable
      public MileageLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "mileage");
          final int _cursorIndexOfLogDate = CursorUtil.getColumnIndexOrThrow(_cursor, "logDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final MileageLogEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final int _tmpMileage;
            _tmpMileage = _cursor.getInt(_cursorIndexOfMileage);
            final long _tmpLogDate;
            _tmpLogDate = _cursor.getLong(_cursorIndexOfLogDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new MileageLogEntity(_tmpId,_tmpVehicleId,_tmpMileage,_tmpLogDate,_tmpNotes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MileageLogEntity>> getMileageLogsInDateRange(final long vehicleId,
      final long startDate, final long endDate) {
    final String _sql = "\n"
            + "        SELECT * FROM mileage_logs \n"
            + "        WHERE vehicleId = ? \n"
            + "        AND logDate BETWEEN ? AND ? \n"
            + "        ORDER BY logDate ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mileage_logs"}, new Callable<List<MileageLogEntity>>() {
      @Override
      @NonNull
      public List<MileageLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "mileage");
          final int _cursorIndexOfLogDate = CursorUtil.getColumnIndexOrThrow(_cursor, "logDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<MileageLogEntity> _result = new ArrayList<MileageLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MileageLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final int _tmpMileage;
            _tmpMileage = _cursor.getInt(_cursorIndexOfMileage);
            final long _tmpLogDate;
            _tmpLogDate = _cursor.getLong(_cursorIndexOfLogDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new MileageLogEntity(_tmpId,_tmpVehicleId,_tmpMileage,_tmpLogDate,_tmpNotes);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
