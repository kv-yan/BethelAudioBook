package ru.bethel.book.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.bethel.book.R
import ru.bethel.book.ui.actionBar.HomeActionBar
import ru.bethel.book.ui.screens.HomeScreen

@Composable
fun DrawerScreenContent(
    isLightMode: MutableState<Boolean>, state: DrawerState, scope: CoroutineScope
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(modifier = Modifier.fillMaxSize() ,
            painter = painterResource(
                id = if (isLightMode.value) {
                    R.drawable.light_background
                } else {
                    R.drawable.dark_background
                }
            ), contentDescription = null, contentScale = ContentScale.FillBounds
        )
        Column {
            HomeActionBar(isLightMode.value, onMenuClick = {
                scope.launch {
                    state.open()
                }
            }) {
                isLightMode.value = !isLightMode.value
            }

            HomeScreen(isLightMode = isLightMode)

        }


    }
}