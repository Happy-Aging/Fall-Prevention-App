package com.appname.happyAging.data.dto.auth.response

import com.appname.happyAging.domain.model.auth.JwtToken

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)

fun TokenResponse.toDomain() = JwtToken(
    accessToken = accessToken,
    refreshToken = refreshToken,
)
