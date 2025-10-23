package com.techcult.salesman.feature.Home.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techcult.salesman.core.data.AdminNavItem
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.Home.navigation.HomeGraph
import io.ktor.client.request.invoke
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    HomeScreenContent(state = state, onAction = { action ->
        when (action) {
            is HomeAction.OnMenuItemSelected -> {
                navController.navigate(action.selectedOption.route)
            }
        }

        viewModel.onAction(action)

    }, navController, logout = {
        viewModel.logout()
    })


}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Preview
@Composable
fun HomeScreenContent(
    state: HomeUiState,
    onAction: (HomeAction) -> Unit,
    navController: NavHostController,
    logout: () -> Unit = {}
) {


    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    Scaffold(
        topBar = {
            if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(

                    ),
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Welcome",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f),




                            )

                        }
                    },

                    )
            }
        }, floatingActionButton = {


        },
        bottomBar = {
            if (deviceConfiguration != DeviceConfiguration.TABLET_LANDSCAPE && deviceConfiguration != DeviceConfiguration.DESKTOP) {
                HomeBottomBar(state = state, onAction = onAction)
            }
        }) { padding ->


        when (deviceConfiguration) {

            DeviceConfiguration.TABLET_LANDSCAPE -> {
                WideHomeScreenContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.padding(top = padding.calculateTopPadding())
                )
                HomeGraph(navController, modifier = Modifier.padding(top = padding.calculateTopPadding(), start = 96.dp))

            }
            DeviceConfiguration.TABLET_PORTRAIT->
            {
                HomeGraph(
                    navController,
                    modifier = Modifier.padding(top = padding.calculateTopPadding())
                )
            }

            DeviceConfiguration.MOBILE_PORTRAIT -> {
                HomeGraph(
                    navController,
                    modifier = Modifier.padding(top = padding.calculateTopPadding())
                )

            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
              HomeGraph(
                    navController,
                    modifier = Modifier.padding(top = padding.calculateTopPadding())
              )


            }

            DeviceConfiguration.DESKTOP -> {
                WideHomeScreenContent(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.padding(top = padding.calculateTopPadding())
                )
                HomeGraph(
                    navController,
                    modifier = Modifier.padding(top = padding.calculateTopPadding(), start = 96.dp)
                )

            }

        }


    }
}


@Composable
fun WideHomeScreenContent(
    modifier: Modifier = Modifier,
    navList: List<AdminNavItem> = AdminNavItem.entries.toList(),
    state: HomeUiState,
    onAction: (HomeAction) -> Unit
) {
    HomeScreenNavigationRail(
        modifier = modifier,
        navList,
        state.selectedOption,
        onNavItemClick = {
            onAction(HomeAction.OnMenuItemSelected(it))
        }
    )
}

@Composable
fun HomeBottomBar(
    navList: List<AdminNavItem> = AdminNavItem.entries.toList(),

    state: HomeUiState,
    onAction: (HomeAction) -> Unit

) {
    NavigationBar(
    ) {
        navList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == state.selectedOption.ordinal,
                onClick = {
                    onAction(HomeAction.OnMenuItemSelected(item))
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.icon.name)
                },
                label = {
                    Text(
                        item.name,

                        )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}


@Composable
fun HomeScreenNavigationRail(
    modifier: Modifier = Modifier,
    navList: List<AdminNavItem>,
    selectedOption: AdminNavItem = AdminNavItem.HOME,
    onNavItemClick: (AdminNavItem) -> Unit = {},
) {
    NavigationRail(modifier = modifier) {
        navList.forEachIndexed { index, navItem ->
            NavigationRailItem(
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                selected = selectedOption == navItem,
                onClick = { onNavItemClick(navItem) },
                icon = { Icon(navItem.icon, contentDescription = navItem.name) },
                label = { Text(navItem.name) }
            )
        }
    }
}









