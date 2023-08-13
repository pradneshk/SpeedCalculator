package com.pradyk.speedcalculator.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.pradyk.speedcalculator.model.Calculator
import org.joda.time.DateTime
import kotlin.time.Duration.Companion.hours

class MainUiState {

    var currentMode by mutableStateOf(Calculator.Mode.GET_SPEED)
        internal set

    var speed by mutableStateOf("")
        internal set

    var distance by mutableStateOf("")
        internal set

    var calculatorStateDropdownExpanded by mutableStateOf(false)
        internal set

    internal var time by mutableStateOf(DateTime().withMillis(0).withMinuteOfHour(0))

    val hours by derivedStateOf { "${time.millis.hours.inWholeHours}" }
    val minutes by derivedStateOf { "${time.minuteOfHour().get()}" }
    val seconds by derivedStateOf { "${time.secondOfMinute().get()}" }

    internal fun setResult(result: Double) {
        when (currentMode) {
            Calculator.Mode.GET_SPEED -> speed = "$result"
            Calculator.Mode.GET_DISTANCE -> distance = "$result"
            Calculator.Mode.GET_TIME -> time.withMillis(result.toLong())
        }
    }
}