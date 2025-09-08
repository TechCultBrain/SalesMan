package com.techcult.salesman.feature.user.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.techcult.salesman.core.presentation.components.ButtonWithIcon
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.theme.LocalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(state: UserUiState, action: (UserActions) -> Unit) {

    if (state.isUserAddDialogOpen) {
        Dialog(onDismissRequest = {
            action(UserActions.OnAddUserClick(false))
        })
        {
            Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
                Column(
                    modifier = Modifier.padding(LocalPadding.current.normal),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (!state.isEditMode) "Create NewUser" else "Edit User",
                            style = MaterialTheme.typography.titleMedium
                        )

                        IconButton(onClick = { action(UserActions.OnAddUserClick(false)) })
                        {
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        value = state.userName.toString(),
                        onValueChange = {
                            action(UserActions.OnUserNameChange(it))
                        },
                        label = "Full Name *",
                        placeholder = "Enter Full Name",
                        isError = state.userNameError != null,
                        supportingText = state.userNameError
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        value = state.email.toString(),
                        onValueChange = {
                            action(UserActions.OnUserEmailChange(it))
                        },
                        label = "Email *",
                        placeholder = "Enter Email",
                        isError = state.emailError != null,
                        supportingText = state.emailError,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        value = state.phoneNo.toString(),
                        onValueChange = {
                            action(UserActions.OnUserPhoneChange(it))
                        },
                        label = "Phone No *",
                        placeholder = "Enter Phone No",
                        isError = state.phoneNoError != null,
                        supportingText = state.phoneNoError,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        )

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(expanded = false, onExpandedChange = {}) {
                        MyTextField(
                            isError = state.roleError != null,
                            supportingText = state.roleError,
                            readOnly = true,
                            value = if (state.roleName == null) "Select Role" else state.roleName.toString(),
                            onValueChange = {},
                            label = "Role *",
                            placeholder = "Select Role",
                            onTrailingIconClick = {
                                if (state.isRoleDropOpen) {
                                    action(UserActions.OnRoleDropClick(false))
                                } else {
                                    action(UserActions.OnRoleDropClick(true))
                                }

                            },
                            trailingIcon = Icons.Outlined.ArrowDropDown
                        )
                        ExposedDropdownMenu(expanded = state.isRoleDropOpen, onDismissRequest = {
                            action(UserActions.OnRoleDropClick(false))
                        })
                        {
                            state.roleList.forEach { role ->
                                Text(
                                    text = role.roleName.toString(),
                                    modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                                        action(
                                            UserActions.OnUserRoleClick(
                                                role.roleId,
                                                role.roleName.toString()
                                            )
                                        )
                                    })
                            }
                        }

                    }





                    Spacer(modifier = Modifier.height(16.dp))

                    MyTextField(
                        value = state.password.toString(),
                        onValueChange = {
                            action(UserActions.OnUserPasswordChange(it))
                        },
                        label = "Password *",
                        placeholder = "Enter Password",
                        isError = state.passwordError != null,
                        supportingText = state.passwordError,
                        isPassword = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    MyTextField(
                        value = state.confirmPassword.toString(),
                        onValueChange = {
                            action(UserActions.OnUserConfirmPasswordChange(it))
                        },
                        label = "Confirm Password *",
                        placeholder = "Enter Confirm Password",
                        isError = state.confirmPasswordError != null,
                        supportingText = state.confirmPasswordError,
                        isPassword = true,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ButtonWithIcon(
                            isOutlined = true,
                            icon = Icons.Outlined.Close,
                            text = "Cancel",
                            onClick = { action(UserActions.OnAddUserClick(false)) })
                        Spacer(modifier = Modifier.width(16.dp))
                        ButtonWithIcon(icon = Icons.Outlined.Save, text = "Save", onClick = {
                            action(UserActions.OnSaveUserClick)
                        })

                    }

                }

            }


        }
    }
}

