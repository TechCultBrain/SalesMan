package com.techcult.salesman.feature.product.presentation.brand

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.StatusLabel
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.core.utils.FilePicker
import com.techcult.salesman.feature.product.domain.model.Brand

import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.img_placeholder

@Composable
fun BrandScreen(viewModel: BrandViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.event) {
        when (it) {
            is BrandEvent.SaveBrandError -> {
                scope.launch {
                    snackBarState.showSnackbar("R saved Failed")
                }
            }

            BrandEvent.SaveBrandSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Brand saved successfully")
                }
            }

            is BrandEvent.UpdateBrandError -> {
                scope.launch {
                    snackBarState.showSnackbar("Brand updated Failed")
                }
            }

            BrandEvent.UpdateBrandSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Brand updated Successful")
                }
            }
        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarState)
    }) {
        BrandScreenContent(state = state.value, onAction = viewModel::onAction)
        if (state.value.isAddImageDialogOpen) {
            FilePicker { uri ->
                viewModel.onAction(BrandAction.OnBrandImageChange(uri.toString()))

            }

        }
    }


}

@Composable
fun BrandScreenContent(state: BrandState, onAction: (BrandAction) -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {
            HeaderTextWithIcon(
                title = "Brand",
                subtitle = "Manage product brand",
                icon = Icons.Outlined.Inventory2,
                buttonText = "Add Brand",
                deviceConfiguration = deviceConfiguration,
                onButtonClicked = {
                    onAction(BrandAction.OnAddClick)
                }, isAddButton = true

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            MySearchBar(
                value = state.searchQuery,
                onValueChange = {
                    onAction(BrandAction.OnSearchQueryChange(it))
                },
                placeholder = "Search Brand",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP || deviceConfiguration == DeviceConfiguration.MOBILE_LANDSCAPE) {
                BrandListContent(state = state, onAction = onAction)
            } else {
                BrandListScreen(state = state, onAction = onAction)

            }
            BrandAddScreen(state = state, onAction = onAction)


            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

    }

}


@Composable
fun BrandListScreen(state: BrandState, onAction: (BrandAction) -> Unit) {
    LazyColumn {
        if (state.brandList.isEmpty()) {
            item {

                Box(
                    modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Brand Found",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
        items(state.brandList) {
            BrandCard(brand = it, onEditClick = {
                onAction(BrandAction.OnEditClick(it.brandId!!))
            })
            if (state.brandList.last() != it) {
                HorizontalDivider()
            }

        }
    }
}

@Composable
fun BrandCard(brand: Brand, onEditClick: () -> Unit) {

    Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(LocalPadding.current.tiny),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(2f)
                ) {
                    AsyncImage(
                        model = brand.image,
                        modifier = Modifier.clip(MaterialTheme.shapes.medium).width(40.dp)
                            .height(40.dp),
                        contentDescription = "Brand Image",
                        onError = {
                            it.result.throwable.printStackTrace()
                        },
                        contentScale = ContentScale.FillBounds,
                        error = painterResource(Res.drawable.img_placeholder),
                        placeholder = painterResource(Res.drawable.img_placeholder)
                    )
                    Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                    Column {
                        Text(text = brand.brandName, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                        Text(
                            text = brand.description.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }

                Spacer(modifier = Modifier.height(LocalPadding.current.micro))


                var isExpanded by remember {
                    mutableStateOf(false)
                }

                Box() {
                    IconButton(onClick = {
                        isExpanded = !isExpanded

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                    DropdownMenu(expanded = isExpanded, onDismissRequest = {
                        isExpanded = false
                    }) {
                        DropdownMenuItem(text = {
                            Text("Edit")
                        }, onClick = {
                            isExpanded = false
                            onEditClick()

                        })
                    }




                    Spacer(modifier = Modifier.width(LocalPadding.current.large))
                    /* IconButton(onClick = {
                         onAction(UomAction.OnDeleteClick(uom.uomId!!))

                     }) {
                         Icon(
                             imageVector = Icons.Outlined.Delete,
                             contentDescription = "Delete",
                             tint = MaterialTheme.colorScheme.outlineVariant
                         )

                     }*/

                }


            }

        }
    }
}



@Composable
fun BrandListContent(state: BrandState, onAction: (BrandAction) -> Unit) {
    Surface(
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    horizontal = LocalPadding.current.tiny,
                    vertical = LocalPadding.current.normal
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "Code",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),

                    )

                Text(
                    text = "Status",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "UpdatedOn",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)

                )

                Text(
                    text = "Actions",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )


            }
            HorizontalDivider()
            LazyColumn {
                if (state.brandList.isEmpty()) {
                    item {

                        Box(
                            modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Brand Found",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }
                items(state.brandList) {
                    BrandItem(brand = it, onEditClick = {
                        onAction(BrandAction.OnEditClick(it.brandId!!))
                    })
                    if (state.brandList.last() != it) {
                        HorizontalDivider()
                    }

                }

            }

        }
    }
}

@Composable
fun BrandItem(brand: Brand, onEditClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            vertical = LocalPadding.current.tiny,
            horizontal = LocalPadding.current.normal
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(2f)) {
            AsyncImage(
                model = brand.image,
                modifier = Modifier.clip(MaterialTheme.shapes.medium).width(40.dp).height(40.dp),
                contentDescription = "Brand Image",
                onError = {
                    it.result.throwable.printStackTrace()
                },
                contentScale = ContentScale.FillBounds,
                error = painterResource(Res.drawable.img_placeholder),
                placeholder = painterResource(Res.drawable.img_placeholder)


            )
            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
            Text(text = brand.brandName, style = MaterialTheme.typography.bodyMedium)

        }


        Text(
            text = brand.brandCode,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = brand.description.toString(), style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
            modifier = Modifier.weight(1f)
        )


        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopStart) {
            StatusLabel(isActive = brand.isActive)
        }
        Text(
            text = if (brand.updatedAt == null) "N/A" else brand.updatedAt.date.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)

        )

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                onEditClick()

            }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.outlineVariant
                )

            }
            Text(text = "Edit", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.width(LocalPadding.current.large))
            /* IconButton(onClick = {
                 onAction(UomAction.OnDeleteClick(uom.uomId!!))

             }) {
                 Icon(
                     imageVector = Icons.Outlined.Delete,
                     contentDescription = "Delete",
                     tint = MaterialTheme.colorScheme.outlineVariant
                 )

             }*/

        }

    }

}