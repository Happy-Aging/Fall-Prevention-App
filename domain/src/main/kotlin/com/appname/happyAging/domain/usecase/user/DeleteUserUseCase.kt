package com.appname.happyAging.domain.usecase.user

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val logoutUseCase: LogoutUseCase,
){
    suspend operator fun invoke() : ApiResponse<Unit> {
        val resp = userRepository.deleteUser()
        val logout = logoutUseCase()
        return resp
    }
}