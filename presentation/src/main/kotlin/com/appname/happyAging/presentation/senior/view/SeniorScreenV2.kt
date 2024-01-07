package com.appname.happyAging.presentation.senior.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.R
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.utils.noRippleClickable

@Composable
fun SeniorScreenV2() {
    DefaultLayout(
        title = "시니어 관리",
        actions = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.happy_aging_icon),
                    contentDescription = null
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(Sizes.DEFAULT_PADDING)
        ) {
            SeniorSummary()
            Spacer(modifier = Modifier.height(30.dp))
            TakeSurvey()
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Manage Information",
                style = TextStyles.TITLE_MEDIUM1,
            )
            Spacer(modifier = Modifier.height(10.dp))
            ManageSurveyItem("설문조사 결과")
            Spacer(modifier = Modifier.height(10.dp))
            ManageSurveyItem("집 내부")

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Personal Solution",
                style = TextStyles.TITLE_MEDIUM1,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                PersonalSolutionItem("건강")
                Spacer(modifier = Modifier.weight(1f))
                PersonalSolutionItem("교육")
                Spacer(modifier = Modifier.weight(1f))
                PersonalSolutionItem("물품")
            }

        }
    }
}

@Composable
fun SeniorSummary(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .background(
                color = Color(0xFFf8f8f8),
                shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
            )
            .padding(Sizes.DEFAULT_PADDING)
    ) {
        Text(
            text = "김복자",
            style = TextStyles.TITLE_MEDIUM1,
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL4))
        Text(
            text = "대구 북구 / 가족 / 16세",
            style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                color = Colors.GREY_TEXT,
            ),
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(69.dp, 30.dp)
                    .background(
                        color = Color(0xFF93FF13),
                        shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2등급",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(Sizes.INTERVAL2))
            Text(
                text = "낙상 사고에 취약",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT,
                ),
            )

            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .width(52.dp)
                    .height(30.dp)
                    .background(
                        color = Color(0xFFF2F2F2),
                    )
                    .noRippleClickable {

                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "수정",
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Colors.GREY_TEXT,
                    ),
                )
            }
        }
    }
}

@Composable
fun ManageSurveyItem(
    title: String,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            modifier = Modifier
                .size(20.dp)
                .padding(2.dp),
            painter = painterResource(id = R.drawable.list),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = TextStyles.CONTENT_TEXT3_STYLE,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(52.dp)
                .height(30.dp)
                .background(
                    color = Color(0xFF93FF13),
                    shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
                )
                .noRippleClickable {

                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "보기",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT,
                ),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun TakeSurvey(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(162.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Sizes.DEFAULT_PADDING),
        ) {
            Icon(
                Icons.Default.Search, contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "설문조사를 통해 낙상 위험도를 확인해보세요",
                    style = TextStyles.CONTENT_TEXT3_STYLE,
                )
                Text(
                    text = "보고서와 함께 시니어의 건강 상태를 확인할 수 있습니다",
                    style = TextStyles.CONTENT_SMALL1_STYLE.copy(
                        color = Colors.GREY_TEXT,
                    ),
                )

            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Box(
            modifier = Modifier
                .size(223.dp, 40.dp)
                .background(
                    color = Color(0xFF93FF13),
                    shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
                )
                .noRippleClickable {

                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "설문조사 시작하기",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT,
                ),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}


@Composable
fun PersonalSolutionItem(title: String){
    Column(
        modifier = Modifier
            .size(104.dp, 124.dp)
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(48.dp)
                .padding(2.dp),
            painter = painterResource(id = R.drawable.picture ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            style = TextStyles.CONTENT_TEXT3_STYLE,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SeniorScreenV2Preview() {
    SeniorScreenV2()
}