package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): Result<JwtToken> {
        return runCatching {
            authRepository.login(email, password)
        }
    }

}