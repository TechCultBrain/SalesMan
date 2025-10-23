package com.techcult.salesman.feature.product.presentation.AddProduct

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyRupee
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.ButtonWithIcon
import com.techcult.salesman.core.presentation.components.DropDownMenu
import com.techcult.salesman.core.presentation.components.MultilineTextField
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.RadioGroupOption
import com.techcult.salesman.core.presentation.components.SubHeaderWithIcon
import com.techcult.salesman.core.presentation.components.SwitchWithText
import com.techcult.salesman.core.presentation.components.WidePageHeader
import com.techcult.salesman.core.presentation.theme.LocalPadding
import io.ktor.client.request.invoke
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddProductScreen(viewModel: ProductEditViewModel = koinViewModel(), navigateBack: () -> Unit) {

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(viewModel.event) {

    }
    AddProductScreenContent(state, onAction = { action ->
        when (action) {
            is ProductEditActions.OnBarCodeScanClicked -> {

            }

            is ProductEditActions.OnBackClicked -> {
                navigateBack()
            }

            is ProductEditActions.OnCancelClicked -> {
                navigateBack()
            }

            else -> Unit

        }
        viewModel.onAction(action)
    })


}

@Composable
fun AddProductScreenContent(state: ProductEditState, onAction: (ProductEditActions) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {

        WidePageHeader(
            title = if (state.editMode) "Edit Product" else "Add Product",
            isBackButton = true,
            isAddButton = true,
            buttonText = "Save",
            onBackClicked = {
                onAction(ProductEditActions.OnBackClicked)

            },
            onButtonClicked = {
                onAction(ProductEditActions.OnSave)
            },
            onCancelButtonClicked = {
                onAction(ProductEditActions.OnCancelClicked)
            },
            buttonIcon = Icons.Outlined.Save
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)
            ) {
                BasicInformationSection(state, onAction)
                RetailInformationSection(state, onAction)
                // TextileSpecificationsSection(state, onAction)
                PricingAndProfitSection(state, onAction)
                StockManagementSection(state, onAction)
                ProductVariantsSection(state, onAction)
                AdditionalInformationSection(state, onAction)
            }
        }
    }
}

@Composable
private fun BasicInformationSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Color(0xFF000000).copy(0.1f))

    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            // Title
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Basic Information"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            // Product Name & SKU
            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = state.productName,
                    onValueChange = {
                        onAction(ProductEditActions.OnProductNameChange(it))

                    },
                    label = "Product Name *",
                    placeholder = "Enter product name"
                )
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = state.productCode,
                    onValueChange = {
                        onAction(ProductEditActions.OnProductCodeChange(it))

                    },
                    label = "SKU / Product Code *",
                    placeholder = "e.g., PROD-001"
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            // Barcode & Category
            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = state.productBarcode,
                    onValueChange = {
                        onAction(ProductEditActions.OnProductBarcodeChange(it))

                    },
                    label = "Barcode / QR Code",
                    placeholder = "Scan or enter barcode",
                    trailingIcon = Icons.Outlined.QrCodeScanner,
                    onTrailingIconClick = {
                        onAction(ProductEditActions.OnBarCodeScanClicked)
                    }
                )
                DropDownMenu(
                    isExpanded = state.isCategoryExpanded,
                    modifier = Modifier.weight(1f),
                    label = "Category *",
                    placeholder = "Select category",
                    itemList = state.categoryList.map { it.categoryName },
                    value = state.selectedCategory?.categoryName ?: "",
                    onExpandedChange = { onAction(ProductEditActions.OnCategoryExpandedChange(it)) },
                    onItemSelected = { onAction(ProductEditActions.OnCategoryChange(it)) }
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            // Description
            MultilineTextField(
                value = state.productDescription,
                onValueChange = {
                    onAction(ProductEditActions.OnProductDescriptionChange(it))

                },
                label = "Description",
                placeholder = "Enter product description, features, specifications...",
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            // Tags
            MyTextField(
                value = state.tags,
                onValueChange = {
                    onAction(ProductEditActions.OnTagsChange(it))

                },
                label = "Tags",
                placeholder = "e.g., organic, premium, bestseller (comma-separated)",
                modifier = Modifier.fillMaxWidth()
            )
            // Helper text for tags
            Text(
                text = "Use tags to help categorize and search for products",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(
                    start = LocalPadding.current.small,
                    top = LocalPadding.current.tiny
                )
            )
        }
    }
}

@Composable
private fun RetailInformationSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.Store,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Retail Information"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                DropDownMenu(
                    isExpanded = state.isBrandExpanded,
                    modifier = Modifier.weight(1f),
                    label = "Brand *",
                    placeholder = "Select brand",
                    itemList = state.brandList.map { it.brandName },
                    value = state.selectedBrand?.brandName ?: "",
                    onExpandedChange = { onAction(ProductEditActions.OnBrandExpandedChange(it)) },
                    onItemSelected = { onAction(ProductEditActions.OnBrandChange(it)) }
                )
                DropDownMenu(
                    isExpanded = state.isSupplierExpanded,
                    modifier = Modifier.weight(1f),
                    label = "Supplier *",
                    placeholder = "Select supplier",
                    itemList = state.supplierList.map { it.supplierName },
                    value = state.selectedSupplier?.supplierName ?: "",
                    onExpandedChange = { onAction(ProductEditActions.OnSupplierExpandedChange(it)) },
                    onItemSelected = { onAction(ProductEditActions.OnSupplierChange(it)) }
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            MyTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.warrantyPeriod,
                onValueChange = {
                    onAction(ProductEditActions.OnWarrantyPeriodChange(it))
                },
                label = "Warranty Period",
                placeholder = "e.g., 1 year, 6 months"
            )
        }
    }
}

@Composable
private fun TextileSpecificationsSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.Style,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Textile Specifications"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Fabric Type",
                    placeholder = "e.g., Cotton, Polyester, Silk"
                )
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Color",
                    placeholder = "e.g., Navy Blue, Red"
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Size / Dimensions",
                    placeholder = "e.g., 2m x 3m, XL"
                )
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "GSM / Thread Count",
                    placeholder = "e.g., 200 GSM"
                )
            }
        }
    }
}


@Composable
private fun PricingAndProfitSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.MonetizationOn,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Pricing & Profit"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            PricingModuleUI(selectedOptionIndex = state.selectedPriceOptions, onOptionSelected = {
                onAction(ProductEditActions.OnPriceOptionSelected(it))

            })
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SwitchWithText(
                    checked = state.isCalcSellingPrice, onCheckedChange = {
                        onAction(ProductEditActions.OnCalcSellingPriceChange(it))
                    },
                    text = "Calculate Selling Price from profit"

                )
                SwitchWithText(
                    checked = state.isDiscountAdded, onCheckedChange = {
                        onAction(ProductEditActions.OnDiscountAddChange(it))    },
                    text = "Add Discount"
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            val selectedPricingOptions = productPricingOptions[state.selectedPriceOptions]
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 2,

                horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
            ) {

                AnimatedVisibility(
                    selectedPricingOptions.costPrice,
                    modifier = Modifier.weight(1f)
                ) {

                    MyTextField(
                        leadingIcon = Icons.Outlined.CurrencyRupee,
                        modifier = Modifier.weight(0.5f),
                        value = state.productCostPrice.toString(),
                        onValueChange = { newValue ->
                            val regex = Regex("^\\d*\\.?\\d{0,2}$")
                            if (newValue.matches(regex) || newValue.isEmpty()) {
                                // Dispatch action to update the state in the ViewModel
                                onAction(ProductEditActions.OnCostPriceChange(newValue))

                                // Dispatch action to update the state in the ViewModel
                            }
                        },
                        label = "Cost Price",
                        placeholder = "0.00 ",
                    )
                }

                AnimatedVisibility(visible = state.isCalcSellingPrice) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DropDownMenu(
                            placeholder = "Select Profit",
                            onExpandedChange = {
                                onAction(ProductEditActions.OnProfitExpandedChange(it))

                            },
                            itemList = listOf("Percentage", "Fixed"),
                            isExpanded =state.isProfitExpanded,
                            modifier = Modifier.weight(1f),
                            label = "Profit Type",
                            value = state.selectedProfitOption,
                            onItemSelected = {
                                onAction(ProductEditActions.OnProfitAddChange(it))

                            }
                        )
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        MyTextField(
                            modifier = Modifier.weight(1f),
                            value = if (state.selectedProfitOption == "Percentage") state.productProfitPercentage else state.productProfit,
                            onValueChange = {
                                onAction(ProductEditActions.OnProfitChange(it))

                            },
                            label = if (state.selectedProfitOption == "Percentage") "Profit %" else "Profit",
                            placeholder = "0.00",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                            leadingIcon = if (state.selectedProfitOption == "Percentage") Icons.Outlined.Percent else Icons.Outlined.CurrencyRupee
                        )
                    }
                }
                AnimatedVisibility(
                    selectedPricingOptions.isMrp,
                    modifier = Modifier.weight(1f)
                ) {
                    MyTextField(
                        modifier = Modifier.weight(0.5f),
                        value = state.productMrp.toString(),
                        onValueChange = { newValue ->
                            val regex = Regex("^\\d*\\.?\\d{0,2}$")
                            if (newValue.matches(regex) || newValue.isEmpty()) {
                                // Dispatch action to update the state in the ViewModel
                                onAction(ProductEditActions.OnMrpChange(newValue))
                            }

                        },
                        label = "M.R.P *",
                        placeholder = "0.00 ",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = Icons.Outlined.CurrencyRupee
                    )
                }
                AnimatedVisibility(
                    selectedPricingOptions.productRate,
                    modifier = Modifier.weight(1f)
                ) {
                    MyTextField(

                        value = state.productRate.toString(),
                        onValueChange = { newValue ->
                            val regex = Regex("^\\d*\\.?\\d{0,2}$")
                            if (newValue.matches(regex) || newValue.isEmpty()) {
                                // Dispatch action to update the state in the ViewModel
                                onAction(ProductEditActions.OnRateChange(newValue))
                            }

                        },
                        label = "Rate *",
                        placeholder = "0.00 ",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = Icons.Outlined.CurrencyRupee
                    )
                }



                AnimatedVisibility(visible = state.isDiscountAdded) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DropDownMenu(
                            placeholder = "Select Discount",
                            onExpandedChange = {
                                onAction(ProductEditActions.OnDiscountExpandedChange(it))

                            },
                            isExpanded = state.isDiscountExpanded,
                            modifier = Modifier.weight(1f),
                            label = "Disc Rate *",
                            itemList = state.discountList.map { it ->
                                it.name

                            },
                            value = state.selectedDiscount?.name ?: "",
                            onItemSelected = {
                                onAction(ProductEditActions.OnDiscountChange(it))

                            },

                            )
                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        MyTextField(
                            modifier = Modifier.weight(1f),
                            value = "",
                            onValueChange = { newValue ->
                            },
                            label = "Discount",
                            placeholder = "0.00",
                        )
                    }
                }
                AnimatedVisibility(selectedPricingOptions.isTax) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)
                    ) {

                        DropDownMenu(
                            placeholder = "Select Tax",
                            onExpandedChange = {
                                onAction(ProductEditActions.OnTaxExpandedChange(it))

                            },
                            isExpanded = state.isTaxExpanded,
                            modifier = Modifier.weight(1f),
                            label = "Tax Rate *",
                            itemList = state.taxList.map { it ->
                                it.slab.slabName

                            },
                            value = state.selectedTax?.slab?.slabName ?: "",
                            onItemSelected = {
                                onAction(ProductEditActions.OnTaxChange(it))

                            },

                            )
                        MyTextField(
                            modifier = Modifier.weight(1f),
                            value = "",
                            onValueChange = { newValue ->
                            },
                            label = "Tax Amount",
                            placeholder = "0.00",
                        )
                    }
                }
                AnimatedVisibility(
                    selectedPricingOptions.isSellingPrice,
                    modifier = Modifier.weight(1f)
                ) {
                    MyTextField(
                        modifier = Modifier.weight(1f),
                        value = state.productSellingPrice,
                        onValueChange = { newValue ->
                            val regex = Regex("^\\d*\\.?\\d{0,2}$")
                            if (newValue.matches(regex) || newValue.isEmpty()) {
                                // Dispatch action to update the state in the ViewModel
                                onAction(ProductEditActions.OnSellingPriceChange(newValue))
                            }

                        },
                        label = if (state.isCalcSellingPrice) "Auto Selling Price" else "Selling Price *",
                        placeholder = "0.00",
                        trailingIcon = Icons.AutoMirrored.Outlined.TrendingUp,
                        leadingIcon = Icons.Outlined.CurrencyRupee,
                        readOnly = state.isCalcSellingPrice
                    )
                }


            }

        }
    }
}

@Composable
private fun StockManagementSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.Inventory2,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Stock Management"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Current Stock *",
                    placeholder = "0",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                DropDownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Unit *",
                    itemList = listOf(), // Should be populated with units
                    value = ""
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Min Stock Alert *",
                    placeholder = "0",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Max Stock Level",
                    placeholder = "Optional",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            ) {
                Row(
                    modifier = Modifier.padding(LocalPadding.current.normal),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Stock Alert Settings",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(LocalPadding.current.small))
                    Column {
                        Text(
                            text = "Stock Alert Settings",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "You'll receive notifications when stock falls below the minimum level. Set max stock for reorder quantity calculations.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductVariantsSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubHeaderWithIcon(
                    icon = {
                        Icon(
                            Icons.Outlined.Category,
                            contentDescription = null
                        )
                    }, // Or some other icon
                    headerText = "Product Variants / Multi-Unit Packaging"
                )

            }
            Text(
                text = "Sell the same product in different units or packaging (e.g., box, piece, pack)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))

            // Empty state for variants
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(LocalPadding.current.large),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Inbox,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text("No variants added", style = MaterialTheme.typography.titleMedium)
                Text(
                    "Add variants to sell this product in different units or packaging",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                ButtonWithIcon(
                    text = "Add First Variant",
                    onClick = { /* TODO */ },
                    icon = Icons.Outlined.Add
                )
            }
        }
    }
}

@Composable
private fun AdditionalInformationSection(
    state: ProductEditState,
    onAction: (ProductEditActions) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
            SubHeaderWithIcon(
                icon = {
                    Icon(
                        Icons.Outlined.Description,
                        contentDescription = null
                    )
                }, // Or some other icon
                headerText = "Additional Information"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                DropDownMenu(
                    modifier = Modifier.weight(1f),
                    label = "Status",
                    itemList = listOf("Active", "Inactive"),
                    value = ""
                )
                MyTextField(
                    modifier = Modifier.weight(1f),
                    value = "",
                    onValueChange = { },
                    label = "Supplier / Vendor",
                    placeholder = "Enter supplier name"
                )
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.large))

            // Product Images
            Text("Product Images", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                        ),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(
                        vertical = LocalPadding.current.extraLarge,
                        horizontal = LocalPadding.current.normal
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)
            ) {
                Icon(
                    imageVector = Icons.Outlined.UploadFile,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text("Drag and drop images here")
                Text("or")
                OutlinedButton(onClick = { /* TODO: Browse files */ }) {
                    Text("Browse Files")
                }
                Text(
                    "PNG, JPG up to 5MB each",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


@Composable
fun PricingModule(modifier: Modifier = Modifier, optionTitle: String) {
    Surface(modifier = modifier) {
        Column {
            Row {
                Text(optionTitle)
            }

        }
    }

}
