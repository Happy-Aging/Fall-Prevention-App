package com.appname.happyAging.domain.repository.senior

import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams

interface SeniorRepository {
    suspend fun getSeniorList() : List<SeniorModel>
    suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams)
    suspend fun deleteSenior(id: Long)
}