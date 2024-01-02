package com.appname.happyAging.presentation.user.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.repository.user.UserRepository
import com.appname.happyAging.domain.usecase.auth.LoginUseCase
import com.appname.happyAging.domain.usecase.auth.SignupUseCase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
) : ViewModel() {

    fun emailLogin(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).onSuccess {
                Log.i(TAG, "로그인 성공")
            }.onFailure {
                Log.e(TAG, "로그인 실패", it)
            }
        }
    }

    fun signup(email: String, password: String, nickname: String) {
        viewModelScope.launch {
            signupUseCase(email, password, nickname).onSuccess {
                Log.i(TAG, "회원가입 성공")
            }.onFailure {
                Log.e(TAG, "회원가입 실패", it)
            }
        }
    }

    fun kakaoLogin(context: Context) {
        viewModelScope.launch {
            val kakaoAccessToken = handleKakaoLogin(context)
            if(kakaoAccessToken == null){
                Log.e(TAG, "카카오계정으로 로그인 실패")
            }else{
                Log.i(TAG, "카카오계정으로 로그인 성공 $kakaoAccessToken")
            }
        }
    }

    private suspend fun handleKakaoLogin(context: Context): OAuthToken? =
        suspendCoroutine { continuation ->
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    continuation.resume(null)
                } else if (token != null) {
                    continuation.resume(token)
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }


    companion object {
        private const val TAG = "UserViewModel"
    }
}