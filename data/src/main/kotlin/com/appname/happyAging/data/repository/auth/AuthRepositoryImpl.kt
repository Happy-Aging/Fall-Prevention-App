package com.appname.happyAging.data.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken
import com.appname.happyAging.domain.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun login(email: String, password: String): JwtToken {
        return JwtToken("accessToken", "refreshToken")
    }

    override suspend fun signup(email: String, password: String, nickname: String) {

    }
}