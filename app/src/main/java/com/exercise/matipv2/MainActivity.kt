package com.exercise.matipv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.exercise.matipv2.layout.MainLayout
import com.exercise.matipv2.ui.theme.MaTIPv2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaTIPv2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainLayout()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatipPreview() {
    MaTIPv2Theme {
        MainLayout()
    }
}
