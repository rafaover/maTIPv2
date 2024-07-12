package com.exercise.matipv2.ui.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.matipv2.data.local.model.List
import com.exercise.matipv2.data.local.model.Tip
import com.exercise.matipv2.data.repository.MatipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val matipRepository: MatipRepository
) : ViewModel() {

    var showAddListDialog by mutableStateOf(false)
    var showDeleteListDialog by mutableStateOf(false)
    var showSnackBar by mutableStateOf(false)
    var newListName by mutableStateOf("")

    fun updateList(list: List) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.updateList(list)
        }
    }

    fun updateNewListName(listName: String) {
        newListName = listName
    }

    fun updateShowAddListDialog(showDialog: Boolean) {
        showAddListDialog = showDialog
    }

    fun updateShowDeleteListDialog(showDialog: Boolean) {
        showDeleteListDialog = showDialog
    }

    fun insertList(list: List) {
        viewModelScope.launch(Dispatchers.IO) {
            matipRepository.insertList(list)
        }
        updateNewListName("")
    }

    fun deleteList(list: List?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (list != null) {
                matipRepository.deleteList(list)
            }
        }
    }

    fun getAllLists(): Flow<kotlin.collections.List<List>> {
        return matipRepository.getAllLists()
    }

    fun getListById(listId: Int): Flow<List> {
        return matipRepository.getListById(listId)
    }

    fun getAllTipsFromList(listId: Int): Flow<kotlin.collections.List<Tip>> {
        return matipRepository.getAllTipsFromList(listId)
    }
}