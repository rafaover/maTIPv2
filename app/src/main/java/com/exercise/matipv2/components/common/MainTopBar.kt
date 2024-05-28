package com.exercise.matipv2.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.exercise.matipv2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.logo_topbar_size)),
                painter = painterResource(R.drawable.matip_logo_sml),
                contentDescription = "Matip Logo",
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            } else {
                IconButton(
                    onClick = { },
                    enabled = false
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_mid)),
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Icon"
                    )
                }
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}