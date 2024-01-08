package com.appname.happyAging.data.repository.user

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
        val resp = apiService.getUser()
        if(resp.isSuccessful){
            return ApiResponse.Success(resp.body()!!.toDomain())
        }
        return ApiResponse.Error(resp.message())
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) : ApiResponse<Unit>{
        val resp = apiService.updateUser(updateUserParams.toData())
        if(!resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return ApiResponse.Success(Unit)
    }

    override suspend fun deleteUser() : ApiResponse<Unit>{
        val resp = apiService.deleteUser()
        if(!resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return ApiResponse.Success(Unit)
    }
}