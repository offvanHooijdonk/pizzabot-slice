package com.itechart.interview.pizzabotroute.service.input

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class AreaSizeValidatorTest {
    private val validator = AreaSizeValidator()

    @Test
    fun `test validate valid inputs`() {
        assertDoesNotThrow { validator.validate(listOf("14x12")) }
        assertDoesNotThrow { validator.validate(listOf("1x1")) }
        assertDoesNotThrow { validator.validate(listOf("10x5")) }
        assertDoesNotThrow { validator.validate(listOf("42x20")) }
        assertDoesNotThrow { validator.validate(listOf("210x108")) }
    }

    @Test
    fun `test invalid inputs`() {
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("0x5")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("6x0")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("0x0")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("x5")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("4x")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("x")) }

        assertThrows(InputValidationException::class.java) { validator.validate(listOf("rx5")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("6xa")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("(x0")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("ktx)")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("4duh")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("7560")) }
        assertThrows(InputValidationException::class.java) { validator.validate(listOf("")) }
    }

}