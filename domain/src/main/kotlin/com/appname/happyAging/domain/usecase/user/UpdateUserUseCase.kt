package com.appname.happyAging.domain.usecase.user

import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.domain.repository.user.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(updateUserParams: UpdateUserParams) : Result<Unit> {
        return runCatching {
            userRepository.updateUser(updateUserParams)
        }
    }
}