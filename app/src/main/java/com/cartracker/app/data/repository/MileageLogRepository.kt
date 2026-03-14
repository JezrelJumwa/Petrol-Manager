package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.MileageLogDao
import com.cartracker.app.data.model.MileageLogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MileageLogRepository @Inject constructor(
    private val mileageLogDao: MileageLogDao
) {
    fun getMileageLogsByVehicle(vehicleId: Long): Flow<List<MileageLogEntity>> =
        mileageLogDao.getMileageLogsByVehicle(vehicleId)

    suspend fun insertMileageLog(log: MileageLogEntity): Long =
        mileageLogDao.insertMileageLog(log)

    suspend fun updateMileageLog(log: MileageLogEntity) =
        mileageLogDao.updateMileageLog(log)

    suspend fun deleteMileageLog(log: MileageLogEntity) =
        mileageLogDao.deleteMileageLog(log)
}
