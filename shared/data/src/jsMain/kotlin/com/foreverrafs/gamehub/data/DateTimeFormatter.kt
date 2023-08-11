package com.foreverrafs.gamehub.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.internal.JSJoda.DateTimeFormatter

actual fun LocalDate.format(format: String): String {
    val date = kotlinx.datetime.internal.JSJoda.LocalDate.parse(
        text = this.toString(),
    )

    return DateTimeFormatter.ofPattern(format).format(date)
}