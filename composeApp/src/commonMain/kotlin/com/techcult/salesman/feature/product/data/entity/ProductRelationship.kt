package com.techcult.salesman.feature.product.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.techcult.salesman.feature.uom.data.UomEntity


data class ProductWithCategoryAndUom(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val category: CategoryEntity,
    @Relation(
        parentColumn = "baseUomId",
        entityColumn = "uomId"
    )
    val baseUom: UomEntity
)

data class ProductWithVariantsAndUom(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "productId"
    )
    val variants: List<ProductVariantEntity>,
)