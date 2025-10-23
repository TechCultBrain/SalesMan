package com.techcult.salesman.core.data.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConvertor {
    @TypeConverter
    fun timeToString(time: LocalDateTime?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun stringToDateTIme(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }
}