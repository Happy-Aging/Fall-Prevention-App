package com.appname.happyAging.presentation.senior.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.domain.params.senior.update
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
    val senior: StateFlow<UiState<List<SeniorModel>>> get() = _senior
    private val _senior = MutableStateFlow<UiState<List<SeniorModel>>>(UiState.Loading)

    init {
        getSenior()
    }

    fun getSenior() {
        viewModelScope.launch {
            getSeniorUseCase().onSuccess { seniorList ->
                _senior.value = UiState.Success(seniorList)
            }.onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun createSenior(createSeniorParams: CreateSeniorParams) {
        if (_senior.value !is UiState.Success) return
        viewModelScope.launch {
            createSeniorUseCase(createSeniorParams).onSuccess { newId ->
                val list = (_senior.value as UiState.Success).data.toMutableList()
                val newModel = createSeniorParams.toModel(newId)
                list.add(newModel)
                _senior.value = UiState.Success(list)
            }.onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun updateSenior(updateSeniorParams: UpdateSeniorParams) {
        if (_senior.value !is UiState.Success) return
        val state = (_senior.value as UiState.Success).data
        val model = state.find { it.id == updateSeniorParams.id } ?: return

        // 긍정적 응답
        val updatedModel = model.update(updateSeniorParams)
        val list = state.toMutableList()
        val index = list.indexOf(model)
        list[index] = updatedModel
        _senior.value = UiState.Success(list)

        viewModelScope.launch {
            updateSeniorUseCase(updateSeniorParams).onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun deleteSenior(id: Long) {
        if (_senior.value !is UiState.Success) return
        val state = (_senior.value as UiState.Success).data
        val model = state.find { it.id == id } ?: return

        // 긍정적 응답
        val list = state.toMutableList()
        list.remove(model)
        _senior.value = UiState.Success(list)

        viewModelScope.launch {
            deleteSeniorUseCase(id).onFailure {
                _senior.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }
}
