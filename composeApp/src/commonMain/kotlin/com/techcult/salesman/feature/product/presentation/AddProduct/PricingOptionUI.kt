package com.techcult.salesman.feature.product.presentation.AddProduct

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun PricingModuleUI(modifier: Modifier = Modifier,selectedOptionIndex:Int,onOptionSelected:(Int)->Unit) {


    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pricing Module",
                style = MaterialTheme.typography.titleMedium,
            )
            Text("TaxInclusive")
        }

        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

        // Create a 2x2 grid
        Column(verticalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                PricingOptionCard(
                    modifier = Modifier.weight(1f),
                    option = productPricingOptions[0],
                    isSelected = selectedOptionIndex == 0,
                    onClick ={
                        onOptionSelected(0)
                    }
                )
                PricingOptionCard(
                    modifier = Modifier.weight(1f),
                    option = productPricingOptions[1],
                    isSelected = selectedOptionIndex == 1,
                    onClick = {  onOptionSelected(1) }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)) {
                PricingOptionCard(
                    modifier = Modifier.weight(1f),
                    option = productPricingOptions[2],
                    isSelected = selectedOptionIndex == 2,
                    onClick = { onOptionSelected(2) }
                )
                PricingOptionCard(
                    modifier = Modifier.weight(1f),
                    option = productPricingOptions[3],
                    isSelected = selectedOptionIndex == 3,
                    onClick = {  onOptionSelected(3)}
                )
            }
        }
    }
}

@Composable
private fun PricingOptionCard(
    modifier: Modifier = Modifier,
    option: ProductPricingOptions,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val cardColors = if (isSelected) {
        CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    } else {
        CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    }

    val border = if (isSelected) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    } else {
        BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    }

    Card(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = cardColors,
        border = border
    ) {
        Row(
            modifier = Modifier.padding(LocalPadding.current.normal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = MaterialTheme.shapes.medium,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            ) {
                Icon(
                    imageVector = option.optionIcon,
                    contentDescription = option.title,
                    modifier = Modifier.padding(LocalPadding.current.small),
                    tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(LocalPadding.current.normal))

            Column {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = option.subTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = MaterialTheme.typography.bodySmall.fontSize * 1.2
                )
            }
        }
    }
}