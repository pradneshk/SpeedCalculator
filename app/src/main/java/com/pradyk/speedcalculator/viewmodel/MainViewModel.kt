package com.pradyk.speedcalculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pradyk.speedcalculator.model.Calculator
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class MainViewModel : ViewModel() {

    val mainUIState = MainUiState()

    fun setMode(newMode: Calculator.Mode) {
        dismissCalculatorStateDropdownExpanded()
        mainUIState.currentMode = newMode
    }

    fun setDistance(distance: String) {
        if (mainUIState.currentMode != Calculator.Mode.GET_DISTANCE) {
            mainUIState.distance = distance
            calculate()
        }
    }

    fun setSpeed(speed: String) {
        if (mainUIState.currentMode != Calculator.Mode.GET_SPEED) {
            mainUIState.speed = speed
            calculate()
        }
    }

    fun setHour(hour: String) {
        if (mainUIState.currentMode != Calculator.Mode.GET_TIME) {
            val hourMillis = hour.toLong().hours.inWholeMilliseconds
            val minutes = mainUIState.minutes.toInt().minutes.inWholeMilliseconds
            val seconds = mainUIState.seconds.toInt().seconds.inWholeMilliseconds
            val millis = hourMillis + minutes + seconds
            mainUIState.time = mainUIState.time.withMillis(millis)
            calculate()
        }
    }

    fun setMinute(minute: String) {
        if (mainUIState.currentMode != Calculator.Mode.GET_TIME) {
            mainUIState.time = mainUIState.time.withMinuteOfHour(minute.toInt())
            calculate()
        }
    }

    fun setSeconds(seconds: String) {
        if (mainUIState.currentMode != Calculator.Mode.GET_TIME) {
            mainUIState.time = mainUIState.time.withSecondOfMinute(seconds.toInt())
            calculate()
        }
    }

    private fun calculate() {
        val result = Calculator.calculate(
            mode = mainUIState.currentMode,
            distance = mainUIState.distance.toDoubleOrNull() ?: 0.0,
            speed = mainUIState.speed.toDoubleOrNull() ?: 0.0,
            time = mainUIState.time.millis.toDouble()
        )
        mainUIState.setResult(result)
    }

    fun toggleCalculatorStateDropdownExpanded() {
        mainUIState.apply {
            calculatorStateDropdownExpanded = !calculatorStateDropdownExpanded
        }
    }

    fun dismissCalculatorStateDropdownExpanded() {
        mainUIState.calculatorStateDropdownExpanded = false
    }
}