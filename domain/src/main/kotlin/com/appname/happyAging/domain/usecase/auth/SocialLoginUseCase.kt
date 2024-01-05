package com.appname.happyAging.domain.usecase.auth

import com.appname.happyAging.domain.model.auth.SocialInfoModel
import com.appname.happyAging.domain.params.auth.SocialLoginParams
import com.appname.happyAging.domain.repository.auth.AuthRepository
import com.appname.happyAging.domain.repository.auth.JwtTokenRepository
import javax.inject.Inject

class SocialLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtTokenRepository: JwtTokenRepository,
){
    suspend operator fun invoke(socialLoginParams: SocialLoginParams): Result<SocialInfoModel> {
        return runCatching {
            when(val resp = authRepository.socialLogin(socialLoginParams)){
                is SocialInfoModel.Error -> {
                    resp
                }
                is SocialInfoModel.Progress -> {
                    resp
                }
                is SocialInfoModel.Success -> {
                    jwtTokenRepository.saveJwtToken(resp.token)
                    resp
                }
            }

        }
    }
}