package com.techcult.salesman.feature.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.feature.user.domain.GetUserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(val useCase: GetUserListUseCase) : ViewModel() {


    private val _state = MutableStateFlow(UserUiState())
    val state = _state.asStateFlow()


    init {
        getUserList()


    }

    private fun getUserList() {
        viewModelScope.launch {
            useCase.invoke()
                .onSuccess { userList ->



                }
                .onError {

                }


            }
        }
    }


