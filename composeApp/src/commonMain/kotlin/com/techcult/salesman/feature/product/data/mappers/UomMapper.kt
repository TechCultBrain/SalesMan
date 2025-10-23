package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.UomEntity
import com.techcult.salesman.feature.product.domain.model.Uom

fun UomEntity.toUom(): Uom{
    return Uom(
        uomId = uomId,
        name = name,
        symbol = symbol,
        baseUomName = baseUomName,
        isActive = isActive,
        uomCategory = category.toString(),
        description = description,
        baseUomId = baseUomId,
        conversionFactor = conversionFactor,
        createdAt = createdAt,
        updatedAt = updatedAt
    )


}

fun Uom.toUomEntity(): UomEntity {
    return UomEntity(
        uomId = uomId,
        name = name,
        symbol = symbol,
        category = uomCategory,
        description = description,
        baseUomId = baseUomId,
        baseUomName = baseUomName,
        conversionFactor = conversionFactor,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isActive = isActive
    )
}