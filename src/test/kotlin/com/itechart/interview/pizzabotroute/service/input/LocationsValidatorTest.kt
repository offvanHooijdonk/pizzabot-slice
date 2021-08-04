package com.itechart.interview.pizzabotroute.service.input

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows

class LocationsValidatorTest {
    private val validator = LocationsValidator()

    @Test
    fun `test valid inputs`() {
        assertDoesNotThrow { validator.validateSingle("(1,5)") }
        assertDoesNotThrow { validator.validateSingle("(10,5)") }
        assertDoesNotThrow { validator.validateSingle("(10, 5)") }
        assertDoesNotThrow { validator.validateSingle("(10,   5)") }
        assertDoesNotThrow { validator.validateSingle("(1,50)") }
        assertDoesNotThrow { validator.validateSingle("(01,50)") }
        assertDoesNotThrow { validator.validateSingle("(1,050)") }
        assertDoesNotThrow { validator.validateSingle("(0,12)") }
        assertDoesNotThrow { validator.validateSingle("(8,0)") }
        assertDoesNotThrow { validator.validateSingle("(0,0)") }
        assertDoesNotThrow { validator.validate(listOf("(1,5)", "(1, 50)", "(10, 5)", )) }
    }

    @Test
    fun `test invalid inputs`() {
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(1.5)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("1,5)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(-1, 5)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(1,-5)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(, 5)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(1,)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(s,1)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(1,r)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(p,e)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("(,)") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle(",") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("()") }
        assertThrows(InputValidationException::class.java) { validator.validateSingle("") }
    }
}