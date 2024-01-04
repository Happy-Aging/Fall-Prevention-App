package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.VendorType
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
) {
    suspend operator fun invoke(signupParams: SignupParams): Result<Unit>{
        return runCatching {
            authRepository.signup(signupParams)
            if(signupParams.vendor == VendorType.HAPPY_AGING){
                val loginParams = LoginParams(
                    email = signupParams.email,
                    password = signupParams.password!!
                )
                val token = authRepository.login(loginParams)
                jwtTokenRepository.saveJwtToken(token)
            }
        }
    }
}
