package com.appname.happyAging.presentation.my.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.common.utils.noRippleClickable
import com.appname.happyAging.presentation.my.viewmodel.UserViewModel

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel : UserViewModel = hiltViewModel(),
){
    DefaultLayout(title = BottomNavRouter.PROFILE.korean) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextRow(text = "로그아웃") {
                viewModel.logout()
            }
            Divider()
            TextRow(text = "정보수정") {
                navController.navigate(Router.EDIT_INFO.routePath)
            }
            Divider()
            TextRow(text = "개인정보 이용약관") {
                //todo
            }
            Divider()
            TextRow(text = "회원 탈퇴") {
                viewModel.deleteUser()
            }
            Divider()
        }

    }
}

@Composable
fun TextRow(text: String, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Sizes.DEFAULT_PADDING)
            .noRippleClickable {
                onClick()
            }
    ) {
        Text(
            text,
            style = TextStyles.CONTENT_TEXT1_STYLE
        )
    }
}

@Preview
@Composable
fun MyPageScreenPreview(){
    MyPageScreen(navController = NavController(LocalContext.current))
}