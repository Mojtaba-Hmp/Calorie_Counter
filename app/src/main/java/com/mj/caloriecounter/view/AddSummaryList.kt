package com.mj.caloriecounter.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise
import com.mj.caloriecounter.ui.theme.Dimens.Large
import com.mj.caloriecounter.utils.LogEntry
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
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
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
                            .heightIn(min = 64.dp)    //minHeight
                            .padding(top = 10.dp, start = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.3f),
                            textAlign = TextAlign.Center,
                            text = result.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(
                            modifier = Modifier.padding(end = 30.dp)
                        )
                        Text(
                            text = middleValue.toPersianDigits(),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(
                            modifier = Modifier.padding(end = 50.dp)
                        )
                        Text(
                            text = "${result.calories.toInt()} کالری".toPersianDigits(),
                            style = MaterialTheme.typography.bodyLarge,
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
