package com.techcult.salesman.feature.product.data.mappers

import com.techcult.salesman.feature.product.data.entity.CategoryEntity
import com.techcult.salesman.feature.product.domain.model.Category

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        categoryId = categoryId,
        categoryName = categoryName,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt,
        available = active,
        categoryImage = categoryImage,
        parentCategoryId = topCategoryId


    )

}


fun CategoryEntity.toCategory(): Category {
    return Category(
        categoryId = categoryId,
        categoryName = categoryName,
        description = description.toString(),
        createdAt = createdAt,
        updatedAt = updatedAt,
        active = available,
        categoryDescription = description,
        categoryImage = categoryImage,
        topCategoryId = parentCategoryId

    )
}