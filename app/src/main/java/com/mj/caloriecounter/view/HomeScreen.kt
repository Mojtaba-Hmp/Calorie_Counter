package com.mj.caloriecounter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mj.caloriecounter.model.dataclasses.MacroGoals
import com.mj.caloriecounter.ui.theme.CarbYellow
import com.mj.caloriecounter.ui.theme.Dimens.iconLarge
import com.mj.caloriecounter.ui.theme.FatRed
import com.mj.caloriecounter.ui.theme.GreenContainer
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.ui.theme.LightGreenContainer
import com.mj.caloriecounter.ui.theme.ProteinGreen
import com.mj.caloriecounter.ui.theme.TealSecondary
import com.mj.caloriecounter.utils.toPersianDigits
import com.mj.caloriecounter.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAddFood: () -> Unit,
    onNavigateToAddExercise: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {

    var showSummarySheet by remember { mutableStateOf(false) }
    var showOptionSheet by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }


    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProtein by viewModel.totalProtein.collectAsState()
    val totalCarbs by viewModel.totalCarbs.collectAsState()
    val totalFats by viewModel.totalFats.collectAsState()
    val totalBurntCalories by viewModel.totalBurntCalories.collectAsState()

    val calorieGoal by viewModel.calorieGoal.collectAsState()
    val proteinGoal by viewModel.proteinGoal.collectAsState()
    val carbGoal by viewModel.carbGoal.collectAsState()
    val fatGoal by viewModel.fatGoal.collectAsState()

    val currentTheme by viewModel.themeMode.collectAsState()


    val currentGoals = MacroGoals(calorieGoal, proteinGoal, carbGoal, fatGoal)


    val remainingCalories by remember(
        totalCalories,
        totalBurntCalories
    ) { derivedStateOf { calorieGoal - totalCalories + totalBurntCalories } }


    Scaffold(
        floatingActionButton = {
            AddButton(
                onClick = {
                    showOptionSheet = true
                },
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        floatingActionButtonPosition = FabPosition.Center,


        topBar = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                TopAppBar(
                    title = {
                    },
                    actions = {
                        SettingsButton(
                            onClick = {
                                showSettingsDialog = true
                            }
                        )
                    }
                )
            }

        }


    ) { innerPadding ->


        if (showSettingsDialog) {
            AddSettingsDialog(
                onClose = {
                    showSettingsDialog = false
                },
                currentGoals = currentGoals,
                onGoalChange = { newGoals ->
                    viewModel.updateGoals(newGoals)
                },
                currentTheme = currentTheme,
                onThemeChange = { newTheme ->
                    viewModel.updateTheme(newTheme)
                }
            )
        }


        if (showOptionSheet) {
            AddOptionsBottomSheet(
                onClose = {
                    showOptionSheet = false
                },
                onFoodClick = {
                    onNavigateToAddFood()
                    showOptionSheet = false
                },
                onExerciseClick = {
                    onNavigateToAddExercise()
                    showOptionSheet = false
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Top screen row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Right: Exercise
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = totalBurntCalories.toInt().toString().toPersianDigits(),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "کالری مصرفی",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Center: The Big Circle (
                Box(
                    contentAlignment = Alignment.Center,
                )
                {
                    CircularProgressIndicator(
                        progress = {
                            1f - (remainingCalories.toFloat() / calorieGoal.toFloat()).coerceIn(
                                0f,
                                1f
                            )
                        },
                        modifier = Modifier
                            .size(180.dp)
                            .focusable()
                            .clickable {
                                showSummarySheet = true
                            },
                        trackColor = LightGreenContainer,
                        strokeWidth = 12.dp,
                        color = when {
                            remainingCalories >= calorieGoal -> LightGreenContainer
                            remainingCalories < 0 -> Color.Red
                            remainingCalories <= 200.0 -> CarbYellow
                            else -> GreenPrimary
                        }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = remainingCalories.toInt().toString().toPersianDigits(),
                            style = MaterialTheme.typography.displaySmall
                        )
                        Text(
                            text = "کالری مجاز",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Left: Food
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = totalCalories.toInt().toString().toPersianDigits(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "کالری دریافتی",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Middle Screen Column
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightGreenContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MacroProgressBar(
                        title = "پروتئین",
                        current = totalProtein,
                        goal = currentGoals.protein,
                        color = ProteinGreen
                    )
                    MacroProgressBar(
                        title = "کربوهیدرات",
                        current = totalCarbs,
                        goal = currentGoals.carbs,
                        color = CarbYellow
                    )
                    MacroProgressBar(
                        title = "چربی",
                        current = totalFats,
                        goal = currentGoals.fat,
                        color = FatRed
                    )
                }
            }

        }



        if (showSummarySheet) {
            AddTodaySummaryBottomSheet(
                onClose = { showSummarySheet = false },
                viewModel = viewModel
            )
        }

    }
}


@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = GreenContainer,
        contentColor = TealSecondary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            Modifier.size(iconLarge)
        )
    }
}

@Composable
fun SettingsButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            Modifier.size(iconLarge),
            MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MacroProgressBar(
    title: String,
    current: Double,
    goal: Double,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title, style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = " ${goal.toInt()}/ ${current.toInt()}".toPersianDigits(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // The Progress Bar
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) { // Ltr progress
            LinearProgressIndicator(
                progress = { (current / goal).toFloat().coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = color,
                trackColor = color.copy(alpha = 0.2f),
                gapSize = (-10).dp
            )
        }
    }
}


