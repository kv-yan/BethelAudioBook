package ru.bethel.book.ui.drawer

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.oldTestament
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

    val selectedBook = remember { mutableStateOf(oldTestament.first()) }

    val animationDuration = 250


    Surface(
        color = if (isLightMode.value) {
            Color(0xFFF5F9FB)
        } else {
            Color(0xFF010101)
        }
    ) {
        NavHost(navController = chaptersNavController, startDestination = DrawerScreen.BOOK.route) {

            // BOOK screen with slide to left when navigating away
            composable(
                route = DrawerScreen.BOOK.route,
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = animationDuration)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = animationDuration)
                    )
                }
            ) {
                DrawerBookScreen(
                    isLightMode = isLightMode,
                    state = state,
                    scope = scope,
                    bookUiType = bookUiType,
                    onBookItemClick = {
                        selectedBook.value = it
                        chaptersNavController.navigate(DrawerScreen.CHAPTER.route)
                    }
                )
            }

            // CHAPTER screen with slide to right when navigating forward
            composable(
                route = DrawerScreen.CHAPTER.route,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = animationDuration) // Adjust duration here
                    ) + fadeIn(
                        initialAlpha = 0.3f,
                        animationSpec = tween(durationMillis = animationDuration) // Adjust duration here
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(durationMillis = animationDuration) // Adjust duration here
                    )
                }
            ) {
                DrawerChapterScreen(
                    mainViewModel = mainViewModel,
                    currentBook = selectedBook,
                    navController = chaptersNavController,
                    isLightMode = isLightMode,
                    drawerState = state,
                    scope = scope,
                    booksUiType = bookUiType
                ) {
                    chaptersNavController.popBackStack()
                }
            }
        }

    }

}