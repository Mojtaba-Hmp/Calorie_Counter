package com.mj.caloriecounter.model.database

import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class CaloriesRepository(private val dao: CaloriesDao) {

    private fun getStartOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun getTodayFoods(): Flow<List<ConsumedFood>> = dao.getTodayFoods(getStartOfDay())

    fun getTodayExercises(): Flow<List<FinishedExercise>> = dao.getTodayExercises(getStartOfDay())

    suspend fun insertFood(food: ConsumedFood) = dao.insertFood(food)

    suspend fun insertExercise(exercise: FinishedExercise) = dao.insertExercise(exercise)
    
    suspend fun deleteFood(food: ConsumedFood) = dao.deleteFood(food)
    suspend fun deleteExercise(exercise: FinishedExercise) = dao.deleteExercise(exercise)
}
