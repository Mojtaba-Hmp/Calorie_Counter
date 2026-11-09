package com.mj.caloriecounter.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.model.dataclasses.ConsumedFood
import com.mj.caloriecounter.model.dataclasses.FinishedExercise
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

    var itemToDelete by remember { mutableStateOf<LogEntry?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
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
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
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
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(
                            modifier = Modifier.padding(end = 30.dp)
                        )
                        Text(
                            text = middleValue.toPersianDigits(),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Spacer(
                            modifier = Modifier.padding(end = 50.dp)
                        )
                        Text(
                            text = "${result.calories.toInt()} کالری".toPersianDigits(),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            tint = Color.Gray,
                            contentDescription = "Delete",
                            modifier = Modifier
                                .size(Large)
                                .clickable {
                                    itemToDelete = result
                                }
                        )
                    }
                }
            }
        }
    }

    DeleteConfirmation(
        itemToDelete = itemToDelete,
        onDismissRequest = { itemToDelete = null },
        title = "حذف آیتم",
        text = "آیا از حذف «${itemToDelete?.name}» اطمینان دارید؟",
        onDelete = {
            itemToDelete?.let { onDelete(it) }
            itemToDelete = null
        }
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DeleteConfirmation(
    itemToDelete: LogEntry?,
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    onDelete: () -> Unit
) {
    if (itemToDelete != null) {
        BasicAlertDialog(
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.surface),
            onDismissRequest = onDismissRequest,
            content = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        TextButton(
                            modifier = Modifier.border(
                                2.dp,
                                Color.Red,
                                RoundedCornerShape(50.dp)
                            ),
                            onClick = onDelete,
                            colors = ButtonDefaults.textButtonColors(Color.Red),
                        ) {
                            Text(
                                text = "حذف",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.surface

                            )
                        }

                        TextButton(
                            modifier = Modifier.border
                                (
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(50.dp)
                            ),
                            onClick = onDismissRequest,
                        ) {
                            Text(
                                text = "انصراف",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }

                    }
                }
            }
        )
    }
}
