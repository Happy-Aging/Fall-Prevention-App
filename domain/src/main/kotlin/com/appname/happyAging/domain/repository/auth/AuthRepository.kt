package com.appname.happyAging.domain.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.model.auth.SocialInfoModel
import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.SocialLoginParams

interface AuthRepository {
    suspend fun login(loginParams: LoginParams) : ApiResponse<JwtToken>
    suspend fun socialLogin(socialLoginParams: SocialLoginParams) : ApiResponse<SocialInfoModel>
    suspend fun signup(signupParams: SignupParams) : ApiResponse<JwtToken>
}