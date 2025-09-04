package com.techcult.salesman.feature.Home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()


    fun onAction(action: HomeAction) {

        when (action) {
            is HomeAction.OnMenuItemSelected -> {
                _state.update {
                    it.copy(selectedOption = action.selectedOption)
                }
            }


        }
    }


}