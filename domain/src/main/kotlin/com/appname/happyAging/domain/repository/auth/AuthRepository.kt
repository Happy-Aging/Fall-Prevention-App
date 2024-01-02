package com.appname.happyAging.domain.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken

interface AuthRepository {
    suspend fun login(email: String, password: String) : JwtToken
    suspend fun signup(email: String, password: String, nickname: String)
}