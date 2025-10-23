package com.techcult.salesman.feature.product.presentation.uom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.RoundedBorderTextSurface
import com.techcult.salesman.core.presentation.components.StatusLabel
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.core.utils.toStringWithFixedDecimal
import com.techcult.salesman.feature.product.domain.model.Uom
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UomScreen(viewModel: UomViewModel = koinViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.event) {
        when (it) {
            is UomEvents.SaveUomError -> {
                scope.launch {
                    snackBarState.showSnackbar("Uom saved Failed")
                }
            }

            UomEvents.SaveUomSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Uom saved successfully")
                }
            }

            is UomEvents.UpdateUomError -> {
                scope.launch {
                    snackBarState.showSnackbar("Uom updated Failed")
                }
            }

            UomEvents.UpdateUomSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Uom updated Successful")
                }
            }
        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarState)
    }) {
        UomScreenContent(state = state.value, onAction = viewModel::onAction)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UomScreenContent(state: UomState, onAction: (UomAction) -> Unit) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {
            HeaderTextWithIcon(
                title = "Unit Of Measure",
                subtitle = "Manage unit of measure for products",
                icon = Icons.Outlined.Inventory2,
                buttonText = "Add Unit",
                deviceConfiguration = deviceConfiguration,
                onButtonClicked = {
                    onAction(UomAction.OnAddClick)
                }, isAddButton = true

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            MySearchBar(
                value = state.searchQuery,
                onValueChange = {
                    onAction(UomAction.OnSearchQueryChange(it))
                },
                placeholder = "Search Uom",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP || deviceConfiguration == DeviceConfiguration.MOBILE_LANDSCAPE) {
                UomListContent(state = state, onAction = onAction)
            }
            else{
                UomListScreen(state = state, onAction = onAction)

            }
            UomAddScreen(state = state, onAction = onAction)


            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

    }

}

@Composable
fun UomListScreen(state: UomState, onAction: (UomAction) -> Unit) {
    LazyColumn {
        if (state.uomList.isEmpty()) {
            item {

                Box(
                    modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Uom Found",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
        items(state.uomList) {
            UomCard(uom = it, onAction = onAction)
            if (state.uomList.last() != it) {
                HorizontalDivider()
            }

        }
    }
}


@Composable
fun UomListContent(state: UomState, onAction: (UomAction) -> Unit) {
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
                    text = "Unit",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),

                    )
                Text(
                    text = "Conversion",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Status",
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
                if (state.uomList.isEmpty()) {
                    item {

                        Box(
                            modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Uom Found",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )

                        }
                    }
                }
                items(state.uomList) {
                    UomItem(uom = it, onAction = onAction)
                    if (state.uomList.last() != it) {
                        HorizontalDivider()
                    }

                }

            }

        }
    }

}

@Composable
fun UomItem(uom: Uom, onAction: (UomAction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(LocalPadding.current.tiny),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = uom.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                Text(
                    text = uom.symbol.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
                        .clip(MaterialTheme.shapes.medium).padding(4.dp)

                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.micro))
            Text(
                text = uom.description.toString(), style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)
            )
        }
        Text(
            text = uom.uomCategory,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Column(modifier = Modifier.weight(1f)) {
            if (uom.baseUomId != null) {
                Text(
                    text = uom.conversionFactor!!.toStringWithFixedDecimal(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                Text(
                    text = "Base Unit : ${uom.baseUomName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)
                )

            } else {
                RoundedBorderTextSurface("Base Unit")

            }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopStart) {
            StatusLabel(isActive = uom.isActive)
        }

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                onAction(UomAction.OnEditClick(uom.uomId!!))

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

@Composable
fun UomCard(uom: Uom, onAction: (UomAction) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(),shape = MaterialTheme.shapes.medium){
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(LocalPadding.current.tiny),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(2f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = uom.name, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                        Text(
                            text = uom.symbol.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clip(MaterialTheme.shapes.medium).background(MaterialTheme.colorScheme.surfaceContainer)
                               .padding(4.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                    Text(
                        text = uom.description.toString(), style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    if (uom.baseUomId != null) {
                        Text(
                            text = uom.conversionFactor!!.toStringWithFixedDecimal(),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                        Text(
                            text = "Base Unit : ${uom.baseUomName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)
                        )

                    } else {
                        RoundedBorderTextSurface("Base Unit")

                    }
                }


                    var  isExpanded by remember {
                        mutableStateOf(false)
                    }

                        Box(){
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
                            }){
                                DropdownMenuItem(text = {
                                    Text("Edit")
                                }, onClick = {
                                    isExpanded = false
                                    onAction(UomAction.OnEditClick(uom.uomId!!))

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


