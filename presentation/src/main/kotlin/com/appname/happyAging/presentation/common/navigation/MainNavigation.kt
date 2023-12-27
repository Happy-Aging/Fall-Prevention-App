package com.appname.happyAging.presentation.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appname.happyAging.presentation.common.view.MainScreen


private const val MAIN_GRAPH_ROUTE_PATTERN = "/"
const val MAIN_ROUTE = "main"
fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    navigation(
        route = MAIN_GRAPH_ROUTE_PATTERN,
        startDestination = MAIN_ROUTE,
    ) {
        composable(route = MAIN_ROUTE) {
            MainScreen(navController)
        }

    }
}
fun NavController.navigateMain(
    navOptions: androidx.navigation.NavOptions? = null,
) {
    navigate(MAIN_GRAPH_ROUTE_PATTERN, navOptions)
}
