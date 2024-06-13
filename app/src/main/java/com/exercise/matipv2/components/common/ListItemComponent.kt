package com.exercise.matipv2.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListItemComponent(
    item: T,
    getName: (T) -> String,
    detailsWhenClickListItem: @Composable (T) -> Unit,
    listItemTrailingIcon: ImageVector,
    modifier: Modifier
    ) {
    ListItem(
        modifier = modifier,
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
                    text = getName(item),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        trailingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                detailsWhenClickListItem(item)
                Spacer(Modifier.width(16.dp))
                Icon(
                    imageVector = listItemTrailingIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                )
            }
        }
    )
}