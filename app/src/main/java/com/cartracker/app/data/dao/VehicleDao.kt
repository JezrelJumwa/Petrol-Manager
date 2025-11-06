package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Query("SELECT * FROM vehicles WHERE isActive = 1 ORDER BY updatedAt DESC")
    fun getAllActiveVehicles(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicles ORDER BY updatedAt DESC")
    fun getAllVehicles(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    fun getVehicleById(vehicleId: Long): Flow<VehicleEntity?>

    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    suspend fun getVehicleByIdSync(vehicleId: Long): VehicleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity): Long

    @Update
    suspend fun updateVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Query("UPDATE vehicles SET currentMileage = :mileage, updatedAt = :timestamp WHERE id = :vehicleId")
    suspend fun updateMileage(vehicleId: Long, mileage: Int, timestamp: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM vehicles WHERE isActive = 1")
    suspend fun getActiveVehicleCount(): Int
}
