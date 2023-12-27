package com.appname.happyAging.navigation

enum class TopLevelDestination(
    val routeName: String,
    val routePath: String,
) {
    LOGIN(
        routeName = "login",
        routePath = "/login",
    ),
    MAIN(
        routeName = "main",
        routePath = "/",
    ),
}