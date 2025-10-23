package com.techcult.salesman.feature.product.presentation.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Filter
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.ProductionQuantityLimits
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.app.LoadingScreen
import com.techcult.salesman.core.presentation.components.CompactPageHeader
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.WidePageHeader
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = koinViewModel(),
    navigateToAddProduct: (Long?) -> Unit
) {


    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(viewModel.event)
    {


    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarHostState)
    }) {

        ProductScreenContent(state, onAction = { action ->
            when (action) {
                is ProductActions.AddClick -> {
                    navigateToAddProduct(1)
                }

                else -> Unit

            }
            viewModel.onAction(action)
        })
        FilterProductDialog(state, viewModel::onAction)
    }


}

@Composable
fun ProductScreenContent(state: ProductUiState, onAction: (ProductActions) -> Unit) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            CompactProductScreenContent(state = state, action = onAction)
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            WideProductScreenContent(state = state, onAction = onAction)

        }

        DeviceConfiguration.TABLET_PORTRAIT -> {
            CompactProductScreenContent(state = state, action = onAction)

        }

        DeviceConfiguration.TABLET_LANDSCAPE -> {
            WideProductScreenContent(state = state, onAction = onAction)
        }

        DeviceConfiguration.DESKTOP -> {
            WideProductScreenContent(state = state, onAction = onAction)
        }
    }


}

@Composable
fun WideProductScreenContent(
    state: ProductUiState = ProductUiState(),
    onAction: (ProductActions) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                WidePageHeader(
                    icon = Icons.Outlined.Inventory2,
                    title = "Product Management",
                    subtitle = "Manage your inventory products,pricing and stock levels",
                    buttonText = "Add Product",
                    onButtonClicked = {
                        onAction(ProductActions.AddClick(true))
                    },
                    isAddButton = true

                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.large))

            Surface(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f)
                ), shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MySearchBar(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.padding(LocalPadding.current.normal).fillMaxWidth()
                            .weight(1f),
                        placeholder = "Search Product"
                    )
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.FilterList, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                }

            }
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            Surface(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f)
                ), shape = MaterialTheme.shapes.medium
            ) {
                Column {
                    RetailProductHeader()
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(LocalPadding.current.tiny)
                    ) {

                        item {
                            if (state.loadingProduct) {
                                Text("Loading")
                            } else if (state.productList.isEmpty()) {
                                Text("No Product")
                            }

                        }
                        items(state.productList.size) {


                        }
                    }
                }

            }

        }

    }


}

@Composable
fun CompactProductScreenContent(
    state: ProductUiState = ProductUiState(),
    action: (ProductActions) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                CompactPageHeader(
                    icon = Icons.Outlined.Inventory2,
                    title = "Product Management",
                    buttonText = "Add",
                    onButtonClicked = {
                        action(ProductActions.AddClick(true))

                    },
                    isAddButton = true

                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))


            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                MySearchBar(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f),
                    placeholder = "Search Product"
                )
                Spacer(modifier = Modifier.width(LocalPadding.current.normal))

                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.FilterList, contentDescription = null)
                }
            }


            Spacer(modifier = Modifier.height(LocalPadding.current.large))


        }

    }


}

