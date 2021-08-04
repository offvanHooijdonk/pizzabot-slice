package com.itechart.interview.pizzabotroute.model

data class DeliveryModel(
    val areaModel: AreaModel,
    val deliveryPoints: List<DeliveryPointModel>,
)

data class AreaModel(val x: Int, val y: Int) {
    override fun toString(): String = "$x*$y"
}

data class DeliveryPointModel(val x: Int, val y: Int) {
    override fun toString(): String = "($x, $y)"
}