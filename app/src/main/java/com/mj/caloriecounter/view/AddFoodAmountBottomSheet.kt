package com.mj.caloriecounter.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.model.Food
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.ui.theme.grayBackground
import com.mj.caloriecounter.utils.macroCalculator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodAmountBottomSheet(
    food: Food,
    onClose: () -> Unit,
    onSaveSuccess: (Double?) -> Unit
) {

    var foodAmount by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("گرم") }
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
        selectedOption = selectedUnit,
        onUnitSelected = { selectedUnit = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodAmountContent(
    food: Food,
    foodAmount: String,
    selectedOption: String,
    onAmountChange: (String) -> Unit,
    onConfirmClick: (Double?) -> Unit,
    onClose: () -> Unit,
    onUnitSelected: (String) -> Unit
) {


    val amount = foodAmount.toDoubleOrNull()

    val unitMultiplier =
        if (selectedOption != "گرم") {
            food.unit.find { it.name == selectedOption }?.gramsPerUnit ?: 1
        } else {
            1
        }

    val finalGrams = if (amount != null) amount * unitMultiplier.toDouble() else null

    val sheetState = rememberModalBottomSheetState()

    val unitOptions = remember(food) {
        listOf("گرم") + food.unit.map { it.name }
    }


    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onClose,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
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
                    .fillMaxWidth(fraction = 0.75f)
                    .padding(bottom = 20.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(grayBackground),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .padding(start = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = foodAmount,
                    onValueChange = {
                        onAmountChange(it)
                    },
                    placeholder = {
                        Text(
                            text = "مقدار مصرف",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )

                AddUnitsDropdownMenu(
                    unitOptions,
                    selectedOption = selectedOption,
                    onOptionSelected = onUnitSelected
                )

            }

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                if (finalGrams != null) {
                    AddMacroText(finalGrams, "کالری", food.caloriesPer100g)
                    AddMacroText(finalGrams, "پروتئین", food.proteinsPer100g)
                    AddMacroText(finalGrams, "کربوهیدرات", food.carbsPer100g)
                    AddMacroText(finalGrams, "چربی", food.fatsPer100g)

                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenPrimary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    ),
                    onClick = {
                        onConfirmClick(finalGrams)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUnitsDropdownMenu(
    options: List<String>,
    selectedOption: String = options[0],
    onOptionSelected: (String) -> Unit,
) {

    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp)),
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
    ) {

        TextField(
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)// Essential for positioning the popup
                .focusProperties {
                    canFocus = false
                }, // Prevents focus transfer so keyboard stays open,
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                // Animated Arrow icon that changes orientation when expanded
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )


        ExposedDropdownMenu(
            modifier = Modifier
                .background(grayBackground),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {

            options.forEach { option ->
                DropdownMenuItem(
                    modifier = Modifier.background(grayBackground),
                    text = {
                        Text(
                            text = option,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        isExpanded = false
                    }
                )
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
                onClose = {},
                selectedOption = "عدد متوسط",
                onUnitSelected = {}
            )
        }
    }
}




