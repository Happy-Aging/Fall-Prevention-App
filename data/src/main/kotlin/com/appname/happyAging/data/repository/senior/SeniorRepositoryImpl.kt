package com.appname.happyAging.data.repository.senior

import com.appname.happyAging.data.api.ApiConstants
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
        runCatching {
            apiService.getSeniorList()
        }.onSuccess {
            if(it.isSuccessful){
                return ApiResponse.Success(it.body()!!.map { dto -> dto.toDomain() })
            }
            return ApiResponse.Error(it.message())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun createSenior(createSeniorParams: CreateSeniorParams): ApiResponse<Long> {
        runCatching {
            apiService.createSenior(createSeniorParams.toData())
        }.onSuccess {
            if(it.isSuccessful){
                return ApiResponse.Success(it.body()!!)
            }
            return ApiResponse.Error(it.message())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) : ApiResponse<Unit>{
        runCatching {
            apiService.updateSenior(updateSeniorParams.id,updateSeniorParams.toData())
        }.onSuccess {
            if(it.isSuccessful){
                return ApiResponse.Success(Unit)
            }
            return ApiResponse.Error(it.message())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun deleteSenior(id: Long) : ApiResponse<Unit> {
        runCatching {
            apiService.deleteSenior(id)
        }.onSuccess {
            if(it.isSuccessful){
                return ApiResponse.Success(Unit)
            }
            return ApiResponse.Error(it.message())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

}