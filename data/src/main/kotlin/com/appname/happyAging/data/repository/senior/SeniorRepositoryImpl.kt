package com.appname.happyAging.data.repository.senior

import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.model.senior.Sex
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeniorRepositoryImpl @Inject constructor() : SeniorRepository {
    override suspend fun getSeniorList(): List<SeniorModel> {
        return listOf(
            SeniorModel(
                1,
                "김철수",
                65,
                Sex.MALE,
                "2021-01-01",
                "2021-01-01",
                1,
            )
        )
    }

    override suspend fun updateSenior(updateSeniorParams: UpdateSeniorParams) {

    }

    override suspend fun deleteSenior(id: Long) {

    }

}