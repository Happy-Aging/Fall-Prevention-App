package com.appname.happyAging.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles

@Composable
fun CustomTextEditField(
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String,
    label: String="",
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(Sizes.BUTTON_HEIGHT)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Colors.TEXT_FIELD_GREY,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .background(color = Colors.WHITE),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Sizes.INTERVAL1),
            textStyle = TextStyles.CONTENT_TEXT2_STYLE,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            value = value,
            onValueChange = onValueChange,
            keyboardActions = keyboardActions
        ){inner ->
            if(value.isEmpty()){
                Text(
                    text = label,
                    style = TextStyles.CONTENT_TEXT3_STYLE.copy(
                        color = Colors.GREY_TEXT
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = Sizes.INTERVAL1),
                )
            }
            inner()
        }
    }
}

@Composable
fun CustomTextFieldWithTitle(
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column() {
        Text(
            text = text,
            style = TextStyles.TITLE_MEDIUM2,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = Sizes.INTERVAL1),
        )
        CustomTextEditField(
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun PreviewCustomTextEditField() {
    CustomTextEditField(value = "", onValueChange = {})
}
