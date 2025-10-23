package com.techcult.salesman.feature.product.domain.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun searchCategories(query: String): Flow<List<Category>>
    suspend fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(categoryId: Long): Category?
    suspend fun insertorUpdateCategory(category: Category): Result<Unit, DataError>


}