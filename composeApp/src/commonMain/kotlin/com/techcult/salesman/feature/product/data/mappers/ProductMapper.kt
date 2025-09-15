package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.domain.Product

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        productId = productId,
        productCode = productCode,
        productName = productName,
        description = description,
        productType = productType,
        productStatus = productStatus,
        brandId = brandId,
        categoryId = category,
        baseUomId = uom,
    )

}