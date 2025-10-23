package com.techcult.salesman.feature.product.data.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.data.dao.CategoryDao
import com.techcult.salesman.feature.product.data.entity.CategoryEntity
import com.techcult.salesman.feature.product.data.mappers.toCategory
import com.techcult.salesman.feature.product.data.mappers.toCategoryEntity
import com.techcult.salesman.feature.product.domain.model.Category
import com.techcult.salesman.feature.product.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepoImpl(val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun searchCategories(query: String): Flow<List<Category>> {
        return categoryDao.searchCategories(query).map { categories ->
            categories.map { it.toCategory() }
        }
    }

    override suspend fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { categories ->
            categories.map { it.toCategory() }
        }
    }

    override suspend fun getCategoryById(categoryId: Long): Category? {
        return categoryDao.getCategoryById(categoryId)?.toCategory()
    }

    override suspend fun insertorUpdateCategory(category: Category): Result<Unit, DataError> {
        try {
            categoryDao.insertOrUpdate(category.toCategoryEntity())
            return Result.Success(Unit)

        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)

        }
    }
}