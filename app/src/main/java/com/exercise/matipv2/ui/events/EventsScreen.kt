package com.exercise.matipv2.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.exercise.matipv2.data.model.Event
import kotlinx.coroutines.flow.Flow

@Composable
fun EventsScreen(
    allEvents: Flow<List<Event>>
) {
    val allEventsFlow by allEvents.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            userScrollEnabled = true
        ) {
            itemsIndexed(allEventsFlow) { _, event ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = event.name,
                            style = MaterialTheme.typography.headlineSmall
                        ) },
                    modifier = Modifier,
                    trailingContent = { Text(text = "100") }
                )
                if (allEventsFlow.size > 1) {
                    HorizontalDivider()
                }
            }
        }
    }
}
