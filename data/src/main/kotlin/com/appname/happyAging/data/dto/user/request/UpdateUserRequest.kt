package com.appname.happyAging.data.dto.user.request

import com.appname.happyAging.domain.params.auth.UserType

data class UpdateUserRequest(
    val name: String,
    val phoneNumber : String,
    val userType: UserType,
    val password : String?,
)
