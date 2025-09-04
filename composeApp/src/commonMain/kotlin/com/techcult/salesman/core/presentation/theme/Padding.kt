package com.techcult.salesman.core.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(
    val none: Dp = 0.dp,
    val micro: Dp = 4.dp,
    val tiny: Dp = 8.dp,
    val small: Dp = 12.dp,
    val normal: Dp = 16.dp,
    val big: Dp = 20.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val huge: Dp = 40.dp
)


val LocalPadding = compositionLocalOf { Padding() }