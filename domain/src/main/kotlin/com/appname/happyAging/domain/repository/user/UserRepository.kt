package com.appname.happyAging.domain.repository.user

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.user.UpdateUserParams

interface UserRepository {
    suspend fun getUser(): ApiResponse<UserModel>
    suspend fun updateUser(updateUserParams: UpdateUserParams) : ApiResponse<Unit>
    suspend fun deleteUser() : ApiResponse<Unit>

}