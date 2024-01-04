package com.appname.happyAging.data.repository.senior

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeniorRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SeniorRepository {
    override suspend fun getSeniorList(): List<SeniorModel> {
        return listOf(
            SeniorModel.fixture(),
        )
    }

    override suspend fun createSenior(createSeniorParams: CreateSeniorParams): Long {
        return LocalDateTime.now().second.toLong() % 137
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) {

    }

    override suspend fun deleteSenior(id: Long) {
        //apiService.deleteSenior(id)
    }

}