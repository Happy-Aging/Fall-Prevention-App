package com.appname.happyAging.presentation.common.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.senior.view.SeniorScreen

enum class BottomNavRouter(
    val routePath: String,
    val korean : String,
    val icon: ImageVector
) {
    FALL_PREVENTION("fall-prevention", "낙상 예방 콘텐츠", Icons.Default.Home),
    SENIOR_LIST("senior-list", "시니어 목록", Icons.Default.Home),
    PROFILE("profile", "내정보", Icons.Default.Home),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val items = BottomNavRouter.values().toList()

    val mainNavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawWithContent {
                        drawContent()
                        drawLine(
                            color = Colors.DIVIDER_GREY,
                            strokeWidth = 1.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val navBackStackEntry by mainNavHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                vertical = Sizes.INTERVAL1,
                            )
                            .clickable {
                                mainNavHostController.navigate(item.routePath) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                    ){
                        Icon(imageVector = item.icon, contentDescription = null)
                        Text(text = item.korean, style = TextStyles.CONTENT_SMALL2_STYLE)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = mainNavHostController,
            startDestination = BottomNavRouter.SENIOR_LIST.routePath,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BottomNavRouter.FALL_PREVENTION.routePath) {
                Text("낙상예방 콘텐츠")
            }
            composable(route = BottomNavRouter.SENIOR_LIST.routePath) {
                SeniorScreen(navController = mainNavHostController)
            }
            composable(route = BottomNavRouter.PROFILE.routePath) {
                Text("내정보")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = NavController(LocalContext.current))
}