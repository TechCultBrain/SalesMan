@file:OptIn(ExperimentalTime::class)

package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "uoms")
data class UomEntity(
    @PrimaryKey val uomId: Long? = 0,       // UUID
    val name: String,                    // e.g., Piece, Kilogram, Box
    val symbol: String,
    val description: String? = null,
    val category: String? = null,       // e.g., pcs, kg, box
    val baseUomId: Long? = null,       // link to base UOM if this is a conversion unit
    val conversionFactor: Double? = null, // factor relative to base UOM (e.g., 1 box = 12 pcs → factor = 12)
    val baseUomName: String? = null,
    val isActive: Boolean,
    val createdAt: Long? = Clock.System.now().epochSeconds,
    val updatedAt: Long? = Clock.System.now().epochSeconds,
    val syncStatus: String = "LOCAL"     // LOCAL, SYNCED, UPDATED
)