package com.appname.happyAging.data.dto.senior.request

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.CreateSeniorParams

data class CreateSeniorRequest(
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: String,
    val relation : RelationWithSenior,
)

fun CreateSeniorParams.toData(): CreateSeniorRequest {
    return CreateSeniorRequest(
        name = name,
        address = address,
        phoneNumber = phoneNumber,
        birth = birth.toString(),
        relation = relation,
    )
}