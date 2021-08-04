package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class InputParserServiceITest {
    private val inputValidator = mock(InputValidator::class.java)
    private val parser = InputParser(inputValidator)

    @Test
    fun `test validation is called when parsing`() {
        val input = listOf("5x5", "(7,3)", "(2, 1)")
        //val inputNormalized = listOf("5x5", "7,3", "2,1")
        parser.parseInput(input)

        verify(inputValidator).validateParamsNumber(input)
        verify(inputValidator).validateAreaSize(input)
        verify(inputValidator).validateLocationsFormat(input.slice(1 until input.size))
        verify(inputValidator).validateLocationsByArea(
            AreaModel(5, 5),
            listOf(DeliveryPointModel(7, 3), DeliveryPointModel(2, 1))
        )
    }
}