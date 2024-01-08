package com.appname.happyAging.data.repository.senior

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.senior.request.toData
import com.appname.happyAging.data.dto.senior.response.toDomain
import com.appname.happyAging.domain.model.common.ApiResponse
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
    override suspend fun getSeniorList(): ApiResponse<List<SeniorModel>> {
        val resp = apiService.getSeniorList()
        if(resp.isSuccessful){
            return ApiResponse.Success(resp.body()!!.map { it.toDomain() })
        }
        return ApiResponse.Error(resp.message())
    }

    override suspend fun createSenior(createSeniorParams: CreateSeniorParams): ApiResponse<Long> {
        val resp = apiService.createSenior(createSeniorParams.toData())
        if(resp.isSuccessful){
            return ApiResponse.Success(resp.body()!!)
        }
        return ApiResponse.Error(resp.message())
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) : ApiResponse<Unit>{
        val resp = apiService.updateSenior(updateSeniorParams.id,updateSeniorParams.toData())
        if(!resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return ApiResponse.Success(Unit)
    }

    override suspend fun deleteSenior(id: Long) : ApiResponse<Unit> {
        val resp = apiService.deleteSenior(id)
        if(!resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return ApiResponse.Success(Unit)
    }

}