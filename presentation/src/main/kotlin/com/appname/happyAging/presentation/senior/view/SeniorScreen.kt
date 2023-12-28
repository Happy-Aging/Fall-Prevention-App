package com.appname.happyAging.presentation.senior.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.senior.component.SeniorItemFactory
import com.appname.happyAging.presentation.senior.component.fromModel
import com.appname.happyAging.presentation.senior.component.people

@Composable
fun SeniorScreen(
    navController: NavController,
) {
    val scrollState = rememberScrollState()
    DefaultLayout(
        title = BottomNavRouter.SENIOR_LIST.korean,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            people.forEach { person ->
                SeniorItemFactory.fromModel(person)
                Divider()
            }
            Spacer(modifier =Modifier.height(Sizes.INTERVAL_LARGE4))
            CommonButton(
                text = "시니어 추가하기",
                color = Colors.WHITE,
                shape = RoundedCornerShape(
                    size =  Sizes.BUTTON_ROUND,
                ),
                textColor = Colors.BLACK,
                modifier = Modifier
                    .padding(
                        horizontal = Sizes.DEFAULT_PADDING,
                    ).drawBehind {
                        drawRoundRect(
                            color = Colors.BLACK,
                            cornerRadius = CornerRadius(5f,5f),
                            style = Stroke(
                                width = 1f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                            )
                        )
                    }
            ) {
                navController.navigate(Router.SENIOR_CREATE.routePath)
            }
        }

    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SeniorScreenPreview() {
    SeniorScreen(navController = NavController(LocalContext.current))
}