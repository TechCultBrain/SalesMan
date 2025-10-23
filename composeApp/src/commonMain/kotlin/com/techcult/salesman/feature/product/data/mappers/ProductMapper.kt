package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.data.entity.ProductWithCategoryAndUom
import com.techcult.salesman.feature.product.domain.model.Product

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        productId = productId,
        productCode = productCode,
        productName = productName,
        productDescription = description,
        brandId = brandId,
        categoryId = categoryId,
        baseUomId = uomId,
    )

}

fun ProductWithCategoryAndUom.toProduct(): Product {
    return Product(
        productId = product.productId,
        productCode = product.productCode.toString(),
        productSKU = product.productCode.toString(),
        productName = product.productName,
        description = product.productDescription.toString(),
        brandId = product.brandId!!,
        categoryId = product.categoryId,
        categoryName = category.categoryName,
        uomId = product.baseUomId,
        uomCode = baseUom.name,
        createdAt = product.createdAt,
        updatedAt = product.updatedAt,
        price = 0.0,
        productStatus = ""
    )

}