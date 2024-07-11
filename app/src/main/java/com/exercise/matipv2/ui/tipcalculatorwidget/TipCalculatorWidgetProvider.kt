package com.exercise.matipv2.ui.tipcalculatorwidget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text
import com.exercise.matipv2.data.local.model.Tip

class TipCalculatorWidgetReceiver: GlanceAppWidgetReceiver() {

    /** Let [TipCalculatorWidgetReceiver] know which GlanceAppWidget to use */
    override val glanceAppWidget: GlanceAppWidget = TipCalculatorWidget()
}

class TipCalculatorWidget: GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                TipCalculatorWidgetScreen()
            }
        }
    }
}

@Composable
fun TipCalculatorWidgetScreen() {
    Text(text = "Hello World!")
}