package com.mj.caloriecounter.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.viewModel.AddFoodViewModel

@Composable
fun AddFoodScreen(
    viewModel: AddFoodViewModel = viewModel(),
    onFoodConfirmed: (food: Food, amount: Double) -> Unit
) {
    var foodName by remember { mutableStateOf("") }
    var showAmountSheet by remember { mutableStateOf(false) }
    var selectedFood by remember { mutableStateOf<Food?>(null) }

    AddFoodContent(
        searchQuery = foodName,
        onQueryChange = {
            foodName = it
            viewModel.searchFood(foodName)
        },
        searchResults = viewModel.foodResultList,
        onFoodClick = { result ->
            selectedFood = result
            showAmountSheet = true
        }

    )

    if (showAmountSheet && selectedFood != null) {
        AddFoodAmountBottomSheet(
            food = selectedFood!!,
            onClose = { showAmountSheet = false },
            onSaveSuccess = { amount ->
                onFoodConfirmed(selectedFood!!, amount!!)
                showAmountSheet = false
                foodName = "" //Clearing the search bar
                viewModel.searchFood("")

            }

        )
    }
}

@Composable
fun AddFoodContent(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    searchResults: List<Food>,
    onFoodClick: (Food) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            textStyle = MaterialTheme.typography.headlineSmall,
            onValueChange = {
                onQueryChange(it)
            },
            placeholder = {
                Text(
                    text = "جست‌وجوی غذا ...",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        )

        LazyColumn {
            items(searchResults)

            { result ->
                Text(
                    text = result.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .clickable {
                            onFoodClick(result)
                        }
                )
                HorizontalDivider(
                    modifier = Modifier,
                    DividerDefaults.Thickness,
                    DividerDefaults.color
                )
            }
        }
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_8)
@Composable
fun AddFoodPreview() {
    CalorieCounterTheme {
        AddFoodContent(
            "موز",
            onQueryChange = {},
            searchResults = listOf(
                Food(
                    name = "سیب تخم‌مرغی شیرین",
                    caloriesPer100g = 52.0,
                    proteinsPer100g = 0.3,
                    carbsPer100g = 14.0,
                    fatsPer100g = 0.2
                ),
                Food(
                    name = "گلابی",
                    caloriesPer100g = 52.0,
                    proteinsPer100g = 0.3,
                    carbsPer100g = 14.0,
                    fatsPer100g = 0.2
                ),
                Food(
                    name = "انبه",
                    caloriesPer100g = 52.0,
                    proteinsPer100g = 0.3,
                    carbsPer100g = 14.0,
                    fatsPer100g = 0.2
                )
            ),
            onFoodClick = {}
        )
    }
}



