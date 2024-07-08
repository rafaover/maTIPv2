package com.exercise.matipv2.components.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.exercise.matipv2.R
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.ui.MainScreenViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun AddTipToEventDialogBox(
    viewModel: MainScreenViewModel,
    allEvents: Flow<List<Event>>,
    onEventSelected: (Event) -> Unit
) {
    val allEventsFlow by allEvents.collectAsState(initial = emptyList())
    val (selectedOption, onOptionSelected) =
        remember { mutableStateOf<Event?>(null) }

    Dialog(
        onDismissRequest = { viewModel.updateShowAddEventDialog(false) }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 280.dp, max = 380.dp)
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.select_list),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    /* Button to Save the selected event */

                    TextButton(
                        enabled = selectedOption != null,
                        onClick = {
                            selectedOption?.let { event ->
                                onEventSelected(event)
                            }
                            viewModel.updateShowAddEventDialog(false)
                        },
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_button))
                    ) {
                        Text(
                            text = stringResource(R.string.confirm),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                if (allEventsFlow.isNotEmpty()) {
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
                                        onClick = {
                                            onOptionSelected(event)
                                            viewModel.updateEventId(event.id)
                                        },
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
                }
            }
        }
    }
}