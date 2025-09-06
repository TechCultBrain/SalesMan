package com.techcult.salesman.feature.Settings.common.data.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.components.ButtonWithIcon
import com.techcult.salesman.core.utils.DeviceConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(deviceConfiguration: DeviceConfiguration,onBackClicked:()->Unit) {


    if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP) {
        Surface(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()
                .height(72.dp)
        ) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Outlined.ChevronLeft, contentDescription = null)

                }

                Text(
                    text = "User Management",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )


                Spacer(modifier = Modifier.width(16.dp))
                ButtonWithIcon(icon = Icons.Outlined.Add, text = "Add User", onClick = {})


            }
        }


    }
    else{
        Surface(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth()
                .height(72.dp)
        ) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.ChevronLeft, contentDescription = null)
                }
                Text(
                    text = "User Management",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )

            }
        }
    }
}







