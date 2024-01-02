package com.appname.happyAging.domain.usecase.senior

import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject

class DeleteSeniorUseCase @Inject constructor(
    private val seniorRepository: SeniorRepository,
) {
    suspend operator fun invoke(seniorId: Long) : Result<Unit> {
        return runCatching {
            seniorRepository.deleteSenior(seniorId)
        }
    }
}