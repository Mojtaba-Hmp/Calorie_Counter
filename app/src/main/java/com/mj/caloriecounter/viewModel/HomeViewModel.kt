package com.mj.caloriecounter.viewModel

import android.app.Application
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mj.caloriecounter.model.database.AppDataBase
import com.mj.caloriecounter.model.database.CaloriesRepository
import com.mj.caloriecounter.model.dataclasses.ConsumedFood
import com.mj.caloriecounter.model.dataclasses.Exercise
import com.mj.caloriecounter.model.dataclasses.FinishedExercise
import com.mj.caloriecounter.model.dataclasses.Food
import com.mj.caloriecounter.model.dataclasses.MacroGoals
import com.mj.caloriecounter.ui.theme.AppThemeMode
import com.mj.caloriecounter.utils.dataStore
import com.mj.caloriecounter.utils.macroCalculator
import com.mj.caloriecounter.utils.timeCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {


    //change goal  and theme datastore
    private object PreferencesKeys {
        val CALORIE_GOAL = doublePreferencesKey("calorie_goal")
        val PROTEIN_GOAL = doublePreferencesKey("protein_goal")
        val CARB_GOAL = doublePreferencesKey("carb_goal")
        val FAT_GOAL = doublePreferencesKey("fat_goal")
        val THEME_STATE = intPreferencesKey("theme_state")
    }

    val calorieGoal: StateFlow<Double> = application.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.CALORIE_GOAL] ?: 2500.0
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 2500.0
        )

    val proteinGoal: StateFlow<Double> = application.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.PROTEIN_GOAL] ?: 140.0
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 140.0
        )

    val carbGoal: StateFlow<Double> = application.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.CARB_GOAL] ?: 330.0
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 330.0
        )

    val fatGoal: StateFlow<Double> = application.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.FAT_GOAL] ?: 80.0
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 80.0
        )

    val themeMode: StateFlow<AppThemeMode> = application.dataStore.data
        .map { preferences ->
            val savedOrdinal = preferences[PreferencesKeys.THEME_STATE]
            if (savedOrdinal != null && savedOrdinal in AppThemeMode.entries.indices) {
                AppThemeMode.entries[savedOrdinal]
            } else {
                AppThemeMode.SYSTEM
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppThemeMode.SYSTEM
        )


    fun updateGoals(goals: MacroGoals) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[PreferencesKeys.CALORIE_GOAL] = goals.calories
                preferences[PreferencesKeys.PROTEIN_GOAL] = goals.protein
                preferences[PreferencesKeys.CARB_GOAL] = goals.carbs
                preferences[PreferencesKeys.FAT_GOAL] = goals.fat
            }
        }
    }

    fun updateTheme(themeMode: AppThemeMode) {
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[PreferencesKeys.THEME_STATE] = themeMode.ordinal
            }
        }
    }


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
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalFats = consumedFoods.map { it.sumOf { food -> food.fat } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    val totalBurntCalories = finishedExercises.map { it.sumOf { ex -> ex.calories } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    fun addConsumedFood(food: Food, amount: Double) {
        viewModelScope.launch {
            repository.insertFood(
                ConsumedFood(
                    name = food.name,
                    amount = amount,
                    calories = macroCalculator(amount, food.caloriesPer100g).toDouble(),
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