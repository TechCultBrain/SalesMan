package com.techcult.salesman.feature.user.presentation


sealed interface UserActions {

    data class OnAddUserClick(val isAddDialogOpen: Boolean) : UserActions
    data class OnCloseAddDialog(val isAddDialogOpen: Boolean) : UserActions
    data class OnSearchTextChange(val searchText: String) : UserActions
    data class OnDeleteUserClick(val userId: Int) : UserActions
    data class OnEditUserClick(val userId: Int) : UserActions
    data class OnUserClick(val userId: Int) : UserActions
    data class OnUserNameChange(val userName: String) : UserActions
    data class OnUserEmailChange(val userEmail: String) : UserActions
    data class OnUserPhoneChange(val userPhone: String) : UserActions
    data class OnUserStatusChange(val userStatus: String) : UserActions
    data class OnUserPasswordChange(val userPassword: String) : UserActions
    data class OnUserConfirmPasswordChange(val userConfirmPassword: String) : UserActions
    data class OnUserImageChange(val userImage: String) : UserActions
    data object OnSaveUserClick : UserActions
    data object OnCancelUserClick : UserActions
    data class OnUserRoleClick(val userRole: String) : UserActions

}