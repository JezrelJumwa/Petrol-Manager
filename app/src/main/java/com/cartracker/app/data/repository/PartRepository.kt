package com.cartracker.app.data.repository

import com.cartracker.app.data.dao.PartDao
import com.cartracker.app.data.model.PartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartRepository @Inject constructor(
    private val partDao: PartDao
) {
    fun getPartsByVehicle(vehicleId: Long): Flow<List<PartEntity>> =
        partDao.getPartsByVehicle(vehicleId)

    suspend fun updatePart(part: PartEntity) =
        partDao.updatePart(part)

    suspend fun deletePart(part: PartEntity) =
        partDao.deletePart(part)
}
