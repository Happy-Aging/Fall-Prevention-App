package com.appname.happyAging.presentation.common.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.appname.happyAging.presentation.common.constant.Colors

@Composable
fun RadioButtonRow(text: String, value: Boolean, id: Int, onClick: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
//        Checkbox(
//            checked = value,
//            onCheckedChange = onClick,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Colors.PRIMARY_ORANGE,
//            )
//        )
        RadioButton(
            modifier = Modifier.size(12.dp),
            selected = value,
            onClick = {
                onClick(id)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Colors.PRIMARY_ORANGE,
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        ClickableText(
            text = AnnotatedString(text), onClick = onClick, modifier = Modifier.fillMaxWidth()
        )
    }
}
