package com.exercise.matipv2.components.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.exercise.matipv2.R
import com.exercise.matipv2.components.common.EditTextForm
import com.exercise.matipv2.ui.MainScreenViewModel

@Composable
fun AddAnEventDialog(
    viewModel: MainScreenViewModel,
    onSaveRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            viewModel.updateShowAddEventDialog(false)
            viewModel.updateNewEventName("")
        },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_dialog)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_mid))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    /* Button to save an List */
                    Text(
                        text = stringResource(R.string.create_list),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    TextButton(
                        enabled = viewModel.newEventName.isNotBlank(),
                        onClick = { onSaveRequest() },
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
                EditTextForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    value = viewModel.newEventName,
                    label = R.string.list_name,
                    keyboardOptions = KeyboardOptions.Default,
                    onValueChange = { viewModel.updateNewEventName(it) }
                )
            }
        }
    }
}