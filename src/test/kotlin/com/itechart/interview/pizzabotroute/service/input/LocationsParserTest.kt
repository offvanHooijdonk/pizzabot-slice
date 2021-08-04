package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.random.Random

class LocationsParserTest {
    private val parser = InputParser.LocationsParser()

    @Test
    fun `test random correct input is parsed`() {
        for (i in 1..10) {
            val size = Random.nextInt(1, 20)
            assertEquals(generateLocationsParsed(size), parser.parse(generateLocationsInput(size)))
        }
    }

    @Test
    fun `test empty list input results in empty list`() {
        assertEquals(emptyList<DeliveryPointModel>(), parser.parse(emptyList()))
    }

    @Test
    fun `test invalid input leads to exceptions`() {
        assertThrows(Exception::class.java) { parser.parse(listOf("(1.3)")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("(1,u)")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("(,5)")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("(78)")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("7, 8)")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("(7, 8")) }
        assertThrows(Exception::class.java) { parser.parse(listOf("(8,2)", "(,5)")) }
    }

    companion object {
        private fun generateLocationsInput(size: Int) = Array(size) { i -> "($i, $i)" }.asList()

        private fun generateLocationsParsed(size: Int) = Array(size) { i -> DeliveryPointModel(i, i) }.asList()
    }
}