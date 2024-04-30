package com.exercise.matipv2.components.common

import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavigationTopTab() {
    Tab(
        text = { Text("Tip") },
        selected = true,
        onClick = { /*TODO*/ }
    )
    Tab(
        text = { Text("Events") },
        selected = false,
        onClick = { /*TODO*/ }
    )
}