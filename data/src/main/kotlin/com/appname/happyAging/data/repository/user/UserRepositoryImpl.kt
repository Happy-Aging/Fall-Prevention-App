package com.appname.happyAging.data.repository.user

import com.appname.happyAging.data.api.ApiService
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
        //apiService.getUser().toDomain()
        return UserModel.fixture()
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) {
        //apiService.updateUser(updateUserParams.toData())
    }

    override suspend fun deleteUser() {
        //apiService.deleteUser()
    }
}