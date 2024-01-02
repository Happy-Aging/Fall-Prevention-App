package com.appname.happyAging.domain.repository.auth

import com.appname.happyAging.domain.model.auth.JwtToken

interface JwtTokenRepository {
    suspend fun saveJwtToken(jwtToken: JwtToken)
    suspend fun getJwtToken(): JwtToken
    suspend fun deleteJwtToken()
}