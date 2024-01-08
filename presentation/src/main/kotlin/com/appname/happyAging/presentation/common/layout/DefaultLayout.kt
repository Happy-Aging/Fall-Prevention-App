package com.appname.happyAging.presentation.common.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.TextStyles.TITLE_LARGE2
import com.appname.happyAging.presentation.common.utils.noRippleClickable


/**
 * DefaultLayout is a composable function that takes a title and a content lambda as parameters.
 * @param title the title of the screen. If title is not null, it will be displayed in the topAppBar.
 * @param actions title이 null이 아닌 경우에 넣어야한다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultLayout(
    title: String? = null,
    backButtonAction : (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    body: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            title?.let {
                TopAppBar(
                    title = {
                        Text(text = title, style = TITLE_LARGE2)
                    },
                    navigationIcon = {
                        backButtonAction?.let {
                            Icon(
                                modifier = Modifier.noRippleClickable {
                                    backButtonAction()
                                },
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier
                        .background(
                            color = Color.Gray,
                        )
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                color = Colors.DIVIDER_GREY,
                                strokeWidth = 1.dp.toPx(),
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height)
                            )
                        },
                    actions = actions
                )
            }
        },
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(Colors.BACKGROUND_COLOR)
        ) {
            body()
        }
    }

}


@Preview
@Composable
fun DefaultLayoutPreview() {
    DefaultLayout(
        title = "Title",
        actions ={
            Text(text = "Actions")
        }
    ) {
        Text(text = "Content")
    }
}