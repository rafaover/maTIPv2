package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exercise.matipv2.R
import com.exercise.matipv2.components.EditNumber
import com.exercise.matipv2.components.RoundTheTipSwitch

@SuppressLint("VisibleForTests")
@Composable
fun MainLayout(viewModel: MainLayoutViewModel) {
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
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .align(alignment = Alignment.Start),
            text = stringResource(R.string.calculate_tip),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )

        /* Function to edit Bill Amount */
        EditNumber(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
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

        /* Split value */
        Row(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.split),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = { viewModel.incrementCounter() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors()
            ) { Text(text = "+") }
            AnimatedContent(
                targetState = uiState.counter,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { -it } togetherWith slideOutVertically { it }
                    } else {
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    }
                }, label = "Animated Counter"
            ) { count ->
                Text(
                    text = "$count",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                )
            }
//            Text(
//                text = uiState.counter.toString(),
//                style = MaterialTheme.typography.displaySmall,
//                textAlign = TextAlign.Center,
//            )
            Button(
                onClick = { viewModel.decrementCounter() },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors()
            ) { Text(text = "-") }
        }

        /* Text Box for total Tip Amount */
        Row(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_sml))
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.tip_amount),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = viewModel.finalTip(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        RoundTheTipSwitch(
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_mid))
                .align(alignment = Alignment.Start),
            roundUp = uiState.roundUp,
            onRoundUpChange = { viewModel.updateRoundUp(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview() {
    MainLayout(viewModel())
}