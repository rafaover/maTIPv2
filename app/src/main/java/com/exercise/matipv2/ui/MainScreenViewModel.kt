package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.ui.tipcalculator.TipCalculatorScreenUiState
import com.exercise.matipv2.data.local.model.Event
import com.exercise.matipv2.data.local.model.Tip
import com.exercise.matipv2.data.repository.MatipRepository
import com.exercise.matipv2.util.calculateTip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    private val matipRepository: MatipRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TipCalculatorScreenUiState())
    val uiState = _uiState.asStateFlow()

    var showAddEventDialog by mutableStateOf(false)
    var showDeleteEventDialog by mutableStateOf(false)
    var showSnackBar by mutableStateOf(false)
    var newEventName by mutableStateOf("")

    var tipAmountInput by mutableStateOf("")
    var tipPercentInput by mutableStateOf("")

    init {
        resetState()
    }

    /*
    * Update Functions
    */

    private fun updateState(update: (TipCalculatorScreenUiState) -> TipCalculatorScreenUiState) {
        _uiState.value = update(_uiState.value)
    }

    fun updateTipAmount(amount: String) {
        tipAmountInput = amount
    }

    fun updateTipPercent(tipPercent: String) {
        tipPercentInput = tipPercent
    }

    fun updateRoundUp(roundUp: Boolean) {
        updateState { it.copy(roundUp = roundUp) }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.updateEvent(event)
        }
    }

    fun updateNewEventName(eventName: String) {
        newEventName = eventName
    }

    fun updateShowAddEventDialog(showDialog: Boolean) {
        showAddEventDialog = showDialog
    }

    fun updateShowDeleteEventDialog(showDialog: Boolean) {
        showDeleteEventDialog = showDialog
    }

    fun increaseCounter() {
        updateState { it.copy(splitShare = uiState.value.splitShare + 1)}
    }

    fun decreaseCounter() {
        if (uiState.value.splitShare > 0) {
            updateState { it.copy(splitShare = uiState.value.splitShare - 1)}
        }
    }

    @SuppressLint("VisibleForTests")
    fun updateFinalTip(): String {
        val calculatedTip = calculateTip(
            amount = tipAmountInput,
            tipPercent = tipPercentInput,
            roundUp = uiState.value.roundUp,
            tipSplit = uiState.value.splitShare
        )
        _uiState.value = uiState.value.copy(finalTip = calculatedTip)
        return calculatedTip
    }

    fun resetState() {
        _uiState.value = TipCalculatorScreenUiState()
    }

    fun updateShowSnackBar (snackBar: Boolean) {
        showSnackBar = snackBar
    }

    /*
    * Insert Functions
    */

    fun insertTip() {
        viewModelScope.launch(Dispatchers.IO) {
            val tip = uiState.value.toTip()
            matipRepository.insertTip(tip)
        }
    }

    fun insertEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.insertEvent(event)
        }
        updateNewEventName("")
    }

    fun addTipToEvent(tip: Tip, eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            tip.eventId = eventId
            matipRepository.updateTip(tip)
        }
    }

    /*
    * Delete Functions
    */

    fun deleteEvent(event: Event?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (event != null) {
                matipRepository.deleteEvent(event)
            }
        }
    }

    /*
    * Get Functions
    */

    suspend fun getLastTipSaved(): Tip {
        return matipRepository.getLastTipSaved()
    }

    fun getAllEvents(): Flow<List<Event>> {
        return matipRepository.getAllEvents()
    }

    fun getEventById(eventId: Int): Flow<Event> {
        return matipRepository.getEventById(eventId)
    }

    fun getAllTipsFromEvent(eventId: Int): Flow<List<Tip>> {
        return matipRepository.getAllTipsFromEvent(eventId)
    }

    /*
    * Extension Functions
    */

    private fun TipCalculatorScreenUiState.toTip(): Tip = Tip(
        tipAmount = finalTip,
        tipPercent = tipPercentInput
    )
}

