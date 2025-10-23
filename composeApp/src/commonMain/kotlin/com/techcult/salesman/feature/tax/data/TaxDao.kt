package com.techcult.salesman.feature.tax.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TaxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlabInternal(slab: TaxSlabEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComponents(components: List<TaxComponentEntity>)

    @Transaction
    suspend fun insertSlabWithComponents(
        slabName: String,
        components: List<Pair<String, Double>>, // (taxName, rate)
        countryCode: String? = "IN",
        applicableFor: String? = "GST"
    ): Long {
        // 1. Insert slab first
        val slabId = insertSlabInternal(
            TaxSlabEntity(
                slabName = slabName,
                isActive = true,
                totalRate = components.sumOf { it.second },

            )
        )

        // 2. Insert components linked to slabId
        val compEntities = components.map { (name, rate) ->
            TaxComponentEntity(
                slabId = slabId,
                taxName = name,
                rate = rate
            )
        }
        insertComponents(compEntities)

        return slabId
    }

    @Transaction
    @Query("SELECT * FROM tax_slab WHERE isActive = 1")
    fun getAllActiveSlabs(): Flow<List<TaxSlabWithComponents>>

    @Transaction
    @Query("SELECT * FROM tax_slab WHERE id = :id")
    suspend fun getSlabWithComponents(id: Long): TaxSlabWithComponents
}
