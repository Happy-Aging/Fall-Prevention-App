package com.appname.happyAging.data.dto.user.response

import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType

data class UserResponse (
    val name: String,
    val phoneNumber : String,
    val userType: UserType,
    val vendor : VendorType,
)