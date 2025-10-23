package com.techcult.salesman.feature.product.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BaseUomWithConversions(
    @Embedded val baseUom: UomEntity,
    @Relation(
        parentColumn = "uomId",
        entityColumn = "baseUomId"
    )
    val conversions: List<UomEntity>
)