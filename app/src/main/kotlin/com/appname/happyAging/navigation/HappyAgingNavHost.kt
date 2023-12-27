package com.appname.happyAging.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.appname.happyAging.presentation.common.view.MainScreen
import com.appname.happyAging.presentation.user.view.LoginScreen
import com.appname.happyAging.ui.HappyAgingAppState

@Composable
fun HappyAgingNavHost(
    appState: HappyAgingAppState,
    modifier: Modifier = Modifier,
    startDestination: String = TopLevelDestination.LOGIN.routeName,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ){
        composable(TopLevelDestination.LOGIN.routeName) {
            LoginScreen(navController)
        }
        composable(TopLevelDestination.MAIN.routeName) {
            MainScreen(navController)
        }
    }
}
