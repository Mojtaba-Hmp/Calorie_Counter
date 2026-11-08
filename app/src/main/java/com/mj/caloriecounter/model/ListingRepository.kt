package com.mj.caloriecounter.model

import com.mj.caloriecounter.model.data.exercises
import com.mj.caloriecounter.model.data.foods
import com.mj.caloriecounter.model.dataclasses.Exercise
import com.mj.caloriecounter.model.dataclasses.Food

class ListingRepository {
    fun getFoods(): List<Food> {
        return foods
    }

    fun getExercises(): List<Exercise>{
        return exercises
    }
}