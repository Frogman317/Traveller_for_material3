package com.mrfrogman.traveller2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

@Database(
    entities = [
        PlanEntity::class,
        MemberEntity::class,
        ExpensesEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(MyTypeConverters::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun planDAO(): PlanDAO
    abstract fun memberDAO(): MemberDAO
    abstract fun expensesDAO(): ExpensesDAO
}

class MyTypeConverters {
    @TypeConverter
    fun fromLocalDate(localDate: LocalDateTime): String {
        return localDate.toString()
    }
    @TypeConverter
    fun toLocalDate(stringDate: String): LocalDateTime {
        return LocalDateTime.parse(stringDate)
    }
    private val gson = Gson()
    @TypeConverter
    fun fromMap(map: Map<String, String>): String {
        return gson.toJson(map)
    }
    @TypeConverter
    fun toMap(json: String): Map<String, String> {
        val type = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(json, type)
    }
}
