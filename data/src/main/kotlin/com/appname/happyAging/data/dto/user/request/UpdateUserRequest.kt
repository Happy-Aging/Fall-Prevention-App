package com.appname.happyAging.data.dto.user.request

import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.user.UpdateUserParams

data class UpdateUserRequest(
    val name: String,
    val phoneNumber : String,
    val userType: UserType,
    val password : String?,
)

fun UpdateUserParams.toData() = UpdateUserRequest(
    name = name,
    phoneNumber = phoneNumber,
    userType = userType,
    password = password,
)
