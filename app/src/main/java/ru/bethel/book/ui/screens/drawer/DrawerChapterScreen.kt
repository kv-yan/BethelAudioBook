package ru.bethel.book.ui.screens.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.bethel.book.ui.lazyColumn.BibleChaptersColumn
import ru.bethel.book.ui.lazyColumn.BibleChaptersGrid
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.domain.model.Chapter
import ru.bethel.domain.model.ui.BooksUiType
import ru.bethel.domain.model.ui.drawer.DrawerScreen

@Composable
fun DrawerChapterScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    isLightMode: MutableState<Boolean>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    booksUiType: MutableState<BooksUiType>
) {
    val onChapterItemClick: (Chapter) -> Unit = {
        mainViewModel.currentChapter.value = it
        navController.navigate(DrawerScreen.BOOK.route)
        scope.launch {
            drawerState.close()
        }
    }
    Column {
        val currentBook = mainViewModel.currentBook.value
        DrawerIconsHeader(isLightMode = isLightMode.value,
            bookUiType = booksUiType.value,
            onUiStateBtnClick = {
                when (booksUiType.value) {
                    BooksUiType.GRID -> {
                        booksUiType.value = BooksUiType.COLUMN
                    }

                    BooksUiType.COLUMN -> {
                        booksUiType.value = BooksUiType.GRID
                    }
                }
            },
            onCloseBtnClick = {
                scope.launch {
                    drawerState.close()
                }
            })


        BibleHeader(isLightMode = isLightMode, title = currentBook.name)
        when (booksUiType.value) {
            BooksUiType.COLUMN -> {
                BibleChaptersColumn(
                    chapters = currentBook.chapters, isLightMode = isLightMode
                ) {
                    onChapterItemClick(it)
                }
            }

            BooksUiType.GRID -> {
                BibleChaptersGrid(
                    chapters = currentBook.chapters, isLightMode = isLightMode
                ) {
                    onChapterItemClick(it)
                }

            }
        }
    }
}