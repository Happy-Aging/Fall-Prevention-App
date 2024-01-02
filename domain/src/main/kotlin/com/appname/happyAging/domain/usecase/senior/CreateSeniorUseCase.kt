package com.appname.happyAging.domain.usecase.senior

import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject

class CreateSeniorUseCase @Inject constructor(
    private val seniorRepository: SeniorRepository,
) {
    suspend operator fun invoke(createSeniorParams: CreateSeniorParams) : Result<Long> {
        return runCatching {
            seniorRepository.createSenior(createSeniorParams)
        }
    }
}