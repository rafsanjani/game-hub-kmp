package com.foreverrafs.gamehub

import com.foreverrafs.gamehub.data.format
import kotlinx.datetime.LocalDate
import kotlin.test.Test

class GameRepositoryTest {
    @Test
    fun testDateFormat() {
        val date =LocalDate.parse("2023-06-01")
            .format("dd.MM.yy")
        println(date)
    }
}