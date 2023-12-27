package com.appname.happyAging.presentation.user.component

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.TextStyles

@Composable
fun KakaoButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier
            .width(315.dp)
            .height(43.dp)
            .background(
                color = Colors.KAKAO_YELLOW,
                shape = RoundedCornerShape(
                    size = 5.dp
                )
            ),
    ) {
        Text(
            text = text,
            style = TextStyles.CONTENT_TEXT3_STYLE,
        )
    }
}

@Composable
@Preview
fun KakaoButtonPreview() {
    KakaoButton(text = "카카오 로그인") {}
}