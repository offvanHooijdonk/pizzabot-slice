package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel

/**
 * Parses input into models with validation via [InputValidator]
 */
class InputParser(private val validator: InputValidator) {
    /**
     * @return [DeliveryModel]: Model of the input delivery info
     * @throws InputValidationException
     */
    fun parseInput(input: List<String>): DeliveryModel {
        validator.validateParamsNumber(input)
        validator.validateAreaSize(input)

        val area = AreaSizeParser().parse(input[0])

        val locationsInput = input.subList(1, input.size)
        validator.validateLocationsFormat(locationsInput)

        val locations = LocationsParser().parse(locationsInput)
        validator.validateLocationsByArea(area, locations)

        return DeliveryModel(area, locations)
    }

    class AreaSizeParser {
        fun parse(input: String): AreaModel {
            val separatorIndex = input.indexOf(SIZE_SEPARATOR)
            val x = input.substring(0 until separatorIndex).trim()
            val y = input.substring(separatorIndex + 1 until input.length).trim()

            return AreaModel(x.toInt(), y.toInt())
        }

        companion object {
            private const val SIZE_SEPARATOR = "x"
        }
    }

    class LocationsParser {
        fun parse(inputLocations: List<String>): List<DeliveryPointModel> =
            inputLocations.map { parseSingle(it) }

        private fun parseSingle(input: String): DeliveryPointModel {
            val startIndex = input.indexOf(START_SEPARATOR).checkFound()
            val separatorIndex = input.indexOf(COORDINATE_SEPARATOR, startIndex).checkFound()
            val endIndex = input.indexOf(END_SEPARATOR, separatorIndex).checkFound()

            val x = input.substring(startIndex + 1 until separatorIndex).trim()
            val y = input.substring(separatorIndex + 1 until endIndex).trim()

            return DeliveryPointModel(x.toInt(), y.toInt())
        }

        companion object {
            private const val START_SEPARATOR = "("
            private const val COORDINATE_SEPARATOR = ","
            private const val END_SEPARATOR = ")"
        }
    }

}

private fun Int.checkFound(): Int { if (this > -1) return this else throw Exception("Input format violation, expected (1, 1) format") }

