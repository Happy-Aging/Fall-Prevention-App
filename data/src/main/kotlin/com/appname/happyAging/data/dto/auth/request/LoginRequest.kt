package com.appname.happyAging.data.dto.auth.request

import com.appname.happyAging.domain.params.auth.LoginParams

data class LoginRequest(
    val email: String,
    val password: String,
)

fun LoginParams.toData() = LoginRequest(
    email = email,
    password = password,
)
