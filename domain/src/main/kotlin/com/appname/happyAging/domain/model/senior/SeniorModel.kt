package com.appname.happyAging.domain.model.senior

import java.time.LocalDate

/**
 * 신규 시니어는 [name], [address], [relation]만 가진다.
 *
 * [fallRiskRank] 1~3까지의 숫자로 표현된다. 1이 가장 위험하고 3이 덜 위험하다.
 *
 * [fallRiskRank]는 설문조사를 거친 이후에만 얻을 수 있으므로 바로 얻을 수 없다.
 */
class SeniorModel(
    val id: Long,
    val name: String,
    val birth: LocalDate,
    val phoneNumber: String?,
    val address: String,
    val relation: RelationWithSenior,
    val fallRiskRank: Int?,
) {
    companion object {
        fun fixture(
            id: Long = 1,
            name: String = "김철수",
            birth: LocalDate = LocalDate.of(1950, 1, 1),
            phoneNumber: String = "010-1234-5678",
            address: String = "서울시 강북구",
            relation: RelationWithSenior = RelationWithSenior.CARE_SENIOR,
            fallRiskRank: Int? = 1,
        ): SeniorModel {
            return SeniorModel(
                id = id,
                name = name,
                birth = birth,
                phoneNumber = phoneNumber,
                address = address,
                relation = relation,
                fallRiskRank = fallRiskRank,
            )
        }
    }
}


enum class RelationWithSenior(val korean: String, val english: String) {
    SELF("본인", "self"),
    FAMILY("가족", "family"),
    CARE_SENIOR("돌봄 대상 시니어", "care-senior"),
}
