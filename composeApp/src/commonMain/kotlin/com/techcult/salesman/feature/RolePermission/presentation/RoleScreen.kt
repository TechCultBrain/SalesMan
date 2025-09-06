package com.techcult.salesman.feature.RolePermission.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.data.database.RolePermissionId
import com.techcult.salesman.core.presentation.components.ButtonWithIcon
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.TextChip
import com.techcult.salesman.core.presentation.components.TextWithIcon
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun RolePermissionScreen(viewModel: RoleViewModel = koinViewModel()) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    UserManagementScreenContent(state, onAction = {

        viewModel.onAction(it)
    })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementScreenContent(
    state: RolePermissionUiState,
    onAction: (RolePermissionAction) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    var selectedTab by remember { mutableIntStateOf(0) }
    Scaffold(floatingActionButton = {
        if (deviceConfiguration == DeviceConfiguration.MOBILE_PORTRAIT) {
            FloatingActionButton(modifier = Modifier.padding(100.dp), onClick = {
                onAction(RolePermissionAction.OnAddRoleClicked(true))
            }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
            }
        }
    }) { paddingValues ->

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.small)) {

                HeaderTextWithIcon(
                    title = "Role Management",
                    icon = Icons.Outlined.Shield,
                    subtitle = "Manage Roles and Permissions",
                    buttonText = "Create Role",
                    deviceConfiguration = deviceConfiguration,
                    onButtonClicked = {
                        onAction(RolePermissionAction.OnAddRoleClicked(true))

                    }
                )
                PrimaryTabRow(selectedTabIndex = selectedTab)
                {
                    Tab(selected = selectedTab == 0, onClick = {
                        selectedTab = 0
                    }, text = { Text("Role") })
                    Tab(selected = selectedTab == 1, onClick = {
                        selectedTab = 1
                    }, text = { Text("Permission") })
                }
                Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                when (selectedTab) {
                    0 -> RoleContent(state, action = onAction)
                    1 -> PermissionContent(state)
                }
                Spacer(modifier = Modifier.height(LocalPadding.current.large))
                AddRoleDialog(state, action = onAction)
                ViewRoleDialog(state, action = onAction)


            }

        }

    }
}


@Composable
fun RoleContent(state: RolePermissionUiState, action: (RolePermissionAction) -> Unit) {

    Column {
        RoleHeader(state,action)
    }

}

@Composable
fun RoleHeader(state: RolePermissionUiState = RolePermissionUiState(),onAction: (RolePermissionAction) -> Unit) {
    Surface(
        modifier = Modifier.padding(LocalPadding.current.normal),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            Text(
                "Roles",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))

            Row(
                modifier = Modifier.height(LocalDimensions.current.viewTiny)
                    .padding(horizontal = LocalPadding.current.normal).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Role Name",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "Role Description",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "Permissions",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(0.5f)
                )
                Text(
                    "Status",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(0.3f)
                )
                Text(
                    "Action",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(0.5f)
                )


            }
            HorizontalDivider()
            RoleList(state,onAction)

        }
    }

}


@Composable
fun RoleList(state: RolePermissionUiState,onAction: (RolePermissionAction) -> Unit) {
    LazyColumn {
        if (
            state.rolePermission.isEmpty()

        ) {
            item {
                Text("No Role")
            }
        }
        items(state.rolePermission.size)
        {
            RoleDisplayItem(state.rolePermission[it],onAction = onAction)
            if (it != state.rolePermission.size - 1) {
                HorizontalDivider()
            }


        }

    }

}

@Composable
fun RoleDisplayItem(id: RolePermissionId,onAction: (RolePermissionAction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(LocalDimensions.current.viewSmall)
            .padding(horizontal = LocalPadding.current.normal).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            id.roleName.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            id.roleDescription.toString(),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
        Box(modifier = Modifier.weight(0.5f), contentAlignment = Alignment.CenterStart) {


            AssistChip(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier,
                label = {
                    Text(text = id.permissionId.size.toString() + " Permissions")
                },
                onClick = {

                })
        }


        Box(modifier = Modifier.weight(0.3f), contentAlignment = Alignment.CenterStart) {


            AssistChip(
                shape = MaterialTheme.shapes.medium,
                colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier,
                label = {
                    Text(text = "Active")
                },
                onClick = {

                })
        }

        Box(modifier = Modifier.weight(0.5f), contentAlignment = Alignment.TopStart) {

           Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp),verticalAlignment = Alignment.CenterVertically) {

              TextWithIcon(text = "View",icon = Icons.Outlined.RemoveRedEye, onClick = {
                  onAction(RolePermissionAction.OnViewRoleClicked(true,id.roleId))

              })
               TextWithIcon(text = "Edit",icon = Icons.Outlined.Edit, onClick = {
                   onAction(RolePermissionAction.OnEditRoleClicked(true,id.roleId))
               })



           }
        }

    }
}


@Composable
fun PermissionContent(state: RolePermissionUiState) {
    if (state.permissions.isEmpty()) {
        Text("No Permission")
    } else {

        val groupedPermissions = state.permissions.groupBy { it.permissionDepartment }

        LazyColumn {
            groupedPermissions.forEach { (department, permissions) ->
                stickyHeader {
                    Text(
                        text = department,
                        modifier = Modifier.padding(LocalPadding.current.tiny),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    )
                }
                items(permissions.size) { index ->
                    Surface(
                        modifier = Modifier.padding(LocalPadding.current.micro),
                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceContainer.copy(1f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(LocalPadding.current.tiny).fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                permissions[index].permissionName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface

                            )
                            Spacer(modifier = Modifier.height(LocalPadding.current.micro))

                            Text(
                                permissions[index].permissionDescription,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }

    }


}


@Composable
fun AddRoleDialog(state: RolePermissionUiState, action: (RolePermissionAction) -> Unit) {
    if (state.isRoleAddDialogVisible) {

        Dialog(onDismissRequest = {
            action(RolePermissionAction.OnAddRoleClicked(false))
        }) {
            Surface(
                modifier = Modifier.padding(LocalPadding.current.large)
                    .verticalScroll(state = rememberScrollState()),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            {
                Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Create New Role", style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = {
                            action(RolePermissionAction.OnAddRoleClicked(false))

                        }) {
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = null)

                        }
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    MyTextField(
                        value = state.roleName,
                        onValueChange = {
                            action(RolePermissionAction.OnRoleNameChanged(it))
                        },
                        label = "Role Name",
                        placeholder = "Enter Role Name",
                        isError = state.roleNameError != null,
                        supportingText = state.roleNameError,

                        modifier = Modifier.fillMaxWidth()

                    )
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(
                        value = state.roleDescription,
                        onValueChange = {
                            action(RolePermissionAction.OnRoleDescriptionChanged(it))
                        },
                        label = "Role Description",
                        isError = state.roleDescriptionError != null,
                        supportingText = state.roleDescriptionError,
                        placeholder = "Enter Role Description",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Select Permissions", style = MaterialTheme.typography.titleMedium)
                        state.permissionError?.let {
                            Text(text = it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    state.permissionChecked.forEach { permission ->
                        Surface(
                            modifier = Modifier.padding().fillMaxWidth(),
                            onClick = {
                                action(
                                    RolePermissionAction.OnPermissionSelected(
                                        permission.permissionEntity.permissionId,
                                        permission.isChecked
                                    )
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth().padding(start = 8.dp, top = 4.dp, end = 8.dp),
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Text(
                                        permission.permissionEntity.permissionName,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant

                                    )
                                    Spacer(modifier = Modifier.height(LocalPadding.current.micro))

                                    Text(
                                        permission.permissionEntity.permissionDescription,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                            alpha = 0.7f
                                        ),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Checkbox(
                                    checked = permission.isChecked,
                                    onCheckedChange = { state ->
                                        action(
                                            RolePermissionAction.OnPermissionSelected(
                                                permission.permissionEntity.permissionId,
                                                state
                                            )
                                        )
                                    },
                                    modifier = Modifier


                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
//                        ButtonWithIcon(icon = Icons.Outlined.Cancel, text = "Cancel", onClick = {
//                            action(RolePermissionAction.OnAddRoleClicked(false))
//                        },isOutlined = true)
//                        Spacer(modifier = Modifier.width(LocalPaddin
                        //                        g.current.normal))

                        ButtonWithIcon(icon = Icons.Outlined.Save, text = "Save", onClick = {
                            action(RolePermissionAction.OnSaveClicked)
                        }, modifier = Modifier.width(200.dp))

                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))

                }
            }
        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))


    }


}



@Composable
fun ViewRoleDialog(state: RolePermissionUiState, action: (RolePermissionAction) -> Unit) {
    if (state.isRoleDetailDialogVisible) {

        Dialog(onDismissRequest = {
            action(RolePermissionAction.OnViewRoleClicked(false,0))
        }) {
            Surface(
                modifier = Modifier.padding(LocalPadding.current.large)
                    .verticalScroll(state = rememberScrollState()),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            {
                Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(state.selectedRole?.roleName.toString(), style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = {
                            action(RolePermissionAction.OnViewRoleClicked(false,0))

                        }) {
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = null)

                        }
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    Text("Description")
                    Text(state.selectedRole?.roleDescription.toString(), style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Select Permissions", style = MaterialTheme.typography.titleMedium)
                        state.permissionError?.let {
                            Text(text = it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    state.permissionChecked.forEach { permission ->
                        Surface(
                            modifier = Modifier.padding().fillMaxWidth(),
                            onClick = {

                            },
                            shape = MaterialTheme.shapes.medium,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth().padding(start = 8.dp, top = 4.dp, end = 8.dp),
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Text(
                                        permission.permissionEntity.permissionName,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant

                                    )
                                    Spacer(modifier = Modifier.height(LocalPadding.current.micro))

                                    Text(
                                        permission.permissionEntity.permissionDescription,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                            alpha = 0.7f
                                        ),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Checkbox(
                                    checked =state.selectedRole?.permissionId.let { state.selectedRole?.permissionId?.contains(permission.permissionEntity.permissionId)
                                        ?: false } ,
                                    onCheckedChange = {
                                    },
                                    modifier = Modifier


                                )
                            }
                        }
                    }
                    if(state.isDeleteOptionSelected){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
//                        ButtonWithIcon(icon = Icons.Outlined.Cancel, text = "Cancel", onClick = {
//                            action(RolePermissionAction.OnAddRoleClicked(false))
//                        },isOutlined = true)
//                        Spacer(modifier = Modifier.width(LocalPaddin
                        //                        g.current.normal))

                        ButtonWithIcon(icon = Icons.Outlined.Delete, text = "Delete", onClick = {
                            action(RolePermissionAction.OnDeleteRoleClicked)
                        }, modifier = Modifier.width(200.dp))

                    }
                        }




                }
            }
        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))


    }


}



