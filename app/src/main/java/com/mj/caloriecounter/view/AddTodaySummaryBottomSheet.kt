package com.mj.caloriecounter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise
import com.mj.caloriecounter.ui.theme.CalorieCounterTheme
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.viewModel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodaySummaryBottomSheet(
    onClose: () -> Unit,
    viewModel: HomeViewModel
) {

    // Collect the flows here
    val consumedFoods by viewModel.consumedFoods.collectAsState()
    val finishedExercises by viewModel.finishedExercises.collectAsState()

    AddTodaySummaryContent(
        consumedFoods = consumedFoods,
        finishedExercises = finishedExercises,
        onDeleteFood = { viewModel.deleteFood(it) },
        onDeleteExercise = { viewModel.deleteExercise(it) },
        onClose = onClose
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodaySummaryContent(
    consumedFoods: List<ConsumedFood>,
    finishedExercises: List<FinishedExercise>,
    onDeleteFood: (ConsumedFood) -> Unit,
    onDeleteExercise: (FinishedExercise) -> Unit,
    onClose: () -> Unit
){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // This forces it to open fully
    )
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(0.62f)
        ) {
            @OptIn(ExperimentalMaterial3Api::class)
            (SecondaryTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White,
                contentColor = GreenPrimary,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(pagerState.currentPage),
                        color = GreenPrimary
                    )
                })
            {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                    text = {
                        Text(
                            "غذاها",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    })
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
                    text = {
                        Text(
                            "فعالیت‌ها",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    })
            })

            Column(
                modifier = Modifier.padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "امروز",
                    style = MaterialTheme.typography.titleLarge
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 10.dp),
                    DividerDefaults.Thickness,
                    DividerDefaults.color
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "نام",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = if (pagerState.currentPage == 0) "مقدار" else "زمان",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "انرژی",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.Top
            ) { page ->
                if (page == 0) {
                    AddSummaryList(
                        items = consumedFoods,
                        emptyMessage = "امروز غذایی ثبت نکرده‌اید!",
                        unit = "گرم",
                        onDelete = { entry ->
                            if (entry is ConsumedFood) {
                                onDeleteFood(entry)
                            }
                        }
                    )
                } else {
                    AddSummaryList(
                        items = finishedExercises,
                        emptyMessage = "امروز فعالیتی ثبت نکرده‌اید!",
                        unit = "دقیقه",
                        onDelete = { entry ->
                            if (entry is FinishedExercise) {
                                onDeleteExercise(entry)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_8
)
@Composable
fun AddTodaySummaryPreview() {
    // Wrap it in your theme so colors look correct
    CalorieCounterTheme {
        AddTodaySummaryContent(
            consumedFoods = listOf(
                ConsumedFood(name = "سیب", amount = 100.0, calories = 52.0)
            ),
            finishedExercises = listOf(
                FinishedExercise(name = "پیاده‌روی", time = 30, calories = 150.0)
            ),
            onDeleteFood = {},
            onDeleteExercise = {},
            onClose = {}
        )
    }
}



