package com.appname.happyAging.presentation.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appname.happyAging.presentation.auth.view.LoginScreen
import com.appname.happyAging.presentation.auth.view.SignupScreen
import com.appname.happyAging.presentation.auth.viewmodel.AuthViewModel

const val LOGIN_GRAPH_ROUTE_PATTERN = "/login"

enum class LoginRouter(
    val routePath: String,
    val korean : String,
) {
    LOGIN("login", "로그인"),
    EMAIL_SIGNUP("signup", "회원가입"),
    KAKAO_SIGNUP("kakao-signup", "회원가입"),
}




@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.loginGraph(
    navController: NavController,
) {
    navigation(
        route = LOGIN_GRAPH_ROUTE_PATTERN,
        startDestination = LoginRouter.LOGIN.routePath,
    ) {
        composable(route = LoginRouter.LOGIN.routePath) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(MAIN_GRAPH_ROUTE_PATTERN){
                        popUpTo(LOGIN_GRAPH_ROUTE_PATTERN){
                            inclusive = true
                        }
                    }
                },
                onEmailSignup = {
                    navController.go(LoginRouter.EMAIL_SIGNUP)
                },
                onKakaoSignup = {
                    navController.go(LoginRouter.KAKAO_SIGNUP)
                }
            )
        }
        composable(route = LoginRouter.EMAIL_SIGNUP.routePath) {
            val parentEntry = remember { navController.getBackStackEntry(LoginRouter.LOGIN.routePath) }
            val authViewModel : AuthViewModel = hiltViewModel(parentEntry)
            SignupScreen(
                onBackButton = {
                    navController.popBackStack()
                },
                signupSuccess = {
                    navController.navigate(MAIN_GRAPH_ROUTE_PATTERN){
                        popUpTo(LOGIN_GRAPH_ROUTE_PATTERN){
                            inclusive = true
                        }
                    }
                },
                isKakaoSignup = false,
                authViewModel = authViewModel,
            )
        }
        composable(route = LoginRouter.KAKAO_SIGNUP.routePath) {
            val parentEntry = remember { navController.getBackStackEntry(LoginRouter.LOGIN.routePath) }
            val authViewModel : AuthViewModel = hiltViewModel(parentEntry)
            SignupScreen(
                onBackButton = {
                    navController.popBackStack()
                },
                signupSuccess = {
                    navController.navigate(MAIN_GRAPH_ROUTE_PATTERN){
                        popUpTo(LOGIN_GRAPH_ROUTE_PATTERN){
                            inclusive = true
                        }
                    }
                },
                isKakaoSignup = true,
                authViewModel = authViewModel,
            )
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
            navigate(LoginRouter.EMAIL_SIGNUP.routePath)
        }
        LoginRouter.KAKAO_SIGNUP -> {
            navigate(LoginRouter.KAKAO_SIGNUP.routePath)
        }
    }
}