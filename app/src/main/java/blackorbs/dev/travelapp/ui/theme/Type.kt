package blackorbs.dev.travelapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import blackorbs.dev.travelapp.R

val satoshi = FontFamily(
    Font(R.font.satoshi_regular, FontWeight.Normal),
    Font(R.font.satoshi_bold, FontWeight.Bold),
    Font(R.font.satoshi_light, FontWeight.Light),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_black, FontWeight.Black)
)

val Typography = Typography(
    headlineSmall = TextStyle(
        fontFamily = satoshi,
    ),
    bodyLarge = TextStyle(
        fontFamily = satoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = satoshi
    ),
    bodySmall = TextStyle(
        fontFamily = satoshi,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontFamily = satoshi,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = satoshi,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelSmall = TextStyle(
        fontFamily = satoshi,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)