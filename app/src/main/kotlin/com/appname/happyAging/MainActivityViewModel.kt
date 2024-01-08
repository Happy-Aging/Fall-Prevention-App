package com.appname.happyAging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import com.appname.happyAging.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState : StateFlow<MainActivityUiState> = flow{
        kotlin.runCatching {
            userRepository.getUser()
        }.onSuccess {
            emit(MainActivityUiState.Success(it))
        }.onFailure {
            emit(MainActivityUiState.Success(null))
            jwtTokenRepository.deleteJwtToken()
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, MainActivityUiState.Loading)
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val data: UserModel?) : MainActivityUiState
}