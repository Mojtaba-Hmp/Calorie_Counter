package com.mj.caloriecounter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.R
import com.mj.caloriecounter.model.dataclasses.MacroGoals
import com.mj.caloriecounter.ui.theme.AppThemeMode
import com.mj.caloriecounter.ui.theme.Dimens.iconLarge
import com.mj.caloriecounter.ui.theme.GreenPrimary

enum class SettingsScreen { MAIN, THEME, GOAL }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSettingsDialog(
    onClose: () -> Unit,
    currentTheme: AppThemeMode,
    currentGoals: MacroGoals,
    onThemeChange: (AppThemeMode) -> Unit,
    onGoalChange: (MacroGoals) -> Unit,
) {
    var currentScreen by remember { mutableStateOf(SettingsScreen.MAIN) }

    BasicAlertDialog(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .background(color = MaterialTheme.colorScheme.surface),

        onDismissRequest = onClose,
        content = {

            when (currentScreen) {

                SettingsScreen.MAIN -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(
                                onClick = onClose,
                                modifier = Modifier.padding(15.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    Modifier.size(iconLarge)
                                )
                            }
                        }

                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AddOptionRow(
                                "تغییر تم",
                                painter = rememberVectorPainter(Icons.Default.BrightnessMedium),
                                "Change theme",
                                onClick = {
                                    currentScreen = SettingsScreen.THEME
                                }
                            )
                            AddOptionRow(
                                "تغییر هدف",
                                painter = painterResource(id = R.drawable.target_icon),
                                "Change goal",
                                onClick = {
                                    currentScreen = SettingsScreen.GOAL
                                }
                            )
                            AddOptionRow(
                                "توسعه دهنده",
                                rememberVectorPainter(Icons.Default.Code),
                                "About Developer",
                                onClick = {

                                }
                            )
                            Spacer(modifier = Modifier.padding(top = 35.dp))

                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "نسخه 1.1",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }


                SettingsScreen.THEME -> {

                    AddThemeChanger(
                        currentTheme = currentTheme,
                        onClose = { currentScreen = SettingsScreen.MAIN },
                        onOptionSelected = { newTheme ->
                            onThemeChange (newTheme)
                        },
                    )
                }

                SettingsScreen.GOAL -> {
                    AddGoalChanger(
                        onClose = { currentScreen = SettingsScreen.MAIN },
                        currentGoals = currentGoals,
                        onSaveGoals = {
                            onGoalChange(it)
                        }
                    )
                }
            }

        }
    )
}

@Composable
fun AddOptionRow(
    title: String,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(start = 50.dp, top = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            Modifier.size(iconLarge),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
fun AddThemeChanger(
    currentTheme: AppThemeMode,
    onOptionSelected: (AppThemeMode) -> Unit,
    onClose: () -> Unit
) {

    val themeOptions = listOf(
        "تاریک" to AppThemeMode.DARK,
        "روشن" to AppThemeMode.LIGHT,
        "پیش ‌فرض سیستم" to AppThemeMode.SYSTEM
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onClose,
                modifier = Modifier.padding(15.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close",
                    Modifier.size(iconLarge)
                )
            }
        }

        Column(
            Modifier
                .selectableGroup()
                .fillMaxSize()
                .padding(top = 20.dp),
        ) {

            themeOptions.forEach { (label,mode) ->

                val isSelected = (mode == currentTheme)

                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = isSelected,
                            onClick = { onOptionSelected(mode) },
                            role = Role.RadioButton
                        )
                        .padding(start = 50.dp, top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = null
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddGoalChanger(
    onClose: () -> Unit,
    currentGoals: MacroGoals,
    onSaveGoals: (MacroGoals) -> Unit,
) {
    var calorieText by remember { mutableStateOf(currentGoals.calories.toString()) }
    var proteinText by remember { mutableStateOf(currentGoals.protein.toString()) }
    var carbText by remember { mutableStateOf(currentGoals.carbs.toString()) }
    var fatText by remember { mutableStateOf(currentGoals.fat.toString()) }

    Column(
        Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onClose,
                modifier = Modifier.padding(15.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Close",
                    Modifier.size(iconLarge)
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddGoalRow(
                value = calorieText,
                onValueChange = { calorieText = it },
                "کالری",
            )
            AddGoalRow(
                value =proteinText,
                onValueChange ={ proteinText = it },
                "پروتئین",
            )
            AddGoalRow(
                value =carbText,
                onValueChange ={ carbText = it },
                "کربوهیدرات",
            )
            AddGoalRow(
                value =fatText,
                onValueChange ={ fatText = it },
                "چربی",
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                ),
                onClick = {
                    onSaveGoals(
                        MacroGoals(
                            calorieText.toDoubleOrNull() ?: currentGoals.calories,
                            proteinText.toDoubleOrNull() ?: currentGoals.protein,
                            carbText.toDoubleOrNull() ?: currentGoals.carbs,
                            fatText.toDoubleOrNull() ?: currentGoals.fat
                        )
                    )
                    onClose()
                },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.75f)
                    .padding(bottom = 20.dp, top = 20.dp),
            ) {
                Text("ذخیره", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Composable
fun AddGoalRow(
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding( bottom = 15 .dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.6f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = value,
            textStyle = MaterialTheme.typography.headlineSmall,
            onValueChange = {
                onValueChange(it)
            }
        )
    }
}