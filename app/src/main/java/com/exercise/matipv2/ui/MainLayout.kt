package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exercise.matipv2.R
import com.exercise.matipv2.components.EditNumber
import com.exercise.matipv2.components.RoundTheTipSwitch

@SuppressLint("VisibleForTests")
@Composable
fun MainLayout() {

    val viewModel: MainLayoutViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        /* TODO("Add Branding") */

        Text(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start),
            text = stringResource(R.string.calculate_tip),
            fontWeight = FontWeight.Bold,
        )

        /* Function to edit Bill Amount */
        EditNumber(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .align(alignment = Alignment.Start),
            label = R.string.bill_amount,
            value = uiState.amountInput,
            onValueChange = { viewModel.updateAmountInput(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            leadingIcon = R.drawable.attach_money
        )

        /* Function to edit Tip percentage */
        EditNumber(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(alignment = Alignment.Start),
            label = R.string.tip_percentage,
            value = uiState.tipPercentInput,
            onValueChange = { viewModel.updateTipPercentInput(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            leadingIcon = R.drawable.percent
        )
        RoundTheTipSwitch(
            roundUp = uiState.roundUp,
            onRoundUpChange = { viewModel.updateRoundUp(it) }
        )

        /* Text Box for total Tip Amount */
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 16.dp),
            text = stringResource(R.string.tip_amount, viewModel.finalTip()),
            style = MaterialTheme.typography.displaySmall,
        )

        /* TODO("Add feature to split the total tip by a number and give a result, save this
            value instead the whole tip.") */
    }
}