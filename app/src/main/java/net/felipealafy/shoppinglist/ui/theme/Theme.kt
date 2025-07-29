package net.felipealafy.shoppinglist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = Coral,
    secondary = Ocher,
    tertiary = Marine,
    background = LightGray,
    surface = LightGray,
    onBackground = LightGray
)

@Composable
fun ShoppingListTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}