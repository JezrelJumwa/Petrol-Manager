package com.cartracker.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val make: String,
    val model: String,
    val year: Int,
    val licensePlate: String,
    val vin: String? = null,
    val currentMileage: Int,
    val purchaseDate: Long? = null,
    val notes: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
