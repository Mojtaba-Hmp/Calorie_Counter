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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.viewModel.AddFoodViewModel

@Composable
fun AddFoodScreen(
    viewModel: AddFoodViewModel = viewModel(),
    onFoodConfirmed: (food: Food, amount: Double) -> Unit
) {
    var foodName by remember { mutableStateOf("") }
    var showAmountSheet by remember { mutableStateOf(false) }
    var selectedFood by remember { mutableStateOf<Food?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = foodName,
            textStyle = MaterialTheme.typography.headlineSmall,
            onValueChange = {
                foodName = it
                viewModel.searchFood(foodName)
            },
            placeholder = {
                Text(
                    text = "جست‌وجوی غذا ...",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        )

        LazyColumn {
            items(viewModel.foodResultList)

            { result ->
                Text(
                    text = result.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .clickable {
                            selectedFood = result
                            showAmountSheet = true
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
    if (showAmountSheet && selectedFood != null) {
        AddFoodAmountBottomSheet(
            food = selectedFood!!,
            onClose = { showAmountSheet = false },
            onSaveSuccess = { amount ->
                onFoodConfirmed(selectedFood!!,amount!!)
                showAmountSheet = false
                foodName = "" //Clearing the search bar
                viewModel.searchFood("")

            }

        )
    }

}



