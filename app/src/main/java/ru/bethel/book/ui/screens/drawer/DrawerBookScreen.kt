package ru.bethel.book.ui.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.bethel.book.ui.drawer.DrawerIconsHeader
import ru.bethel.book.ui.lazyColumn.BibleBooksColumn
import ru.bethel.book.ui.lazyColumn.BibleBooksGrid
import ru.bethel.domain.model.BookHead
import ru.bethel.domain.model.newTestament
import ru.bethel.domain.model.oldTestament
import ru.bethel.domain.model.ui.BooksUiType

@Composable
fun DrawerBookScreen(
    isLightMode: MutableState<Boolean>,
    state: DrawerState,
    scope: CoroutineScope,
    bookUiType: MutableState<BooksUiType>,
    onBookItemClick: (BookHead) -> Unit
) {
    val verticalScrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(verticalScrollState),
    ) {
        DrawerIconsHeader(isLightMode = isLightMode.value,
            bookUiType = bookUiType.value,
            onUiStateBtnClick = {
                when (bookUiType.value) {
                    BooksUiType.GRID -> {
                        bookUiType.value = BooksUiType.COLUMN
                    }

                    BooksUiType.COLUMN -> {
                        bookUiType.value = BooksUiType.GRID
                    }
                }
            },
            onCloseBtnClick = {
                scope.launch {
                    state.close()
                }
            })
        Spacer(modifier = Modifier.height(8.dp))

        BibleHeader(isLightMode = isLightMode, title = "Հին կտակարան")

        when (bookUiType.value) {
            BooksUiType.GRID -> {
                BibleBooksGrid(books = oldTestament, isLightMode = isLightMode) {
                    onBookItemClick(it)
                }
            }

            else -> {
                BibleBooksColumn(books = newTestament, isLightMode = isLightMode) {
                    onBookItemClick(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        BibleHeader(isLightMode = isLightMode, title = "Նոր կտակարան")
        when (bookUiType.value) {
            BooksUiType.GRID -> {
                BibleBooksGrid(books = newTestament, isLightMode = isLightMode) {
                    onBookItemClick(it)
                }
            }

            else -> {
                BibleBooksColumn(books = newTestament, isLightMode = isLightMode) {
                    onBookItemClick(it)
                }
            }
        }
    }
}


@Composable
fun BibleHeader(isLightMode: MutableState<Boolean>, title: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(if (isLightMode.value) Color(0xFFF3F3F3) else Color(0xFF121323)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp, start = 16.dp)

        )

    }
}


