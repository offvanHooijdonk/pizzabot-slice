package com.itechart.interview.pizzabotroute

import com.itechart.interview.pizzabotroute.di.CommonFactory
import com.itechart.interview.pizzabotroute.service.input.InputParser
import com.itechart.interview.pizzabotroute.service.input.InputValidationException
import com.itechart.interview.pizzabotroute.model.DeliveryModel
import com.itechart.interview.pizzabotroute.service.routes.RouteFinderService

fun main(args: Array<String>) {
    val factory = CommonFactory()

    val deliveryModel = try {
        parseInput(args, factory.inputParser)
    } catch (e: InputValidationException) {
        println("""Validation failed: ${e.message}
            Exiting..
        """.trimMargin())
        return
    }

    val resultRoute = getDeliveryRoute(deliveryModel, factory.routeFinderService)

    println("""
        Route is built for the Pizzabot!
        |$resultRoute
        """.trimMargin())
}

/**
 * @throws [InputValidationException]
 */
private fun parseInput(input: Array<String>, parser: InputParser): DeliveryModel = parser.parseInput(input.asList())

private fun getDeliveryRoute(deliveryModel: DeliveryModel, service: RouteFinderService) = service.findPath(deliveryModel)