package com.mj.caloriecounter.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.model.ListingRepository

class AddFoodViewModel : ViewModel() {

    private val repository = ListingRepository()
    private val allFoods = repository.getFoods()
    var foodResultList by mutableStateOf(emptyList<Food>())
        private set

    fun searchFood(foodName: String) {

        foodResultList = (if (foodName.isEmpty()) {
            emptyList()
        } else {
            allFoods.filter {
                it.name.contains(foodName)
            }
        })
    }

}
