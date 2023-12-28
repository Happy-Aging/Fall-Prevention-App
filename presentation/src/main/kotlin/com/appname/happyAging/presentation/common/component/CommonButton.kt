package com.appname.happyAging.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles


/**
 * 공통 버튼
 * @param modifier Modifier 추가 변경 필요시 사용
 * @param text String 텍스트 string
 * @param textColor Color modifier로 배경색 변경이후 텍스트 색상 변경이 필요할 경우 사용
 * @param color Color 텍스트버튼 색상
 * @param shape RoundedCornerShape 버튼 shape
 * @param onClick () -> Unit
 */
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor:Color = Colors.WHITE,
    color : Color = Colors.PRIMARY_ORANGE,
    shape: RoundedCornerShape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .height(Sizes.BUTTON_HEIGHT)
            .background(
                color = color,
                shape = shape,
            ).then(modifier),
    ) {
        Text(
            text = text,
            style = TextStyles.TITLE_MEDIUM2.copy(
                color = textColor,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
fun CommonButtonPreview() {
    CommonButton(onClick = {}, text = "로그인")
}