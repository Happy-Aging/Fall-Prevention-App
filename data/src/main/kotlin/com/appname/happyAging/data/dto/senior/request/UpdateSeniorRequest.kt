package com.appname.happyAging.data.dto.senior.request

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams

data class UpdateSeniorRequest(
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: String,
    val relation : RelationWithSenior,
)

fun UpdateSeniorParams.toData(): UpdateSeniorRequest {
    return UpdateSeniorRequest(
        name = name,
        address = address,
        phoneNumber = phoneNumber,
        birth = birth.toString(),
        relation = relation,
    )
}