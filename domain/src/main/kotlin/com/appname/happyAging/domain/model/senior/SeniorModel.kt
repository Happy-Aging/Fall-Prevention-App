package com.appname.happyAging.domain.model.senior

class SeniorModel(
    val id:Long,
    val name: String,
    val age : Int,
    val sex: Sex,
    val address: String,
    val profile: String,
    val rank : Int,
)

enum class Sex{
    MALE, FEMALE
}