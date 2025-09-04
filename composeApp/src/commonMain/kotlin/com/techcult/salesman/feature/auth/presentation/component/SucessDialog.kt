package com.techcult.salesman.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.ok_image

@Composable
fun SuccessDialog(onDismiss: () -> Unit, isDialogOpen: Boolean, message: String) {
    if (isDialogOpen) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.padding(LocalPadding.current.large),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(LocalPadding.current.large), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter= painterResource(Res.drawable.ok_image),contentDescription = null, modifier = Modifier.size(
                        LocalDimensions.current.viewLarge
                    ))
                    Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                    Text(message, style = MaterialTheme.typography.bodyMedium)
                }

            }

        }
    }
}

@Preview
@Composable
fun SuccessDialogPreview() {
    SuccessDialog(onDismiss = {}, isDialogOpen = true, message = "Success!")
}




