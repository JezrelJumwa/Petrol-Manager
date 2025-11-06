package com.cartracker.app.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cartracker.app.data.model.PartEntity;
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
public final class PartDao_Impl implements PartDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PartEntity> __insertionAdapterOfPartEntity;

  private final EntityDeletionOrUpdateAdapter<PartEntity> __deletionAdapterOfPartEntity;

  private final EntityDeletionOrUpdateAdapter<PartEntity> __updateAdapterOfPartEntity;

  public PartDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPartEntity = new EntityInsertionAdapter<PartEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `parts` (`id`,`serviceRecordId`,`partName`,`partNumber`,`brand`,`quantity`,`cost`,`warranty`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PartEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getServiceRecordId());
        statement.bindString(3, entity.getPartName());
        if (entity.getPartNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPartNumber());
        }
        if (entity.getBrand() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBrand());
        }
        statement.bindLong(6, entity.getQuantity());
        statement.bindDouble(7, entity.getCost());
        if (entity.getWarranty() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getWarranty());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfPartEntity = new EntityDeletionOrUpdateAdapter<PartEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `parts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PartEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPartEntity = new EntityDeletionOrUpdateAdapter<PartEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `parts` SET `id` = ?,`serviceRecordId` = ?,`partName` = ?,`partNumber` = ?,`brand` = ?,`quantity` = ?,`cost` = ?,`warranty` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PartEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getServiceRecordId());
        statement.bindString(3, entity.getPartName());
        if (entity.getPartNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPartNumber());
        }
        if (entity.getBrand() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBrand());
        }
        statement.bindLong(6, entity.getQuantity());
        statement.bindDouble(7, entity.getCost());
        if (entity.getWarranty() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getWarranty());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insertPart(final PartEntity part, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPartEntity.insertAndReturnId(part);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertParts(final List<PartEntity> parts,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPartEntity.insert(parts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePart(final PartEntity part, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPartEntity.handle(part);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePart(final PartEntity part, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPartEntity.handle(part);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PartEntity>> getPartsByServiceRecord(final long serviceRecordId) {
    final String _sql = "SELECT * FROM parts WHERE serviceRecordId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, serviceRecordId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"parts"}, new Callable<List<PartEntity>>() {
      @Override
      @NonNull
      public List<PartEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfServiceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceRecordId");
          final int _cursorIndexOfPartName = CursorUtil.getColumnIndexOrThrow(_cursor, "partName");
          final int _cursorIndexOfPartNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "partNumber");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfWarranty = CursorUtil.getColumnIndexOrThrow(_cursor, "warranty");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PartEntity> _result = new ArrayList<PartEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PartEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpServiceRecordId;
            _tmpServiceRecordId = _cursor.getLong(_cursorIndexOfServiceRecordId);
            final String _tmpPartName;
            _tmpPartName = _cursor.getString(_cursorIndexOfPartName);
            final String _tmpPartNumber;
            if (_cursor.isNull(_cursorIndexOfPartNumber)) {
              _tmpPartNumber = null;
            } else {
              _tmpPartNumber = _cursor.getString(_cursorIndexOfPartNumber);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpWarranty;
            if (_cursor.isNull(_cursorIndexOfWarranty)) {
              _tmpWarranty = null;
            } else {
              _tmpWarranty = _cursor.getString(_cursorIndexOfWarranty);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PartEntity(_tmpId,_tmpServiceRecordId,_tmpPartName,_tmpPartNumber,_tmpBrand,_tmpQuantity,_tmpCost,_tmpWarranty,_tmpNotes,_tmpCreatedAt);
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
  public Flow<List<PartEntity>> getPartsByVehicle(final long vehicleId) {
    final String _sql = "\n"
            + "        SELECT p.* FROM parts p\n"
            + "        INNER JOIN service_records sr ON p.serviceRecordId = sr.id\n"
            + "        WHERE sr.vehicleId = ?\n"
            + "        ORDER BY sr.serviceDate DESC, p.createdAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"parts",
        "service_records"}, new Callable<List<PartEntity>>() {
      @Override
      @NonNull
      public List<PartEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfServiceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceRecordId");
          final int _cursorIndexOfPartName = CursorUtil.getColumnIndexOrThrow(_cursor, "partName");
          final int _cursorIndexOfPartNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "partNumber");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfWarranty = CursorUtil.getColumnIndexOrThrow(_cursor, "warranty");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PartEntity> _result = new ArrayList<PartEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PartEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpServiceRecordId;
            _tmpServiceRecordId = _cursor.getLong(_cursorIndexOfServiceRecordId);
            final String _tmpPartName;
            _tmpPartName = _cursor.getString(_cursorIndexOfPartName);
            final String _tmpPartNumber;
            if (_cursor.isNull(_cursorIndexOfPartNumber)) {
              _tmpPartNumber = null;
            } else {
              _tmpPartNumber = _cursor.getString(_cursorIndexOfPartNumber);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpWarranty;
            if (_cursor.isNull(_cursorIndexOfWarranty)) {
              _tmpWarranty = null;
            } else {
              _tmpWarranty = _cursor.getString(_cursorIndexOfWarranty);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PartEntity(_tmpId,_tmpServiceRecordId,_tmpPartName,_tmpPartNumber,_tmpBrand,_tmpQuantity,_tmpCost,_tmpWarranty,_tmpNotes,_tmpCreatedAt);
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
  public Flow<List<PartEntity>> searchPartsByName(final long vehicleId, final String partName) {
    final String _sql = "\n"
            + "        SELECT p.* FROM parts p\n"
            + "        INNER JOIN service_records sr ON p.serviceRecordId = sr.id\n"
            + "        WHERE sr.vehicleId = ? AND p.partName LIKE '%' || ? || '%'\n"
            + "        ORDER BY sr.serviceDate DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vehicleId);
    _argIndex = 2;
    _statement.bindString(_argIndex, partName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"parts",
        "service_records"}, new Callable<List<PartEntity>>() {
      @Override
      @NonNull
      public List<PartEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfServiceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceRecordId");
          final int _cursorIndexOfPartName = CursorUtil.getColumnIndexOrThrow(_cursor, "partName");
          final int _cursorIndexOfPartNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "partNumber");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfWarranty = CursorUtil.getColumnIndexOrThrow(_cursor, "warranty");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PartEntity> _result = new ArrayList<PartEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PartEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpServiceRecordId;
            _tmpServiceRecordId = _cursor.getLong(_cursorIndexOfServiceRecordId);
            final String _tmpPartName;
            _tmpPartName = _cursor.getString(_cursorIndexOfPartName);
            final String _tmpPartNumber;
            if (_cursor.isNull(_cursorIndexOfPartNumber)) {
              _tmpPartNumber = null;
            } else {
              _tmpPartNumber = _cursor.getString(_cursorIndexOfPartNumber);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final String _tmpWarranty;
            if (_cursor.isNull(_cursorIndexOfWarranty)) {
              _tmpWarranty = null;
            } else {
              _tmpWarranty = _cursor.getString(_cursorIndexOfWarranty);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PartEntity(_tmpId,_tmpServiceRecordId,_tmpPartName,_tmpPartNumber,_tmpBrand,_tmpQuantity,_tmpCost,_tmpWarranty,_tmpNotes,_tmpCreatedAt);
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
