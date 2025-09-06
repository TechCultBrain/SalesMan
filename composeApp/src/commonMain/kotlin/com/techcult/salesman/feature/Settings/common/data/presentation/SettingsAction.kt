package com.techcult.salesman.feature.Settings.common.data.presentation

import com.techcult.salesman.feature.Settings.common.data.SettingsRouting

sealed interface SettingsAction {

    data class OnSettingOptionClicked(val option: SettingsRouting) : SettingsAction
}