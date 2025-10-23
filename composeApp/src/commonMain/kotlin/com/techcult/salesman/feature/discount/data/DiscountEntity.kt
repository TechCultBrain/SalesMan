package com.techcult.salesman.feature.discount.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

@Entity(tableName = "discounts")
data class DiscountEntity(
    @PrimaryKey val id: Long?=0,
    val name: String,
    val type: DiscountType,
    val value: Double,
    val scope: DiscountScope,
    val minPurchaseAmount: Double? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val couponCode: String? = null,
    val isActive: Boolean = true
)

enum class DiscountType { PERCENTAGE, FIXED }
enum class DiscountScope { PRODUCT, CATEGORY, BILL, CUSTOMER }

class DiscountConverters {
    @TypeConverter
    fun fromDiscountType(value: DiscountType): String = value.name
    @TypeConverter
    fun toDiscountType(value: String): DiscountType = DiscountType.valueOf(value)

    @TypeConverter
    fun fromDiscountScope(value: DiscountScope): String = value.name
    @TypeConverter
    fun toDiscountScope(value: String): DiscountScope = DiscountScope.valueOf(value)

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? = value?.toString()
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
}