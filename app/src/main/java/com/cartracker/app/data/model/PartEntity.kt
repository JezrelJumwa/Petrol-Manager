package com.cartracker.app.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "parts",
    foreignKeys = [
        ForeignKey(
            entity = ServiceRecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["serviceRecordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("serviceRecordId"), Index("partName")]
)
data class PartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val serviceRecordId: Long,
    val partName: String, // Oil Filter, Brake Pads, Air Filter, etc.
    val partNumber: String? = null,
    val brand: String? = null,
    val quantity: Int = 1,
    val cost: Double,
    val warranty: String? = null, // e.g., "1 year or 12,000 miles"
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
