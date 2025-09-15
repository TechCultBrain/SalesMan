package com.techcult.salesman.feature.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.database.UserEntity
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.feature.Settings.RolePermission.domain.RolePermissionRepository
import com.techcult.salesman.feature.user.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    val userRepository: UserRepository, val rolePermissionRepository: RolePermissionRepository
) : ViewModel() {


    private val _state = MutableStateFlow(UserUiState())
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            getUserList()
        }
        viewModelScope.launch {
            rolePermissionRepository.getRoles().collect { roles ->
                _state.update {
                    it.copy(roleList = roles)
                }
            }
        }
    }


    fun onAction(action: UserActions) {
        when (action) {
            is UserActions.OnAddUserClick -> {
                _state.update {
                    it.copy(isUserAddDialogOpen = action.isUserAddDialogOpen)
                }
            }

            UserActions.OnCancelUserClick -> TODO()

            is UserActions.OnDeleteUserClick -> TODO()
            is UserActions.OnEditUserClick -> TODO()
            UserActions.OnSaveUserClick -> {

                if (validateForm(_state.value)) {
                    _state.update {
                        it.copy(isUserAddDialogOpen = false)
                    }
                    viewModelScope.launch {
                        if (_state.value.isEditMode) {
                            userRepository.updateUser(
                                UserEntity(
                                    id = _state.value.selectedUser!!.id,
                                    userName = _state.value.userName!!,
                                    email = _state.value.email!!,
                                    role = _state.value.roleId,
                                    password = _state.value.password!!,
                                )
                            )


                        } else {
                            userRepository.createUser(
                                UserEntity(
                                    userName = _state.value.userName!!,
                                    email = _state.value.email!!,
                                    role = _state.value.roleId,
                                    password = _state.value.password!!,
                                )

                            )
                            clearState()
                        }


                    }
                }

            }

            is UserActions.OnSearchTextChange -> TODO()
            is UserActions.OnUserClick -> TODO()
            is UserActions.OnUserConfirmPasswordChange -> {
                _state.update {
                    it.copy(
                        confirmPassword = action.userConfirmPassword,
                        confirmPasswordError = null
                    )
                }
            }

            is UserActions.OnUserEmailChange -> {
                _state.update {
                    it.copy(email = action.userEmail, emailError = null)
                }
            }

            is UserActions.OnUserImageChange -> TODO()
            is UserActions.OnUserNameChange -> {
                _state.update {
                    it.copy(userName = action.userName, userNameError = null)
                }
            }

            is UserActions.OnUserPasswordChange -> {
                _state.update {
                    it.copy(password = action.userPassword, passwordError = null)
                }
            }

            is UserActions.OnUserPhoneChange -> {
                _state.update {
                    it.copy(phoneNo = action.userPhone, phoneNoError = null)
                }
            }

            is UserActions.OnUserRoleClick -> {
                _state.update {
                    it.copy(
                        isRoleDropOpen = false,
                        roleId = action.roleId,
                        roleName = action.roleName,
                        roleError = null
                    )
                }
            }

            is UserActions.OnUserStatusChange -> TODO()
            is UserActions.OnRoleDropClick -> {
                _state.update {
                    it.copy(isRoleDropOpen = action.isRoleDropOpen)
                }
            }

            is UserActions.OnQueryChange -> {
                _state.update {
                    it.copy(query = action.query)
                }
                viewModelScope.launch {
                    userRepository.getUserByUserName(action.query).onSuccess { result ->
                        result.collect { list ->
                            _state.update {
                                it.copy(users = list)
                            }
                        }
                    }.onError {
                    }

                }
            }
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                userName = "",
                email = "",
                phoneNo = "",
                password = "",
                confirmPassword = "",
                userNameError = null,
                emailError = null,
                phoneNoError = null,
                passwordError = null,
                confirmPasswordError = null,
                roleName = null,
                roleId = null,
                roleError = null,
                isEditMode = false,
                selectedUser = null
            )
        }
    }

    private fun validateForm(value: UserUiState): Boolean {
        if (value.userName.isNullOrEmpty()) {
            _state.update {
                it.copy(userNameError = "Please Enter User Name")
            }
            return false

        }
        if (value.email.isNullOrEmpty()) {
            _state.update {
                it.copy(emailError = "Please Enter Email")
            }
            return false
        }
        if (value.phoneNo.isNullOrEmpty()) {
            _state.update {
                it.copy(phoneNoError = "Please Enter Phone No")
            }
            return false
        }
        if (value.roleId == null) {
            _state.update {
                it.copy(roleError = "Please Select Role")
            }
            return false
        }
        if (value.password.isNullOrEmpty()) {
            _state.update {
                it.copy(passwordError = "Please Enter Password")
            }
            return false
        }
        if (value.confirmPassword.isNullOrEmpty()) {
            _state.update {
                it.copy(confirmPasswordError = "Please Enter Confirm Password")
            }
            return false
        }
        if (value.password != value.confirmPassword) {
            _state.update {
                it.copy(confirmPasswordError = "Password and Confirm Password should be same")
            }
            return false
        } else {
            return true
        }


    }

    private fun getUserList() {
        viewModelScope.launch {
            userRepository.getUserList().onSuccess { result ->
                result.collect { list ->
                    _state.update {
                        it.copy(users = list)
                    }
                }
            }.onError {
            }


        }
    }
}


