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
            val resp = apiService.getSeniorList()
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!.map { dto -> dto.toDomain() })
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun createSenior(createSeniorParams: CreateSeniorParams): ApiResponse<Long> {
        runCatching {
            val resp = apiService.createSenior(createSeniorParams.toData())
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!)
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) : ApiResponse<Unit>{
        runCatching {
            val resp = apiService.updateSenior(updateSeniorParams.id,updateSeniorParams.toData())
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(Unit)
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun deleteSenior(id: Long) : ApiResponse<Unit> {
        runCatching {
            val resp = apiService.deleteSenior(id)
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(Unit)
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

}