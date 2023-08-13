package com.pradyk.speedcalculator.viewmodel

import androidx.lifecycle.ViewModel
import com.pradyk.speedcalculator.model.Calculator
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class MainViewModel : ViewModel() {

    val mainUIState = MainUiState()

    fun setMode(index: Int) {
        val newMode = Calculator.Mode.values()[index]
        mainUIState.currentMode = newMode
    }

    fun setDistance(distance: String) {
        mainUIState.distance
    }

    fun setSpeed(speed: String) {
        mainUIState.speed = speed
    }

    fun setHour(hour: String) {
        val hourMillis = hour.toLong().hours.inWholeMilliseconds
        val minutes = mainUIState.minutes.toInt().minutes.inWholeMilliseconds
        val seconds = mainUIState.seconds.toInt().seconds.inWholeMilliseconds
        val millis = hourMillis + minutes + seconds
        mainUIState.time = mainUIState.time.withMillis(millis)
    }

    fun setMinute(minute: String) {
        mainUIState.time = mainUIState.time.withMinuteOfHour(minute.toInt())
    }

    fun setSeconds(seconds: String) {
        mainUIState.time = mainUIState.time.withSecondOfMinute(seconds.toInt())
    }

    fun calculate() {
        val result = Calculator.calculate(
            mode = mainUIState.currentMode,
            distance = mainUIState.distance.toDoubleOrNull() ?: 0.0,
            speed = mainUIState.speed.toDoubleOrNull() ?: 0.0,
            time = mainUIState.time.millis.toDouble()
        )
        mainUIState.setResult(result)
    }
}