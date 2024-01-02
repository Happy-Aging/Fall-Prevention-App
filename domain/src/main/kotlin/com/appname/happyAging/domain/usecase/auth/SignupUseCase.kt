package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.repository.auth.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String, nickname: String): Result<JwtToken>{
        return runCatching {
            authRepository.signup(email, password, nickname)
            val jwtToken = authRepository.login(email, password)
            jwtToken
        }
    }
}