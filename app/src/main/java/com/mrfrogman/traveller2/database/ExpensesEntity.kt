package com.mrfrogman.traveller2.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDateTime

@Entity
data class ExpensesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val detail: String,
    val planId: Int,
    val amount: Int,
    val payer: Int,
    val receipt: Map<String,String>,
    val create: LocalDateTime,
    val timestamp: LocalDateTime,
)
@Dao
interface ExpensesDAO {

    @Insert(entity = ExpensesEntity::class)
    fun insert(member: ExpensesEntity): Long

    @Query("SELECT * FROM ExpensesEntity")
    fun getAll(): List<ExpensesEntity>

    @Query("SELECT * FROM ExpensesEntity WHERE planId = :planId")
    fun listSearch(planId: String): List<ExpensesEntity>

    @Query("SELECT * FROM ExpensesEntity WHERE id = :expensesId")
    fun search(expensesId: String): List<ExpensesEntity>

    @Query("SELECT SUM(amount) FROM ExpensesEntity WHERE planId = :planId")
    fun getAmount(planId: String): Long

    @Update(entity = ExpensesEntity::class)
    fun update(member: ExpensesEntity): Int
}
