package ru.bethel.book.ui.lazyColumn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bethel.domain.model.BookHead

@Composable
fun BibleBooksGrid(
    books: List<BookHead>, isLightMode: MutableState<Boolean>, onBookItemClick: (BookHead) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 1000.dp),
//        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(books) { book ->
            Box(modifier = Modifier
                .clickable {
                    onBookItemClick(book)
                }
                .size(35.dp)
                .background(if (isLightMode.value) Color(0xFFF3F3F3) else Color(0xFF010101)),
                contentAlignment = Alignment.Center) {
                Text(
                    text = book.shortName,
                    color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
