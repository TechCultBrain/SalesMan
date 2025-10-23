package com.techcult.salesman.feature.product.presentation.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun RetailProductHeader()
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    )
    {
        Checkbox(checked = false, onCheckedChange = {})
        Spacer(modifier = Modifier.width(LocalPadding.current.large))
        Text(
            "Products",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(2f)
        )
        Text(
            "SKU",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            "Category",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            "Price",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            "Stock",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            "Status",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            "Updated",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            "Actions",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )


    }
}