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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import java.time.LocalDate


object SeniorItemFactory

@Composable
fun SeniorItemFactory.fromModel(
    model: SeniorModel,
    onEditClicked: () -> Unit = {},
    onSurveyHistoryClick: () -> Unit = {},
    onSurveyClick: () -> Unit = {},
    onTakeImageClicked : () -> Unit = {},
) {
    SeniorItem(
        fallRiskRank = model.fallRiskRank,
        name = model.name,
        age = model.birth?.let { LocalDate.now().year - it.year },
        address = model.address,
        relation = model.relation,
        onEditClicked = onEditClicked,
        onSurveyHistoryClick = onSurveyHistoryClick,
        onSurveyClick = onSurveyClick,
        onTakeImageClick = onTakeImageClicked,
    )
}

enum class FallRiskRank(val rankNum : Int?, val rankContnet : String, val rankColor : Color){
    FIRST(1, "낙상 사고에 매우 위험", Color(0xFFD96666)),
    SECOND(2, "낙상 사고에 보통", Color(0xFF5BC15F)),
    THIRD(3, "낙상 사고에 비교적 안전", Color(0xFF5BC15F)),
    NONE(null, "낙상 위험도 측정 안 함", Color(0xFFF2F2F2)),
}

@Composable
fun SeniorItem(
    fallRiskRank: Int?,
    name: String,
    age: Int?,
    address: String,
    relation: RelationWithSenior,
    onEditClicked: () -> Unit = {},
    onSurveyHistoryClick: () -> Unit = {},
    onSurveyClick: () -> Unit = {},
    onTakeImageClick : () -> Unit = {},
) {
    var isClicked by rememberSaveable { mutableStateOf(false) }
    val riskType = FallRiskRank.values().first{ it.rankNum == fallRiskRank }
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .padding(Sizes.DEFAULT_PADDING)
        ) {
            Text(
                text = name,
                style = TextStyles.TITLE_MEDIUM1,
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL4))
            Text(
                text = "$address / ${relation.korean} ${age?.let { "/ $it 세" } ?: ""}",
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
                val boxWidth = if(riskType.rankNum == null){
                    152.dp
                }else{
                    69.dp
                }
                Box(
                    modifier = Modifier
                        .size(boxWidth, 30.dp)
                        .background(
                            color = riskType.rankColor,
                            shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    fallRiskRank?.let {
                        Text(
                            text = "${it}등급",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    } ?: Text(
                        text = "낙상 위험도 측정 안 함",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFA7A7A7)
                    )
                }
                riskType.rankNum?.let {
                    Spacer(modifier = Modifier.width(Sizes.INTERVAL2))
                    Text(
                        text = riskType.rankContnet,
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
                    onClick = onSurveyClick)
                SeniorDetailMenu(title = "이전", detailContent = "낙상 위험도 보기",
                    iconId = R.drawable.folder,
                    onClick = onSurveyHistoryClick)
                SeniorDetailMenu(title = "집 안", detailContent = "사진찍기",
                    iconId = R.drawable.picture,
                    onClick = onTakeImageClick)
            }
        }
    }
//    var isClicked by rememberSaveable { mutableStateOf(false) }
//    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    vertical = Sizes.INTERVAL_MEDIUM,
//                    horizontal = Sizes.INTERVAL_LARGE4
//                ),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFD96666)),
//                contentAlignment = Alignment.Center
//            ) {
//                fallRiskRank?.let {
//                    Text(
//                        text = "$it",
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        color = Color.White
//                    )
//                } ?: Text(
//                    text = "등급\n없음",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 12.sp,
//                    lineHeight = 14.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                )
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(
//                    text = name,
//                    style = TextStyles.TITLE_MEDIUM1,
//                )
//                Text(
//                    text = "$address / ${relation.korean} ${age?.let { "/ $it 세" } ?: ""}",
//                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
//                        color = Colors.GREY_TEXT,
//                    ),
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            Box(
//                modifier = Modifier
//                    .width(52.dp)
//                    .height(30.dp)
//                    .background(
//                        color = Color(0xFFF2F2F2),
//                    )
//                    .noRippleClickable {
//                        if (isClicked) onEditClicked()
//                        isClicked = !isClicked
//                    },
//                contentAlignment = Alignment.Center,
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = if(isClicked) "수정" else "보기",
//                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
//                        color = Colors.GREY_TEXT,
//                    ),
//                )
//            }
//        }
//        if (isClicked) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(0xFFF5F5F5))
//                    .padding(
//                        vertical = Sizes.INTERVAL_LARGE4,
//                    )
//            ) {
//                SeniorDetailMenu(title = "새로", detailContent = "낙상 위험도 측정하기",
//                    iconId = R.drawable.list,
//                    onClick = { })
//                SeniorDetailMenu(title = "이전", detailContent = "낙상 위험도 보기",
//                    iconId = R.drawable.folder,
//                    onClick = { })
//                SeniorDetailMenu(title = "집 안", detailContent = "사진찍기",
//                    iconId = R.drawable.picture,
//                    onClick = { })
//            }
//        }
//    }
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

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SeniorItemPreview2() {
    SeniorItemFactory.fromModel(model = SeniorModel.fixture(fallRiskRank = 1))
}
