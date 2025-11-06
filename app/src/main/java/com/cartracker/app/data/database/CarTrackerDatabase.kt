package com.cartracker.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cartracker.app.data.dao.*
import com.cartracker.app.data.model.*

@Database(
    entities = [
        VehicleEntity::class,
        ServiceRecordEntity::class,
        PartEntity::class,
        MaintenanceScheduleEntity::class,
        MileageLogEntity::class,
        ExpenseEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CarTrackerDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun serviceRecordDao(): ServiceRecordDao
    abstract fun partDao(): PartDao
    abstract fun maintenanceScheduleDao(): MaintenanceScheduleDao
    abstract fun mileageLogDao(): MileageLogDao
    abstract fun expenseDao(): ExpenseDao
}
