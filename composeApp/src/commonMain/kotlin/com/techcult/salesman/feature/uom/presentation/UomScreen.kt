package com.techcult.salesman.feature.uom.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.DropDownMenu
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MultilineTextField
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UomScreen(viewModel: UomViewModel = koinViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.event) {
        when (it) {
            is UomEvents.SaveUomError -> {

            }

            UomEvents.SaveUomSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Uom saved successfully")
                }

            }
            is UomEvents.UpdateUomError -> TODO()
            UomEvents.UpdateUomSuccess -> TODO()
        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarState)
    }){
        UomScreenContent(state = state.value, onAction = viewModel::onAction)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UomScreenContent(state: UomState, onAction: (UomAction) -> Unit) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {
            HeaderTextWithIcon(
                title = "Unit Of Measure",
                subtitle = "Manage unit of measure for products",
                icon = Icons.Outlined.Inventory2,
                buttonText = "Add Unit",
                deviceConfiguration = deviceConfiguration,
                onButtonClicked = {
                    onAction(UomAction.OnAddClick)
                }, isAddButton = true

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            MySearchBar(
                value = "",
                onValueChange = {},
                labelText = "",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            LazyColumn(){
               if (state.uomList.isEmpty()){
                   item {
                       Text(text = "No Uom found")
                   }
               }
                items(state.uomList){uom->
                    Row(modifier = Modifier.fillMaxWidth().clickable {
                        onAction(UomAction.OnEditClick(uom.uomId!!))
                    }){
                        Text(uom.name)
                    }
                }

            }

            UomAddScreen(state = state, onAction = onAction)


            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

    }

}


