package com.techcult.salesman.feature.user.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.Settings.common.data.presentation.component.MyAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserManagementScreen(
    onBackClicked: () -> Unit = {},
    viewModel: UserViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    UserManagementScreenContent(onBackClicked)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementScreenContent(onBackClicked: () -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    Scaffold(
        topBar = {
            MyAppBar(deviceConfiguration, onBackClicked = onBackClicked)


        },
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
        if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP) {
            UserWideScreen(
                modifier = Modifier.padding(
                    vertical = paddingValues.calculateTopPadding(),
                    horizontal = 16.dp
                )
            )
        } else {
            UserCompactScreen(
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCompactScreen(modifier: Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 90.dp)) {
                MySearchBar(
                    labelText = "Search",
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )

            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserWideScreen(modifier: Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var searchText by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.surfaceContainer.copy(1f)
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            MySearchBar(labelText = "Search", value = searchText, onValueChange = {
                searchText = it
            }, modifier = Modifier)
            Spacer(modifier = Modifier.height(16.dp))


            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(
                    1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = "User Management", modifier = Modifier.padding(16.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "User Management", modifier = Modifier.padding(16.dp))
                }


            }

        }


    }
}
