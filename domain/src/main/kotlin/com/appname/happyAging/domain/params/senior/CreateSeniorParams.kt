package com.appname.happyAging.domain.params.senior

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.model.senior.SeniorModel
import java.time.LocalDate

class CreateSeniorParams(
    val name: String,
    val address: String,
    val birth: LocalDate,
    val phoneNumber: String?,
    val relation: RelationWithSenior,
)

/**
 * 긍정적 응답을 위해 [SeniorModel]로 변환한다.
 */
fun CreateSeniorParams.toModel(id: Long): SeniorModel {
    return SeniorModel(
        id = id,
        name = name,
        birth = birth,
        address = address,
        phoneNumber = phoneNumber,
        relation = relation,
        fallRiskRank = null,
    )
}
