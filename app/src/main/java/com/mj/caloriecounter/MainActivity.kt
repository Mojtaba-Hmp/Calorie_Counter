package com.mj.caloriecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.view.AddExerciseScreen
import com.mj.caloriecounter.view.AddFoodScreen
import com.mj.caloriecounter.view.HomeScreen
import com.mj.caloriecounter.viewModel.HomeViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalorieCounterTheme {
                MainNavigation()
            }
        }
    }

    @Composable
    fun MainNavigation() {
        val backStack = remember { mutableStateListOf<Any>(Screen.Home) }

        val homeViewModel: HomeViewModel = viewModel()
        NavDisplay(
            backStack = backStack,
            onBack = {
                if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
            },
            entryProvider = { key ->
                when (key) {
                    is Screen.Home -> NavEntry(key) {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onNavigateToAddFood = {
                                backStack.add(Screen.AddFood)
                            },
                            onNavigateToAddActivity = {
                                backStack.add(Screen.AddActivity)
                            }
                        )
                    }

                    is Screen.AddFood -> NavEntry(key) {
                        AddFoodScreen(
                            onFoodConfirmed ={ food,amount ->
                                homeViewModel.addConsumedFood(food,amount)
                            }
                        )
                    }

                    is Screen.AddActivity -> NavEntry(key) {
                        AddExerciseScreen (
                            onExerciseConfirmed ={ exercise,time ->
                                homeViewModel.addFinishedExercised(exercise,time)
                            }
                        )
                    }

                    else -> NavEntry(key) { Text("Unknown Screen") }
                }
            }
        )
    }
}

// 3. Screen keys should be Serializable
@Serializable
sealed class Screen {
    @Serializable
    object Home : Screen()

    @Serializable
    object AddFood : Screen()

    @Serializable
    object AddActivity : Screen()
}