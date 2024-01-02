package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
) {
    suspend operator fun invoke(email: String, password: String, nickname: String): Result<Unit>{
        return runCatching {
            authRepository.signup(email, password, nickname)
            val jwtToken = authRepository.login(email, password)
            jwtTokenRepository.saveJwtToken(jwtToken)
        }
    }
}
