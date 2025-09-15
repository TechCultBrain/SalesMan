package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.BrandEntity
import com.techcult.salesman.feature.product.domain.Brand


fun BrandEntity.toBrand(): Brand {
    return Brand(
        brandId = brandId,
        brandName = brandName,
        description = description.toString(),
        createdAt = createdAt,
        updatedAt = updatedAt,
        active = true
    )


}

fun Brand.toBrandEntity(): BrandEntity {
    return BrandEntity(
        brandId = brandId,
        brandName = brandName,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

}