package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.ServiceRecordDao
import com.cartracker.app.data.model.ServiceRecordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRecordRepository @Inject constructor(
    private val serviceRecordDao: ServiceRecordDao
) {
    fun getServiceRecordsByVehicle(vehicleId: Long): Flow<List<ServiceRecordEntity>> =
        serviceRecordDao.getServiceRecordsByVehicle(vehicleId)

    fun getServiceRecordById(recordId: Long): Flow<ServiceRecordEntity?> =
        serviceRecordDao.getServiceRecordById(recordId)

    suspend fun getLatestServiceRecord(vehicleId: Long): ServiceRecordEntity? =
        serviceRecordDao.getLatestServiceRecord(vehicleId)

    suspend fun getTotalServiceCost(vehicleId: Long): Double =
        serviceRecordDao.getTotalServiceCost(vehicleId) ?: 0.0

    suspend fun insertServiceRecord(record: ServiceRecordEntity): Long =
        serviceRecordDao.insertServiceRecord(record)

    suspend fun updateServiceRecord(record: ServiceRecordEntity) =
        serviceRecordDao.updateServiceRecord(record)

    suspend fun deleteServiceRecord(record: ServiceRecordEntity) =
        serviceRecordDao.deleteServiceRecord(record)
}
