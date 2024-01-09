package com.appname.happyAging.presentation.survey.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout

@Composable
fun SurveyGuideScreen(
    onBackButtonClick: () -> Unit = {},
    onStartSurveyClick : () -> Unit = {}
) {
    DefaultLayout(
        title = "",
        backButtonAction = onBackButtonClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Sizes.DEFAULT_PADDING)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "낙상 위험도 측정을\n시작하겠습니다",
                style = TextStyles.TITLE_LARGE1,
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextCol(title = "응답소요시간", content = "약 15분")
            Spacer(modifier = Modifier.height(32.dp))
            TextCol(title = "준비물", content = "스마트 기기")
            Spacer(modifier = Modifier.height(32.dp))
            TextCol(title = "측정 방법", content = "질문에 해당하는 답변을 선택하고 ‘다음 문제’ 버튼을 누릅니다")
            Spacer(modifier = Modifier.height(32.dp))
            CommonButton(
                text = "측정 시작하기",
                color = Colors.DARK_BLACK,
                onClick = onStartSurveyClick,
            )
        }
    }
}

@Composable
internal fun TextCol(
    title: String,
    content: String,
){
    Row{
        Text(
            text = "•",
            style = TextStyles.TITLE_MEDIUM2,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = title,
                style = TextStyles.TITLE_MEDIUM2,
            )
            Text(
                text = content,
                style = TextStyles.CONTENT_TEXT2_STYLE,
            )
        }
    }

}

@Preview
@Composable
fun SurveyGuideScreenPreview() {
    SurveyGuideScreen()
}