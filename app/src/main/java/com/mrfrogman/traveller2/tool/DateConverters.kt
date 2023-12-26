package com.mrfrogman.traveller2.tool

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime

class DateConverters {
    @TypeConverter
    fun fromLocalDate(localDate: LocalDateTime): String {
        return localDate.toString()
    }

    @TypeConverter
    fun toLocalDate(stringDate: String): LocalDateTime {
        return LocalDateTime.parse(stringDate)
    }
}