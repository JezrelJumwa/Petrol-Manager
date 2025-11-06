package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.MaintenanceScheduleDao
import com.cartracker.app.data.model.MaintenanceScheduleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MaintenanceScheduleRepository @Inject constructor(
    private val maintenanceScheduleDao: MaintenanceScheduleDao
) {
    fun getActiveSchedulesByVehicle(vehicleId: Long): Flow<List<MaintenanceScheduleEntity>> =
        maintenanceScheduleDao.getActiveSchedulesByVehicle(vehicleId)

    fun getAllSchedulesByVehicle(vehicleId: Long): Flow<List<MaintenanceScheduleEntity>> =
        maintenanceScheduleDao.getAllSchedulesByVehicle(vehicleId)

    fun getScheduleById(scheduleId: Long): Flow<MaintenanceScheduleEntity?> =
        maintenanceScheduleDao.getScheduleById(scheduleId)

    fun getUpcomingMaintenance(vehicleId: Long, currentMileage: Int, currentDate: Long): Flow<List<MaintenanceScheduleEntity>> =
        maintenanceScheduleDao.getUpcomingMaintenance(vehicleId, currentMileage, currentDate)

    suspend fun insertSchedule(schedule: MaintenanceScheduleEntity): Long =
        maintenanceScheduleDao.insertSchedule(schedule)

    suspend fun updateSchedule(schedule: MaintenanceScheduleEntity) =
        maintenanceScheduleDao.updateSchedule(schedule)

    suspend fun deleteSchedule(schedule: MaintenanceScheduleEntity) =
        maintenanceScheduleDao.deleteSchedule(schedule)
}
