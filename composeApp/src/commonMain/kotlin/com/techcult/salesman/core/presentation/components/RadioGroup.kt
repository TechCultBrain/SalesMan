package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroupOption(optionList:List<String>,onOptionSelected:(String)->Unit,selectedOption:String)
{
    optionList.forEach { text ->
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            RadioButton(
                selected =selectedOption==text ,
                onClick = { onOptionSelected(text) }
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.merge(),
                modifier = Modifier.padding(start = 16.dp)
            )

        }
    }

}