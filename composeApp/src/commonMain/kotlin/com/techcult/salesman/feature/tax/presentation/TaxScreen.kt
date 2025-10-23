package com.techcult.salesman.feature.tax.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TaxScreen(viewModel: TaxViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    TaxContentScreen(state, onAction = viewModel::onAction)

}

@Composable
private fun TaxContentScreen(
    state: TaxUiState,
    onAction: (TaxUiAction) -> Unit = {}
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {
            HeaderTextWithIcon(
                title = "Tax Settings",
                subtitle = "Manage Tax slabs",
                icon = Icons.Outlined.MonetizationOn,
                buttonText = "Add Tax",
                deviceConfiguration = deviceConfiguration,
                onButtonClicked = {
                    onAction(TaxUiAction.OnTaxAddDialogOpen(true))

                }, isAddButton = true

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            MySearchBar(
                value = state.searchQuery,
                onValueChange = {

                },
                placeholder = "Search Tax",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TaxListContent(state, onAction)
            TaxAddScreen(state, onAction)


        }

    }


}

@Composable
fun TaxAddScreen(state: TaxUiState, onAction: (TaxUiAction) -> Unit) {

    if (state.isAddDialogOpen) {
        Dialog(onDismissRequest = {
            onAction(TaxUiAction.OnTaxAddDialogOpen(false))
        }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            if (state.isEditMode) "Update TaxSlab" else "Add New TaxSlab",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = {
                            onAction(TaxUiAction.OnTaxAddDialogOpen(false))
                        })
                        {
                            Icon(
                                Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(
                        value = state.taxSlabName,
                        onValueChange = {
                            onAction(TaxUiAction.OnTaxNameChange(it))

                        },
                        placeholder = ".eg.5 %GST",
                        label = "Tax Name"
                    )
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Text(text = "Tax Components")
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    state.taxComponents.forEachIndexed { index, taxComponent ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            MyTextField(
                                modifier = Modifier.weight(1f),
                                value = taxComponent.taxName,
                                onValueChange = { new ->
                                    onAction(TaxUiAction.OnTaxComponentNameChange(new, index))
                                },
                                placeholder = "Gst"
                            )
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            MyTextField(
                                modifier = Modifier.weight(1f),
                                value = taxComponent.taxValue.toString(),
                                onValueChange = { new ->
                                    if (new.isEmpty() || new.matches(Regex("^\\d*\\.?\\d*\$"))) {
                                        onAction(TaxUiAction.OnTaxComponentValueChange(new, index))
                                    }

                                },
                                placeholder = "5%"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Button(onClick = {
                        onAction(TaxUiAction.OnTaxAddClick)
                    })
                    {
                        Text("Add")
                    }

                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.taxSaveError ?: "",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedButton(onClick = {
                                onAction(TaxUiAction.OnTaxAddDialogOpen(false))
                            }, shape = MaterialTheme.shapes.medium) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            Button(onClick = {
                                onAction(TaxUiAction.OnSaveClick)
                            }, shape = MaterialTheme.shapes.medium) {
                                Text(text = if (state.isEditMode) "Update" else "Save")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaxListScreen(state: TaxUiState, onAction: (TaxUiAction) -> Unit) {
}

@Composable
fun TaxListContent(
    state: TaxUiState,
    onAction: (TaxUiAction) -> Unit,
) {
    if (state.taxSlabs.isEmpty()) {
        Text("No Tax Slabs")

    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.taxSlabs.size) { index ->
                val taxSlab = state.taxSlabs[index]
                TaxListItem(taxSlab = taxSlab)
            }
        }
    }

}

@Composable
fun TaxListItem(taxSlab: TaxSlabWithComponents) {
    Text(text = taxSlab.slab.slabName)
}






