package com.cartracker.app.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("vehicleId"), Index("expenseDate"), Index("category")]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val vehicleId: Long,
    val category: String, // Fuel, Insurance, Registration, Parking, Tolls, etc.
    val amount: Double,
    val expenseDate: Long,
    val vendor: String? = null,
    val notes: String? = null,
    val mileageAtExpense: Int? = null,
    val createdAt: Long = System.currentTimeMillis()
)
