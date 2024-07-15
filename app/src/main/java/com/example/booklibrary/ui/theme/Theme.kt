package com.example.booklibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF834EFF),
    primaryContainer = Color(0xFFB692EE),
    secondary = Color(0xFF5b00b8),
    secondaryContainer = Color(0xFFe2d5f5),
    background = Color(0xFFB692EE),
    surface = Color(0xFFe2d5f5),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    error = Color.Red,
    onError = Color.White,
)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF834EFF),
    primaryContainer = Color(0xFFB692EE),
    secondary = Color(0xFF5b00b8),
    secondaryContainer = Color(0xFFe2d5f5),
//    background = Color(0xFFB692EE),
    background = Color.White,
    surface = Color(0xFFe2d5f5),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFFF5F5F5),
    error = Color.Red,
    onError = Color.White,
)

@Composable
fun BookLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}