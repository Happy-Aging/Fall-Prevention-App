package com.appname.happyAging.domain.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.SocialLoginParams

interface AuthRepository {
    suspend fun login(loginParams: LoginParams) : JwtToken
    suspend fun socialLogin(socialLoginParams: SocialLoginParams) : JwtToken
    suspend fun signup(signupParams: SignupParams)
}