package com.pradyk.speedcalculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradyk.speedcalculator.R
import com.pradyk.speedcalculator.model.Calculator
import com.pradyk.speedcalculator.view.extension.stringResourceValue
import com.pradyk.speedcalculator.view.ui.theme.SpeedCalculatorTheme
import com.pradyk.speedcalculator.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: MainViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        val calculatorStateDropdownExpanded = viewModel.mainUIState.calculatorStateDropdownExpanded
        val textFieldValue = viewModel.mainUIState.currentMode.stringResourceValue
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 8.dp)
        ) {
            CalculatorModeSelector(
                calculatorStateDropdownExpanded,
                textFieldValue,
                viewModel::toggleCalculatorStateDropdownExpanded,
                viewModel::dismissCalculatorStateDropdownExpanded,
                viewModel::setMode
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Distance (Km)",
                    modifier = Modifier.weight(0.4f)
                )
                TextField(
                    value = viewModel.mainUIState.distance,
                    onValueChange = viewModel::setDistance,
                    modifier = Modifier.weight(0.5f),
                    readOnly = viewModel.mainUIState.currentMode == Calculator.Mode.GET_DISTANCE,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Time (HH:MM:SS)",
                    modifier = Modifier.weight(0.4f)
                )
                Row(
                    modifier = Modifier.weight(0.5f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = viewModel.mainUIState.hours,
                        onValueChange = viewModel::setHour,
                        modifier = Modifier.weight(0.33f),
                        readOnly = viewModel.mainUIState.currentMode == Calculator.Mode.GET_DISTANCE,
                        maxLines = 1
                    )
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextField(
                        value = viewModel.mainUIState.minutes,
                        onValueChange = viewModel::setMinute,
                        modifier = Modifier.weight(0.33f),
                        readOnly = viewModel.mainUIState.currentMode == Calculator.Mode.GET_DISTANCE,
                        maxLines = 1
                    )
                    Text(
                        text = ":",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TextField(
                        value = viewModel.mainUIState.seconds,
                        onValueChange = viewModel::setSeconds,
                        modifier = Modifier.weight(0.33f),
                        readOnly = viewModel.mainUIState.currentMode == Calculator.Mode.GET_DISTANCE,
                        maxLines = 1
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Speed (Kmph)",
                    modifier = Modifier.weight(0.4f)
                )
                TextField(
                    value = viewModel.mainUIState.speed,
                    onValueChange = viewModel::setSpeed,
                    modifier = Modifier.weight(0.5f),
                    readOnly = viewModel.mainUIState.currentMode == Calculator.Mode.GET_DISTANCE
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CalculatorModeSelector(
    calculatorStateDropdownExpanded: Boolean,
    textFieldValue: Int,
    toggleCalculatorStateDropdownExpanded: () -> Unit,
    dismissCalculatorStateDropdownExpanded: () -> Unit,
    setMode: (Calculator.Mode) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = calculatorStateDropdownExpanded,
        onExpandedChange = { toggleCalculatorStateDropdownExpanded() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = stringResource(id = textFieldValue),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = calculatorStateDropdownExpanded) }
        )
        ExposedDropdownMenu(
            expanded = calculatorStateDropdownExpanded,
            onDismissRequest = dismissCalculatorStateDropdownExpanded,
            modifier = Modifier.exposedDropdownSize()
        ) {

            Calculator.Mode.values().forEach { mode ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = mode.stringResourceValue)) },
                    onClick = { setMode(mode) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    SpeedCalculatorTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainContent(MainViewModel())
        }
    }
}