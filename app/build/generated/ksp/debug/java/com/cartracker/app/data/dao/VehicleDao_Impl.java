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
import com.cartracker.app.data.model.VehicleEntity;
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
public final class VehicleDao_Impl implements VehicleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VehicleEntity> __insertionAdapterOfVehicleEntity;

  private final EntityDeletionOrUpdateAdapter<VehicleEntity> __deletionAdapterOfVehicleEntity;

  private final EntityDeletionOrUpdateAdapter<VehicleEntity> __updateAdapterOfVehicleEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateMileage;

  public VehicleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVehicleEntity = new EntityInsertionAdapter<VehicleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vehicles` (`id`,`make`,`model`,`year`,`licensePlate`,`vin`,`currentMileage`,`purchaseDate`,`notes`,`isActive`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VehicleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getMake());
        statement.bindString(3, entity.getModel());
        statement.bindLong(4, entity.getYear());
        statement.bindString(5, entity.getLicensePlate());
        if (entity.getVin() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getVin());
        }
        statement.bindLong(7, entity.getCurrentMileage());
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPurchaseDate());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfVehicleEntity = new EntityDeletionOrUpdateAdapter<VehicleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vehicles` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VehicleEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfVehicleEntity = new EntityDeletionOrUpdateAdapter<VehicleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vehicles` SET `id` = ?,`make` = ?,`model` = ?,`year` = ?,`licensePlate` = ?,`vin` = ?,`currentMileage` = ?,`purchaseDate` = ?,`notes` = ?,`isActive` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VehicleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getMake());
        statement.bindString(3, entity.getModel());
        statement.bindLong(4, entity.getYear());
        statement.bindString(5, entity.getLicensePlate());
        if (entity.getVin() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getVin());
        }
        statement.bindLong(7, entity.getCurrentMileage());
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPurchaseDate());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateMileage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE vehicles SET currentMileage = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertVehicle(final VehicleEntity vehicle,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVehicleEntity.insertAndReturnId(vehicle);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteVehicle(final VehicleEntity vehicle,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVehicleEntity.handle(vehicle);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateVehicle(final VehicleEntity vehicle,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVehicleEntity.handle(vehicle);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMileage(final long vehicleId, final int mileage, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateMileage.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, mileage);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 3;
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
          __preparedStmtOfUpdateMileage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VehicleEntity>> getAllActiveVehicles() {
    final String _sql = "SELECT * FROM vehicles WHERE isActive = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vehicles"}, new Callable<List<VehicleEntity>>() {
      @Override
      @NonNull
      public List<VehicleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMake = CursorUtil.getColumnIndexOrThrow(_cursor, "make");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfLicensePlate = CursorUtil.getColumnIndexOrThrow(_cursor, "licensePlate");
          final int _cursorIndexOfVin = CursorUtil.getColumnIndexOrThrow(_cursor, "vin");
          final int _cursorIndexOfCurrentMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentMileage");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<VehicleEntity> _result = new ArrayList<VehicleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VehicleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMake;
            _tmpMake = _cursor.getString(_cursorIndexOfMake);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final String _tmpLicensePlate;
            _tmpLicensePlate = _cursor.getString(_cursorIndexOfLicensePlate);
            final String _tmpVin;
            if (_cursor.isNull(_cursorIndexOfVin)) {
              _tmpVin = null;
            } else {
              _tmpVin = _cursor.getString(_cursorIndexOfVin);
            }
            final int _tmpCurrentMileage;
            _tmpCurrentMileage = _cursor.getInt(_cursorIndexOfCurrentMileage);
            final Long _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new VehicleEntity(_tmpId,_tmpMake,_tmpModel,_tmpYear,_tmpLicensePlate,_tmpVin,_tmpCurrentMileage,_tmpPurchaseDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<VehicleEntity>> getAllVehicles() {
    final String _sql = "SELECT * FROM vehicles ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vehicles"}, new Callable<List<VehicleEntity>>() {
      @Override
      @NonNull
      public List<VehicleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMake = CursorUtil.getColumnIndexOrThrow(_cursor, "make");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfLicensePlate = CursorUtil.getColumnIndexOrThrow(_cursor, "licensePlate");
          final int _cursorIndexOfVin = CursorUtil.getColumnIndexOrThrow(_cursor, "vin");
          final int _cursorIndexOfCurrentMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentMileage");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<VehicleEntity> _result = new ArrayList<VehicleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VehicleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMake;
            _tmpMake = _cursor.getString(_cursorIndexOfMake);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final String _tmpLicensePlate;
            _tmpLicensePlate = _cursor.getString(_cursorIndexOfLicensePlate);
            final String _tmpVin;
            if (_cursor.isNull(_cursorIndexOfVin)) {
              _tmpVin = null;
            } else {
              _tmpVin = _cursor.getString(_cursorIndexOfVin);
            }
            final int _tmpCurrentMileage;
            _tmpCurrentMileage = _cursor.getInt(_cursorIndexOfCurrentMileage);
            final Long _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new VehicleEntity(_tmpId,_tmpMake,_tmpModel,_tmpYear,_tmpLicensePlate,_tmpVin,_tmpCurrentMileage,_tmpPurchaseDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<VehicleEntity> getVehicleById(final long vehicleId) {
    final String _sql = "SELECT * FROM vehicles WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vehicles"}, new Callable<VehicleEntity>() {
      @Override
      @Nullable
      public VehicleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMake = CursorUtil.getColumnIndexOrThrow(_cursor, "make");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfLicensePlate = CursorUtil.getColumnIndexOrThrow(_cursor, "licensePlate");
          final int _cursorIndexOfVin = CursorUtil.getColumnIndexOrThrow(_cursor, "vin");
          final int _cursorIndexOfCurrentMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentMileage");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final VehicleEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMake;
            _tmpMake = _cursor.getString(_cursorIndexOfMake);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final String _tmpLicensePlate;
            _tmpLicensePlate = _cursor.getString(_cursorIndexOfLicensePlate);
            final String _tmpVin;
            if (_cursor.isNull(_cursorIndexOfVin)) {
              _tmpVin = null;
            } else {
              _tmpVin = _cursor.getString(_cursorIndexOfVin);
            }
            final int _tmpCurrentMileage;
            _tmpCurrentMileage = _cursor.getInt(_cursorIndexOfCurrentMileage);
            final Long _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new VehicleEntity(_tmpId,_tmpMake,_tmpModel,_tmpYear,_tmpLicensePlate,_tmpVin,_tmpCurrentMileage,_tmpPurchaseDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getVehicleByIdSync(final long vehicleId,
      final Continuation<? super VehicleEntity> $completion) {
    final String _sql = "SELECT * FROM vehicles WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VehicleEntity>() {
      @Override
      @Nullable
      public VehicleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMake = CursorUtil.getColumnIndexOrThrow(_cursor, "make");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfLicensePlate = CursorUtil.getColumnIndexOrThrow(_cursor, "licensePlate");
          final int _cursorIndexOfVin = CursorUtil.getColumnIndexOrThrow(_cursor, "vin");
          final int _cursorIndexOfCurrentMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentMileage");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final VehicleEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMake;
            _tmpMake = _cursor.getString(_cursorIndexOfMake);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final String _tmpLicensePlate;
            _tmpLicensePlate = _cursor.getString(_cursorIndexOfLicensePlate);
            final String _tmpVin;
            if (_cursor.isNull(_cursorIndexOfVin)) {
              _tmpVin = null;
            } else {
              _tmpVin = _cursor.getString(_cursorIndexOfVin);
            }
            final int _tmpCurrentMileage;
            _tmpCurrentMileage = _cursor.getInt(_cursorIndexOfCurrentMileage);
            final Long _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new VehicleEntity(_tmpId,_tmpMake,_tmpModel,_tmpYear,_tmpLicensePlate,_tmpVin,_tmpCurrentMileage,_tmpPurchaseDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getActiveVehicleCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM vehicles WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
