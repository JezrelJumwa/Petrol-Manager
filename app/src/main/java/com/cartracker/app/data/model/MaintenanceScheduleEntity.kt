package com.cartracker.app.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "maintenance_schedules",
    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("vehicleId"), Index("maintenanceType")]
)
data class MaintenanceScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val vehicleId: Long,
    val maintenanceType: String,
    val intervalMiles: Int? = null, // Every X miles
    val intervalMonths: Int? = null, // Every X months
    val lastServiceDate: Long? = null,
    val lastServiceMileage: Int? = null,
    val nextDueDate: Long? = null,
    val nextDueMileage: Int? = null,
    val isActive: Boolean = true,
    val reminderEnabled: Boolean = true,
    val reminderAdvanceMiles: Int = 500, // Remind X miles before due
    val reminderAdvanceDays: Int = 7, // Remind X days before due
    val createdAt: Long = System.currentTimeMillis()
)
