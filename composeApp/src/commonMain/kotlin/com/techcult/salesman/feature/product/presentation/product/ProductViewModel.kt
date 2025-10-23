package com.techcult.salesman.feature.product.presentation.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {


    private var _state = MutableStateFlow(ProductUiState())
    val state = _state
        .onStart {
            loadProducts()

        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ProductUiState())

    private val _event = Channel<ProductUiEvents>()
    val event = _event.receiveAsFlow()


    fun onAction(action: ProductActions) {

        when (action) {
            is ProductActions.AddClick -> {
                _state.update {
                    it.copy(addProductDialog = action.addProductDialog)
                }
            }

            is ProductActions.AddImageClick -> {
                _state.update {
                    it.copy(addImageDialog = action.addImageDialog)
                }
            }

            is ProductActions.FilterClick -> {
                _state.update {
                    it.copy(filterDialog = action.filterDialog)

                }
            }

        }

    }


    private fun loadProducts() {
        _state.value = _state.value.copy(loadingProduct = true)
        viewModelScope.launch {
            delay(2000L)
            _state.value = _state.value.copy(loadingProduct = false)
        }

    }


}