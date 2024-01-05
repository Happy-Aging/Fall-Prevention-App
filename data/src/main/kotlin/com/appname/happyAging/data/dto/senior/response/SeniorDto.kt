package com.appname.happyAging.data.dto.senior.response

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import java.time.LocalDate

data class SeniorDto(
    val id: Long,
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: LocalDate,
    val relation : RelationWithSenior,
    val fallRiskRank : Int,
)
