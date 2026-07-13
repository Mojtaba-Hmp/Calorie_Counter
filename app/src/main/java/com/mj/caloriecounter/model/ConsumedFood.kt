package com.mj.caloriecounter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mj.caloriecounter.utils.LogEntry

@Entity(tableName = "consumed_foods")
data class ConsumedFood(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    override val name: String,
    val amount: Double,
    override val calories: Double,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fat: Double = 0.0,
    val date: Long = System.currentTimeMillis()
) : LogEntry