package com.appname.happyAging.presentation.common.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 패스워드 입력시 마스킹 처리. 맨 마지막 문자는 마스킹 처리하지 않음.
 * @param mask 마스킹할 문자
 */
class CustomPassWordVisualTransformation(val mask: Char = '\u2022') : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if(text.text.isEmpty()) return TransformedText(text, OffsetMapping.Identity)
        val prefix = mask.toString().repeat(text.text.length - 1)
        val postfix = text.text.takeLast(1)
        return TransformedText(
            AnnotatedString(prefix + postfix),
            OffsetMapping.Identity
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PasswordVisualTransformation) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}
