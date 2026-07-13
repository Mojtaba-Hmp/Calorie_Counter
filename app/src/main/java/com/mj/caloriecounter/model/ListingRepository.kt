package com.mj.caloriecounter.model

class ListingRepository {
    fun getFoods(): List<Food> {
        return foods
    }

    fun getExercises(): List<Exercise>{
        return exercises
    }
}