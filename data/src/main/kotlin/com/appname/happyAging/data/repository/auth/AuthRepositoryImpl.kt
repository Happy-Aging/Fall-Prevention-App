package com.appname.happyAging.data.repository.auth

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.auth.request.toData
import com.appname.happyAging.data.dto.auth.request.toEmailSignupData
import com.appname.happyAging.data.dto.auth.request.toSocialSignupData
import com.appname.happyAging.data.dto.auth.response.toDomain
import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.model.auth.SocialInfoModel
import com.appname.happyAging.domain.params.auth.LoginParams
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.SocialLoginParams
import com.appname.happyAging.domain.params.auth.VendorType
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(loginParams: LoginParams): JwtToken {
        return apiService.login(loginParams.toData()).toDomain()
    }

    override suspend fun socialLogin(socialLoginParams: SocialLoginParams): SocialInfoModel {
        val resp = apiService.loginSocial(socialLoginParams.toData())
        return if(resp.isSuccessful){
            SocialInfoModel.Success(resp.body()!!.toDomain())
        }else if (resp.code() == 401){
            val errorBody = resp.errorBody()?.string()
            val gson = Gson()
            val responseJson = gson.fromJson(errorBody, Map::class.java)
            val email = responseJson["email"].toString()
            val vendor = VendorType.valueOf(responseJson["vendor"].toString())
            SocialInfoModel.Progress(email, vendor)
        }else{
            SocialInfoModel.Error(resp.message())
        }
    }

    override suspend fun signup(signupParams: SignupParams) : JwtToken{
        return if(signupParams.password == null){
            apiService.socialSignup(signupParams.toSocialSignupData()).toDomain()
        }else{
            apiService.signup(signupParams.toEmailSignupData()).toDomain()
        }
    }
}