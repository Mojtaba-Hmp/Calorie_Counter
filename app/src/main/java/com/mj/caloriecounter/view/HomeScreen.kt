package com.mj.caloriecounter.view

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
    onNavigateToAddActivity: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    var showSummarySheet by remember { mutableStateOf(false) }
    var showOptionSheet by remember { mutableStateOf(false) }


    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProtein by viewModel.totalProtein.collectAsState()
    val totalCarbs by viewModel.totalCarbs.collectAsState()
    val totalFats by viewModel.totalFats.collectAsState()
    val totalBurntCalories by viewModel.totalBurntCalories.collectAsState()

    var goal by remember { mutableDoubleStateOf(2500.0) }
    val remainingCalories by remember(
        totalCalories,
        totalBurntCalories
    ) { derivedStateOf { goal - totalCalories + totalBurntCalories } }


    Scaffold(
        floatingActionButton = {
            AddButton(
                onClick = {
                    showOptionSheet = true
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,

        ) { innerPadding ->


        if (showOptionSheet) {
            AddOptionsBottomSheet(
                onClose = {
                    showOptionSheet = false
                },
                onFoodClick = {
                    onNavigateToAddFood()
                    showOptionSheet = false
                },
                onActivityClick = {
                    onNavigateToAddActivity()
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
                        progress = { 1f - (remainingCalories.toFloat() / goal.toFloat()).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .size(180.dp)
                            .focusable()
                            .clickable {
                                showSummarySheet = true
                            },
                        trackColor = LightGreenContainer,
                        strokeWidth = 12.dp,
                        color = when {
                            remainingCalories >= goal -> LightGreenContainer
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
                            style = MaterialTheme.typography.bodyMedium
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
                        goal = 140.0,
                        color = ProteinGreen
                    )
                    MacroProgressBar(
                        title = "کربوهیدرات",
                        current = totalCarbs,
                        goal = 250.0,
                        color = CarbYellow
                    )
                    MacroProgressBar(
                        title = "چربی",
                        current = totalFats,
                        goal = 100.0,
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


