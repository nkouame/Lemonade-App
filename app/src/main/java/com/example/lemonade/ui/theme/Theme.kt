package com.example.lemonade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun LemonadeTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
){
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Yellow,
            background = Color.Black,
            surface = Color.Black,
        )
    } else {
        lightColorScheme(
            primary = Yellow,
            background = Color.White,
            surface = Color.White,
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

val Yellow = Color(0xFFF4CA16)