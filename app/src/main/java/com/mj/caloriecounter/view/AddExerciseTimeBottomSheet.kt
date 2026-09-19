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
import com.mj.caloriecounter.model.Exercise
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.utils.timeCalculator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseTimeBottomSheet(
    onClose: () -> Unit,
    exercise: Exercise,
    onSaveSuccess: (Int?) -> Unit
) {
    val context = LocalContext.current
    var exerciseTime by remember { mutableStateOf("") }

    AddExerciseTimeContent(
        exercise = exercise,
        exerciseTime = exerciseTime,
        onTimeChange = { exerciseTime = it },
        onConfirmClick = { time ->
            Toast.makeText(context, "ثبت شد", Toast.LENGTH_SHORT).show()
            onSaveSuccess(time)
        },
        onClose = onClose,
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseTimeContent(
    exercise: Exercise,
    exerciseTime: String,
    onTimeChange: (String) -> Unit,
    onConfirmClick: (Int?) -> Unit,
    onClose: () -> Unit
) {
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
                text = exercise.name,
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
                        .fillMaxWidth(fraction = 0.75f)
                        .padding(bottom = 20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = exerciseTime,
                    onValueChange = {
                        onTimeChange(it)
                    },
                    placeholder = {
                        Text(
                            text = "زمان فعالیت برحسب دقیقه",
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
                val time = exerciseTime.toIntOrNull()
                if (time != null) {
                    Text(
                        text = "کالری سوزانده شده: ${
                            timeCalculator(
                                time,
                                exercise.burntCaloriesPer1h
                            )
                        }",
                        style = MaterialTheme.typography.titleLarge
                    )

                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenPrimary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    ),
                    onClick = {
                        onConfirmClick(time)
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

@Preview(showSystemUi = true, device = Devices.PIXEL_8)
@Composable
fun AddExerciseTimePreview() {
    CalorieCounterTheme {
        // Putting it inside a full screen Box forces the BottomSheet to open up on top of it!
        Box(modifier = Modifier.fillMaxSize()) {
            AddExerciseTimeContent(
                exercise = Exercise(name = "دویدن سریع", burntCaloriesPer1h = 600),
                exerciseTime = "55",
                onTimeChange = {},
                onConfirmClick = {},
                onClose = {}
            )
        }
    }
}




