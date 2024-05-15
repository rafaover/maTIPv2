package com.exercise.matipv2.components.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.exercise.matipv2.R
import com.exercise.matipv2.data.model.Event
import kotlinx.coroutines.flow.Flow

@Composable
fun AddTipToEventDialogBox(
    onDismissRequest: () -> Unit,
    allEvents: Flow<List<Event>>,
    onEventSelected: (Event) -> Unit
) {
    val allEventsFlow by allEvents.collectAsState(initial = emptyList())

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnBackPress = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 500.dp)
                .padding(dimensionResource(R.dimen.padding_mid))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_lg)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_sml))
            ) {
                Text(
                    text = stringResource(R.string.event_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                val (selectedOption, onOptionSelected) =
                    remember { mutableStateOf<Event?>(null) }
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    userScrollEnabled = true
                ) {
                    itemsIndexed(allEventsFlow) { _, event ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (event == selectedOption),
                                    onClick = { onOptionSelected(event) },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (event == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = event.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .padding(start = dimensionResource(R.dimen.padding_mid))
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    enabled = selectedOption != null,
                    onClick = {
                        selectedOption?.let { onEventSelected(it) }
                        onDismissRequest()
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            }
        }
    }
}