package com.mj.caloriecounter.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.utils.LogEntry
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise
import com.mj.caloriecounter.ui.theme.Dimens.Large
import com.mj.caloriecounter.utils.toPersianDigits


@Composable
fun AddSummaryList(
    items: List<LogEntry>,
    emptyMessage: String,
    unit: String,
    onDelete: (LogEntry) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (items.isEmpty()) {
            item {
                Text(
                    text = emptyMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {

            items(items) { result ->

                val middleValue = when (result) {
                    is ConsumedFood -> "${result.amount.toInt()} $unit"
                    is FinishedExercise -> "${result.time} $unit"
                    else -> ""
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = result.name,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(
                            modifier = Modifier.padding(end = 20.dp)
                        )
                        Text(
                            text = middleValue.toPersianDigits(),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "${result.calories.toInt()} کالری".toPersianDigits(),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            tint = Color.Gray,
                            contentDescription = "Delete",
                            modifier = Modifier
                                .size(Large)
                                .clickable {
                                    onDelete(result)
                                }
                        )
                    }
                }
            }
        }
    }
}
