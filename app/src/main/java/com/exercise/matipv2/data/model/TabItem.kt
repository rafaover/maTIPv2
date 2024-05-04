package com.exercise.matipv2.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)