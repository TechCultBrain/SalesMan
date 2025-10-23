package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    isExpanded: Boolean=false,
    onExpandedChange: (Boolean) -> Unit={},
    value: String,
    label: String,
    itemList: List<String>,
    onItemSelected: (String) -> Unit={},
    modifier: Modifier = Modifier,
    addNone: String? = null,
    placeholder: String? = null,
    readOnly: Boolean = true
) {

    ExposedDropdownMenuBox(modifier = modifier, expanded = isExpanded, onExpandedChange = {
        onExpandedChange(it)
    }) {
        MyOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly,
            value = value,
            onValueChange = {},
            label = label,
            trailingIcon =

                    Icons.Outlined.ArrowDropDown
            ,
            placeholder = placeholder,
            onTrailingIconClick = {
                onExpandedChange(true)
            }

           )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {
            onExpandedChange(false)
        }) {
            val mutableList: MutableList<String> = itemList.toMutableList()
            if (addNone != null) {
                mutableList.add(0, addNone)
            }

            mutableList.forEach {

                Text(
                    text = it,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp).clickable() {
                            onItemSelected(it)
                            onExpandedChange(false)
                        }
                )
            }
        }

    }
}
