package com.techcult.salesman.feature.product.domain.model

data class Uom(
    val uomId: Long?,
    val name: String,
    val symbol: String,
    val description: String?,
    val uomCategory: String,
    val baseUomId: Long?,
    val baseUomName: String?,
    val conversionFactor: Double?,
    val isActive: Boolean=true,
    val createdAt: Long?=null,
    val updatedAt: Long?=null,
)