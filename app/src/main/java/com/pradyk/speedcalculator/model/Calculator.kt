package com.pradyk.speedcalculator.model

object Calculator {

    enum class Mode {
        GET_SPEED, GET_TIME, GET_DISTANCE
    }

    private fun getSpeed(distance: Double, time: Double) = distance / time

    private fun getTime(distance: Double, speed: Double) = distance / speed

    private fun getDistance(speed: Double, time: Double) = speed * time

    fun calculate(
        mode: Mode,
        distance: Double = 0.0,
        speed: Double = 0.0,
        time: Double = 0.0
    ) = when (mode) {
        Mode.GET_SPEED -> getSpeed(distance, time)
        Mode.GET_TIME -> getTime(distance, speed)
        Mode.GET_DISTANCE -> getDistance(speed, time)
    }
}