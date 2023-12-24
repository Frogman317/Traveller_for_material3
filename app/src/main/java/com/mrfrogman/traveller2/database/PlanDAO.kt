package com.mrfrogman.traveller2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanDAO {
    @Insert
    fun insert(plan: PlanEntity)

    @Query("select * from PlanEntity")
    fun getAll(): LiveData<List<PlanEntity>>
}