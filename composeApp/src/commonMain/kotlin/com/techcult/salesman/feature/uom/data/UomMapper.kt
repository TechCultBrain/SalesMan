package com.techcult.salesman.feature.uom.data

import com.techcult.salesman.feature.uom.domain.Uom

fun UomEntity.toUom(): Uom{
    return Uom(
        uomId = uomId,
        name = name,
        symbol = symbol,
        uomCategory = category.toString(),
        baseUomId = baseUomId,
        conversionFactor = conversionFactor,
        createdAt = createdAt,
        updatedAt = updatedAt
    )


}

fun Uom.toUomEntity(): UomEntity{
    return UomEntity(
        uomId = uomId,
        name = name,
        symbol = symbol,
        category = uomCategory,
        baseUomId = baseUomId,
        conversionFactor = conversionFactor,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}