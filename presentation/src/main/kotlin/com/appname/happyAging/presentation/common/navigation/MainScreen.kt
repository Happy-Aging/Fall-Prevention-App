package com.appname.happyAging.presentation.common.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.appname.happyAging.presentation.R
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.utils.noRippleClickable
import com.appname.happyAging.presentation.my.view.EditInfoScreen
import com.appname.happyAging.presentation.my.view.MyPageScreen
import com.appname.happyAging.presentation.senior.view.CreateSeniorScreen
import com.appname.happyAging.presentation.senior.view.EditSeniorScreen
import com.appname.happyAging.presentation.senior.view.SeniorScreen
import com.appname.happyAging.presentation.senior.view.SeniorScreenV2

enum class BottomNavRouter(
    val routePath: String,
    val korean: String,
    val icon: Int
) {
    FALL_PREVENTION("fall-prevention", "낙상 예방 콘텐츠", R.drawable.content),
    SENIOR_LIST("senior-list", "시니어 목록", R.drawable.home),
    PROFILE("profile", "내정보", R.drawable.my),
}

enum class Router(
    val routePath: String,
    val korean: String,
) {
    SENIOR_CREATE("senior-create", "시니어 생성"),
    SENIOR_EDIT("senior-edit", "시니어 수정"),
    EDIT_INFO("edit-info", "정보 수정"),
}

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogoutClick: () -> Unit = {},
) {
    val items = BottomNavRouter.values().toList()

    val mainNavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by mainNavHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute in items.map { it.routePath }) {
                NavigationBar(
                    modifier = Modifier.shadow(
                        elevation = 15.dp,
                        spotColor = Color(0xff000000),
                        ambientColor = Color(0xff000000),
                    ),
                    containerColor = Colors.WHITE,
                ) {
                    items.forEach { item ->
                        val selected = item.routePath == currentRoute
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .noRippleClickable {
                                    mainNavHostController.navigate(item.routePath) {
                                        popUpTo(mainNavHostController.graph.startDestinationId) {
                                            inclusive = false
                                        }
                                        launchSingleTop = true
                                    }
                                }
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = null,
                                tint = if (selected) Colors.SELECTED_ICON else Colors.UNSELECTED_ICON,
                            )
                            Text(text = item.korean, style = TextStyles.CONTENT_SMALL2_STYLE)
                        }
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
                SeniorScreenV2()
            }
            composable(route = BottomNavRouter.SENIOR_LIST.routePath) {
                SeniorScreen(
                    onSeniorEditClick = { seniorId->
                        mainNavHostController.navigate("${Router.SENIOR_EDIT.routePath}/$seniorId")
                    },
                    onAddSeniorClick = {
                        mainNavHostController.navigate(Router.SENIOR_CREATE.routePath)
                    },
                )
            }
            composable(route = BottomNavRouter.PROFILE.routePath) {
                MyPageScreen(
                    onLogoutClick = onLogoutClick,
                    onEditUserInfoClick = {
                        mainNavHostController.navigate(Router.EDIT_INFO.routePath)
                    },
                    onDeleteUserClick = onLogoutClick,
                )
            }
            composable(route = Router.SENIOR_CREATE.routePath) {
                val parentEntry = remember { mainNavHostController.getBackStackEntry(BottomNavRouter.SENIOR_LIST.routePath) }
                CreateSeniorScreen(
                    backAction = {
                        mainNavHostController.popBackStack()
                    },
                    seniorViewModel = hiltViewModel(parentEntry),
                )
            }
            composable(route = "${Router.SENIOR_EDIT.routePath}/{id}") {
                val parentEntry = remember { mainNavHostController.getBackStackEntry(BottomNavRouter.SENIOR_LIST.routePath) }

                EditSeniorScreen(
                    backAction = {
                        mainNavHostController.popBackStack()
                    },
                    id = it.arguments?.getString("id")?.toLong()!!,
                    seniorViewModel = hiltViewModel(parentEntry),
                )
            }
            composable(route = Router.EDIT_INFO.routePath) {
                val parentEntry = remember { mainNavHostController.getBackStackEntry(BottomNavRouter.PROFILE.routePath) }
                EditInfoScreen(
                    onSaveButtonClick= {
                        mainNavHostController.popBackStack()
                    },
                    userViewModel = hiltViewModel(parentEntry),
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}