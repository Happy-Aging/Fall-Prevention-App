package com.appname.happyAging.data.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.SocialLoginParams
import com.appname.happyAging.domain.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun login(loginParams: LoginParams): JwtToken {
        return JwtToken("accessToken", "refreshToken")
    }

    override suspend fun socialLogin(socialLoginParams: SocialLoginParams): JwtToken {
        return JwtToken("acc","ref")
    }

    override suspend fun signup(signupParams: SignupParams) {

    }
}