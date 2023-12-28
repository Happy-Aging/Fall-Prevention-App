package com.appname.happyAging.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appname.happyAging.presentation.user.view.LoginScreen
import com.appname.happyAging.presentation.user.view.SignupScreenFactory
import com.appname.happyAging.presentation.user.view.emailSignup
import com.appname.happyAging.presentation.user.view.kakaoSignup

const val LOGIN_GRAPH_ROUTE_PATTERN = "/login"

enum class LoginRouter(
    val routePath: String,
    val korean : String,
) {
    LOGIN("login", "로그인"),
    EMAIL_SIGNUP("signup", "회원가입"),
    KAKAO_SIGNUP("kakao-signup", "회원가입"),
}




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
    navigate(LoginRouter.LOGIN.routePath, navOptions)
}


fun NavController.go(
    loginRouter: LoginRouter,
) {
    when (loginRouter) {
        LoginRouter.LOGIN -> navigateLogin()
        LoginRouter.EMAIL_SIGNUP -> {
            navigate(LoginRouter.KAKAO_SIGNUP.routePath)
        }
        LoginRouter.KAKAO_SIGNUP -> {
            navigate(LoginRouter.EMAIL_SIGNUP.routePath)
        }
    }
}