package com.techcult.salesman.feature.Settings.presentation.common.data.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SettingsViewModel : ViewModel() {


    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState: StateFlow<SettingsState> = _settingsState


    private val _event = Channel<SettingsEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: SettingsAction) {
        when (action) {

            else -> {}
        }
    }
}