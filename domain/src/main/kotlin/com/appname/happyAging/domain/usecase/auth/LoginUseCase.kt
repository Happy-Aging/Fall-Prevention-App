package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
) {
    suspend operator fun invoke(loginParams: LoginParams): ApiResponse<Unit> {
        return when (val token = authRepository.login(loginParams)) {
            is ApiResponse.Success -> {
                jwtTokenRepository.saveJwtToken(token.data)
                ApiResponse.Success(Unit)
            }
            is ApiResponse.Error -> {
                ApiResponse.Error(token.message)
            }
        }
    }

}