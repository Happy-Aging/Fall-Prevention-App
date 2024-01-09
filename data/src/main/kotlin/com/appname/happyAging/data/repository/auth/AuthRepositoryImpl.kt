package com.appname.happyAging.data.repository.auth

import com.appname.happyAging.data.api.ApiConstants
import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.auth.request.toData
import com.appname.happyAging.data.dto.auth.request.toEmailSignupData
import com.appname.happyAging.data.dto.auth.request.toSocialSignupData
import com.appname.happyAging.data.dto.auth.response.toDomain
import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.model.auth.SocialInfoModel
import com.appname.happyAging.domain.model.common.ApiResponse
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
    override suspend fun login(loginParams: LoginParams): ApiResponse<JwtToken> {
        runCatching {
            apiService.login(loginParams.toData())
        }.onSuccess {
            if(!it.isSuccessful){
                return ApiResponse.Error(it.message())
            }
            return ApiResponse.Success(it.body()!!.toDomain())
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun socialLogin(socialLoginParams: SocialLoginParams): ApiResponse<SocialInfoModel> {
        runCatching {
            apiService.loginSocial(socialLoginParams.toData())
        }.onSuccess {
            if(!it.isSuccessful){
                return ApiResponse.Error(it.message())
            }else if(it.code() == 401){
                val errorBody = it.errorBody()?.string()
                val gson = Gson()
                val responseJson = gson.fromJson(errorBody, Map::class.java)
                val email = responseJson["email"].toString()
                val vendor = VendorType.valueOf(responseJson["vendor"].toString())
                return ApiResponse.Success(SocialInfoModel.Progress(email, vendor))
            }
            return ApiResponse.Success(SocialInfoModel.Success(it.body()!!.toDomain()))
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }

    override suspend fun signup(signupParams: SignupParams) : ApiResponse<JwtToken>{
        kotlin.runCatching {
            val resp = if(signupParams.password == null) {
                apiService.socialSignup(signupParams.toSocialSignupData())
            }else{
                apiService.signup(signupParams.toEmailSignupData())
            }
            if(!resp.isSuccessful) {
                ApiResponse.Error(resp.message())
            }else{
                ApiResponse.Success(resp.body()!!.toDomain())
            }
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error("Unknown Error")
    }
}