package com.exercise.matipv2.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FabAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
    ) {
        Icon(Icons.Filled.Add, "FAB to add an Event")
    }
}