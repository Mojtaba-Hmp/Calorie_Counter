package com.mj.caloriecounter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mj.caloriecounter.utils.LogEntry

@Entity(tableName = "finished_exercises")
data class FinishedExercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    override val name: String,
    val time: Int,
    override val calories: Double,
    val date: Long = System.currentTimeMillis()
) : LogEntry