package com.exercise.matipv2.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.exercise.matipv2.R
import com.exercise.matipv2.components.EditNumber
import com.exercise.matipv2.components.RoundTheTipSwitch
import com.exercise.matipv2.util.calculateTip

@Composable
fun MainLayout() {
    /* hoisting the state for EditNumber func */
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipPercentInput by remember { mutableStateOf("") }
    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0
    var roundUp by remember { mutableStateOf(false) }
    val tip = calculateTip(amount, tipPercent, roundUp)

    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start),
            text = stringResource(R.string.calculate_tip),
            fontWeight = FontWeight.Bold,
        )
        // Function to edit Bill Amount
        EditNumber(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .align(alignment = Alignment.Start),
            label = R.string.bill_amount,
            value = amountInput,
            onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            leadingIcon = R.drawable.attach_money

        )
        // function to edit Tip percentage
        EditNumber(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(alignment = Alignment.Start),
            label = R.string.tip_percentage,
            value = tipPercentInput,
            onValueChange = { tipPercentInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            leadingIcon = R.drawable.percent
        )
        RoundTheTipSwitch(roundUp, onRoundUpChange = { roundUp = it })
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 16.dp),
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}