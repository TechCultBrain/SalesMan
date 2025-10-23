package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MySearchBar(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search",
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState()

   BasicTextField(
       modifier = modifier,
       value = value,
       onValueChange = onValueChange,
       decorationBox = { innerTextField ->
           Surface(
               modifier = Modifier.height(48.dp).fillMaxWidth(),
               shape = MaterialTheme.shapes.medium,
               border = BorderStroke(
                   width = 1.dp,
                   color = if (focused.value) {
                       MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f)
                   } else {
                       Color.Transparent
                   }
               ),
               color = MaterialTheme.colorScheme.surfaceContainer
           ) {
               Box(
                   contentAlignment = Alignment.CenterStart,
                   modifier = Modifier
                       .fillMaxSize()

               ) {
                   Row(
                       modifier = Modifier.padding(horizontal = 8.dp),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Icon(
                           imageVector = Icons.Outlined.Search,
                           contentDescription = null,
                           tint = MaterialTheme.colorScheme.onSurfaceVariant
                       )
                       Surface(
                           modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                           color = Color.Transparent
                       ) {
                           Box(
                               modifier = Modifier.padding(vertical = 4.dp).fillMaxSize(),
                               contentAlignment = Alignment.CenterStart
                           ) {

                               innerTextField()

                               if (value.isEmpty()) {

                                   Text(
                                       text = placeholder,
                                       style = MaterialTheme.typography.bodyMedium,
                                       color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                           0.5f
                                       )
                                   )


                               }

                           }

                       }


                   }
               }
           }
       })

       }













