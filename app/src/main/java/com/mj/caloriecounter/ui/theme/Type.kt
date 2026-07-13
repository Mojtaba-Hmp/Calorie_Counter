package com.mj.caloriecounter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mj.caloriecounter.R


// Set of Material typography styles to start with

val Vazir = FontFamily(
    Font(
        R.font.vazir,
        FontWeight.Normal
    )
)

val Typography = Typography(

    // عدد کالری اصلی وسط صفحه
    displaySmall = TextStyle(
        fontFamily = Vazir,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),

    // عنوان صفحه
    headlineSmall = TextStyle(
        fontFamily = Vazir,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    // عنوان کارت غذا
    titleLarge = TextStyle(
        fontFamily = Vazir,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Vazir,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),

    // متن معمولی
    bodyLarge = TextStyle(
        fontFamily = Vazir,
        fontSize = 16.sp
    ),

    // توضیحات کوچک
    bodyMedium = TextStyle(
        fontFamily = Vazir,
        fontSize = 14.sp
    ),

    // متن دکمه‌ها
    labelLarge = TextStyle(
        fontFamily = Vazir,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)
