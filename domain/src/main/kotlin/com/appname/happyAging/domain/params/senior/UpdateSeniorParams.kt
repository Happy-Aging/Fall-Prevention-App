package com.appname.happyAging.domain.params.senior

import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.model.senior.SeniorModel
import java.time.LocalDate

class UpdateSeniorParams(
    val id: Long,
    val name: String,
    val birth: LocalDate,
    val address: String,
    val relation: RelationWithSenior,
)

/**
 * 긍정적 응답을 위해 [SeniorModel]로 변환한다.
 */
fun SeniorModel.update(updateSeniorParams: UpdateSeniorParams): SeniorModel {
    return SeniorModel(
        id = updateSeniorParams.id,
        name = updateSeniorParams.name,
        birth = updateSeniorParams.birth,
        address = updateSeniorParams.address,
        phoneNumber = this.phoneNumber,
        relation = updateSeniorParams.relation,
        fallRiskRank = this.fallRiskRank,
    )
}