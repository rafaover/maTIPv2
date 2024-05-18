package com.exercise.matipv2.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.automirrored.outlined.ViewList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.exercise.matipv2.R
import com.exercise.matipv2.data.model.NavBarItem

@Composable
fun MainNavigationBar(
    currentTabIndex: Int,
    selectedTabIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val navBarItems = listOf(
        NavBarItem(
            title = stringResource(R.string.tipping_tab),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavBarItem(
            title = stringResource(R.string.event_tab),
            selectedIcon = Icons.AutoMirrored.Filled.ViewList,
            unselectedIcon = Icons.AutoMirrored.Outlined.ViewList
        )
    )

    NavigationBar(
        modifier = modifier
    ) {
        navBarItems.forEachIndexed { index, tabItem ->
            NavigationBarItem(
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
                label = {
                    Text(text = tabItem.title)
                }
            )
        }
    }
}