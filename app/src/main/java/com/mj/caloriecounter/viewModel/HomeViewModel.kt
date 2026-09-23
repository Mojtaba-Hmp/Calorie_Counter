package com.mj.caloriecounter.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.Exercise
import com.mj.caloriecounter.model.FinishedExercise
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.model.database.AppDataBase
import com.mj.caloriecounter.model.database.CaloriesRepository
import com.mj.caloriecounter.utils.macroCalculator
import com.mj.caloriecounter.utils.timeCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CaloriesRepository

    private val _consumedFoods: Flow<List<ConsumedFood>>
    private val _finishedExercises: Flow<List<FinishedExercise>>

    val consumedFoods: StateFlow<List<ConsumedFood>>
    val finishedExercises: StateFlow<List<FinishedExercise>>

    init {
        val dao = AppDataBase.getDatabase(application).caloriesDao()
        repository = CaloriesRepository(dao)

        _consumedFoods = repository.getTodayFoods()
        _finishedExercises = repository.getTodayExercises()

        consumedFoods = _consumedFoods.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        finishedExercises = _finishedExercises.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    // Totals as StateFlows
    val totalCalories = consumedFoods.map { it.sumOf { food -> food.calories } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalProtein = consumedFoods.map { it.sumOf { food -> food.protein } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalCarbs = consumedFoods.map { it.sumOf { food -> food.carbs } }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalFats = consumedFoods.map { it.sumOf { food -> food.fat } }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalBurntCalories = finishedExercises.map { it.sumOf { ex -> ex.calories } }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    fun addConsumedFood(food: Food, amount: Double) {
        viewModelScope.launch {
            repository.insertFood(
                ConsumedFood(
                    name = food.name,
                    amount = amount,
                    calories = macroCalculator(amount, food.caloriesPer100g,).toDouble(),
                    protein = macroCalculator(amount, food.proteinsPer100g).toDouble(),
                    carbs = macroCalculator(amount, food.carbsPer100g).toDouble(),
                    fat = macroCalculator(amount, food.fatsPer100g).toDouble()
                )
            )
        }
    }

    fun addFinishedExercised(exercise: Exercise, time: Int) {
        viewModelScope.launch {
            repository.insertExercise(
                FinishedExercise(
                    name = exercise.name,
                    time = time,
                    calories = timeCalculator(time, exercise.burntCaloriesPer1h).toDouble()
                )
            )
        }
    }

    fun deleteFood(food: ConsumedFood) {
        viewModelScope.launch {
            repository.deleteFood(food)
        }
    }

    fun deleteExercise(exercise: FinishedExercise) {
        viewModelScope.launch {
            repository.deleteExercise(exercise)
        }
    }
}