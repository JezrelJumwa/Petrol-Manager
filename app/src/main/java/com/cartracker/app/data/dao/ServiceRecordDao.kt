package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.ServiceRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceRecordDao {
    @Query("SELECT * FROM service_records WHERE vehicleId = :vehicleId ORDER BY serviceDate DESC")
    fun getServiceRecordsByVehicle(vehicleId: Long): Flow<List<ServiceRecordEntity>>

    @Query("SELECT * FROM service_records WHERE id = :recordId")
    fun getServiceRecordById(recordId: Long): Flow<ServiceRecordEntity?>

    @Query("SELECT * FROM service_records WHERE vehicleId = :vehicleId ORDER BY serviceDate DESC LIMIT 1")
    suspend fun getLatestServiceRecord(vehicleId: Long): ServiceRecordEntity?

    @Query("""
        SELECT * FROM service_records 
        WHERE vehicleId = :vehicleId 
        AND serviceType = :serviceType 
        ORDER BY serviceDate DESC 
        LIMIT 1
    """)
    suspend fun getLatestServiceByType(vehicleId: Long, serviceType: String): ServiceRecordEntity?

    @Query("SELECT SUM(cost) FROM service_records WHERE vehicleId = :vehicleId")
    suspend fun getTotalServiceCost(vehicleId: Long): Double?

    @Query("""
        SELECT SUM(cost) FROM service_records 
        WHERE vehicleId = :vehicleId 
        AND serviceDate BETWEEN :startDate AND :endDate
    """)
    suspend fun getServiceCostInDateRange(vehicleId: Long, startDate: Long, endDate: Long): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceRecord(record: ServiceRecordEntity): Long

    @Update
    suspend fun updateServiceRecord(record: ServiceRecordEntity)

    @Delete
    suspend fun deleteServiceRecord(record: ServiceRecordEntity)

    @Query("DELETE FROM service_records WHERE vehicleId = :vehicleId")
    suspend fun deleteAllServiceRecordsForVehicle(vehicleId: Long)
}
