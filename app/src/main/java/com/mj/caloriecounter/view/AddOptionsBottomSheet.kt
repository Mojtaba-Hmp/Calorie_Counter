package com.mj.caloriecounter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mj.caloriecounter.ui.theme.Dimens.iconLarge
import com.mj.caloriecounter.ui.theme.GreenPrimary
import com.mj.caloriecounter.ui.theme.LightGreenContainer
import com.mj.caloriecounter.ui.theme.TealSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOptionsBottomSheet(onClose: () -> Unit, onFoodClick: () -> Unit,onActivityClick: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onClose,
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            AddOptionCard(
                "غذا",
                Icons.Default.Restaurant,
                GreenPrimary,
                "AddFood",
                onClick = onFoodClick
            )

            AddOptionCard(
                "فعالیت",
                Icons.Default.FitnessCenter,
                TealSecondary,
                "AddActivity",
                onClick = onActivityClick
            )
        }
    }
}

@Composable
fun AddOptionCard(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    contentDescription: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = LightGreenContainer
        ),
    ) {
        Column(
            modifier = Modifier.size(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                Modifier.size(iconLarge),
                iconColor
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

