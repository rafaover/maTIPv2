package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.data.MatipRepository
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
import com.exercise.matipv2.util.calculateTip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    private val matipRepository: MatipRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            // TODO("Comment this before going to production")
            matipRepository.deleteAllEvents()
            matipRepository.deleteAllTips()
            //

            matipRepository.insertEvent(Event(1, "Event"))
            matipRepository.insertTip(Tip(1, "200", "10", 1) )
        }
    }

    fun updateAmountInput(amount: String) {
        _uiState.value = uiState.value.copy(amountInput = amount)
    }

    fun updateTipPercentInput(tipPercent: String) {
        _uiState.value = uiState.value.copy(tipPercentInput = tipPercent)
    }

    fun updateRoundUp(roundUp: Boolean) {
        _uiState.value = uiState.value.copy(roundUp = roundUp)
    }

    fun updateSelectedTabIndex(index: Int) {
        _uiState.value = uiState.value.copy(selectedTabIndex = index)
    }

    fun increaseCounter() {
        _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare + 1)
    }

    fun decreaseCounter() {
        if (uiState.value.splitShare > 0) {
            _uiState.value = uiState.value.copy(splitShare = uiState.value.splitShare - 1)
        }
    }

    fun updateShowDialog(showDialog: Boolean) {
        _uiState.value = uiState.value.copy(showDialog = showDialog)
    }

    @SuppressLint("VisibleForTests")
    fun updateFinalTip(): String {
        val calculatedTip = calculateTip(
            amount = uiState.value.amountInput,
            tipPercent = uiState.value.tipPercentInput,
            roundUp = uiState.value.roundUp,
            tipSplit = uiState.value.splitShare
        )
        _uiState.value = uiState.value.copy(finalTip = calculatedTip)
        return calculatedTip
    }
}

