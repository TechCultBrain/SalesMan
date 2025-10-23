package com.techcult.salesman.feature.product.presentation.AddProduct

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.feature.discount.domain.DiscountRepository
import com.techcult.salesman.feature.product.domain.repository.CategoryRepository
import com.techcult.salesman.feature.product.domain.repository.ProductRepository
import com.techcult.salesman.feature.product.presentation.product.ProductUiEvents
import com.techcult.salesman.feature.tax.domain.TaxRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductEditViewModel(
    val savedStateHandle: SavedStateHandle,
    val repository: ProductRepository,
    val categoryRepository: CategoryRepository,
    val taxRepository: TaxRepository,
    val discountRepository: DiscountRepository
) : ViewModel() {


    val productId: Long? = savedStateHandle["productId"]
    private val _state = MutableStateFlow(ProductEditState())
    val state = _state.onStart {
        //loadProduct(productId)
        loadCategories()
        loadTax()
        loadDiscounts()

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductEditState())

    private fun loadDiscounts() {
        viewModelScope.launch {
            discountRepository.getActiveDiscounts().collect { discounts ->
                _state.update {
                    it.copy(discountList = discounts)
                }
            }
        }
    }


    private val _event = Channel<ProductUiEvents>()
    val event = _event.receiveAsFlow()


    fun onAction(action: ProductEditActions) {
        when (action) {
            is ProductEditActions.OnProductNameChange -> {
                _state.update {
                    it.copy(productName = action.name)
                }
            }

            is ProductEditActions.OnProductCodeChange -> {
                _state.update {
                    it.copy(productCode = action.code)
                }
            }

            is ProductEditActions.OnProductDescriptionChange -> {
                _state.update {
                    it.copy(productDescription = action.description)
                }
            }

            ProductEditActions.OnSave -> {
            }

            is ProductEditActions.OnCategoryExpandedChange -> {
                _state.update {
                    it.copy(isCategoryExpanded = action.expanded)
                }
            }

            is ProductEditActions.OnCategoryChange -> {
                _state.update {
                    it.copy(selectedCategory = state.value.categoryList.find { category -> category.categoryName == action.category })
                }
            }

            is ProductEditActions.OnProductBarcodeChange -> {
                _state.update {
                    it.copy(productBarcode = action.barcode)
                }
            }

            is ProductEditActions.OnTagsChange -> {
                _state.update {
                    it.copy(tags = action.tags)
                }

            }

            is ProductEditActions.OnBrandExpandedChange -> {
                _state.update {
                    it.copy(isBrandExpanded = action.expanded)
                }

            }

            is ProductEditActions.OnBrandChange -> {
                _state.update {
                    it.copy(
                        selectedBrand = state.value.brandList.find { brand -> brand.brandName == action.brand })
                }

            }

            is ProductEditActions.OnSupplierExpandedChange -> {
                _state.update {
                    it.copy(isSupplierExpanded = action.expanded)
                }


            }

            is ProductEditActions.OnSupplierChange -> {
                _state.update {
                    it.copy(
                        selectedSupplier = state.value.supplierList.find { supplier -> supplier.supplierName == action.supplier })
                }


            }

            is ProductEditActions.OnWarrantyPeriodChange -> {
                _state.update {
                    it.copy(warrantyPeriod = action.period)
                }


            }

            is ProductEditActions.OnCalcSellingPriceChange -> {
                _state.update {
                    it.copy(
                        isCalcSellingPrice = action.calc,

                        )
                }


            }

            is ProductEditActions.OnMrpChange -> {

                _state.update {
                    it.copy(productMrp = action.mrp)
                }


            }

            is ProductEditActions.OnCostPriceChange -> {

                    _state.update {
                        it.copy(
                            productCostPrice = action.costPrice,

                        )



            }

         
            }

            is ProductEditActions.OnProfitChange -> {

                var productRate = 0.0
                var profit = 0.0
                if (state.value.selectedProfitOption == "Percentage") {

                    profit =
                        (action.profit.toDouble() / 100) * state.value.productCostPrice.toDouble()
                    productRate = profit + state.value.productCostPrice.toDouble()

                } else {
                    if (action.profit.isEmpty()) return
                    profit = action.profit.toDouble()
                    productRate = profit + state.value.productCostPrice.toDouble()


                }
                _state.update {
                    it.copy(
                        productProfit = action.profit,
                        productProfitPercentage = action.profit,
                        productRate = productRate.toString(),
                        productSellingPrice = productRate.toString()
                    )
                }

            }

            is ProductEditActions.OnSellingPriceChange -> {
                _state.update {
                    it.copy(productSellingPrice = action.sellingPrice)
                }
            }

            is ProductEditActions.OnTaxExpandedChange -> {
                _state.update {
                    it.copy(isTaxExpanded = action.expanded)
                }
            }

            is ProductEditActions.OnTaxChange -> {
                _state.update {
                    it.copy(selectedTax = state.value.taxList.find { tax -> tax.slab.slabName == action.tax })
                }

            }

            is ProductEditActions.OnTaxFormChange -> {
                _state.update {
                    it.copy(selectedTaxForm = action.form)
                }


            }

            is ProductEditActions.OnPriceOptionSelected -> {
                _state.update {
                    it.copy(selectedPriceOptions = action.option)
                }
            }

            is ProductEditActions.OnRateChange -> {
                if (action.rate.isEmpty()) {
                    _state.update {
                        it.copy(productRate = action.rate, productSellingPrice = action.rate)
                    }
                    return
                } else {
                    if (state.value.productCostPrice.isNotEmpty()) {
                        val productRate = action.rate.toDouble()
                        val profit = productRate - state.value.productCostPrice.toDouble()
                        val profitPercentage =
                            (profit / state.value.productCostPrice.toDouble()) * 100
                        _state.update {
                            it.copy(
                                productRate = action.rate,
                                productProfit = profit.toString(),
                                productProfitPercentage = profitPercentage.toString(),
                                productSellingPrice = action.rate
                            )
                        }
                    }
                }

            }


            is ProductEditActions.OnDiscountExpandedChange -> {
                _state.update {
                    it.copy(isDiscountExpanded = action.expanded)
                }

            }

            is ProductEditActions.OnDiscountChange -> {
                _state.update {
                    it.copy(selectedDiscount = state.value.discountList.find { discount -> discount.name == action.option })
                }

            }

            is ProductEditActions.OnDiscountAddChange -> {
                _state.update {
                    it.copy(isDiscountAdded = action.option)
                }


            }

            is ProductEditActions.OnProfitExpandedChange -> {
                _state.update {
                    it.copy(isProfitExpanded = action.expanded)
                }

            }

            is ProductEditActions.OnProfitAddChange -> {


                _state.update {
                    it.copy(
                        selectedProfitOption = action.option,

                        )
                }

            }


            else -> {}
        }

    }

    private fun loadCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categories ->
                _state.update {
                    it.copy(categoryList = categories)
                }

            }

        }

    }

    private fun loadTax() {
        viewModelScope.launch {
            taxRepository.getAllActiveTaxSlabs().collect { taxList ->
                _state.update {
                    it.copy(taxList = taxList)

                }
            }
        }


    }
}