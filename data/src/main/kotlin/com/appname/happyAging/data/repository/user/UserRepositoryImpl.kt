package com.appname.happyAging.data.repository.user

import com.appname.happyAging.data.api.ApiConstants
import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.user.request.toData
import com.appname.happyAging.data.dto.user.response.toDomain
import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUser(): ApiResponse<UserModel> {
        runCatching {
            val resp = apiService.getUser()
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!.toDomain())
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) : ApiResponse<Unit>{
        runCatching {
            val resp = apiService.updateUser(updateUserParams.toData())
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

    override suspend fun deleteUser() : ApiResponse<Unit>{
        runCatching{
            val resp = apiService.deleteUser()
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