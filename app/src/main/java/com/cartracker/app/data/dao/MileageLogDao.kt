package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.MileageLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MileageLogDao {
    @Query("SELECT * FROM mileage_logs WHERE vehicleId = :vehicleId ORDER BY logDate DESC")
    fun getMileageLogsByVehicle(vehicleId: Long): Flow<List<MileageLogEntity>>

    @Query("SELECT * FROM mileage_logs WHERE vehicleId = :vehicleId ORDER BY logDate DESC LIMIT 1")
    suspend fun getLatestMileageLog(vehicleId: Long): MileageLogEntity?

    @Query("""
        SELECT * FROM mileage_logs 
        WHERE vehicleId = :vehicleId 
        AND logDate BETWEEN :startDate AND :endDate 
        ORDER BY logDate ASC
    """)
    fun getMileageLogsInDateRange(vehicleId: Long, startDate: Long, endDate: Long): Flow<List<MileageLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMileageLog(log: MileageLogEntity): Long

    @Update
    suspend fun updateMileageLog(log: MileageLogEntity)

    @Delete
    suspend fun deleteMileageLog(log: MileageLogEntity)
}
