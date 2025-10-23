package com.techcult.salesman.feature.tax.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Entity(tableName = "tax_slab")
data class TaxSlabEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val slabName: String, // e.g. "GST 18%"
    val totalRate: Double, // e.g. 18.0
    val isActive: Boolean = true,
    val createdAT: Long= Clock.System.now().epochSeconds,
    val updatedAT: Long= Clock.System.now().epochSeconds,
)


