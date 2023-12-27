package com.appname.happyAging.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appname.happyAging.presentation.user.view.LoginScreen
import com.appname.happyAging.presentation.user.view.SignupScreenFactory
import com.appname.happyAging.presentation.user.view.emailSignup
import com.appname.happyAging.presentation.user.view.kakaoSignup

private const val LOGIN_GRAPH_ROUTE_PATTERN = "/login"

enum class LoginRouter(
    val routePath: String,
    val korean : String,
) {
    LOGIN(LOGIN_ROUTE, "로그인"),
    EMAIL_SIGNUP(EMAIL_SIGNUP_ROUTE, "회원가입"),
    KAKAO_SIGNUP(KAKAO_SIGNUP_ROUTE, "회원가입"),
}

const val LOGIN_ROUTE = "login"
const val EMAIL_SIGNUP_ROUTE = "signup"
const val KAKAO_SIGNUP_ROUTE = "kakao-signup"


fun NavGraphBuilder.loginGraph(
    navController: NavController,
) {
    navigation(
        route = LOGIN_GRAPH_ROUTE_PATTERN,
        startDestination = LoginRouter.LOGIN.routePath,
    ) {
        composable(route = LoginRouter.LOGIN.routePath) {
            LoginScreen(navController)
        }
        composable(route = LoginRouter.EMAIL_SIGNUP.routePath) {
            SignupScreenFactory.emailSignup(navController)
        }
        composable(route = LoginRouter.KAKAO_SIGNUP.routePath) {
            SignupScreenFactory.kakaoSignup(navController)
        }
    }
}

fun NavController.navigateLogin(
    navOptions: androidx.navigation.NavOptions? = null,
) {
    navigate(LOGIN_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavController.navigateEmailSignup(
    navOptions: androidx.navigation.NavOptions? = null,
) {
    navigate(EMAIL_SIGNUP_ROUTE, navOptions)
}

fun NavController.navigateKakaoSignup(
    navOptions: androidx.navigation.NavOptions? = null,
) {
    navigate(KAKAO_SIGNUP_ROUTE, navOptions)
}