package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.MaintenanceScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MaintenanceScheduleDao {
    @Query("SELECT * FROM maintenance_schedules WHERE vehicleId = :vehicleId AND isActive = 1 ORDER BY maintenanceType")
    fun getActiveSchedulesByVehicle(vehicleId: Long): Flow<List<MaintenanceScheduleEntity>>

    @Query("SELECT * FROM maintenance_schedules WHERE vehicleId = :vehicleId ORDER BY maintenanceType")
    fun getAllSchedulesByVehicle(vehicleId: Long): Flow<List<MaintenanceScheduleEntity>>

    @Query("SELECT * FROM maintenance_schedules WHERE id = :scheduleId")
    fun getScheduleById(scheduleId: Long): Flow<MaintenanceScheduleEntity?>

    @Query("""
        SELECT * FROM maintenance_schedules 
        WHERE vehicleId = :vehicleId 
        AND isActive = 1 
        AND reminderEnabled = 1
        AND (
            (nextDueMileage IS NOT NULL AND nextDueMileage <= :currentMileage + reminderAdvanceMiles)
            OR (nextDueDate IS NOT NULL AND nextDueDate <= :currentDate + (reminderAdvanceDays * 86400000))
        )
        ORDER BY COALESCE(nextDueDate, 9999999999999), COALESCE(nextDueMileage, 999999999)
    """)
    fun getUpcomingMaintenance(vehicleId: Long, currentMileage: Int, currentDate: Long): Flow<List<MaintenanceScheduleEntity>>

    @Query("SELECT * FROM maintenance_schedules WHERE isActive = 1 AND reminderEnabled = 1")
    suspend fun getAllActiveSchedulesWithReminders(): List<MaintenanceScheduleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: MaintenanceScheduleEntity): Long

    @Update
    suspend fun updateSchedule(schedule: MaintenanceScheduleEntity)

    @Delete
    suspend fun deleteSchedule(schedule: MaintenanceScheduleEntity)

    @Query("DELETE FROM maintenance_schedules WHERE vehicleId = :vehicleId")
    suspend fun deleteAllSchedulesForVehicle(vehicleId: Long)
}
