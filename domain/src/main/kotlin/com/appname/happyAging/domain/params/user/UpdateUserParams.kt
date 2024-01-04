package com.appname.happyAging.domain.params.user

import com.appname.happyAging.domain.model.user.UserModel
import com.appname.happyAging.domain.params.auth.UserType

class UpdateUserParams(
    val name: String,
    val phoneNumber: String,
    val userType: UserType,
    val password: String?,
)

fun UserModel.update(updateUserParams: UpdateUserParams) : UserModel{
    return UserModel(
        name = updateUserParams.name,
        phoneNumber = updateUserParams.phoneNumber,
        userType = updateUserParams.userType,
        vendor = this.vendor,
    )
}