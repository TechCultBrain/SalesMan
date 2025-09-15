package com.techcult.salesman.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.app.Navigation.Screen
import com.techcult.salesman.app.Navigation.SetupNavigation
import com.techcult.salesman.core.presentation.MainViewModel
import com.techcult.salesman.core.presentation.theme.AppTheme
import com.techcult.salesman.core.presentation.theme.LocalPadding
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    AppTheme {

        val viewModel = koinInject<MainViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val startDestination =
            if (state.isLoggedIn && state.isUserLoggedIn) {
                Screen.HomeRoute
            } else if (state.isLoggedIn && !state.isUserLoggedIn) {
                Screen.UserLoginRoute
            } else if (state.isLoggedIn.not()) {
                Screen.LoginRoute
            } else {
                Screen.LoginRoute
            }

        SetupNavigation(startDestination)


    }

}

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.error
    ) {
        Box(
            modifier = Modifier.padding(LocalPadding.current.large),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}