package com.appname.happyAging.domain.usecase.user

import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() : Result<UserModel> {
        return runCatching {
            userRepository.getUser()
        }
    }
}