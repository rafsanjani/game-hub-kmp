package com.foreverrafs.gamehub.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSDateFormatter

actual fun LocalDate.format(format: String): String {
    val formatter = NSDateFormatter().apply { dateFormat = format }

    return formatter.stringFromDate(
        toNSDateComponents().date
            ?: throw IllegalStateException("Error converting Kotlin LocalDate to NSDate")
    )
}
