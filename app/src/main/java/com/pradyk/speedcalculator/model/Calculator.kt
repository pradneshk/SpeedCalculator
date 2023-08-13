package com.pradyk.speedcalculator.model

object Calculator {

    fun getSpeed(distance: Double, time: Double) = distance/time

    fun getTime(distance: Double, speed: Double) = distance/speed

    fun getDistance(speed: Double, time: Double) = speed * time
}