package com.appname.happyAging.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.appname.happyAging.presentation.common.navigation.loginGraph
import com.appname.happyAging.presentation.common.navigation.mainGraph
import com.appname.happyAging.ui.HappyAgingAppState

@Composable
fun HappyAgingNavHost(
    appState: HappyAgingAppState,
    modifier: Modifier = Modifier,
    startDestination: String = TopLevelDestination.LOGIN.routePath,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ){
        loginGraph(navController)
        mainGraph(navController)
    }
}
