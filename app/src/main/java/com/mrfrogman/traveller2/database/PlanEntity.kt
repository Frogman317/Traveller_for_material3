package com.mrfrogman.traveller2.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.time.LocalDateTime

@Entity
data class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val detail: String,
    val create: LocalDateTime,
    val timestamp: LocalDateTime,
)
@Dao
interface PlanDAO {

    @Insert(entity = PlanEntity::class)
    fun insert(plan: PlanEntity): Long

    @Query("SELECT * FROM PlanEntity")
    fun getAll(): List<PlanEntity>

    @Query("SELECT * FROM PlanEntity WHERE id = :planId")
    fun search(planId: String): PlanEntity

}