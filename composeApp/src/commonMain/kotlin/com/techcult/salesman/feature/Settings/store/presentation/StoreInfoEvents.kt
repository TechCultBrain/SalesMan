package com.techcult.salesman.feature.Settings.store.presentation

sealed interface StoreInfoEvents {
    data object SaveStoreInfoSuccess : StoreInfoEvents
    data object UpdateStoreInfoSuccess : StoreInfoEvents
    data class SaveStoreInfoError(val message: String) : StoreInfoEvents
}