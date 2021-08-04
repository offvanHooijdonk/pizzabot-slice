package com.itechart.interview.pizzabotroute.service.routes

import com.itechart.interview.pizzabotroute.model.AreaModel
import com.itechart.interview.pizzabotroute.model.DeliveryModel
import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RouteFinderServiceTest {
    private val service = RouteFinderService(RouteComposer())

    @Test
    fun `test correct route built`() {
        assertEquals(
            "ENND",
            service.findPath(
                DeliveryModel(
                    AreaModel(8, 8),
                    listOf(
                        DeliveryPointModel(1, 2),
                    )
                )
            )
        )
        assertEquals(
            "EENNDND",
            service.findPath(
                DeliveryModel(
                    AreaModel(8, 8),
                    listOf(
                        DeliveryPointModel(2, 3),
                        DeliveryPointModel(2, 2),
                    )
                )
            )
        )
        assertEquals(
            "EEEEENDWWNNNDENND",
            service.findPath(
                DeliveryModel(
                    AreaModel(8, 8),
                    listOf(
                        DeliveryPointModel(3, 4),
                        DeliveryPointModel(5, 1),
                        DeliveryPointModel(4, 6),
                    )
                )
            )
        )
    }

    @Test
    fun `test route is build by the nearest locations`() { // test for case when there is only one nearest location to any location

        val area = AreaModel(10, 10)
        val locations = Array(9) { i -> DeliveryPointModel(i + 1, i + 1) }.toList()

        for (i in 1..10) {
            assertEquals(
                service.findPath(DeliveryModel(area, locations)),
                service.findPath(DeliveryModel(area, locations.shuffled()))
            )
        }
    }

    @Test
    fun `test same location are not lost`() {
        assertEquals(
            "DD",
            service.findPath(
                DeliveryModel(
                    AreaModel(10, 10),
                    listOf(DeliveryPointModel(0, 0), DeliveryPointModel(0, 0)),
                )
            )
        )
        assertEquals(
            "EENNNDD",
            service.findPath(
                DeliveryModel(
                    AreaModel(10, 10),
                    listOf(DeliveryPointModel(2, 3), DeliveryPointModel(2, 3))
                )
            )
        )
        assertEquals(
            "EENNNDDEESSD",
            service.findPath(
                DeliveryModel(
                    AreaModel(10, 10),
                    listOf(DeliveryPointModel(2, 3), DeliveryPointModel(4, 1), DeliveryPointModel(2, 3))
                )
            )
        )
    }
}