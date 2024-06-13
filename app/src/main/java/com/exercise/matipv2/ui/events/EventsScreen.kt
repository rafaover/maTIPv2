package com.exercise.matipv2.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.R
import com.exercise.matipv2.components.common.ConfirmationAlertDialog
import com.exercise.matipv2.components.common.FabAdd
import com.exercise.matipv2.components.events.AddAnEventDialog
import com.exercise.matipv2.components.events.AllTipsFromEventCounter
import com.exercise.matipv2.components.events.SwipeBox
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.ui.MainScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun EventsScreen(
    viewModel: MainScreenViewModel,
    allEvents: Flow<List<Event>>,
    navigateTo: (Event) -> Unit
) {
    val allEventsFlow by allEvents.collectAsState(initial = emptyList())
    var selectedEvent by remember { mutableStateOf<Event?>(null) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        if (allEventsFlow.isNotEmpty()) {
            LazyColumn(
                userScrollEnabled = true
            ) {
                itemsIndexed(allEventsFlow) { _, event ->
                    SwipeBox(
                        onDelete = {
                            selectedEvent = event
                            viewModel.updateShowDeleteEventDialog(true)
                        },
                        onEdit = { viewModel.updateEvent(event) }
                    ) {
                        ListItem(
                            modifier = Modifier
                                .clickable { navigateTo(event) }
                                .height(70.dp),
                            headlineContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(
                                        modifier = Modifier
                                            .width(4.dp)
                                            .height(36.dp)
                                            .background(MaterialTheme.colorScheme.primary)
                                    )
                                    Spacer(Modifier.width(12.dp))
                                    Text(
                                        text = event.name,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            },
                            trailingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AllTipsFromEventCounter(viewModel, event)
                                    Spacer(Modifier.width(16.dp))
                                    Icon(
                                        imageVector = Icons.Filled.ChevronRight,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .size(24.dp)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

        /** FAB to Add an Event via [AddAnEventDialog] */

        FabAdd(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(dimensionResource(R.dimen.padding_mid)),
            onClick = { viewModel.updateShowAddEventDialog(true) }
        )

        /** Conditional attached to [FabAdd] composable above to
         * show dialog when clicked */

        if(viewModel.showAddEventDialog) {
            AddAnEventDialog(
                viewModel = viewModel,
                onSaveRequest = {
                    viewModel.viewModelScope.launch {
                        viewModel.insertEvent(Event(name = viewModel.newEventName))
                        viewModel.updateShowAddEventDialog(false)
                    }
                }
            )
        }

        /** Conditional attached to [SwipeBox] call on [EventsScreen] to show on delete action.
         * Opens [ConfirmationAlertDialog] to confirm Delete an Event.
         **/

        if (viewModel.showDeleteEventDialog) {
            val eventName = selectedEvent?.name
            ConfirmationAlertDialog(
                title = stringResource(R.string.dialog_title),
                message = stringResource(
                    id = R.string.dialog_delete_event_text,
                    eventName ?: "Event"
                ),
                icon = Icons.Filled.Info,
                confirmButtonText = stringResource(R.string.delete),
                onConfirm = {
                    viewModel.deleteEvent(selectedEvent)
                    viewModel.updateShowDeleteEventDialog(false)
                },
                onDismiss = {
                    viewModel.updateShowDeleteEventDialog(false)
                }
            )
        }
    }
}