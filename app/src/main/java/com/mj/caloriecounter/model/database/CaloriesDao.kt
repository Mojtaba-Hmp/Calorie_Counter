package com.mj.caloriecounter.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface CaloriesDao {
    // --- Food Queries ---
    @Query("SELECT * FROM consumed_foods WHERE date >= :startOfDay")
    fun getTodayFoods(startOfDay: Long): Flow<List<ConsumedFood>>

    @Insert
    suspend fun insertFood(food: ConsumedFood)

    @Delete
    suspend fun deleteFood(food: ConsumedFood)

    // --- Exercise Queries ---
    @Query("SELECT * FROM finished_exercises WHERE date >= :startOfDay")
    fun getTodayExercises(startOfDay: Long): Flow<List<FinishedExercise>>

    @Insert
    suspend fun insertExercise(exercise: FinishedExercise)

    @Delete
    suspend fun deleteExercise(exercise: FinishedExercise)
}