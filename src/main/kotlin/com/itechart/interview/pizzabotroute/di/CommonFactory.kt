package com.itechart.interview.pizzabotroute.di

import com.itechart.interview.pizzabotroute.service.input.InputParser
import com.itechart.interview.pizzabotroute.service.input.InputValidator
import com.itechart.interview.pizzabotroute.service.routes.RouteComposer
import com.itechart.interview.pizzabotroute.service.routes.RouteFinderService

class CommonFactory {
    val validator: InputValidator by lazy { InputValidator() }
    val inputParser: InputParser by lazy { InputParser(validator) }
    val routeComposer: RouteComposer by lazy { RouteComposer() }
    val routeFinderService: RouteFinderService by lazy { RouteFinderService(routeComposer) }
}