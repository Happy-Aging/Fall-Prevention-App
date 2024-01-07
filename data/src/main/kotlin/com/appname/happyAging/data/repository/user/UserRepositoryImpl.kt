package com.appname.happyAging.data.repository.user

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.user.request.toData
import com.appname.happyAging.data.dto.user.response.toDomain
import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUser(): UserModel {
        val resp = apiService.getUser()
        if(resp.isSuccessful){
            return resp.body()!!.toDomain()
        }
        throw Exception(resp.message())
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) {
        val resp = apiService.updateUser(updateUserParams.toData())
        if(!resp.isSuccessful){
            throw Exception(resp.message())
        }
    }

    override suspend fun deleteUser() {
        val resp = apiService.deleteUser()
        if(!resp.isSuccessful){
            throw Exception(resp.message())
        }
    }
}