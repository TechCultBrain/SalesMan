package com.techcult.salesman.feature.purchase.data

import androidx.room.Embedded
import androidx.room.Relation


data class PurchaseWithItems(
    @Embedded val purchase: PurchaseEntity,
    @Relation(
        parentColumn = "purchaseId",
        entityColumn = "purchaseId"
    )
    val items: List<PurchaseItemEntity>
)