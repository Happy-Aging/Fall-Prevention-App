package com.appname.happyAging.domain.repository.user

import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams

interface UserRepository {
    suspend fun getUser(): UserModel
    suspend fun updateUser(updateUserParams: UpdateUserParams)
    suspend fun deleteUser()

}