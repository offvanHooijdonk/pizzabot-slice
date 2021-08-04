package com.itechart.interview.pizzabotroute.service.input

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.random.Random

class ParamsNumberValidatorTest {
    private val validator = ParamsNumberValidator()

    @Test
    fun `test valid inputs`() {
        val minSize = 2
        assertDoesNotThrow { validator.validate(Array(minSize) { "" }.toList()) } // test with minimal number
        for (i in 1..5) {
            val size = Random.nextInt(0, 20) + minSize
            assertDoesNotThrow { validator.validate(Array(size) { "" }.toList()) }
        }
    }

    @Test
    fun `test invalid size`() {
        assertThrows(InputValidationException::class.java) { validator.validate(emptyList()) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("")) }
    }
}