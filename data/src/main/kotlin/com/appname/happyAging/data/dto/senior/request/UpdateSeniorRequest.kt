package com.appname.happyAging.data.dto.senior.request

import com.appname.happyAging.domain.model.senior.RelationWithSenior

data class UpdateSeniorRequest(
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: String,
    val relation : RelationWithSenior,
)


