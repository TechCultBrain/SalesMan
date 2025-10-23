package com.techcult.salesman.feature.product.presentation.product

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun FilterProductDialog(state: ProductUiState, onAction: (ProductActions) -> Unit) {
    if (state.filterDialog)
    {
        Dialog(onDismissRequest = {
            onAction(ProductActions.FilterClick(false))
        })
        {
            Text("Filter")
        }
    }
}