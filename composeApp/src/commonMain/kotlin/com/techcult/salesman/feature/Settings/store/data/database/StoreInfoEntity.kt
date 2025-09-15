package com.techcult.salesman.feature.Settings.store.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "store_info")
data class StoreInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
