package com.appname.happyAging.domain.usecase.senior

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject

class GetSeniorUseCase @Inject constructor(
    private val seniorRepository: SeniorRepository,
) {
    suspend operator fun invoke() : ApiResponse<List<SeniorModel>> {
        return seniorRepository.getSeniorList()
    }
}