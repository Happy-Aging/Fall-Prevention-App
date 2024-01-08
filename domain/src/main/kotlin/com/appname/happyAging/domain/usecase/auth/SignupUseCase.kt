package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
) {
    suspend operator fun invoke(signupParams: SignupParams): ApiResponse<Unit>{
        return when (val token = authRepository.signup(signupParams)) {
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
