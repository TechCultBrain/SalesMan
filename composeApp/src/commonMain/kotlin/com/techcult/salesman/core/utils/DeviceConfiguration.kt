package com.techcult.salesman.core.utils

import androidx.window.core.layout.WindowSizeClass

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {

            return if (windowSizeClass.isWidthAtLeastBreakpoint(840) && windowSizeClass.isHeightAtLeastBreakpoint(
                    480
                )
            ) {
                DESKTOP
            } else {
                MOBILE_PORTRAIT
            }


        }
    }
}
