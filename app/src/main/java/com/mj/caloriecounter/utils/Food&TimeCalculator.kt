package com.mj.caloriecounter.utils


import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun macroCalculator(
    foodAmount: Double,
    macro: Double
): String {
    val symbols = DecimalFormatSymbols(Locale.US)

    val df = DecimalFormat("#.#", symbols)

    return df.format((foodAmount * macro) / 100)
}

fun timeCalculator(
    activityTime: Int,
    calories: Int
): Int {

    return (activityTime * calories) / 60
}

