package com.appname.happyAging.data.api

import com.appname.happyAging.data.api.ApiConstants.BASE_URL
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val originRequest = response.request
        if(originRequest.header("Authorization").isNullOrEmpty()){
            return null
        }
        val refreshToken = runBlocking {
            jwtTokenRepository.getJwtToken()!!.refreshToken
        }
        val refreshRequest = Request.Builder()
            .url("$BASE_URL/reissue")
            .post("".toRequestBody())
            .addHeader("authorization", "Bearer ${refreshToken}")
            .build()
        val refreshResponse = OkHttpClient().newCall(refreshRequest).execute()
        if(refreshResponse.code == 200) {
            val gson = Gson()
            val refreshResponseJson = gson.fromJson(refreshResponse.body?.string(), Map::class.java)
            val newAccessToken = refreshResponseJson["accessToken"].toString()
            runBlocking {
                jwtTokenRepository.saveAccessToken(newAccessToken)
            }
            val newRequest = originRequest.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer $newAccessToken")
                .build()
            return newRequest
        }else{
            runBlocking {
                jwtTokenRepository.deleteJwtToken()
            }
        }
        return null

    }

}

class HeaderInterceptor @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(chain.request().headers["Auth"] == "false"){
            val newRequest = chain.request().newBuilder()
                .removeHeader("Auth")
                .build()
            return chain.proceed(newRequest)
        }

        var token = ""
        runBlocking {
            val accessToken = jwtTokenRepository.getJwtToken()!!.accessToken
            token = ("Bearer $accessToken")
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        val response = chain.proceed(newRequest)


        return response
    }
}