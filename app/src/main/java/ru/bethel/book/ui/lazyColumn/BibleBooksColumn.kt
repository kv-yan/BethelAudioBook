package ru.bethel.book.ui.lazyColumn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bethel.book.R
import ru.bethel.domain.model.BookHead

@Composable
fun BibleBooksColumn(books: List<BookHead>, isLightMode: MutableState<Boolean> , onBookItemClick: (BookHead) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 16.dp)
            .heightIn(0.dp, 1000.dp)
    ) {
        items(books) { book ->
            Box(
                modifier = Modifier
                    .padding(top = 16.dp).
                        clickable {
                            onBookItemClick(book)
                        }
                    .fillMaxWidth()
            ) {
                Text(
                    text = book.fullName,
                    color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontFamily = FontFamily(Font(R.font.montserratarm_regular))
                )
            }
        }
    }
}
