package com.techcult.salesman.feature.auth.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun TermsDialog(onDismiss:()->Unit,isTermsDialogOpen:Boolean)
{
    if(isTermsDialogOpen) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.large), shape = MaterialTheme.shapes.medium) {


            }

        }
    }
}