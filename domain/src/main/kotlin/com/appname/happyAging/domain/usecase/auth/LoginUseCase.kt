package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
) {
    suspend operator fun invoke(loginParams: LoginParams): Result<Unit> {
        return runCatching {
            val token = authRepository.login(loginParams)
            jwtTokenRepository.saveJwtToken(token)
        }
    }

}