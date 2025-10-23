package com.techcult.salesman.feature.product.presentation.product

import com.techcult.salesman.feature.product.domain.model.Category
import com.techcult.salesman.feature.product.domain.model.Product


data class ProductUiState(
    val loadingProduct: Boolean = false,
    val loadingProcess: Boolean = false,
    val searchQuery: String = "",
    val productList: List<Product> = emptyList(),
    val categoryList: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val addProductDialog: Boolean = false,
    val filterDialog: Boolean = false,
    val addImageDialog: Boolean = false,

    )

