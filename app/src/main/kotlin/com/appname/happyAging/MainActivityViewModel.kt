package com.appname.happyAging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
) : ViewModel() {
    val uiState : StateFlow<MainActivityUiState> = flow{
        val token = jwtTokenRepository.getJwtToken()
        emit(MainActivityUiState.Success(token))
    }.stateIn(viewModelScope, SharingStarted.Lazily, MainActivityUiState.Loading)
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val jwtToken: JwtToken?) : MainActivityUiState
}