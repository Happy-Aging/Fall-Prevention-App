package com.appname.happyAging.data.dto.user.response

import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType

data class UserResponse (
    val name: String,
    val phoneNumber : String,
    val userType: UserType,
    val vendor : VendorType,
)

fun UserResponse.toDomain() = UserModel(
    name = name,
    phoneNumber = phoneNumber,
    userType = userType,
    vendor = vendor,
)