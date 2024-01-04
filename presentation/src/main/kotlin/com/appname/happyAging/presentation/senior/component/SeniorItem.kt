package com.appname.happyAging.presentation.senior.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.model.senior.SeniorModel
import com.appname.happyAging.presentation.R
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.utils.noRippleClickable


object SeniorItemFactory

@Composable
fun SeniorItemFactory.fromModel(model: SeniorModel, onEditClicked: () -> Unit = {}) {
    SeniorItem(
        fallRiskRank = model.fallRiskRank,
        name = model.name,
        age = model.age,
        address = model.address,
        relation = model.relation,
        onEditClicked = onEditClicked
    )
}

@Composable
fun SeniorItem(
    fallRiskRank: Int?,
    name: String,
    age: Int?,
    address: String,
    relation: RelationWithSenior,
    onEditClicked: () -> Unit,
) {
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
                fallRiskRank?.let {
                    Text(
                        text = "$it",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                } ?: Text(
                    text = "등급\n없음",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
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
                    text = "$address / ${relation.korean} ${age?.let { "/ $it 세" } ?: ""}",
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
                    )
                    .noRippleClickable {
                        if (isClicked) onEditClicked()
                        isClicked = !isClicked
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = if(isClicked) "수정" else "보기",
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Colors.GREY_TEXT,
                    ),
                )
            }
        }
        if (isClicked) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(
                        vertical = Sizes.INTERVAL_LARGE4,
                    )
            ) {
                SeniorDetailMenu(title = "새로", detailContent = "낙상 위험도 측정하기",
                    iconId = R.drawable.list,
                    onClick = { })
                SeniorDetailMenu(title = "이전", detailContent = "낙상 위험도 보기",
                    iconId = R.drawable.folder,
                    onClick = { })
                SeniorDetailMenu(title = "집 안", detailContent = "사진찍기",
                    iconId = R.drawable.picture,
                    onClick = { })
            }
        }
    }
}

@Composable
fun SeniorDetailMenu(
    title: String, detailContent: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit) {
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
            text = detailContent,
            style = TextStyles.TITLE_MEDIUM2,
        )
        Image(
            modifier = Modifier
                .align(Alignment.End)
                .size(38.dp),
            painter = painterResource(id = iconId),
            contentDescription = null
        )

    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SeniorItemPreview() {
    SeniorItemFactory.fromModel(model = SeniorModel.fixture(fallRiskRank = null))
}