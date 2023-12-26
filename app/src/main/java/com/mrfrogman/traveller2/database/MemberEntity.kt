package com.mrfrogman.traveller2.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.time.LocalDateTime

@Entity
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val detail: String,
    val planId: Int,
    val create: LocalDateTime,
    val timestamp: LocalDateTime,
)
@Dao
interface MemberDAO {

    @Insert(entity = MemberEntity::class)
    fun insert(member: MemberEntity)

    @Query("SELECT * FROM MemberEntity")
    fun getAll(): List<MemberEntity>

}