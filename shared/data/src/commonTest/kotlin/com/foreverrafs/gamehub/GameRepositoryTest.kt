package com.foreverrafs.gamehub

import com.foreverrafs.gamehub.data.format
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test

class GameRepositoryTest {
    @Test
    fun testDateFormat() {
        val date =
            Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).date.format("dd-MM-yyyy")

        println(date)
    }
}