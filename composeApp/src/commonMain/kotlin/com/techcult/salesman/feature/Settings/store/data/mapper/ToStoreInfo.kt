package com.techcult.salesman.feature.Settings.store.data.mapper

import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoEntity
import com.techcult.salesman.feature.Settings.store.domain.StoreInfo

fun StoreInfoEntity.toStoreInfo(storeInfoEntity: StoreInfoEntity): StoreInfo {
    return StoreInfo(
        storeId = storeInfoEntity.id,
        storeRegId = "",
        businessName = storeInfoEntity.businessName,
        businessType = storeInfoEntity.businessType,
        businessLocation = storeInfoEntity.businessLocation,
        businessAddress = storeInfoEntity.businessAddress,
        businessCity = storeInfoEntity.businessCity,
        businessState = storeInfoEntity.businessState,
        businessZipCode = storeInfoEntity.businessZipCode,
        businessEmail = storeInfoEntity.businessEmail,
        businessPhone = storeInfoEntity.businessPhone,
        businessAlternatePhone = storeInfoEntity.businessAlternatePhone,
        businessLogo = storeInfoEntity.businessLogo,
        gstNumber = storeInfoEntity.gstNumber,
        panNumber = storeInfoEntity.panNumber,
        tinNumber = storeInfoEntity.tinNumber,
        fssaiNumber = storeInfoEntity.fssaiNumber
        )
}

fun StoreInfo.toStoreInfoEntity(): StoreInfoEntity {

    return StoreInfoEntity(
         id = this.storeId,
        businessName = this.businessName,
        businessType = this.businessType,
        businessLocation = this.businessLocation,
        businessAddress = this.businessAddress,
        businessCity = this.businessCity,
        businessState = this.businessState,
        businessZipCode = this.businessZipCode,
        businessEmail = this.businessEmail,
        businessPhone = this.businessPhone,
        businessAlternatePhone = this.businessAlternatePhone,
        businessLogo = this.businessLogo,
        gstNumber = this.gstNumber,
        panNumber = this.panNumber,
        tinNumber = this.tinNumber,
        fssaiNumber = this.fssaiNumber

    )
}