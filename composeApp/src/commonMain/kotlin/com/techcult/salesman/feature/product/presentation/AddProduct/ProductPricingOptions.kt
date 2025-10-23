package com.techcult.salesman.feature.product.presentation.AddProduct

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector

data class ProductPricingOptions(
    val title: String,
    val subTitle: String,
    val optionIcon: ImageVector,
    val isMrp: Boolean = false,
    val isProfit: Boolean = false,
    val productRate: Boolean = false,
    val isSellingPrice: Boolean = false,
    val isTax: Boolean = false,
    val costPrice: Boolean = false
)


val productPricingOptions =
    listOf(
        ProductPricingOptions(
            title = "MRP Retail (Tax Inclusive)",
            subTitle = "Cost Price + Profit + Selling Price ≤ MRP (Tax Included)",
            optionIcon = Icons.Outlined.Style,
            isMrp = true,
            isProfit = true,
            productRate = true,
            isSellingPrice = true,
            isTax = false,
            costPrice = true
        ),
        ProductPricingOptions(
            title = "MRP Wholesale (Tax Exclusive)",
            subTitle = "Cost Price + Profit + Selling Price ≤ MRP (Tax Added Separately)",
            optionIcon = Icons.Outlined.Inventory2,
            isMrp = true,
            isProfit = true,
            isSellingPrice = true,
            isTax = true,
            costPrice = true,
            productRate = true
        ),
        ProductPricingOptions(
            title = "Simple Pricing (Tax Inclusive)",
            subTitle = "Purchase Price + Profit + Tax = Selling Price (Tax Included)",
            optionIcon = Icons.Outlined.MonetizationOn,
            isMrp = true,
            isProfit = true,
            productRate = true,
            isSellingPrice = true,
            isTax = true,
            costPrice = true
        ),
        ProductPricingOptions(
            title = "Simple Pricing (Tax Exclusive)",
            subTitle = "Selling Price + Tax Added at Checkout (No MRP/Cost Price)",
            optionIcon = Icons.AutoMirrored.Outlined.TrendingUp,
            isSellingPrice = true,
            isTax = true,
            costPrice = false,
            productRate = true
        )
    )
