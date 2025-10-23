package com.techcult.salesman.feature.product.presentation.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.techcult.salesman.core.presentation.components.DropDownMenu
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.toStringWithFixedDecimal
import com.techcult.salesman.feature.product.presentation.brand.BrandAction
import com.techcult.salesman.feature.product.presentation.uom.UomAction
import org.jetbrains.compose.resources.painterResource
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.img_placeholder

/**
 * Composable function for adding or updating a product category.
 * It displays a dialog with fields for category name, description, image, and top category.
 *
 * @param state The current state of the category screen.
 * @param onAction A callback function to handle actions performed on the screen.
 */
@Composable
fun CategoryAddScreen(state: CategoryState, onAction: (CategoryAction) -> Unit) {
    if (state.isAddCategoryDialogOpen) {
        Dialog(onDismissRequest = {
            onAction(CategoryAction.OnCategoryAddDialogToggle(false))
        }){
            Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surface) {
                Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            if (state.isEditMode) "Update product category" else "Add New Category",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = {
                            onAction(CategoryAction.OnCategoryAddDialogToggle(false))
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(LocalDimensions.current.viewLarge),
                            shape = MaterialTheme.shapes.large,

                            ) {

                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = state.catImage,
                                contentDescription = "Brand Image",
                                onError = { it ->
                                    it.result.throwable.printStackTrace()

                                },
                                contentScale = ContentScale.FillBounds,
                                placeholder = painterResource(Res.drawable.img_placeholder),
                                error = painterResource(Res.drawable.img_placeholder)


                            )

                        }
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        Button(onClick = {
                            onAction(CategoryAction.OnAddImageClick(true))

                        }) {
                            Text("Upload Image")
                        }
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(
                        value = state.catName,
                        onValueChange = {
                            onAction(CategoryAction.OnCategoryNameChange(it))
                        },
                        placeholder = ".eg.Biscuits",
                        label = "Category Name",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(
                        value = state.catDescription,
                        onValueChange = {
                            onAction(CategoryAction.OnCategoryDescriptionChange(it))
                        },
                        placeholder = ".eg.Biscuits",
                        label = "Category Description",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    DropDownMenu(
                        isExpanded = state.isTopCategoryDropDownOpen,
                        onExpandedChange = {
                            onAction(CategoryAction.OnTopCategoryDropDownToggle(it))
                        },
                        value = "None(This is Top Category)",
                        itemList = state.categoryList.map { category -> category.categoryName },
                        onItemSelected = {
                            onAction(CategoryAction.OnTopCategoryChange(it))

                        },
                        modifier = Modifier,
                        label = "Base Uom",
                        addNone = "None(This is Base Uom)"
                    )

                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(checked = state.isActive, onCheckedChange = {
                            onAction(CategoryAction.OnActiveChange(it))
                        })
                        Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                        Text(text = "Active", style = MaterialTheme.typography.bodyMedium)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =  state.catSaveError?:"",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedButton(onClick = {
                                onAction(CategoryAction.OnCategoryAddDialogToggle(false))
                            }, shape = MaterialTheme.shapes.medium) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            Button(onClick = {
                                onAction(CategoryAction.OnSaveClick)
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
