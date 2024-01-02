package com.appname.happyAging.presentation.my.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.Router

@Composable
fun EditInfoScreen(navController: NavController){
    DefaultLayout(
        title = Router.EDIT_INFO.korean,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Sizes.DEFAULT_PADDING)
        ) {
            Text(
                text = "가입자 유형",
                style = TextStyles.TITLE_MEDIUM1
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.size(12.dp),
                    selected = true,
                    onClick = {
                        //todo
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
                    selected = true,
                    onClick = {
                        //todo
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
                //todo
            }
        }
    }
}

@Composable
@Preview
fun EditInfoScreenPreview(){
    EditInfoScreen(navController = NavController(LocalContext.current))
}