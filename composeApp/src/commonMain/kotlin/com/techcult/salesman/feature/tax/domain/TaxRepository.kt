package com.techcult.salesman.feature.tax.domain


import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents
import kotlinx.coroutines.flow.Flow

interface TaxRepository {

    /**
     * Get all active tax slabs with their components.
     */
    fun getAllActiveTaxSlabs(): Flow<List<TaxSlabWithComponents>>

    /**
     * Get single tax slab by its ID.
     */
    suspend fun getTaxSlab(id: Long): TaxSlabWithComponents

    /**
     * Create a new tax slab with its components.
     *
     * @param slabName Name of the tax slab (e.g., "GST 18%")
     * @param components List of tax name and rate pairs (e.g., [("SGST", 9.0), ("CGST", 9.0)])
     * @param countryCode Optional (default "IN") — to support multi-country use.
     * @param applicableFor Optional (default "GST") — identifies tax system.
     */
    suspend fun createTaxSlab(
        slabName: String,
        components: List<Pair<String, Double>>,
        countryCode: String? = "IN",
        applicableFor: String? = "GST"
    ): Long

    /**
     * Update an existing tax slab with new data.
     */
    suspend fun updateTaxSlab(
        slabId: Long,
        newName: String,
        newComponents: List<Pair<String, Double>>,
        isActive: Boolean = true
    )

    /**
     * Soft delete — deactivate a tax slab.
     */
    suspend fun deactivateTaxSlab(slabId: Long)

    /**
     * Permanently delete a tax slab and its components.
     */
    suspend fun deleteTaxSlab(slabId: Long)
}
