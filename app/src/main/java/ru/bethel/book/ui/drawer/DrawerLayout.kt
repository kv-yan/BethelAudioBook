package ru.bethel.book.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.bethel.book.view_model.MainViewModel


@Composable
fun DrawerLayout(
    mainViewModel: MainViewModel,
    isLightMode: MutableState<Boolean>,
    isShowingDrawer: MutableState<Boolean>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    isShowingDrawer.value = drawerState.isOpen
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight()
                    .background(
                        if (isLightMode.value) Color(0xFFF5F9FB) else Color(0xFF010101),
                        shape = RoundedCornerShape(
                            topStart = 0.dp, topEnd = 25.dp, bottomEnd = 25.dp, bottomStart = 0.dp
                        )
                    )
            ) {
                DrawerContent(
                    mainViewModel = mainViewModel,
                    isLightMode = isLightMode,
                    scope = scope,
                    state = drawerState
                )
            }
        },
    ) {
        DrawerScreenContent(
            isLightMode = isLightMode,
            scope = scope,
            state = drawerState,
            mainViewModel = mainViewModel
        )
    }
}


