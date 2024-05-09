package com.exercise.matipv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exercise.matipv2.ui.MainScreen
import com.exercise.matipv2.ui.MainScreenViewModel
import com.exercise.matipv2.ui.theme.MaTIPv2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaTIPv2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainScreenViewModel = viewModel<MainScreenViewModel>()
                    MainScreen(mainScreenViewModel)
                }
            }
        }
    }
}
