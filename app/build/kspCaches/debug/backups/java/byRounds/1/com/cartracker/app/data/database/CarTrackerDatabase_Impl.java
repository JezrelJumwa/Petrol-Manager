package com.cartracker.app.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.cartracker.app.data.dao.ExpenseDao;
import com.cartracker.app.data.dao.ExpenseDao_Impl;
import com.cartracker.app.data.dao.MaintenanceScheduleDao;
import com.cartracker.app.data.dao.MaintenanceScheduleDao_Impl;
import com.cartracker.app.data.dao.MileageLogDao;
import com.cartracker.app.data.dao.MileageLogDao_Impl;
import com.cartracker.app.data.dao.PartDao;
import com.cartracker.app.data.dao.PartDao_Impl;
import com.cartracker.app.data.dao.ServiceRecordDao;
import com.cartracker.app.data.dao.ServiceRecordDao_Impl;
import com.cartracker.app.data.dao.VehicleDao;
import com.cartracker.app.data.dao.VehicleDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CarTrackerDatabase_Impl extends CarTrackerDatabase {
  private volatile VehicleDao _vehicleDao;

  private volatile ServiceRecordDao _serviceRecordDao;

  private volatile PartDao _partDao;

  private volatile MaintenanceScheduleDao _maintenanceScheduleDao;

  private volatile MileageLogDao _mileageLogDao;

  private volatile ExpenseDao _expenseDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `vehicles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `make` TEXT NOT NULL, `model` TEXT NOT NULL, `year` INTEGER NOT NULL, `licensePlate` TEXT NOT NULL, `vin` TEXT, `currentMileage` INTEGER NOT NULL, `purchaseDate` INTEGER, `notes` TEXT, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `service_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vehicleId` INTEGER NOT NULL, `serviceType` TEXT NOT NULL, `serviceDate` INTEGER NOT NULL, `mileageAtService` INTEGER NOT NULL, `cost` REAL NOT NULL, `servicedBy` TEXT, `notes` TEXT, `nextServiceDue` INTEGER, `nextServiceMileage` INTEGER, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`vehicleId`) REFERENCES `vehicles`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_service_records_vehicleId` ON `service_records` (`vehicleId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_service_records_serviceDate` ON `service_records` (`serviceDate`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `parts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `serviceRecordId` INTEGER NOT NULL, `partName` TEXT NOT NULL, `partNumber` TEXT, `brand` TEXT, `quantity` INTEGER NOT NULL, `cost` REAL NOT NULL, `warranty` TEXT, `notes` TEXT, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`serviceRecordId`) REFERENCES `service_records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_parts_serviceRecordId` ON `parts` (`serviceRecordId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_parts_partName` ON `parts` (`partName`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `maintenance_schedules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vehicleId` INTEGER NOT NULL, `maintenanceType` TEXT NOT NULL, `intervalMiles` INTEGER, `intervalMonths` INTEGER, `lastServiceDate` INTEGER, `lastServiceMileage` INTEGER, `nextDueDate` INTEGER, `nextDueMileage` INTEGER, `isActive` INTEGER NOT NULL, `reminderEnabled` INTEGER NOT NULL, `reminderAdvanceMiles` INTEGER NOT NULL, `reminderAdvanceDays` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`vehicleId`) REFERENCES `vehicles`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_maintenance_schedules_vehicleId` ON `maintenance_schedules` (`vehicleId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_maintenance_schedules_maintenanceType` ON `maintenance_schedules` (`maintenanceType`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `mileage_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vehicleId` INTEGER NOT NULL, `mileage` INTEGER NOT NULL, `logDate` INTEGER NOT NULL, `notes` TEXT, FOREIGN KEY(`vehicleId`) REFERENCES `vehicles`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_mileage_logs_vehicleId` ON `mileage_logs` (`vehicleId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_mileage_logs_logDate` ON `mileage_logs` (`logDate`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vehicleId` INTEGER NOT NULL, `category` TEXT NOT NULL, `amount` REAL NOT NULL, `expenseDate` INTEGER NOT NULL, `vendor` TEXT, `notes` TEXT, `mileageAtExpense` INTEGER, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`vehicleId`) REFERENCES `vehicles`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_expenses_vehicleId` ON `expenses` (`vehicleId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_expenses_expenseDate` ON `expenses` (`expenseDate`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_expenses_category` ON `expenses` (`category`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1d9ca59a46254bc7bb18164bead09f49')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `vehicles`");
        db.execSQL("DROP TABLE IF EXISTS `service_records`");
        db.execSQL("DROP TABLE IF EXISTS `parts`");
        db.execSQL("DROP TABLE IF EXISTS `maintenance_schedules`");
        db.execSQL("DROP TABLE IF EXISTS `mileage_logs`");
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsVehicles = new HashMap<String, TableInfo.Column>(12);
        _columnsVehicles.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("make", new TableInfo.Column("make", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("model", new TableInfo.Column("model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("year", new TableInfo.Column("year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("licensePlate", new TableInfo.Column("licensePlate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("vin", new TableInfo.Column("vin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("currentMileage", new TableInfo.Column("currentMileage", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("purchaseDate", new TableInfo.Column("purchaseDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicles.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVehicles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVehicles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVehicles = new TableInfo("vehicles", _columnsVehicles, _foreignKeysVehicles, _indicesVehicles);
        final TableInfo _existingVehicles = TableInfo.read(db, "vehicles");
        if (!_infoVehicles.equals(_existingVehicles)) {
          return new RoomOpenHelper.ValidationResult(false, "vehicles(com.cartracker.app.data.model.VehicleEntity).\n"
                  + " Expected:\n" + _infoVehicles + "\n"
                  + " Found:\n" + _existingVehicles);
        }
        final HashMap<String, TableInfo.Column> _columnsServiceRecords = new HashMap<String, TableInfo.Column>(11);
        _columnsServiceRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("vehicleId", new TableInfo.Column("vehicleId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("serviceType", new TableInfo.Column("serviceType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("serviceDate", new TableInfo.Column("serviceDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("mileageAtService", new TableInfo.Column("mileageAtService", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("cost", new TableInfo.Column("cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("servicedBy", new TableInfo.Column("servicedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("nextServiceDue", new TableInfo.Column("nextServiceDue", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("nextServiceMileage", new TableInfo.Column("nextServiceMileage", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServiceRecords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysServiceRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysServiceRecords.add(new TableInfo.ForeignKey("vehicles", "CASCADE", "NO ACTION", Arrays.asList("vehicleId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesServiceRecords = new HashSet<TableInfo.Index>(2);
        _indicesServiceRecords.add(new TableInfo.Index("index_service_records_vehicleId", false, Arrays.asList("vehicleId"), Arrays.asList("ASC")));
        _indicesServiceRecords.add(new TableInfo.Index("index_service_records_serviceDate", false, Arrays.asList("serviceDate"), Arrays.asList("ASC")));
        final TableInfo _infoServiceRecords = new TableInfo("service_records", _columnsServiceRecords, _foreignKeysServiceRecords, _indicesServiceRecords);
        final TableInfo _existingServiceRecords = TableInfo.read(db, "service_records");
        if (!_infoServiceRecords.equals(_existingServiceRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "service_records(com.cartracker.app.data.model.ServiceRecordEntity).\n"
                  + " Expected:\n" + _infoServiceRecords + "\n"
                  + " Found:\n" + _existingServiceRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsParts = new HashMap<String, TableInfo.Column>(10);
        _columnsParts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("serviceRecordId", new TableInfo.Column("serviceRecordId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("partName", new TableInfo.Column("partName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("partNumber", new TableInfo.Column("partNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("brand", new TableInfo.Column("brand", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("cost", new TableInfo.Column("cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("warranty", new TableInfo.Column("warranty", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysParts = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysParts.add(new TableInfo.ForeignKey("service_records", "CASCADE", "NO ACTION", Arrays.asList("serviceRecordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesParts = new HashSet<TableInfo.Index>(2);
        _indicesParts.add(new TableInfo.Index("index_parts_serviceRecordId", false, Arrays.asList("serviceRecordId"), Arrays.asList("ASC")));
        _indicesParts.add(new TableInfo.Index("index_parts_partName", false, Arrays.asList("partName"), Arrays.asList("ASC")));
        final TableInfo _infoParts = new TableInfo("parts", _columnsParts, _foreignKeysParts, _indicesParts);
        final TableInfo _existingParts = TableInfo.read(db, "parts");
        if (!_infoParts.equals(_existingParts)) {
          return new RoomOpenHelper.ValidationResult(false, "parts(com.cartracker.app.data.model.PartEntity).\n"
                  + " Expected:\n" + _infoParts + "\n"
                  + " Found:\n" + _existingParts);
        }
        final HashMap<String, TableInfo.Column> _columnsMaintenanceSchedules = new HashMap<String, TableInfo.Column>(14);
        _columnsMaintenanceSchedules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("vehicleId", new TableInfo.Column("vehicleId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("maintenanceType", new TableInfo.Column("maintenanceType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("intervalMiles", new TableInfo.Column("intervalMiles", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("intervalMonths", new TableInfo.Column("intervalMonths", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("lastServiceDate", new TableInfo.Column("lastServiceDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("lastServiceMileage", new TableInfo.Column("lastServiceMileage", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("nextDueDate", new TableInfo.Column("nextDueDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("nextDueMileage", new TableInfo.Column("nextDueMileage", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("reminderEnabled", new TableInfo.Column("reminderEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("reminderAdvanceMiles", new TableInfo.Column("reminderAdvanceMiles", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("reminderAdvanceDays", new TableInfo.Column("reminderAdvanceDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaintenanceSchedules.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMaintenanceSchedules = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMaintenanceSchedules.add(new TableInfo.ForeignKey("vehicles", "CASCADE", "NO ACTION", Arrays.asList("vehicleId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMaintenanceSchedules = new HashSet<TableInfo.Index>(2);
        _indicesMaintenanceSchedules.add(new TableInfo.Index("index_maintenance_schedules_vehicleId", false, Arrays.asList("vehicleId"), Arrays.asList("ASC")));
        _indicesMaintenanceSchedules.add(new TableInfo.Index("index_maintenance_schedules_maintenanceType", false, Arrays.asList("maintenanceType"), Arrays.asList("ASC")));
        final TableInfo _infoMaintenanceSchedules = new TableInfo("maintenance_schedules", _columnsMaintenanceSchedules, _foreignKeysMaintenanceSchedules, _indicesMaintenanceSchedules);
        final TableInfo _existingMaintenanceSchedules = TableInfo.read(db, "maintenance_schedules");
        if (!_infoMaintenanceSchedules.equals(_existingMaintenanceSchedules)) {
          return new RoomOpenHelper.ValidationResult(false, "maintenance_schedules(com.cartracker.app.data.model.MaintenanceScheduleEntity).\n"
                  + " Expected:\n" + _infoMaintenanceSchedules + "\n"
                  + " Found:\n" + _existingMaintenanceSchedules);
        }
        final HashMap<String, TableInfo.Column> _columnsMileageLogs = new HashMap<String, TableInfo.Column>(5);
        _columnsMileageLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMileageLogs.put("vehicleId", new TableInfo.Column("vehicleId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMileageLogs.put("mileage", new TableInfo.Column("mileage", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMileageLogs.put("logDate", new TableInfo.Column("logDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMileageLogs.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMileageLogs = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMileageLogs.add(new TableInfo.ForeignKey("vehicles", "CASCADE", "NO ACTION", Arrays.asList("vehicleId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMileageLogs = new HashSet<TableInfo.Index>(2);
        _indicesMileageLogs.add(new TableInfo.Index("index_mileage_logs_vehicleId", false, Arrays.asList("vehicleId"), Arrays.asList("ASC")));
        _indicesMileageLogs.add(new TableInfo.Index("index_mileage_logs_logDate", false, Arrays.asList("logDate"), Arrays.asList("ASC")));
        final TableInfo _infoMileageLogs = new TableInfo("mileage_logs", _columnsMileageLogs, _foreignKeysMileageLogs, _indicesMileageLogs);
        final TableInfo _existingMileageLogs = TableInfo.read(db, "mileage_logs");
        if (!_infoMileageLogs.equals(_existingMileageLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "mileage_logs(com.cartracker.app.data.model.MileageLogEntity).\n"
                  + " Expected:\n" + _infoMileageLogs + "\n"
                  + " Found:\n" + _existingMileageLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsExpenses = new HashMap<String, TableInfo.Column>(9);
        _columnsExpenses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("vehicleId", new TableInfo.Column("vehicleId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("expenseDate", new TableInfo.Column("expenseDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("vendor", new TableInfo.Column("vendor", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("mileageAtExpense", new TableInfo.Column("mileageAtExpense", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenses = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExpenses.add(new TableInfo.ForeignKey("vehicles", "CASCADE", "NO ACTION", Arrays.asList("vehicleId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExpenses = new HashSet<TableInfo.Index>(3);
        _indicesExpenses.add(new TableInfo.Index("index_expenses_vehicleId", false, Arrays.asList("vehicleId"), Arrays.asList("ASC")));
        _indicesExpenses.add(new TableInfo.Index("index_expenses_expenseDate", false, Arrays.asList("expenseDate"), Arrays.asList("ASC")));
        _indicesExpenses.add(new TableInfo.Index("index_expenses_category", false, Arrays.asList("category"), Arrays.asList("ASC")));
        final TableInfo _infoExpenses = new TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses);
        final TableInfo _existingExpenses = TableInfo.read(db, "expenses");
        if (!_infoExpenses.equals(_existingExpenses)) {
          return new RoomOpenHelper.ValidationResult(false, "expenses(com.cartracker.app.data.model.ExpenseEntity).\n"
                  + " Expected:\n" + _infoExpenses + "\n"
                  + " Found:\n" + _existingExpenses);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1d9ca59a46254bc7bb18164bead09f49", "f6ec15e33bcc4a2ee4ea63e40303fb72");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "vehicles","service_records","parts","maintenance_schedules","mileage_logs","expenses");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `vehicles`");
      _db.execSQL("DELETE FROM `service_records`");
      _db.execSQL("DELETE FROM `parts`");
      _db.execSQL("DELETE FROM `maintenance_schedules`");
      _db.execSQL("DELETE FROM `mileage_logs`");
      _db.execSQL("DELETE FROM `expenses`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(VehicleDao.class, VehicleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ServiceRecordDao.class, ServiceRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PartDao.class, PartDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MaintenanceScheduleDao.class, MaintenanceScheduleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MileageLogDao.class, MileageLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public VehicleDao vehicleDao() {
    if (_vehicleDao != null) {
      return _vehicleDao;
    } else {
      synchronized(this) {
        if(_vehicleDao == null) {
          _vehicleDao = new VehicleDao_Impl(this);
        }
        return _vehicleDao;
      }
    }
  }

  @Override
  public ServiceRecordDao serviceRecordDao() {
    if (_serviceRecordDao != null) {
      return _serviceRecordDao;
    } else {
      synchronized(this) {
        if(_serviceRecordDao == null) {
          _serviceRecordDao = new ServiceRecordDao_Impl(this);
        }
        return _serviceRecordDao;
      }
    }
  }

  @Override
  public PartDao partDao() {
    if (_partDao != null) {
      return _partDao;
    } else {
      synchronized(this) {
        if(_partDao == null) {
          _partDao = new PartDao_Impl(this);
        }
        return _partDao;
      }
    }
  }

  @Override
  public MaintenanceScheduleDao maintenanceScheduleDao() {
    if (_maintenanceScheduleDao != null) {
      return _maintenanceScheduleDao;
    } else {
      synchronized(this) {
        if(_maintenanceScheduleDao == null) {
          _maintenanceScheduleDao = new MaintenanceScheduleDao_Impl(this);
        }
        return _maintenanceScheduleDao;
      }
    }
  }

  @Override
  public MileageLogDao mileageLogDao() {
    if (_mileageLogDao != null) {
      return _mileageLogDao;
    } else {
      synchronized(this) {
        if(_mileageLogDao == null) {
          _mileageLogDao = new MileageLogDao_Impl(this);
        }
        return _mileageLogDao;
      }
    }
  }

  @Override
  public ExpenseDao expenseDao() {
    if (_expenseDao != null) {
      return _expenseDao;
    } else {
      synchronized(this) {
        if(_expenseDao == null) {
          _expenseDao = new ExpenseDao_Impl(this);
        }
        return _expenseDao;
      }
    }
  }
}
