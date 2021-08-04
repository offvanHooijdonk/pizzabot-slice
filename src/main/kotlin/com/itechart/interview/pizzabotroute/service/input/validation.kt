package com.itechart.interview.pizzabotroute.service.input

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel

/** Runs validations on the input parameters and throws [InputValidationException] in case of error
 * specifying failure reason. If success - no output, the method returns normally.
 */
class InputValidator(
    private val areaSizeValidator:AreaSizeValidator = AreaSizeValidator(),
    private val paramsNumberValidator:ParamsNumberValidator = ParamsNumberValidator(),
    private val locationsValidator:LocationsValidator = LocationsValidator(),
    private val locationsByAreaValidator:LocationsByAreaValidator = LocationsByAreaValidator(),
) {
    fun validateAreaSize(input: List<String>) {
        areaSizeValidator.validate(input)
    }

    fun validateParamsNumber(input: List<String>) {
        paramsNumberValidator.validate(input)
    }

    fun validateLocationsFormat(input: List<String>) {
        locationsValidator.validate(input)
    }

    fun validateLocationsByArea(area: AreaModel, locations: List<DeliveryPointModel>) {
        locationsByAreaValidator.validate(area, locations)
    }
}

class InputValidationException(failureReason: String) : Exception(failureReason)

/**
 * Validates size format of the Area, on which Pizzabot™ operates
 */
class AreaSizeValidator {
    fun validate(input: List<String>) {
        val areaString = input[0]
        if (!Regex(REGEXP_AREA_SIZE).matches(areaString)) {
            throw InputValidationException("Area size input is invalid: $areaString . Please comply the pattern like 14x20.")
        }
    }

    companion object {
        const val REGEXP_AREA_SIZE = "^[1-9][0-9]*x[1-9][0-9]*\$"
    }
}

/**
 * Validates input parameters number
 */
class ParamsNumberValidator {
    fun validate(input: List<String>) {
        if (input.size < 2) {
            throw InputValidationException("Minimum parameters number is $MIN_INPUT_SIZE while ${input.size} parameters provided.")
        }
    }

    companion object {
        private const val MIN_INPUT_SIZE = 2
    }
}

/**
 * Validates locations format
 */
class LocationsValidator {
    fun validate(input: List<String>) {
        input.forEach { validateSingle(it) }
    }

    fun validateSingle(input: String) {
        if (!Regex(LOCATION_REGEX).matches(input)) {
            throw InputValidationException("Location coordinates have invalid format at: $input")
        }
    }

    companion object {
        private const val LOCATION_REGEX = "^\\(\\d+, *\\d+\\)$"
    }
}

/**
 * Validates that locations are not placed outside the Area
 */
class LocationsByAreaValidator {
    fun validate(area: AreaModel, locations: List<DeliveryPointModel>) {
        locations.forEach { loc ->
            if (loc.x > area.x || loc.y > area.y) {
                throw InputValidationException("Location coordinates $loc are out of the area $area")
            }
        }
    }
}
