package com.exercise.matipv2.ui.tipcalculator

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.R
import com.exercise.matipv2.components.calculator.AddTipToEventDialogBox
import com.exercise.matipv2.components.calculator.SplitCounter
import com.exercise.matipv2.components.calculator.TotalTipAmount
import com.exercise.matipv2.components.common.ButtonToOpenDialog
import com.exercise.matipv2.components.common.EditTextForm
import com.exercise.matipv2.components.common.RoundTheTipSwitch
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.ui.MainScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun TipCalculatorScreen(
    viewModel: MainScreenViewModel,
    uiState: MainScreenState,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        /* Title */
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .align(alignment = Alignment.Start),
            text = stringResource(R.string.calculate_tip),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )

        /* Edit Total Bill Amount */
        EditTextForm(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .align(alignment = Alignment.Start),
            label = R.string.bill_amount,
            value = uiState.tipAmount,
            onValueChange = { viewModel.updateTipAmount(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            leadingIcon = R.drawable.attach_money
        )

        /* Edit Tip percentage */
        EditTextForm(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(alignment = Alignment.Start),
            label = R.string.tip_percentage,
            value = uiState.tipPercent,
            onValueChange = { viewModel.updateTipPercent(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            leadingIcon = R.drawable.percent
        )

        SplitCounter(
            splitUiState = uiState.splitShare,
            onclickPositive = { viewModel.increaseCounter() },
            onclickNegative = { viewModel.decreaseCounter() }
        )

        TotalTipAmount(viewModel.updateFinalTip())

        RoundTheTipSwitch(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .align(alignment = Alignment.Start),
            roundUp = uiState.roundUp,
            onRoundUpChange = { viewModel.updateRoundUp(it) }
        )

        ButtonToOpenDialog(
            dataIsPresent = uiState.tipAmount.isNotEmpty() && uiState.tipPercent.isNotEmpty(),
            updateShowDialog = { viewModel.updateShowDialog(true) },
            buttonText = stringResource(R.string.add_tip_to_event)
        )

        /* Conditional attached to the ButtonToOpenDialog above */
        if(uiState.showDialog) {
            AddTipToEventDialogBox(
                viewModel = viewModel,
                allEvents = viewModel.getAllEvents(),
                onEventSelected = { event ->
                    viewModel.insertTip()
                    viewModel.viewModelScope.launch {
                        viewModel.addTipToEvent(event)
                    }
                    viewModel.resetState()
                    focusManager.clearFocus()
                }
            )
        }
    }
}