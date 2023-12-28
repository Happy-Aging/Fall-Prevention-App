package com.appname.happyAging.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
){
    TextButton(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(Sizes.BUTTON_HEIGHT)
            .background(color = Colors.PRIMARY_ORANGE)
    ) {
        Text(
            text = text,
            style = TextStyles.TITLE_MEDIUM2.copy(
                color = Colors.WHITE,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
fun CommonButtonPreview(){
    CommonButton(onClick = {}, text = "로그인")
}