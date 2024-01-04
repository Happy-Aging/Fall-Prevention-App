package com.appname.happyAging.presentation.my.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.domain.params.user.update
import com.appname.happyAging.domain.usecase.user.DeleteUserUseCase
import com.appname.happyAging.domain.usecase.user.GetUserUseCase
import com.appname.happyAging.domain.usecase.user.UpdateUserUseCase
import com.appname.happyAging.presentation.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel(){
    val user : StateFlow<UiState<UserModel>> get() = _user

    private val _user = MutableStateFlow<UiState<UserModel>>(UiState.Loading)
    fun getUser(){
        viewModelScope.launch {
            getUserUseCase().onSuccess { userModel ->
                _user.value = UiState.Success(userModel)
            }.onFailure {
                _user.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun updateUser(updateUserParams: UpdateUserParams){
        if(_user.value !is UiState.Success) return

        val model = (_user.value as UiState.Success).data.update(updateUserParams)
        _user.value = UiState.Success(model)
        viewModelScope.launch {
            updateUserUseCase(updateUserParams).onFailure {
                _user.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun deleteUser(){
        if(_user.value !is UiState.Success) return

        viewModelScope.launch {
            deleteUserUseCase().onFailure {
                _user.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }
}