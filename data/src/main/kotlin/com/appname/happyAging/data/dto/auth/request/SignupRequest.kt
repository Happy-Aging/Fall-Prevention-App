package com.appname.happyAging.data.dto.auth.request

import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType

data class SignupRequest(
    val name: String,
    val email: String,
    val password :String,
    val phoneNumber : String?,
    val userType: UserType,
    val vendor : VendorType,
)

data class SocialSignupRequest(
    val name: String,
    val email: String,
    val phoneNumber : String?,
    val type: UserType,
    val vendor : VendorType,
)

fun SignupParams.toEmailSignupData() = SignupRequest(
    name = name,
    email = email,
    password = password!!,
    phoneNumber = phoneNumber,
    userType = type,
    vendor = vendor,
)


fun SignupParams.toSocialSignupData() = SocialSignupRequest(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    type = type,
    vendor = vendor,
)