package com.appname.happyAging.data.dto.senior.request

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import java.time.LocalDate

data class UpdateSeniorRequest(
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: LocalDate,
    val relation : RelationWithSenior,
)

fun UpdateSeniorParams.toData(): UpdateSeniorRequest {
    return UpdateSeniorRequest(
        name = name,
        address = address,
        phoneNumber = phoneNumber,
        birth = birth,
        relation = relation,
    )
}