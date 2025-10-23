package com.techcult.salesman.feature.product.presentation.brand

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
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import org.jetbrains.compose.resources.painterResource
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.img_placeholder

@Composable
fun BrandAddScreen(state: BrandState, onAction: (BrandAction) -> Unit) {
    if (state.isAddBrandDialogOpen) {
        Dialog(onDismissRequest = {
            onAction(BrandAction.OnBrandAddDialogToggle(false))
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
                            if (state.isEditMode) "Update brand" else "Add New Brand",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = {
                            onAction(BrandAction.OnBrandAddDialogToggle(false))
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
                                model = state.brandImage,
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
                            onAction(BrandAction.OnAddImageClick(true))

                        }) {
                            Text("Upload Image")
                        }

                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(value = state.brandName, onValueChange = {
                        onAction(BrandAction.OnBrandNameChange(it))

                    }, label = "Brand Name*", placeholder = "e.g.Maruti Suzuki")
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    MyTextField(value = state.brandDescription, onValueChange = {
                        onAction(BrandAction.OnBrandDescriptionChange(it))

                    }, label = "Brand Description", placeholder = "e.g.Maruti Suzuki")
                    Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(checked = state.isActive, onCheckedChange = {
                            onAction(BrandAction.OnStatusChange(it))
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
                            text =  state.brandSaveError?:"",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedButton(onClick = {
                                onAction(BrandAction.OnBrandAddDialogToggle(false))
                            }, shape = MaterialTheme.shapes.medium) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                            Button(onClick = {
                                onAction(BrandAction.OnSaveClick)
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

