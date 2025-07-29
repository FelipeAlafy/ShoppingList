package net.felipealafy.shoppinglist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import net.felipealafy.shoppinglist.R

val kronaFontFamily = FontFamily(Font(R.font.krona))
val numansFontFamily = FontFamily(Font(R.font.numans))

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = kronaFontFamily,
        fontWeight = FontWeight.Bold,
        color = Coral,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = numansFontFamily,
        fontWeight = FontWeight.Normal,
        color = Marine,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = numansFontFamily,
        fontWeight = FontWeight.Normal,
        color = Marine,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = numansFontFamily,
        fontWeight = FontWeight.Normal,
        color = Marine,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 20.sp
    )
)