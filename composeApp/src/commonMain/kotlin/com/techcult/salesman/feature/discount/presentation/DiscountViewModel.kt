package com.techcult.salesman.feature.discount.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.feature.discount.data.DiscountScope
import com.techcult.salesman.feature.discount.data.DiscountType
import com.techcult.salesman.feature.discount.domain.Discount
import com.techcult.salesman.feature.discount.domain.DiscountRepository
import kotlinx.coroutines.launch

class DiscountViewModel(val repository: DiscountRepository) : ViewModel() {


    init {
        viewModelScope.launch {
            repository.addDiscount(
                Discount(
                    id = null,
                    name = "Festival Discount",
                    type = DiscountType.PERCENTAGE,
                    value = 5.0,
                    scope = DiscountScope.PRODUCT,
                    startDate = null,
                    endDate = null,
                    isActive = true

                )
            )
        }
    }

}