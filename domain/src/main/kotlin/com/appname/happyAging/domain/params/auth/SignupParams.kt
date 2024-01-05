package com.appname.happyAging.domain.params.auth

class SignupParams(
    val name: String,
    val email: String,
    val password: String?,
    val phoneNumber: String?,
    val type: UserType,
    val vendor: VendorType,
)

enum class UserType(val english: String, val korean: String) {
    USER("user", "일반"),
    MANAGER("careManager", "돌봄매니저"),
}

enum class VendorType(val korean: String) {
    HAPPY_AGING("해피에이징"),
    KAKAO("카카오"),
}
