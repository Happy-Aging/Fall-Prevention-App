package com.appname.happyAging.presentation.senior.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.R
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles

@Composable
fun EmptySeniorItem(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE3))
        Image(
            modifier = Modifier.size(85.dp),
            painter = painterResource(id = R.drawable.empty_box),
            contentDescription = "시니어가 없습니다"
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL1))
        Text(
            text ="등록된 시니어가 없습니다",
            style = TextStyles.TITLE_MEDIUM2,
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL4))
        Text(
            text ="아래 버튼을 눌러서 시니어를 추가하세요",
            style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                color = Colors.GREY_TEXT,
            ),
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmptySeniorItemPreview(){
    EmptySeniorItem()
}
