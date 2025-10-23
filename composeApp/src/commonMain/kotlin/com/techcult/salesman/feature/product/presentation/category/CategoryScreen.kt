package com.techcult.salesman.feature.product.presentation.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.ListItemWithIcon
import com.techcult.salesman.core.presentation.components.MySearchBar
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.StatusLabel
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.core.utils.FilePicker
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryScreen(viewModel: CategoryViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.event) {
        when (it) {
            is CategoryEvent.SaveCategoryError -> {
                scope.launch {
                    snackBarState.showSnackbar("R saved Failed")
                }
            }

            CategoryEvent.SaveCategorySuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Category saved successfully")
                }
            }

            is CategoryEvent.UpdateCategoryError -> {
                scope.launch {
                    snackBarState.showSnackbar("Category updated Failed")
                }
            }

            CategoryEvent.UpdateCategorySuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Category updated Successful")
                }
            }
        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackBarState)
    }) {
        CategoryScreenContent(state = state.value, onAction = viewModel::onAction)
        if (state.value.isAddImageDialogOpen) {
            FilePicker {
                if (it != null) {
                    viewModel.onAction(CategoryAction.OnCategoryImageChange(it))
                }
            }
        }
    }

}

@Composable
fun CategoryScreenContent(state: CategoryState, onAction: (CategoryAction) -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)) {
            HeaderTextWithIcon(
                title = "Category",
                subtitle = "Manage product Category",
                icon = Icons.Outlined.Inventory2,
                buttonText = "Add Category",
                deviceConfiguration = deviceConfiguration,
                onButtonClicked = {
                    onAction(CategoryAction.OnAddClick)
                }, isAddButton = true

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
            MySearchBar(
                value = state.searchQuery,
                onValueChange = {
                    onAction(CategoryAction.OnSearchQueryChange(it))
                },
                placeholder = "Search Category",
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            if (deviceConfiguration == DeviceConfiguration.TABLET_LANDSCAPE || deviceConfiguration == DeviceConfiguration.DESKTOP || deviceConfiguration == DeviceConfiguration.MOBILE_LANDSCAPE) {
                CategoryListContent(state = state, onAction = onAction)
            } else {
                CategoryListScreen(state = state, onAction = onAction)

            }
            CategoryAddScreen(state = state, onAction = onAction)


            Spacer(modifier = Modifier.height(LocalPadding.current.normal))

        }
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))

    }


}

@Composable
fun CategoryListScreen(state: CategoryState, onAction: (CategoryAction) -> Unit) {

}

@Composable
fun CategoryListContent(state: CategoryState, onAction: (CategoryAction) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(LocalPadding.current.normal),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "CategoryName", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(LocalPadding.current.large))

                Text(text = "Status")
                Spacer(modifier = Modifier.width(LocalPadding.current.extraLarge))
                Text(text = "Actions")
                Spacer(modifier = Modifier.width(LocalPadding.current.normal))

            }
            HorizontalDivider()

            if (state.searchQuery.isNotBlank()) {
                LazyColumn {
                    if (state.categoryList.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "No Category Found")
                            }
                        }
                    }
                    items(
                        state.categoryList

                    )
                    {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))


                            ListItemWithIcon(
                                modifier = Modifier.weight(1f),
                                uri = it.categoryImage.toString(),
                                titleText = it.categoryName,
                                subtitleText = it.description.toString(),
                            )
                            StatusLabel(it.active)
                            IconButton(onClick = {
                                onAction(CategoryAction.OnEditClick(it.categoryId!!))
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = null
                                )
                            }
                        }


                    }
                }

            } else {
                LazyColumn {

                    val topCategoryList = state.categoryList.filter { it.topCategoryId == null }

                    items(topCategoryList) { category ->
                        var isExpanded by remember { mutableStateOf(false) }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                IconButton(onClick = {
                                    isExpanded = !isExpanded
                                }) {
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Outlined.ArrowDropDown else Icons.Outlined.ArrowDropUp,
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                                ListItemWithIcon(
                                    modifier = Modifier.weight(1f),
                                    uri = category.categoryImage.toString(),
                                    titleText = category.categoryName,
                                    subtitleText = category.description.toString(),
                                )
                            }
                            StatusLabel(category.active)
                            Spacer(modifier = Modifier.width(LocalPadding.current.extraLarge))
                            IconButton(onClick = {
                                onAction(CategoryAction.OnEditClick(category.categoryId!!))
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                        }


                        val categoryList =
                            state.categoryList.filter { it.topCategoryId == category.categoryId }
                        Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
                        AnimatedVisibility(visible = isExpanded) {
                            Spacer(modifier = Modifier.height(LocalPadding.current.normal))
                            Column {
                                categoryList.forEachIndexed { index, category ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        ListItemWithIcon(
                                            modifier = Modifier.weight(1f),
                                            uri = category.categoryImage.toString(),
                                            titleText = category.categoryName,
                                            subtitleText = category.description.toString(),
                                        )
                                        StatusLabel(category.active)
                                        Spacer(modifier = Modifier.width(LocalPadding.current.extraLarge))
                                        IconButton(onClick = {
                                            onAction(CategoryAction.OnEditClick(category.categoryId!!))
                                        }) {
                                            Icon(
                                                imageVector = Icons.Outlined.Edit,
                                                contentDescription = null
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(LocalPadding.current.normal))
                                    }

                                }
                            }
                        }
                        HorizontalDivider()

                    }


                }
            }
        }
    }
}

