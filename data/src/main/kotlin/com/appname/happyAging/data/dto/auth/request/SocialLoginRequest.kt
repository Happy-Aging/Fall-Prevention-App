package com.appname.happyAging.data.dto.auth.request

import com.appname.happyAging.domain.params.auth.SocialLoginParams
import com.appname.happyAging.domain.params.auth.VendorType

data class SocialLoginRequest(
    val accessToken: String,
    val vendor: VendorType,
)

fun SocialLoginParams.toData() = SocialLoginRequest(
    accessToken = accessToken,
    vendor = vendor,
)
