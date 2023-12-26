package com.mrfrogman.traveller2.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.time.LocalDateTime

@Entity
data class ExpensesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val detail: String,
    val planId: Int,
    val amount: Int,
    val create: LocalDateTime,
    val timestamp: LocalDateTime,
)
@Dao
interface ExpensesDAO {

    @Insert(entity = ExpensesEntity::class)
    fun insert(member: ExpensesEntity)

    @Query("SELECT * FROM ExpensesEntity")
    fun getAll(): List<ExpensesEntity>

}
