package com.appname.happyAging.data.dto.auth.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
