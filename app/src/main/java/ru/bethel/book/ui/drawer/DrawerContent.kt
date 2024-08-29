package ru.bethel.book.ui.drawer

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import ru.bethel.book.ui.screens.drawer.DrawerBookScreen
import ru.bethel.book.ui.screens.drawer.DrawerChapterScreen
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.domain.model.ui.BooksUiType
import ru.bethel.domain.model.ui.drawer.DrawerScreen


@Composable
fun DrawerContent(
    mainViewModel: MainViewModel,
    isLightMode: MutableState<Boolean>,
    state: DrawerState,
    scope: CoroutineScope
) {
    val chaptersNavController = rememberNavController()

    val bookUiType = remember {
        mutableStateOf(BooksUiType.GRID)
    }


    Surface(
        color = if (isLightMode.value) {
            Color(0xFFF5F9FB)
        } else {
            Color(0xFF010101)
        }
    ) {
        NavHost(navController = chaptersNavController, startDestination = DrawerScreen.BOOK.route) {

            composable(route = DrawerScreen.BOOK.route, exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(targetAlpha = 0.3f)
            }) {
                DrawerBookScreen(
                    isLightMode = isLightMode,
                    state = state,
                    scope = scope,
                    mainViewModel = mainViewModel,
                    navController = chaptersNavController,
                    bookUiType = bookUiType
                )
            }

            composable(
                route = DrawerScreen.CHAPTER.route,
                enterTransition = {
                    slideInHorizontally(initialOffsetX = { it }) + fadeIn(initialAlpha = 0.3f)
                },
                exitTransition = {
                    slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(targetAlpha = 0.3f)
                },
            ) {
                DrawerChapterScreen(
                    mainViewModel = mainViewModel,
                    navController = chaptersNavController,
                    isLightMode = isLightMode,
                    drawerState = state,
                    scope = scope,
                    booksUiType = bookUiType
                )
            }
        }

    }

}