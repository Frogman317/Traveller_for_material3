package com.mrfrogman.traveller2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrfrogman.traveller2.tool.DateConverters

@Database(
    entities = [
        PlanEntity::class,
        MemberEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun planDAO(): PlanDAO
    abstract fun memberDAO(): MemberDAO
}