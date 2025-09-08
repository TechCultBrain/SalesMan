package com.techcult.salesman.feature.user.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.data.database.UserWithRole
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun UserCard(user: UserWithRole, onAction: (UserActions) -> Unit)
{
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    )
    {
        Column(modifier = Modifier.padding(LocalPadding.current.tiny)) {

            Text(text = user.userName, style = MaterialTheme.typography.titleMedium)
            Text(text = user.roleName.toString(), style = MaterialTheme.typography.bodyMedium)

        }

    }


}