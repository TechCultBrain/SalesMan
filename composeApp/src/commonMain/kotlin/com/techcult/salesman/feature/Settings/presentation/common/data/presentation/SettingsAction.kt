package com.techcult.salesman.feature.Settings.presentation.common.data.presentation

import com.techcult.salesman.feature.Settings.presentation.common.data.SettingsRouting

sealed interface SettingsAction {

    data class OnSettingOptionClicked(val option: SettingsRouting) : SettingsAction
}