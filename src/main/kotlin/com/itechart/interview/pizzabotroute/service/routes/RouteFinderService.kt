package com.itechart.interview.pizzabotroute.service.routes

import com.itechart.interview.pizzabotroute.model.DeliveryModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import java.lang.StringBuilder
import java.util.*
import kotlin.math.abs

class RouteFinderService(private val routeComposer: RouteComposer) {
    fun findPath(delivery: DeliveryModel): String {
        val points = LinkedList(delivery.deliveryPoints)
        var currentPoint = STARTING_POINT_DEFAULT
        val deliveryPlan = StringBuilder("")

        for (i in delivery.deliveryPoints.indices) {
            if (points.isEmpty()) {
                throw Exception("Unexpected end of delivery points list while building a route!")
            }
            val closestPoint = findClosestRoutePoint(currentPoint, points)
            deliveryPlan.append(routeComposer.composePlanForRoute(currentPoint, closestPoint))

            points.remove(closestPoint)
            currentPoint = closestPoint
        }

        return deliveryPlan.toString()
    }

    private fun findClosestRoutePoint(currentPoint: DeliveryPointModel, deliveryPoints: LinkedList<DeliveryPointModel>): DeliveryPointModel {
        var found = deliveryPoints.first
        var distanceFound = calculateRouteDistance(currentPoint, found)
        deliveryPoints.forEach { loc ->
            val distance = calculateRouteDistance(currentPoint, loc)
            if (distance < distanceFound) {
                found = loc
                distanceFound = distance
            }
        }
        return found
    }

    private fun calculateRouteDistance(startDeliveryPointModel: DeliveryPointModel, endDeliveryPointModel: DeliveryPointModel): Int =
        abs(endDeliveryPointModel.x - startDeliveryPointModel.x) + abs(endDeliveryPointModel.y - startDeliveryPointModel.y)

    companion object {
        private val STARTING_POINT_DEFAULT = DeliveryPointModel(0, 0)
    }
}