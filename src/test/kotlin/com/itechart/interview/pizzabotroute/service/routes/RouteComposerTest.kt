package com.itechart.interview.pizzabotroute.service.routes

import com.itechart.interview.pizzabotroute.model.DeliveryPointModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RouteComposerTest {
    private val composer = RouteComposer()

    @Test
    fun `test route builds correctly`() {
        assertEquals("EEEED", composer.composePlanForRoute(
            startLocation = DeliveryPointModel(0,0), destLocation = DeliveryPointModel(4,0)
        ))
        assertEquals("NNND", composer.composePlanForRoute(
            startLocation = DeliveryPointModel(0,0), destLocation = DeliveryPointModel(0,3)
        ))
        assertEquals("EEENND", composer.composePlanForRoute(
            startLocation = DeliveryPointModel(0,0), destLocation = DeliveryPointModel(3,2)
        ))
        assertEquals("WND", composer.composePlanForRoute(
            startLocation = DeliveryPointModel(2,1), destLocation = DeliveryPointModel(1,2)
        ))
        assertEquals("EEEESSSSSD", composer.composePlanForRoute(
            startLocation = DeliveryPointModel(5,8), destLocation = DeliveryPointModel(9,3)
        ))

    }
}