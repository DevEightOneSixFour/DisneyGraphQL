package com.superspecialapp.disneygraphql.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.superspecialapp.disneygraphql.domain.CharacterRepositoryImpl
import com.superspecialapp.disneygraphql.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisneyViewModel @Inject constructor(
    private val repository: CharacterRepositoryImpl
) : ViewModel() {

    private val _uiState= MutableLiveData<UIState>()
    val uiState: LiveData<UIState> get() = _uiState

    init {
        queryCharacters(0)
    }

    fun queryCharacters(page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.queryCharacters(page).collect { _uiState.postValue(it) }
        }
    }

    fun queryByName(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.queryByName(name).collect { _uiState.postValue(it) }
        }
    }
}