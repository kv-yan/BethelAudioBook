package ru.bethel.book.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.bethel.book.R

@Preview
@Composable
private fun TestOnly() {
    var mode by remember {
        mutableStateOf(true)
    }
    val lightGradient = painterResource(id = R.drawable.light_background)
    val darkGradient = painterResource(id = R.drawable.dark_background)

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            mode = !mode
        }) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            painter = if (mode) lightGradient else darkGradient,
            contentDescription = null
        )

    }
}