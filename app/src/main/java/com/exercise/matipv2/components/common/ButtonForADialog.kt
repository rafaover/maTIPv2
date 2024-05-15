package com.exercise.matipv2.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonToOpenDialog(
    updateShowDialog: () -> Unit,
    buttonText: String
) {
    Row {
        Button(
            modifier = Modifier.padding(10.dp),
            // Update to "true" to show the dialog box
            onClick = { updateShowDialog() }
        ) {
            Text(buttonText)
        }
    }
}
