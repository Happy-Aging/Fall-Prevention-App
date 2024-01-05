package com.appname.happyAging.domain.model.user

import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType

class UserModel(
    val name: String,
    val phoneNumber: String,
    val userType: UserType,
    val vendor: VendorType,
){
    companion object {
        fun fixture(
            name: String = "홍길동",
            phoneNumber: String = "010-1234-5678",
            userType: UserType = UserType.USER,
            vendor: VendorType = VendorType.KAKAO,
        ) = UserModel(
            name = name,
            phoneNumber = phoneNumber,
            userType = userType,
            vendor = vendor,
        )
    }
}