package com.appname.happyAging.navigation

import com.appname.happyAging.presentation.common.navigation.LOGIN_GRAPH_ROUTE_PATTERN
import com.appname.happyAging.presentation.common.navigation.MAIN_GRAPH_ROUTE_PATTERN

enum class TopLevelDestination(
    val routeName: String,
    val routePath: String,
) {
    LOGIN(
        routeName = "login",
        routePath = LOGIN_GRAPH_ROUTE_PATTERN,
    ),
    MAIN(
        routeName = "main",
        routePath = MAIN_GRAPH_ROUTE_PATTERN,
    ),
}