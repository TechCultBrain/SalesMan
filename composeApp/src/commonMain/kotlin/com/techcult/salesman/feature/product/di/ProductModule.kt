package com.techcult.salesman.feature.product.di

import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.feature.product.data.repository.BrandRepositoryImpl
import com.techcult.salesman.feature.product.data.repository.CategoryRepoImpl
import com.techcult.salesman.feature.product.data.repository.ProductRepoImpl
import com.techcult.salesman.feature.product.data.repository.UomRepositoryImpl
import com.techcult.salesman.feature.product.domain.repository.BrandRepository
import com.techcult.salesman.feature.product.domain.repository.CategoryRepository
import com.techcult.salesman.feature.product.domain.repository.ProductRepository
import com.techcult.salesman.feature.product.domain.repository.UomRepository
import com.techcult.salesman.feature.product.presentation.AddProduct.ProductEditViewModel
import com.techcult.salesman.feature.product.presentation.brand.BrandViewModel
import com.techcult.salesman.feature.product.presentation.category.CategoryViewModel
import com.techcult.salesman.feature.product.presentation.product.ProductViewModel
import com.techcult.salesman.feature.product.presentation.uom.UomViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule = module {

    single {
        get<AppDatabase>().uomDao()
    }
    single {
        get<AppDatabase>().productDao()
    }
    single {
        get<AppDatabase>().categoryDao()
    }
    single {
        get<AppDatabase>().brandDao()
    }
    single {
        UomRepositoryImpl(get())
    }
        .bind<UomRepository>()
    single {
        BrandRepositoryImpl(get())
    }
        .bind<BrandRepository>()
    single {
        CategoryRepoImpl(get())
    }
        .bind<CategoryRepository>()
    single {
        ProductRepoImpl(get())
    }
        .bind<ProductRepository>()
    viewModel {
        UomViewModel(get())
    }
    viewModel {
        BrandViewModel(get())
    }
    viewModel {
        CategoryViewModel(get())
    }
    viewModel {
        ProductViewModel(get())
    }
    viewModel {
        ProductEditViewModel(get(), get(), get(), get(), get())
    }


}