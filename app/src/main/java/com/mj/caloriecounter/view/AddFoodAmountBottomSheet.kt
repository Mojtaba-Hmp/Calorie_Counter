package com.mj.caloriecounter.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.utils.macroCalculator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodAmountBottomSheet(
    food: Food,
    onClose: () -> Unit,
    onSaveSuccess: (Double?) -> Unit
) {

    var foodAmount by remember { mutableStateOf("") }
    val context = LocalContext.current

    AddFoodAmountContent(
        food = food,
        foodAmount = foodAmount,
        onAmountChange = { foodAmount = it },
        onConfirmClick = { amount ->
                Toast.makeText(context, "ثبت شد", Toast.LENGTH_SHORT).show()
                onSaveSuccess(amount)
        },
        onClose = onClose,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodAmountContent(
    food:Food,
    foodAmount: String,
    onAmountChange: (String) -> Unit,
    onConfirmClick: (Double?) -> Unit,
    onClose: () -> Unit,
){

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onClose,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = food.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(bottom = 20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = foodAmount,
                    onValueChange = {
                        onAmountChange(it)
                    },
                    placeholder = {
                        Text(
                            text = "مقدار مصرف برحسب گرم",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
            }

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                val amount = foodAmount.toDoubleOrNull()
                if (amount != null) {

                    AddMacroText(amount, "کالری", food.caloriesPer100g)
                    AddMacroText(amount, "پروتئین", food.proteinsPer100g)
                    AddMacroText(amount, "کربوهیدرات", food.carbsPer100g)
                    AddMacroText(amount, "چربی", food.fatsPer100g)

                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenPrimary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    ),
                    onClick = {
                        onConfirmClick(amount)
                    },
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.75f)
                        .padding(bottom = 20.dp, top = 20.dp),
                ) {
                    Text("ثبت", style = MaterialTheme.typography.titleLarge)
                }

            }


        }
    }
}

@Composable
fun AddMacroText(amount: Double, title: String, macro: Double) {
    Text(
        text = "$title: ${macroCalculator(amount, macro)}",
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(showSystemUi = true, device = Devices.PIXEL_8)
@Composable
fun AddFoodAmountPreview() {
    CalorieCounterTheme {
        // Putting it inside a full screen Box forces the BottomSheet to open up on top of it!
        Box(modifier = Modifier.fillMaxSize()) {
            AddFoodAmountContent(
                food = Food(
                    name = "سیب تخم‌مرغی شیرین",
                    caloriesPer100g = 52.0,
                    proteinsPer100g = 0.3,
                    carbsPer100g = 14.0,
                    fatsPer100g = 0.2
                ),
                foodAmount = "120",
                onAmountChange = {},
                onConfirmClick = {},
                onClose = {}
            )
        }
    }
}




