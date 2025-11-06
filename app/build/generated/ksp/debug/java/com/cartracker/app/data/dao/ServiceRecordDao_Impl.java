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
import com.cartracker.app.data.model.ServiceRecordEntity;
import java.lang.Class;
import java.lang.Double;
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
public final class ServiceRecordDao_Impl implements ServiceRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ServiceRecordEntity> __insertionAdapterOfServiceRecordEntity;

  private final EntityDeletionOrUpdateAdapter<ServiceRecordEntity> __deletionAdapterOfServiceRecordEntity;

  private final EntityDeletionOrUpdateAdapter<ServiceRecordEntity> __updateAdapterOfServiceRecordEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllServiceRecordsForVehicle;

  public ServiceRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfServiceRecordEntity = new EntityInsertionAdapter<ServiceRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `service_records` (`id`,`vehicleId`,`serviceType`,`serviceDate`,`mileageAtService`,`cost`,`servicedBy`,`notes`,`nextServiceDue`,`nextServiceMileage`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServiceRecordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindString(3, entity.getServiceType());
        statement.bindLong(4, entity.getServiceDate());
        statement.bindLong(5, entity.getMileageAtService());
        statement.bindDouble(6, entity.getCost());
        if (entity.getServicedBy() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getServicedBy());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        if (entity.getNextServiceDue() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextServiceDue());
        }
        if (entity.getNextServiceMileage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getNextServiceMileage());
        }
        statement.bindLong(11, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfServiceRecordEntity = new EntityDeletionOrUpdateAdapter<ServiceRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `service_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServiceRecordEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfServiceRecordEntity = new EntityDeletionOrUpdateAdapter<ServiceRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `service_records` SET `id` = ?,`vehicleId` = ?,`serviceType` = ?,`serviceDate` = ?,`mileageAtService` = ?,`cost` = ?,`servicedBy` = ?,`notes` = ?,`nextServiceDue` = ?,`nextServiceMileage` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServiceRecordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getVehicleId());
        statement.bindString(3, entity.getServiceType());
        statement.bindLong(4, entity.getServiceDate());
        statement.bindLong(5, entity.getMileageAtService());
        statement.bindDouble(6, entity.getCost());
        if (entity.getServicedBy() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getServicedBy());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        if (entity.getNextServiceDue() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextServiceDue());
        }
        if (entity.getNextServiceMileage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getNextServiceMileage());
        }
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllServiceRecordsForVehicle = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM service_records WHERE vehicleId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertServiceRecord(final ServiceRecordEntity record,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfServiceRecordEntity.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteServiceRecord(final ServiceRecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfServiceRecordEntity.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateServiceRecord(final ServiceRecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfServiceRecordEntity.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllServiceRecordsForVehicle(final long vehicleId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllServiceRecordsForVehicle.acquire();
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
          __preparedStmtOfDeleteAllServiceRecordsForVehicle.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ServiceRecordEntity>> getServiceRecordsByVehicle(final long vehicleId) {
    final String _sql = "SELECT * FROM service_records WHERE vehicleId = ? ORDER BY serviceDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"service_records"}, new Callable<List<ServiceRecordEntity>>() {
      @Override
      @NonNull
      public List<ServiceRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceDate");
          final int _cursorIndexOfMileageAtService = CursorUtil.getColumnIndexOrThrow(_cursor, "mileageAtService");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfServicedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "servicedBy");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextServiceDue = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceDue");
          final int _cursorIndexOfNextServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceMileage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ServiceRecordEntity> _result = new ArrayList<ServiceRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ServiceRecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpServiceType;
            _tmpServiceType = _cursor.getString(_cursorIndexOfServiceType);
            final long _tmpServiceDate;
            _tmpServiceDate = _cursor.getLong(_cursorIndexOfServiceDate);
            final int _tmpMileageAtService;
            _tmpMileageAtService = _cursor.getInt(_cursorIndexOfMileageAtService);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpServicedBy;
            if (_cursor.isNull(_cursorIndexOfServicedBy)) {
              _tmpServicedBy = null;
            } else {
              _tmpServicedBy = _cursor.getString(_cursorIndexOfServicedBy);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextServiceDue;
            if (_cursor.isNull(_cursorIndexOfNextServiceDue)) {
              _tmpNextServiceDue = null;
            } else {
              _tmpNextServiceDue = _cursor.getLong(_cursorIndexOfNextServiceDue);
            }
            final Integer _tmpNextServiceMileage;
            if (_cursor.isNull(_cursorIndexOfNextServiceMileage)) {
              _tmpNextServiceMileage = null;
            } else {
              _tmpNextServiceMileage = _cursor.getInt(_cursorIndexOfNextServiceMileage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ServiceRecordEntity(_tmpId,_tmpVehicleId,_tmpServiceType,_tmpServiceDate,_tmpMileageAtService,_tmpCost,_tmpServicedBy,_tmpNotes,_tmpNextServiceDue,_tmpNextServiceMileage,_tmpCreatedAt);
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
  public Flow<ServiceRecordEntity> getServiceRecordById(final long recordId) {
    final String _sql = "SELECT * FROM service_records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recordId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"service_records"}, new Callable<ServiceRecordEntity>() {
      @Override
      @Nullable
      public ServiceRecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceDate");
          final int _cursorIndexOfMileageAtService = CursorUtil.getColumnIndexOrThrow(_cursor, "mileageAtService");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfServicedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "servicedBy");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextServiceDue = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceDue");
          final int _cursorIndexOfNextServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceMileage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ServiceRecordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpServiceType;
            _tmpServiceType = _cursor.getString(_cursorIndexOfServiceType);
            final long _tmpServiceDate;
            _tmpServiceDate = _cursor.getLong(_cursorIndexOfServiceDate);
            final int _tmpMileageAtService;
            _tmpMileageAtService = _cursor.getInt(_cursorIndexOfMileageAtService);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpServicedBy;
            if (_cursor.isNull(_cursorIndexOfServicedBy)) {
              _tmpServicedBy = null;
            } else {
              _tmpServicedBy = _cursor.getString(_cursorIndexOfServicedBy);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextServiceDue;
            if (_cursor.isNull(_cursorIndexOfNextServiceDue)) {
              _tmpNextServiceDue = null;
            } else {
              _tmpNextServiceDue = _cursor.getLong(_cursorIndexOfNextServiceDue);
            }
            final Integer _tmpNextServiceMileage;
            if (_cursor.isNull(_cursorIndexOfNextServiceMileage)) {
              _tmpNextServiceMileage = null;
            } else {
              _tmpNextServiceMileage = _cursor.getInt(_cursorIndexOfNextServiceMileage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ServiceRecordEntity(_tmpId,_tmpVehicleId,_tmpServiceType,_tmpServiceDate,_tmpMileageAtService,_tmpCost,_tmpServicedBy,_tmpNotes,_tmpNextServiceDue,_tmpNextServiceMileage,_tmpCreatedAt);
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
  public Object getLatestServiceRecord(final long vehicleId,
      final Continuation<? super ServiceRecordEntity> $completion) {
    final String _sql = "SELECT * FROM service_records WHERE vehicleId = ? ORDER BY serviceDate DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ServiceRecordEntity>() {
      @Override
      @Nullable
      public ServiceRecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceDate");
          final int _cursorIndexOfMileageAtService = CursorUtil.getColumnIndexOrThrow(_cursor, "mileageAtService");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfServicedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "servicedBy");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextServiceDue = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceDue");
          final int _cursorIndexOfNextServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceMileage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ServiceRecordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpServiceType;
            _tmpServiceType = _cursor.getString(_cursorIndexOfServiceType);
            final long _tmpServiceDate;
            _tmpServiceDate = _cursor.getLong(_cursorIndexOfServiceDate);
            final int _tmpMileageAtService;
            _tmpMileageAtService = _cursor.getInt(_cursorIndexOfMileageAtService);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpServicedBy;
            if (_cursor.isNull(_cursorIndexOfServicedBy)) {
              _tmpServicedBy = null;
            } else {
              _tmpServicedBy = _cursor.getString(_cursorIndexOfServicedBy);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextServiceDue;
            if (_cursor.isNull(_cursorIndexOfNextServiceDue)) {
              _tmpNextServiceDue = null;
            } else {
              _tmpNextServiceDue = _cursor.getLong(_cursorIndexOfNextServiceDue);
            }
            final Integer _tmpNextServiceMileage;
            if (_cursor.isNull(_cursorIndexOfNextServiceMileage)) {
              _tmpNextServiceMileage = null;
            } else {
              _tmpNextServiceMileage = _cursor.getInt(_cursorIndexOfNextServiceMileage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ServiceRecordEntity(_tmpId,_tmpVehicleId,_tmpServiceType,_tmpServiceDate,_tmpMileageAtService,_tmpCost,_tmpServicedBy,_tmpNotes,_tmpNextServiceDue,_tmpNextServiceMileage,_tmpCreatedAt);
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
  public Object getLatestServiceByType(final long vehicleId, final String serviceType,
      final Continuation<? super ServiceRecordEntity> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM service_records \n"
            + "        WHERE vehicleId = ? \n"
            + "        AND serviceType = ? \n"
            + "        ORDER BY serviceDate DESC \n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    _argIndex = 2;
    _statement.bindString(_argIndex, serviceType);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ServiceRecordEntity>() {
      @Override
      @Nullable
      public ServiceRecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleId");
          final int _cursorIndexOfServiceType = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceType");
          final int _cursorIndexOfServiceDate = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceDate");
          final int _cursorIndexOfMileageAtService = CursorUtil.getColumnIndexOrThrow(_cursor, "mileageAtService");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfServicedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "servicedBy");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextServiceDue = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceDue");
          final int _cursorIndexOfNextServiceMileage = CursorUtil.getColumnIndexOrThrow(_cursor, "nextServiceMileage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ServiceRecordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpVehicleId;
            _tmpVehicleId = _cursor.getLong(_cursorIndexOfVehicleId);
            final String _tmpServiceType;
            _tmpServiceType = _cursor.getString(_cursorIndexOfServiceType);
            final long _tmpServiceDate;
            _tmpServiceDate = _cursor.getLong(_cursorIndexOfServiceDate);
            final int _tmpMileageAtService;
            _tmpMileageAtService = _cursor.getInt(_cursorIndexOfMileageAtService);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpServicedBy;
            if (_cursor.isNull(_cursorIndexOfServicedBy)) {
              _tmpServicedBy = null;
            } else {
              _tmpServicedBy = _cursor.getString(_cursorIndexOfServicedBy);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextServiceDue;
            if (_cursor.isNull(_cursorIndexOfNextServiceDue)) {
              _tmpNextServiceDue = null;
            } else {
              _tmpNextServiceDue = _cursor.getLong(_cursorIndexOfNextServiceDue);
            }
            final Integer _tmpNextServiceMileage;
            if (_cursor.isNull(_cursorIndexOfNextServiceMileage)) {
              _tmpNextServiceMileage = null;
            } else {
              _tmpNextServiceMileage = _cursor.getInt(_cursorIndexOfNextServiceMileage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ServiceRecordEntity(_tmpId,_tmpVehicleId,_tmpServiceType,_tmpServiceDate,_tmpMileageAtService,_tmpCost,_tmpServicedBy,_tmpNotes,_tmpNextServiceDue,_tmpNextServiceMileage,_tmpCreatedAt);
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
  public Object getTotalServiceCost(final long vehicleId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(cost) FROM service_records WHERE vehicleId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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
  public Object getServiceCostInDateRange(final long vehicleId, final long startDate,
      final long endDate, final Continuation<? super Double> $completion) {
    final String _sql = "\n"
            + "        SELECT SUM(cost) FROM service_records \n"
            + "        WHERE vehicleId = ? \n"
            + "        AND serviceDate BETWEEN ? AND ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
