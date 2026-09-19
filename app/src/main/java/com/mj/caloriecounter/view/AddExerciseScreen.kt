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
import com.mj.caloriecounter.model.Exercise
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.viewModel.AddExerciseViewModel

@Composable
fun AddExerciseScreen(
    viewModel: AddExerciseViewModel = viewModel(),
    onExerciseConfirmed: (exercise: Exercise, time: Int) -> Unit
) {
    var exerciseName by remember { mutableStateOf("") }
    var showTimeSheet by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    AddExerciseContent(
        searchQuery = exerciseName,
        onQueryChange = {
            exerciseName = it
            viewModel.searchExercise(exerciseName)
        },
        searchResults = viewModel.exerciseResultList,
        onExerciseClick = { result ->
            selectedExercise = result
            showTimeSheet = true
        }

    )

    if (showTimeSheet && selectedExercise != null) {
        AddExerciseTimeBottomSheet(
            exercise = selectedExercise!!,
            onClose = { showTimeSheet = false },
            onSaveSuccess = { time ->
                onExerciseConfirmed(selectedExercise!!, time!!)
                showTimeSheet = false
                exerciseName = "" //Clearing the search bar
                viewModel.searchExercise("")

            }

        )
    }
}


@Composable
fun AddExerciseContent(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    searchResults: List<Exercise>,
    onExerciseClick: (Exercise) -> Unit
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
                    text = "جست‌وجوی فعالیت ...",
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
                            onExerciseClick(result)
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
fun AddExercisePreview() {
    CalorieCounterTheme {
        AddExerciseContent(
            "شنا",
            onQueryChange = {},
            searchResults = listOf(
                Exercise(
                    name = "دویدن",
                    burntCaloriesPer1h = 200
                )
            ),
            onExerciseClick = {},
        )
    }
}



