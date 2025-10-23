package com.techcult.salesman.feature.discount.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DiscountScreen(viewModel: DiscountViewModel = koinViewModel()) {

    Text("Discount Screen")

}