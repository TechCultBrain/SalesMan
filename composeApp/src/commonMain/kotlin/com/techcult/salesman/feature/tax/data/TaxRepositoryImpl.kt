package com.techcult.salesman.feature.tax.data

import com.techcult.salesman.feature.tax.domain.TaxRepository

import kotlinx.coroutines.flow.Flow

class TaxRepositoryImpl(
    private val taxDao: TaxDao
) : TaxRepository {

    override fun getAllActiveTaxSlabs(): Flow<List<TaxSlabWithComponents>> =
        taxDao.getAllActiveSlabs()

    override suspend fun getTaxSlab(id: Long): TaxSlabWithComponents =
        taxDao.getSlabWithComponents(id)

    override suspend fun createTaxSlab(
        slabName: String,
        components: List<Pair<String, Double>>,
        countryCode: String?,
        applicableFor: String?
    ): Long {
        require(components.isNotEmpty()) { "At least one tax component is required" }
        require(components.sumOf { it.second } > 0) { "Total tax rate must be greater than 0" }

        return taxDao.insertSlabWithComponents(
            slabName = slabName,
            components = components,
            countryCode = countryCode,
            applicableFor = applicableFor
        )
    }

    override suspend fun updateTaxSlab(
        slabId: Long,
        newName: String,
        newComponents: List<Pair<String, Double>>,
        isActive: Boolean
    ) {
        val existing = taxDao.getSlabWithComponents(slabId)
        val updatedSlab = existing.slab.copy(slabName = newName, isActive = isActive)
        val newComponentEntities = newComponents.map { (name, rate) ->
            TaxComponentEntity(
                slabId = slabId,
                taxName = name,
                rate = rate
            )
        }

        taxDao.insertSlabInternal(updatedSlab)
        taxDao.insertComponents(newComponentEntities)
    }

    override suspend fun deactivateTaxSlab(slabId: Long) {
        val slab = taxDao.getSlabWithComponents(slabId)
        taxDao.insertSlabInternal(slab.slab.copy(isActive = false))
    }

    override suspend fun deleteTaxSlab(slabId: Long) {

    }
}
