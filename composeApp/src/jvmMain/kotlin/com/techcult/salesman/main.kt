package com.techcult.salesman

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.techcult.salesman.app.App
import com.techcult.salesman.core.di.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = false,
        title = "SalesMan",
    ) {
        App()
    }
}