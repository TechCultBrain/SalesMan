package com.techcult.salesman.core.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val viewTiny: Dp = 40.dp,
    val viewSmall: Dp = 48.dp,
    val viewNormal: Dp = 56.dp,
    val viewBig: Dp = 64.dp,
    val viewLarge: Dp = 72.dp,
    val iconTiny: Dp = 16.dp,
    val iconSmall: Dp = 20.dp,
    val iconNormal: Dp = 24.dp,
    val iconBig: Dp = 28.dp,
    val iconLarge: Dp = 32.dp,
    val maxWidthSmall: Dp = 300.dp,
    val minWidthForTextField: Dp = 350.dp,
    val maxWidthForTextField: Dp = 450.dp,
    val minWidthForRow: Dp = 350.dp,
    val maxWidthForRow: Dp = 450.dp,
    val minWidthForButton: Dp = 350.dp,
    val maxWidthForButton: Dp = 450.dp,
)

val LocalDimensions = compositionLocalOf { Dimensions() }