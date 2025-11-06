package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.VehicleDao
import com.cartracker.app.data.model.VehicleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val vehicleDao: VehicleDao
) {
    fun getAllActiveVehicles(): Flow<List<VehicleEntity>> = vehicleDao.getAllActiveVehicles()

    fun getVehicleById(vehicleId: Long): Flow<VehicleEntity?> = vehicleDao.getVehicleById(vehicleId)

    suspend fun insertVehicle(vehicle: VehicleEntity): Long = vehicleDao.insertVehicle(vehicle)

    suspend fun updateVehicle(vehicle: VehicleEntity) = vehicleDao.updateVehicle(vehicle)

    suspend fun deleteVehicle(vehicle: VehicleEntity) = vehicleDao.deleteVehicle(vehicle)

    suspend fun updateMileage(vehicleId: Long, mileage: Int) = vehicleDao.updateMileage(vehicleId, mileage)
}
