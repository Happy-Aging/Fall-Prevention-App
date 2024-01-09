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
            apiService.getUser()
        }.onSuccess {
            if(!it.isSuccessful){
                return ApiResponse.Error(it.message())
            }
            return ApiResponse.Success(it.body()!!.toDomain())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) : ApiResponse<Unit>{
        runCatching {
            apiService.updateUser(updateUserParams.toData())
        }.onSuccess {
            if(!it.isSuccessful){
                return ApiResponse.Error(it.message())
            }
            return ApiResponse.Success(Unit)
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun deleteUser() : ApiResponse<Unit>{
        runCatching{
            apiService.deleteUser()
        }.onSuccess {
            if(!it.isSuccessful){
                return ApiResponse.Error(it.message())
            }
            return ApiResponse.Success(Unit)
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }
}