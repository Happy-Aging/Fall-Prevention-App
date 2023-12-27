package com.appname.happyAging.presentation.common.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.TextStyles.TITLE_LARGE2
import com.appname.happyAging.presentation.user.view.LoginScreen


/**
 * DefaultLayout is a composable function that takes a title and a content lambda as parameters.
 * @param title the title of the screen. If title is not null, it will be displayed in the topAppBar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultLayout(
    title: String? = null,
    body: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            title?.let {
                TopAppBar(
                    title = {
                        Text(text = title, style = TITLE_LARGE2)
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier.background(
                        color = Color.Gray,
                    ).drawWithContent {
                        drawContent()
                        drawLine(
                            color = Colors.DIVIDER_GREY,
                            strokeWidth = 1.dp.toPx(),
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height)
                        )
                    }
                )
            }
        },
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize().background(Colors.BACKGROUND_COLOR)
        ) {
            body()
        }
    }

}


@Preview
@Composable
fun DefaultLayoutPreview() {
    DefaultLayout(title = "Title") {
        Text(text = "Content")
    }
}