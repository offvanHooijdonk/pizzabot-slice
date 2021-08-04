package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class LocationsByAreaValidatorTest {
    private val validator = LocationsByAreaValidator()
    private val area = AreaModel(9, 9)

    private val location1 = DeliveryPointModel(1, 1)
    private val location3 = DeliveryPointModel(3, 3)
    private val location9 = DeliveryPointModel(9, 9)
    private val locationX10 = DeliveryPointModel(10, 9)
    private val locationY10 = DeliveryPointModel(9, 10)
    private val location23 = DeliveryPointModel(23, 23)

    @Test
    fun `test valid values`() {
        assertDoesNotThrow { validator.validate(area, listOf(location1)) }
        assertDoesNotThrow { validator.validate(area, listOf(location1, location3, location9)) }
    }

    @Test
    fun `test invalid inputs`() {
        assertThrows(InputValidationException::class.java) { validator.validate(area, listOf(
            locationX10
        )) }
        assertThrows(InputValidationException::class.java) { validator.validate(area, listOf(
            locationY10
        )) }
        assertThrows(InputValidationException::class.java) { validator.validate(area, listOf(
            location23
        )) }
        assertThrows(InputValidationException::class.java) { validator.validate(area, listOf(
            locationX10, locationY10, location23
        )) }
        assertThrows(InputValidationException::class.java) { validator.validate(area, listOf(
            location3, locationX10
        )) }
    }
}