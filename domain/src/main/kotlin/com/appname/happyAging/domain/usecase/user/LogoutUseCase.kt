package com.appname.happyAging.domain.usecase.user

import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val jwtTokenRepository: JwtTokenRepository,
){
    suspend operator fun invoke() : Result<Unit> {
        return runCatching {
            jwtTokenRepository.deleteJwtToken()
        }
    }
}