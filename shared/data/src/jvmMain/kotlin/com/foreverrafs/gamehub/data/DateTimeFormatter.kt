package com.foreverrafs.gamehub.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

actual fun LocalDate.format(format: String): String {
    return DateTimeFormatter.ofPattern(format).format(this.toJavaLocalDate())
}