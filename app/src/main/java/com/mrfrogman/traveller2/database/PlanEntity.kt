package com.mrfrogman.traveller2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val detail: String,
    val amount: Int,
    val create: Date,
    val timestamp: Date,
)
