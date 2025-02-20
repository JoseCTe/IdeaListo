package com.baeolian.idealisto.view.utils

import com.baeolian.idealisto.view.R
import java.text.DecimalFormat

fun Int.toEuroFormat(): String? {
    val decimalFormat = DecimalFormat("#,###")
    return try {
        decimalFormat.format(this)
    } catch (e: Exception) {
        null
    }
}

fun String.toCountryFormat(): Country? {
    return when (this) {
        Country.Spain.countryCode -> Country.Spain
        else -> null
    }
}

fun Country.countryToResourceFormat(): Int {
    return when (this) {
        Country.Spain -> R.string.country_spain
    }
}

enum class Country(val countryCode: String) {
    Spain("es")
}
