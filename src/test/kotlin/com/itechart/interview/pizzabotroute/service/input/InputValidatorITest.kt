package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class InputValidatorITest {
    private val areaSizeValidator = mock(AreaSizeValidator::class.java)
    private val locationsValidator = mock(LocationsValidator::class.java)
    private val locationsByAreaValidator = mock(LocationsByAreaValidator::class.java)
    private val paramsNumberValidator = mock(ParamsNumberValidator::class.java)
    private val inputValidator = InputValidator(
        areaSizeValidator, paramsNumberValidator, locationsValidator, locationsByAreaValidator
    )

    private val inputSample = listOf("123", "qwerty")

    @Test
    fun `test parameters validator called`() {
        inputValidator.validateParamsNumber(inputSample)
        verify(paramsNumberValidator).validate(inputSample)
    }

    @Test
    fun `test area size validator called`() {
        inputValidator.validateAreaSize(inputSample)
        verify(areaSizeValidator).validate(inputSample)
    }

    @Test
    fun `test locations format validator called`() {
        inputValidator.validateLocationsFormat(inputSample)
        verify(locationsValidator).validate(inputSample)
    }

    @Test
    fun `test locations by area validator called`() {
        val area = AreaModel(3, 16)
        val locations = listOf(DeliveryPointModel(5, 10))
        inputValidator.validateLocationsByArea(area, locations)
        verify(locationsByAreaValidator).validate(area, locations)
    }
}