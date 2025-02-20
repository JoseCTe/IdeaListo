package com.baeolian.idealisto.view.utils

import java.text.DecimalFormat

fun Int.toEuroFormat(): String? {
    val decimalFormat = DecimalFormat("#,###")
    return try {
        decimalFormat.format(this)
    } catch (e: Exception) {
        null
    }
}
