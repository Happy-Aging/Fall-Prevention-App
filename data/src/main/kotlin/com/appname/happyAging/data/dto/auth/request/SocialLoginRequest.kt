package com.appname.happyAging.data.dto.auth.request

import com.appname.happyAging.domain.params.auth.VendorType

data class SocialLoginRequest(
    val accessToken: String,
    val vendor: VendorType,
)
