package com.appname.happyAging.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextEditField(
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String, onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(43.dp)
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
        )
    }
}

@Composable
@Preview
fun PreviewCustomTextEditField() {
    CustomTextEditField(value = "dasdsa", onValueChange = {})
}