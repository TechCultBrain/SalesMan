package com.techcult.salesman.feature.product.presentation.uom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.techcult.salesman.core.presentation.components.DropDownMenu
import com.techcult.salesman.core.presentation.components.MultilineTextField
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.toStringWithFixedDecimal

@Composable
fun UomAddScreen(state: UomState, onAction: (UomAction) -> Unit) {
    if (state.isAddUomDialogOpen) {
        Dialog(onDismissRequest = { onAction(UomAction.OnAddUomDialogOpen(false)) }) {
            Surface(modifier = Modifier, shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            if (state.isEditMode) "Update Unit of Measure" else "Add New Unit of Measure",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = {
                            onAction(UomAction.OnAddUomDialogOpen(false))
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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyTextField(
                                modifier = Modifier.weight(1f),
                                value = state.uomName,
                                onValueChange = {
                                    onAction(UomAction.OnUomNameChange(it))
                                },
                                label = "Name *",
                                placeholder = "e.g.Kilogram",
                                isError = state.uomNameError != null,
                                supportingText = state.uomNameError

                            )
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            MyTextField(
                                modifier = Modifier.weight(1f),
                                value = state.uomSymbol,
                                onValueChange = {
                                    onAction(UomAction.OnUomSymbolChange(it))
                                },
                                label = "Symbol *",
                                placeholder = "e.g .Kg",
                                isError = state.uomSymbolError != null,
                                supportingText = state.uomSymbolError

                            )
                        }
                        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

                        DropDownMenu(
                            isExpanded = state.isCategoryDialogOpen,
                            onExpandedChange = {
                                onAction(UomAction.OnCategoryClick(it))
                            },
                            value = state.uomCategory,
                            label = "Category",
                            placeholder = "Select Category",
                            itemList = state.uomCategoryList,
                            onItemSelected = {

                                onAction(UomAction.OnCategoryChange(it.toString()))
                            },

                            )
                        Spacer(modifier = Modifier.height(LocalPadding.current.normal))



                        MultilineTextField(value = state.uomDescription, onValueChange = {
                            onAction(UomAction.OnUomDescriptionChange(it))

                        }, maxLines = 3, placeholder = "Description", label = "Description")
                        Spacer(modifier = Modifier.height(LocalPadding.current.normal))


                        DropDownMenu(
                            isExpanded = state.isBaseDropOpen,
                            onExpandedChange = {
                                onAction(UomAction.OnBaseDropDownOpen(it))
                            },
                            value = state.uomList.find { it.uomId == state.baseUomId }?.name
                                ?: "None(This is Base Uom)",
                            itemList = state.uomList.filter { it.baseUomId == null }.map { i ->
                                i.name
                            },
                            onItemSelected = {
                                onAction(UomAction.OnBaseUomChange(it))
                            },
                            modifier = Modifier,
                            label = "Base Uom",
                            addNone = "None(This is Base Uom)"
                        )
                        if (state.baseUomId != null) {
                            MyTextField(
                                modifier = Modifier,
                                value = state.conversionFactor.toStringWithFixedDecimal(),
                                onValueChange = {
                                    onAction(
                                        UomAction.OnConversionFactorChange(
                                            it.toDoubleOrNull() ?: 0.0
                                        )
                                    )
                                },
                                label = "Conversion Factor",
                                placeholder = "1"
                            )
                        }
                        Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Switch(checked = state.isActive, onCheckedChange = {
                                onAction(UomAction.OnStatusChange(it))
                            })
                            Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                            Text(text = "Active", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            OutlinedButton(onClick = {
                                onAction(UomAction.OnAddUomDialogOpen(false))
                            }, shape = MaterialTheme.shapes.medium) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            Button(onClick = {
                                onAction(UomAction.OnSaveClick)
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