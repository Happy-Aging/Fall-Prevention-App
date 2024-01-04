package com.appname.happyAging.presentation.my.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.user.UpdateUserParams
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.common.state.UiState
import com.appname.happyAging.presentation.my.viewmodel.UserViewModel

@Composable
fun EditInfoScreen(
    navController: NavController,
    viewModel : UserViewModel = hiltViewModel(),
){
    val state = viewModel.user.collectAsState()
    DefaultLayout(
        title = Router.EDIT_INFO.korean,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Sizes.DEFAULT_PADDING)
        ) {
            var name by rememberSaveable { mutableStateOf("") }
            var phoneNumber by rememberSaveable { mutableStateOf("") }
            var userType by rememberSaveable { mutableStateOf(UserType.INDIVIDUAL) }
            var password : String? by rememberSaveable { mutableStateOf(null) }
            when(state.value){
                is UiState.Error -> TODO()
                is UiState.Loading -> TODO()
                is UiState.Success ->{
                    LaunchedEffect(true){
                        val user = (state.value as UiState.Success).data
                        name = user.name
                        phoneNumber = user.phoneNumber
                        userType = user.userType
                        password = if (user.userType == UserType.INDIVIDUAL) "" else null
                    }
                }
            }
            Text(
                text = "일반",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL0),
            )
            CustomTextEditField(label = "이름을 입력하세요", value = name , onValueChange = {name = it} )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
            CustomTextEditField(label = "전화번호를 입력하세요", value = phoneNumber , onValueChange = {phoneNumber = it} ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
            Text(
                text = "가입자 유형",
                style = TextStyles.TITLE_MEDIUM1
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL4))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(12.dp),
                    selected = userType == UserType.INDIVIDUAL,
                    onClick = {
                          userType = UserType.INDIVIDUAL
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Colors.PRIMARY_ORANGE,
                    )
                )
                Spacer(modifier =Modifier.width(Sizes.INTERVAL1))
                Text(text ="개인", style = TextStyles.CONTENT_TEXT1_STYLE)
                Spacer(modifier =Modifier.width(20.dp))
                RadioButton(
                    modifier = Modifier.size(12.dp),
                    selected = userType == UserType.MANAGER,
                    onClick = {
                        userType = UserType.MANAGER
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Colors.PRIMARY_ORANGE,
                    )
                )
                Spacer(modifier =Modifier.width(Sizes.INTERVAL1))
                Text(text ="돌봄매니저", style = TextStyles.CONTENT_TEXT1_STYLE)
            }
            Spacer(modifier =Modifier.height(Sizes.INTERVAL_LARGE3))
            CommonButton(text ="저장하기") {
                val updateUserParams = UpdateUserParams(
                    name = name,
                    phoneNumber = phoneNumber,
                    userType = userType,
                    password = password,
                )
                viewModel.updateUser(updateUserParams)
                navController.popBackStack()
            }
        }
    }
}

@Composable
@Preview
fun EditInfoScreenPreview(){
    EditInfoScreen(navController = NavController(LocalContext.current))
}