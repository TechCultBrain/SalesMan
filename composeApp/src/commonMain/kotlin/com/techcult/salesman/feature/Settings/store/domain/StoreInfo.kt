package com.techcult.salesman.feature.Settings.store.domain

data class StoreInfo(
    val storeId: Int,
    val storeRegId: String="",
    val businessName: String,
    val businessType: String,
    val businessLocation: String,
    val businessAddress: String,
    val businessCity: String,
    val businessState: String,
    val businessZipCode: String,
    val businessEmail: String,
    val businessPhone: String,
    val businessAlternatePhone: String,
    val businessLogo: String?,
    val gstNumber: String,
    val panNumber: String,
    val tinNumber: String,
    val fssaiNumber: String
)
