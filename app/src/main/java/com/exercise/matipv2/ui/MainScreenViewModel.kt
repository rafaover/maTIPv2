package com.exercise.matipv2.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.data.MainScreenState
import com.exercise.matipv2.data.MatipRepository
import com.exercise.matipv2.data.model.Event
import com.exercise.matipv2.data.model.Tip
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

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        insertTestEvent()
    }

    private fun insertTestEvent() = viewModelScope.launch(Dispatchers.IO) {
        matipRepository.insertEvent(Event(1, "Test List"))
    }

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

    fun updateSelectedTabIndex(index: Int) {
        updateState { it.copy(selectedTabIndex = index) }
    }

    fun increaseCounter() {
        updateState { it.copy(splitShare = uiState.value.splitShare + 1)}
    }

    fun decreaseCounter() {
        if (uiState.value.splitShare > 0) {
            updateState { it.copy(splitShare = uiState.value.splitShare - 1)}
        }
    }

    fun updateEventName(eventName: String) {
        updateState { it.copy(eventName = eventName) }
    }

    fun updateShowDialog(showDialog: Boolean) {
        updateState { it.copy(showDialog = showDialog) }
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

    /* This insertTip method has a return Tip mainly to use on AddTipToEventDialog when
    * adding a Tip to an List(or Event) */
    suspend fun insertTip() {
        val tip = uiState.value.toTip()
        matipRepository.insertTip(tip)
    }

    suspend fun insertEvent(event: Event) {
        matipRepository.insertEvent(event)
    }

    fun addTipToEvent(tip: Tip, event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            tip.eventId = event.id
            matipRepository.updateTip(tip)
        }
    }

    suspend fun getLastTipSaved() = matipRepository.getLastTipSaved()

    fun getAllEvents(): Flow<List<Event>> {
        return matipRepository.getAllEvents()
    }

    private fun MainScreenState.toTip(): Tip = Tip(
        tipAmount = finalTip,
        tipPercent = tipPercent
    )
}

