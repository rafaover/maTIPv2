package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
import com.exercise.matipv2.data.repository.MatipRepository
import com.exercise.matipv2.util.calculateTip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    private val matipRepository: MatipRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    private val _eventToDelete = MutableStateFlow<Event?>(null)
    val eventToDelete: StateFlow<Event?> = _eventToDelete

    init {
        resetState()
    }

    /* Update Functions */

    private fun updateState(update: (MainScreenState) -> MainScreenState) {
        _uiState.value = update(_uiState.value)
    }

    fun updateTipAmount(amount: String) {
        updateState { it.copy(tipAmount = amount) }
    }

    fun updateTipPercent(tipPercent: String) {
        updateState { it.copy(tipPercent = tipPercent)}
    }

    fun updateRoundUp(roundUp: Boolean) {
        updateState { it.copy(roundUp = roundUp) }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.updateEvent(event)
        }
    }

    fun updateEventName(eventName: String) {
        updateState { it.copy(eventName = eventName) }
    }

    fun updateShowAddEventDialog(showDialog: Boolean) {
        updateState { it.copy(showAddEventDialog = showDialog) }
    }

    fun updateShowDeleteEventDialog(showDialog: Boolean) {
        updateState { it.copy(showDeleteEventDialog = showDialog) }
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
            amount = uiState.value.tipAmount,
            tipPercent = uiState.value.tipPercent,
            roundUp = uiState.value.roundUp,
            tipSplit = uiState.value.splitShare
        )
        _uiState.value = uiState.value.copy(finalTip = calculatedTip)
        return calculatedTip
    }

    fun resetState() {
        _uiState.value = MainScreenState()
    }

    /* Insert Functions */

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
        updateEventName("")
    }

    suspend fun addTipToEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            val lastTip = getLastTipSaved()
            lastTip.eventId = event.id
            matipRepository.updateTip(lastTip)
        }
    }

    /* Delete Functions */

    fun deleteEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.deleteEvent(event)
        }
    }

    fun setEventToDelete(event: Event?) {
        _eventToDelete.value = event
    }

    /* Get Functions */

    private suspend fun getLastTipSaved(): Tip {
        return matipRepository.getLastTipSaved()
    }

    fun getAllEvents(): Flow<List<Event>> {
        return matipRepository.getAllEvents()
    }

    fun getAllTipsFromEvent(eventId: Int): Flow<List<Tip>> {
        return matipRepository.getAllTipsFromEvent(eventId)
    }

    /* Extension Functions */

    private fun MainScreenState.toTip(): Tip = Tip(
        tipAmount = finalTip,
        tipPercent = tipPercent
    )
}

