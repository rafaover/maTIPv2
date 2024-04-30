package com.exercise.matipv2.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.automirrored.outlined.ViewList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.exercise.matipv2.R
import com.exercise.matipv2.model.TabItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTabRow(
    currentTabIndex: Int,
    selectedTabIndex: (Int) -> Unit
) {
    val tabItems = listOf(
        TabItem(
            title = stringResource(R.string.tipping_tab),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        TabItem(
            title = stringResource(R.string.event_tab),
            selectedIcon = Icons.AutoMirrored.Filled.ViewList,
            unselectedIcon = Icons.AutoMirrored.Outlined.ViewList
        )
    )

    Column {
        PrimaryTabRow(
            selectedTabIndex = currentTabIndex,
        ) {
            tabItems.forEachIndexed { index, tabItem ->
                            Tab(
                                selected = currentTabIndex == index,
                                onClick = { selectedTabIndex(index) },
                                icon = {
                                    Icon(
                                        imageVector = if (currentTabIndex == index) {
                                            tabItem.selectedIcon
                                        } else tabItem.unselectedIcon,
                                        contentDescription = null
                                    )
                                },
                                text = {
                                    Text(text = tabItem.title)
                                }
                            )
            }
        }
    }
}