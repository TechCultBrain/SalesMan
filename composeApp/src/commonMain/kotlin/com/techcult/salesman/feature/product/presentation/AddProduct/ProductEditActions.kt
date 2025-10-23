package com.techcult.salesman.feature.product.presentation.AddProduct

import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents

sealed interface ProductEditActions {

    data object OnSave : ProductEditActions

    data class OnProductNameChange(val name: String) : ProductEditActions
    data class OnProductDescriptionChange(val description: String) : ProductEditActions
    data class OnProductCodeChange(val code: String) : ProductEditActions
    data class OnCategoryChange(val category: String) : ProductEditActions
    data class OnCategoryExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnProductBarcodeChange(val barcode: String) : ProductEditActions
    data class OnTagsChange(val tags: String) : ProductEditActions
    data object OnBarCodeScanClicked : ProductEditActions

    data class OnBrandChange(val brand: String) : ProductEditActions
    data class OnBrandExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnSupplierChange(val supplier: String) : ProductEditActions
    data class OnSupplierExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnWarrantyPeriodChange(val period: String) : ProductEditActions
    data class OnCalcSellingPriceChange(val calc: Boolean) : ProductEditActions
    data class OnMrpChange(val mrp: String) : ProductEditActions
    data class OnRateChange(val rate: String) : ProductEditActions
    data class OnCostPriceChange(val costPrice: String) : ProductEditActions
    data class OnProfitChange(val profit: String) : ProductEditActions
    data class OnProfitOptionChange(val option: String) : ProductEditActions
    data class OnSellingPriceChange(val sellingPrice: String) : ProductEditActions
    data class OnTaxExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnTaxChange(val tax: String) : ProductEditActions
    data class OnTaxFormChange(val form: String) : ProductEditActions

    data class OnPriceOptionSelected(val option: Int) : ProductEditActions
    data class OnDiscountExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnDiscountChange(val option: String) : ProductEditActions
    data class OnDiscountAddChange(val option: Boolean) : ProductEditActions
    data class OnProfitExpandedChange(val expanded: Boolean) : ProductEditActions
    data class OnProfitAddChange(val option: String) : ProductEditActions
    data object OnBackClicked : ProductEditActions
    data object OnCancelClicked : ProductEditActions


}
