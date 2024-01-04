package com.appname.happyAging.data.dto.auth.request

import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType

data class SignupRequest(
    val name: String,
    val email: String,
    val password :String?,
    val phoneNumber : String?,
    val type: UserType,
    val vendor : VendorType,
)
