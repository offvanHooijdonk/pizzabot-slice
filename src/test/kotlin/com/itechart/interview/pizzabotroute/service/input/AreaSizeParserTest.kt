package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.random.Random

class AreaSizeParserTest {
    private val parser = InputParser.AreaSizeParser()

    @Test
    fun `test random int values formatted are parsed correctly`() {
        for (i in 1..10) {
            val x = Random.nextInt()
            val y = Random.nextInt()
            assertEquals(AreaModel(x, y), parser.parse("${x}x$y"))
        }
    }

    @Test
    fun `test incorrect format fails parsing`() {
        assertThrows(Exception::class.java) { parser.parse("") }
        assertThrows(Exception::class.java) { parser.parse("3") }
        assertThrows(Exception::class.java) { parser.parse("3x5x8") }
    }
}