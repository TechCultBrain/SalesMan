package com.techcult.salesman.feature.user.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.data.database.UserWithRole
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.utils.DeviceConfiguration
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserManagementScreen(
    onUserAddScreen: () -> Unit = {},
    viewModel: UserViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    UserManagementScreenContent(state = state, onAction = {
        viewModel.onAction(it)


    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementScreenContent(state: UserUiState, onAction: (UserActions) -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    Scaffold(
        floatingActionButton = {
            if (deviceConfiguration == DeviceConfiguration.MOBILE_PORTRAIT) {
                FloatingActionButton(onClick = {}, modifier = Modifier.padding(bottom = 100.dp)) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add User")
                    }
                }
            }

        }) { paddingValues ->
        Surface(modifier = Modifier.padding(16.dp)) {

            Column() {
                HeaderTextWithIcon(
                    title = "User Management",
                    icon = Icons.Outlined.Person,
                    subtitle = "Create and manage user accounts and access levels",
                    onButtonClicked = {
                        onAction(UserActions.OnAddUserClick(true))

                    })
                Spacer(modifier = Modifier.height(16.dp))
                MySearchBar(modifier = Modifier, placeholder = "Search", onValueChange = {
                    onAction(UserActions.OnQueryChange(it))
                },value = state.query.toString(),)
                UserListScreen(state = state, action = onAction,deviceConfiguration)
            }
            AddUserScreen(state = state, action = onAction)

        }
    }
}

@Composable
fun UserListScreen(
    state: UserUiState,
    action: (UserActions) -> Unit,
    deviceConfiguration: DeviceConfiguration
) {
    if (state.users.isEmpty()) {
        Text(text = "No User Found")
    } else {
        UserList(state, onAction = action, deviceConfiguration = deviceConfiguration)
    }
}

@Composable
fun UserList(
    state: UserUiState,
    onAction: (UserActions) -> Unit,
    deviceConfiguration: DeviceConfiguration
) {
    LazyColumn {
        if (
            state.users.isEmpty()

        ) {
            item {
                Text("No User")
            }
        }
        items(state.users.size)
        {
            if (deviceConfiguration == DeviceConfiguration.MOBILE_PORTRAIT)
            {
                UserCard(state.users[it], onAction = onAction)

            }else {
                UserDisplayItem(state.users[it], onAction = onAction)
                if (it != state.users.size - 1) {
                    HorizontalDivider()
                }

            }


        }

    }

}

@Composable
fun UserDisplayItem(role: UserWithRole, onAction: (UserActions) -> Unit)
{
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically,) {

        Text(text = role.userName, modifier = Modifier.weight(1f))
        Text(text = role.roleName.toString(), modifier = Modifier.weight(1f))
        Text(text = "Active", modifier = Modifier.weight(0.5f))
        Text(text = "Action", modifier = Modifier.weight(0.5f))

    }

}



