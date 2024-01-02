package com.appname.happyAging.presentation.senior.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.utils.noRippleClickable


object SeniorItemFactory

@Composable
fun SeniorItemFactory.fromModel(person: SeniorModel) {
    SeniorItem(
        number = person.id.toInt(),
        name = person.name,
        age = person.age,
        address = person.address,
        relation = person.profile,
    )
}

@Composable
fun SeniorItem(number: Int,name: String, age: Int, address: String, relation: String) {
    var isClicked by rememberSaveable { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = Sizes.INTERVAL_MEDIUM,
                    horizontal = Sizes.INTERVAL_LARGE4
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD96666)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$number",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    style = TextStyles.TITLE_MEDIUM1,
                )
                Text(
                    text = "${age}세 / $address / $relation",
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Colors.GREY_TEXT,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .width(52.dp)
                    .height(30.dp)
                    .background(
                        color = Color(0xFFF2F2F2),
                    ).noRippleClickable {
                        isClicked = !isClicked
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
        }
        if(isClicked) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(
                        vertical = Sizes.INTERVAL_LARGE4,
                    )
            ){
                SeniorDetailMenu(title = "새로", content = "낙상 위험도 측정하기", onClick = {  })
                SeniorDetailMenu(title = "이전", content = "낙상 위험도 보기", onClick = {  })
                SeniorDetailMenu(title = "집 안", content = "사진찍기", onClick = {  })
            }
        }
    }
}

@Composable
fun SeniorDetailMenu(title: String, content:String,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(
                horizontal = Sizes.DEFAULT_PADDING,
                vertical = Sizes.INTERVAL2
            )
            .background(Colors.WHITE)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = Sizes.INTERVAL_LARGE4,
                vertical = Sizes.INTERVAL_MEDIUM2
            ),
    ) {
        Text(
            text = title,
            style = TextStyles.TITLE_MEDIUM2.copy(
                color = Colors.GREY_TEXT
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = content,
            style = TextStyles.TITLE_MEDIUM2,
        )

    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SeniorItemPreview() {
    SeniorItem(number = 1, name = "김춘자", age = 65, address = "성동구", relation = "가족")
}