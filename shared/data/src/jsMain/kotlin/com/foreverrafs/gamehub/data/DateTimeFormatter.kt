package com.foreverrafs.gamehub.data

import kotlinx.datetime.LocalDate

actual fun LocalDate.format(format: String): String {
    return jsFormatDateTime(
        year,
        month.ordinal,
        dayOfMonth,
        "en-US"
    )
}

@Suppress("UNUSED_PARAMETER", "LongParameterList")
private fun jsFormatDateTime(
    yearArg: Int,
    monthArg: Int,
    dayArg: Int,
    locale: String
): String =
    js(
        "(new Date(yearArg, monthArg, dayArg))" +
                ".toLocaleDateString(locale, {year: 'numeric', month: 'long', day: '2-digit'});"
    ) as String