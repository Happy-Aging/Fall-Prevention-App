package com.appname.happyAging.data.repository.user

import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUser(): UserModel {
        return UserModel.fixture();
    }

    override suspend fun updateUser(updateUserParams: UpdateUserParams) {

    }

    override suspend fun deleteUser() {

    }
}