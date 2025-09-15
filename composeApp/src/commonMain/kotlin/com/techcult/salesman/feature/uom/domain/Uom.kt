package com.techcult.salesman.feature.uom.domain

data class Uom(
    val uomId: Long?,
    val name: String,
    val symbol: String,
    val uomCategory: String,
    val baseUomId: Long?,
    val conversionFactor: Double?,
    val isActive: Boolean=true,
    val createdAt: Long?=null,
    val updatedAt: Long?=null,
)