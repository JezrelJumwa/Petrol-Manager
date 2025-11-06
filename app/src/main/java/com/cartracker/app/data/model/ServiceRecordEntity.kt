package com.cartracker.app.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "service_records",
    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("vehicleId"), Index("serviceDate")]
)
data class ServiceRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val vehicleId: Long,
    val serviceType: String, // Oil Change, Tire Rotation, Brake Service, etc.
    val serviceDate: Long,
    val mileageAtService: Int,
    val cost: Double,
    val servicedBy: String? = null, // Shop name or mechanic
    val notes: String? = null,
    val nextServiceDue: Long? = null,
    val nextServiceMileage: Int? = null,
    val createdAt: Long = System.currentTimeMillis()
)
