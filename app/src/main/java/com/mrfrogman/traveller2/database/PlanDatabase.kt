package com.mrfrogman.traveller2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PlanEntity::class
   ],
    version = 1
)
abstract class PlanDatabase: RoomDatabase() {
    abstract fun planDAO(): PlanDAO
}