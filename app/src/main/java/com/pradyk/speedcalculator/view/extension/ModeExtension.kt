package com.pradyk.speedcalculator.view.extension

import com.pradyk.speedcalculator.R
import com.pradyk.speedcalculator.model.Calculator

val Calculator.Mode.stringResourceValue
    get() = when(this) {
        Calculator.Mode.GET_SPEED -> R.string.option_solve_for_speed
        Calculator.Mode.GET_TIME -> R.string.option_solve_for_time
        Calculator.Mode.GET_DISTANCE -> R.string.option_solve_for_distance
    }