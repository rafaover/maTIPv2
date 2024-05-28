package com.exercise.matipv2.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.exercise.matipv2.ui.MainScreenViewModel

@Composable
fun EventTipListScreen(
    viewModel: MainScreenViewModel,
    eventId: Int?
) {
    val eventTipList = viewModel
        .getAllTipsFromEvent(eventId?: 0)
        .collectAsState(emptyList())

    Column {
        eventTipList.value.forEach { tip ->
            Text(text = tip.tipAmount)
        }
    }
}