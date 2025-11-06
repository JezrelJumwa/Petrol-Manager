package com.cartracker.app.di

import android.content.Context
import androidx.room.Room
import com.cartracker.app.data.dao.*
import com.cartracker.app.data.database.CarTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CarTrackerDatabase {
        return Room.databaseBuilder(
            context,
            CarTrackerDatabase::class.java,
            "car_tracker_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideVehicleDao(database: CarTrackerDatabase): VehicleDao {
        return database.vehicleDao()
    }

    @Provides
    fun provideServiceRecordDao(database: CarTrackerDatabase): ServiceRecordDao {
        return database.serviceRecordDao()
    }

    @Provides
    fun providePartDao(database: CarTrackerDatabase): PartDao {
        return database.partDao()
    }

    @Provides
    fun provideMaintenanceScheduleDao(database: CarTrackerDatabase): MaintenanceScheduleDao {
        return database.maintenanceScheduleDao()
    }

    @Provides
    fun provideMileageLogDao(database: CarTrackerDatabase): MileageLogDao {
        return database.mileageLogDao()
    }

    @Provides
    fun provideExpenseDao(database: CarTrackerDatabase): ExpenseDao {
        return database.expenseDao()
    }
}
