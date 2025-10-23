package com.techcult.salesman.feature.tax.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "tax_component",
    foreignKeys = [
        ForeignKey(
            entity = TaxSlabEntity::class,
            parentColumns = ["id"],
            childColumns = ["slabId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("slabId")]
)
data class TaxComponentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val slabId: Long,          // Link to tax slab
    val taxName: String,       // e.g. "SGST", "CGST", "IGST"
    val rate: Double,          // e.g. 9.0 (for 9%)
    val taxType: String? = null, // Optional (e.g. "State", "Central")
)

data class TaxSlabWithComponents(
    @Embedded val slab: TaxSlabEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "slabId"
    )
    val components: List<TaxComponentEntity>
){
    val totalRate: Double
        get() = components.sumOf { it.rate }
}