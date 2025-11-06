package com.cartracker.app.data.dao

import androidx.room.*
import com.cartracker.app.data.model.PartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartDao {
    @Query("SELECT * FROM parts WHERE serviceRecordId = :serviceRecordId ORDER BY createdAt DESC")
    fun getPartsByServiceRecord(serviceRecordId: Long): Flow<List<PartEntity>>

    @Query("""
        SELECT p.* FROM parts p
        INNER JOIN service_records sr ON p.serviceRecordId = sr.id
        WHERE sr.vehicleId = :vehicleId
        ORDER BY sr.serviceDate DESC, p.createdAt DESC
    """)
    fun getPartsByVehicle(vehicleId: Long): Flow<List<PartEntity>>

    @Query("""
        SELECT p.* FROM parts p
        INNER JOIN service_records sr ON p.serviceRecordId = sr.id
        WHERE sr.vehicleId = :vehicleId AND p.partName LIKE '%' || :partName || '%'
        ORDER BY sr.serviceDate DESC
    """)
    fun searchPartsByName(vehicleId: Long, partName: String): Flow<List<PartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPart(part: PartEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParts(parts: List<PartEntity>)

    @Update
    suspend fun updatePart(part: PartEntity)

    @Delete
    suspend fun deletePart(part: PartEntity)
}
