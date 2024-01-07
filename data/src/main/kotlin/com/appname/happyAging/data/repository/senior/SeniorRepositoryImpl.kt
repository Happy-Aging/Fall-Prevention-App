package com.appname.happyAging.data.repository.senior

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.senior.request.toData
import com.appname.happyAging.data.dto.senior.response.toDomain
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeniorRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SeniorRepository {
    override suspend fun getSeniorList(): List<SeniorModel> {
        val resp = apiService.getSeniorList()
        if(resp.isSuccessful){
            return resp.body()!!.map { it.toDomain() }
        }
        throw Exception(resp.message())
    }

    override suspend fun createSenior(createSeniorParams: CreateSeniorParams): Long {
        val resp = apiService.createSenior(createSeniorParams.toData())
        if(resp.isSuccessful){
            return resp.body()!!
        }
        throw Exception(resp.message())
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) {
        val resp = apiService.updateSenior(updateSeniorParams.id,updateSeniorParams.toData())
        if(!resp.isSuccessful){
            throw Exception(resp.message())
        }
    }

    override suspend fun deleteSenior(id: Long) {
        val resp = apiService.deleteSenior(id)
        if(!resp.isSuccessful){
            throw Exception(resp.message())
        }
    }

}