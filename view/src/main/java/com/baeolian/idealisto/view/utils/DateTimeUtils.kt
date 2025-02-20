package com.baeolian.idealisto.view.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

sealed class DateFormat(val format: String) {
    data object Iso : DateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    class Explicit(customFormat: String) : DateFormat(customFormat)
}

fun Date.toFormattedString(
    dateFormat: DateFormat = DateFormat.Iso,
    timeZoneFormat: TimeZoneFormat = TimeZoneFormat.NONE,
): String {
    val formatter = SimpleDateFormat(dateFormat.format, Locale.getDefault())

    when (timeZoneFormat) {
        TimeZoneFormat.NONE -> { /* Do nothing */ }

        TimeZoneFormat.UTC -> { formatter.timeZone = TimeZone.getTimeZone("UTC") }

        TimeZoneFormat.DEVICE -> {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = this
            val deviceTime = calendar.timeInMillis + TimeZone.getDefault().getOffset(calendar.timeInMillis)
            return formatter.format(Date(deviceTime))
        }
    }

    return formatter.format(this)
}

enum class TimeZoneFormat {
    NONE, UTC, DEVICE
}
