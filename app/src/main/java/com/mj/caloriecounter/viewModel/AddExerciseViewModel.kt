package com.mj.caloriecounter.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mj.caloriecounter.model.Exercise
import com.mj.caloriecounter.model.ListingRepository


class AddExerciseViewModel : ViewModel() {

    private val repository = ListingRepository()
    private val allExercises = repository.getExercises()
    var exerciseResultList by mutableStateOf(emptyList<Exercise>())
        private set

    fun searchExercise(exerciseName: String) {

        exerciseResultList = (if (exerciseName.isEmpty()) {
            emptyList()
        } else {
            allExercises.filter {
                it.name.contains(exerciseName)
            }
        })
    }

}
