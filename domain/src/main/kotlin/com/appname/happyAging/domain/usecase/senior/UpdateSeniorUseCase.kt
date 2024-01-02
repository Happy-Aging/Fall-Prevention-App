package com.appname.happyAging.domain.usecase.senior

import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.domain.repository.senior.SeniorRepository
import javax.inject.Inject

class UpdateSeniorUseCase @Inject constructor(
    private val seniorRepository: SeniorRepository,
) {
    suspend operator fun invoke(
        updateSeniorParams: UpdateSeniorParams
    ) : Result<Unit> {
        return runCatching {
            seniorRepository.updateSenior(updateSeniorParams)
        }
    }
}