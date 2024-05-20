package com.exercise.matipv2.components.events

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.ui.MainScreenViewModel

@Composable
fun AllTipsFromEventCounter(
    viewModel: MainScreenViewModel,
    event: Event
) {
    val tipsFromEvent by viewModel
        .getAllTipsFromEvent(event.id).collectAsState(initial = emptyList())

    Row {
        Text("${tipsFromEvent.size} ")
        if (tipsFromEvent.size == 1) Text("tip") else Text("tips")
    }
}