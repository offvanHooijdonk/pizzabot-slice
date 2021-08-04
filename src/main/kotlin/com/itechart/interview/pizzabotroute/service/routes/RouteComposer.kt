package com.itechart.interview.pizzabotroute.service.routes

import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import java.lang.StringBuilder
import kotlin.math.abs

class RouteComposer {
    fun composePlanForRoute(startLocation: DeliveryPointModel, destLocation: DeliveryPointModel): String {
        val route = StringBuilder()
        val deltaX = destLocation.x - startLocation.x
        val deltaY = destLocation.y - startLocation.y

        val dirCodeX = if (deltaX > 0) Actions.EAST.code else Actions.WEST.code
        val dirCodeY = if (deltaY > 0) Actions.NORTH.code else Actions.SOUTH.code

        for (i in 1..abs(deltaX)) route.append(dirCodeX)
        for (i in 1..abs(deltaY)) route.append(dirCodeY)
        route.append(Actions.DROP_PIZZA.code)

        return route.toString()
    }

    private enum class Actions(val code: String) {
        NORTH("N"), EAST("E"), SOUTH("S"), WEST("W"), DROP_PIZZA("D")
    }
}