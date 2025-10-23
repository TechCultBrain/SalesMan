package com.techcult.salesman.feature.product.presentation.AddProduct

import com.techcult.salesman.feature.discount.domain.Discount
import com.techcult.salesman.feature.product.domain.model.Brand
import com.techcult.salesman.feature.product.domain.model.Category
import com.techcult.salesman.feature.product.domain.model.Product
import com.techcult.salesman.feature.supplier.domain.model.Supplier
import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents

data class ProductEditState(
    val editMode: Boolean = false,
    val productId: Long? = null,
    val product: Product? = null,
    val productName: String = "",
    val productDescription: String = "",
    val productCode: String = "",
    val productBarcode: String = "",
    val categoryList: List<Category> = emptyList(),
    val isCategoryExpanded: Boolean = false,
    val selectedCategory: Category? = null,
    val tags: String = "",
    val brandList: List<Brand> = emptyList(),
    val isBrandExpanded: Boolean = false,
    val selectedBrand: Brand? = null,
    val supplierList: List<Supplier> = emptyList(),
    val isSupplierExpanded: Boolean = false,
    val selectedSupplier: Supplier? = null,
    val warrantyPeriod: String = "",
    val isCalcSellingPrice: Boolean = false,
    val isDiscountAdded: Boolean = false,
    val isTaxAdded: Boolean = false,
    val isProfitAdded: Boolean = false,
    val selectedProfitOption: String = "Percentage",
    val productMrp: String = "",
    val productCostPrice: String = "",
    val productProfitPercentage: String = "",
    val productProfit: String = "",
    val productSellingPrice: String = "",
    val taxList: List<TaxSlabWithComponents> = emptyList(),
    val isTaxExpanded: Boolean = false,
    val selectedTax: TaxSlabWithComponents? = null,
    val selectedTaxForm: String = "Inclusive",
    val selectedPriceOptions: Int = 0,
    val productRate: String = "",
    val discountList: List<Discount> = emptyList(),
    val isDiscountExpanded: Boolean = false,
    val selectedDiscount: Discount? = null,
    val isProfitExpanded: Boolean = false,

    )




