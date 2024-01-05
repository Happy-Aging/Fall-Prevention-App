package com.appname.happyAging.data.dto.senior.response

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.model.senior.SeniorModel
import java.time.LocalDate

data class SeniorDto(
    val id: Long,
    val name: String,
    val address : String,
    val phoneNumber : String?,
    val birth: LocalDate,
    val relation : RelationWithSenior,
    val fallRiskRank : Int?,
)

fun SeniorDto.toDomain() = SeniorModel(
    id = id,
    name = name,
    address = address,
    phoneNumber = phoneNumber,
    birth = birth,
    relation = relation,
    fallRiskRank = fallRiskRank,
)

fun SeniorModel.toDto() = SeniorDto(
    id = id,
    name = name,
    address = address,
    phoneNumber = phoneNumber,
    birth = birth,
    relation = relation,
    fallRiskRank = fallRiskRank,
)