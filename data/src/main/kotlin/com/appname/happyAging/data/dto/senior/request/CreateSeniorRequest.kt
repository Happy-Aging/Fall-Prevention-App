package com.appname.happyAging.data.dto.senior.request

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import java.time.LocalDate

data class CreateSeniorRequest(
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: LocalDate,
    val relation : RelationWithSenior,
)

fun CreateSeniorParams.toData(): CreateSeniorRequest {
    return CreateSeniorRequest(
        name = name,
        address = address,
        phoneNumber = phoneNumber,
        birth = birth,
        relation = relation,
    )
}