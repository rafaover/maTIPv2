package com.exercise.matipv2.ui.events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.exercise.matipv2.R
import com.exercise.matipv2.ui.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventTipListScreen(
    viewModel: MainScreenViewModel,
    onDismissRequest: () -> Unit,
    eventId: Int,
    onBackClick: () -> Unit,
) {
    val eventTipList by viewModel
        .getAllTipsFromEvent(eventId)
        .collectAsState(emptyList())

    val event = viewModel
        .getEventById(eventId).collectAsState(null)

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { event.value?.let {
                        Text(text = it.name) }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_mid))
                ) {
                    Text(
                        text = "Tip List",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_sml))
                    )

                    HorizontalDivider(modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_mid))
                    )

                    LazyColumn(
                        userScrollEnabled = true
                    ) {
                        itemsIndexed(eventTipList) { _, tip ->
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = tip.tipAmount,
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}