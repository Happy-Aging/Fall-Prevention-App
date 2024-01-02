package com.appname.happyAging.presentation.senior.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.model.senior.Sex
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.usecase.senior.CreateSeniorUseCase
import com.appname.happyAging.domain.usecase.senior.DeleteSeniorUseCase
import com.appname.happyAging.domain.usecase.senior.GetSeniorUseCase
import com.appname.happyAging.domain.usecase.senior.UpdateSeniorUseCase
import com.appname.happyAging.presentation.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeniorViewModel @Inject constructor(
    private val getSeniorUseCase: GetSeniorUseCase,
    private val createSeniorUseCase: CreateSeniorUseCase,
    private val updateSeniorUseCase: UpdateSeniorUseCase,
    private val deleteSeniorUseCase: DeleteSeniorUseCase,
) : ViewModel() {
    val senior : StateFlow<UiState<List<SeniorModel>>> get() = _senior
    private val _senior = MutableStateFlow<UiState<List<SeniorModel>>>(UiState.Loading)
    init {
        getSenior()
    }

    fun getSenior(){
        viewModelScope.launch {
            getSeniorUseCase().onSuccess {
                _senior.value = UiState.Success(it)
            }.onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun createSenior(createSeniorParams: CreateSeniorParams){
        if(_senior.value !is UiState.Success) return
        viewModelScope.launch {
            createSeniorUseCase(createSeniorParams).onSuccess {
                val list = (_senior.value as UiState.Success).data.toMutableList()
                val newModel = SeniorModel(
                    id = it,
                    name = createSeniorParams.name,
                    address = createSeniorParams.address,
                    sex = createSeniorParams.sex,
                    age = 12,
                    profile = "",
                    rank = 1,
                )
                list.add(newModel)
                _senior.value = UiState.Success(list)
            }.onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

}