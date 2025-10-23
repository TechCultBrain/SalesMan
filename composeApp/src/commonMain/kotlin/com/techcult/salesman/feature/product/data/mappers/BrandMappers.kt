@file:OptIn(ExperimentalTime::class)

package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.BrandEntity
import com.techcult.salesman.feature.product.domain.model.Brand
import kotlin.time.ExperimentalTime


fun BrandEntity.toBrand(): Brand {
    return Brand(
        brandId = brandId,
        brandName = brandName,
        description = description.toString(),
        createdAt = createdAt,
        updatedAt = updatedAt,
        isActive = available,
        image = logo,
        brandCode = brandCode.toString(),

        )


}

fun Brand.toBrandEntity(): BrandEntity {
    return BrandEntity(
        brandId = brandId,
        brandName = brandName,
        description = description,
        updatedAt = updatedAt,
        brandCode = brandCode,
        logo = image,
        available = isActive

    )

}