package com.appname.happyAging.domain.usecase.user

import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val logoutUseCase: LogoutUseCase,
){
    suspend operator fun invoke() : Result<Unit> {
        return runCatching {
            userRepository.deleteUser()
            logoutUseCase()
        }
    }
}