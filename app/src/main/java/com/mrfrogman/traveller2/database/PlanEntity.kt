package com.mrfrogman.traveller2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val detail: String,
    val amount: Int,
    val timestamp: String
)