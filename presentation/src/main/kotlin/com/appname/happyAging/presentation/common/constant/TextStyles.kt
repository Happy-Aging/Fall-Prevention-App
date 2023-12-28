package com.appname.happyAging.presentation.common.constant

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.appname.happyAging.presentation.R

object TextStyles {
    private val NOTO_SANS = FontFamily(
        Font(R.font.notosans_kr_bold, FontWeight.W700),
        Font(R.font.notosans_kr_regular, FontWeight.W500)
    )
    private val BOLD = FontWeight.W700
    private val REGULAR = FontWeight.W500

    val TITLE_LARGE1 = TextStyle(
        fontSize = 24.sp,
        fontFamily = NOTO_SANS,
        fontWeight = BOLD,
        color = Colors.BLACK
    )
    val TITLE_LARGE2 = TextStyle(
        fontSize = 21.sp,
        fontFamily = NOTO_SANS,
        fontWeight = BOLD,
        color = Colors.BLACK
    )
    val TITLE_MEDIUM1 = TextStyle(
        fontSize = 18.sp,
        fontFamily = NOTO_SANS,
        fontWeight = BOLD,
        color = Colors.BLACK
    )
    val TITLE_MEDIUM2 = TextStyle(
        fontSize = 16.sp,
        fontFamily = NOTO_SANS,
        fontWeight = BOLD,
        color = Colors.BLACK
    )

    val CONTENT_TEXT1_STYLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        fontFamily = NOTO_SANS,
    )

    val CONTENT_TEXT2_STYLE = TextStyle(
        fontSize = 16.sp,
        fontWeight = REGULAR,
        fontFamily = NOTO_SANS,
    )
    val CONTENT_TEXT3_STYLE = TextStyle(
        fontSize = 14.sp,
        fontWeight = REGULAR,
        fontFamily = NOTO_SANS,
    )

    val CONTENT_SMALL0_STYLE = TextStyle(
        fontSize = 13.sp,
        fontWeight = REGULAR,
        fontFamily = NOTO_SANS,
    )

    val CONTENT_SMALL1_STYLE = TextStyle(
        fontSize = 12.sp,
        fontWeight = REGULAR,
        fontFamily = NOTO_SANS,
    )

    val CONTENT_SMALL2_STYLE = TextStyle(
        fontSize = 10.sp,
        fontWeight = REGULAR,
        fontFamily = NOTO_SANS,
    )
}