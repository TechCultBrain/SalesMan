package com.techcult.salesman.feature.RolePermission.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.data.database.RolePermissionId
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.feature.RolePermission.presentation.RolePermissionAction

@Composable
fun RoleCard(id: RolePermissionId, onAction: (RolePermissionAction) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.tiny)) {
            Text(text = id.roleName.toString(), style = MaterialTheme.typography.titleMedium)
            Text(text = id.roleDescription.toString(), style = MaterialTheme.typography.bodyMedium)
            AssistChip(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier,
                label = {
                    Text(text = id.permissionId.size.toString() + " Permissions")
                },
                onClick = {

                })
            AssistChip(
                shape = MaterialTheme.shapes.medium,
                colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier,
                label = {
                    Text(text = "Active")
                },
                onClick = {

                })


        }
    }

}