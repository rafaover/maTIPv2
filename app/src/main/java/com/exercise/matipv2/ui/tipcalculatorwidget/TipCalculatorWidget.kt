package com.exercise.matipv2.ui.tipcalculatorwidget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

class TipCalculatorWidget: GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Text(text = "Hello World!")
            }
        }
    }
}

class TipCalculatorWidgetReceiver: GlanceAppWidgetReceiver() {

    /** Let [TipCalculatorWidgetReceiver] know which GlanceAppWidget to use */
    override val glanceAppWidget: GlanceAppWidget = TipCalculatorWidget()
}