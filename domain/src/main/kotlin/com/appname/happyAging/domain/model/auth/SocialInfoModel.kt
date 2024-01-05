package com.appname.happyAging.domain.model.auth

import com.appname.happyAging.domain.params.auth.VendorType

sealed class SocialInfoModel{
    data class Success(val token: JwtToken) : SocialInfoModel()
    data class Progress(val email: String, val vendor: VendorType) : SocialInfoModel()
    data class Error(val message: String) : SocialInfoModel()
}
