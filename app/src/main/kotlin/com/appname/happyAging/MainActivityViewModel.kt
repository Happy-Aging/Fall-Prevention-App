package com.appname.happyAging

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    prefs: SharedPreferences,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState>
    = prefs.getString("accessToken", "")?.let {
        flow<MainActivityUiState> {
            emit(MainActivityUiState.Success)
        }
    }?.stateIn(viewModelScope, SharingStarted.Lazily, MainActivityUiState.Loading) ?: flow<MainActivityUiState> {
        emit(MainActivityUiState.Loading)
    }.stateIn(viewModelScope, SharingStarted.Lazily, MainActivityUiState.Loading)
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    object Success : MainActivityUiState
}