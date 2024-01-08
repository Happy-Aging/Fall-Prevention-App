package com.appname.happyAging.domain.repository.senior

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams

interface SeniorRepository {
    suspend fun getSeniorList() : ApiResponse<List<SeniorModel>>
    suspend fun createSenior(createSeniorParams: CreateSeniorParams) : ApiResponse<Long>
    suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) : ApiResponse<Unit>
    suspend fun deleteSenior(id: Long) : ApiResponse<Unit>
}