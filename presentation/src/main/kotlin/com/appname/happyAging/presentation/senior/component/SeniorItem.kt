package com.appname.happyAging.presentation.senior.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.TextStyles


@Deprecated("임시 객체")
val people = listOf(
    Person("김춘자", 65, "성동구", "가족"),
    Person("허말순", 81, "강서구", "가족"),
    Person("양미령", 54, "강서구", "가족")
)

object SeniorItemFactory

@Composable
fun SeniorItemFactory.fromModel(person: Person) {
    SeniorItem(
        number = people.indexOf(person) + 1,
        name = person.name,
        age = person.age,
        address = person.address,
        relation = person.relation
    )
}

@Composable
fun SeniorItem(number: Int,name: String, age: Int, address: String, relation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*TODO*/ }
            .padding(16.dp),
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
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "보기")
        }
    }
}

@Deprecated("임시 클래스")
data class Person(
    val name: String,
    val age: Int,
    val address: String,
    val relation: String
)

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SeniorItemPreview() {
    SeniorItem(number = 1, name = "김춘자", age = 65, address = "성동구", relation = "가족")
}