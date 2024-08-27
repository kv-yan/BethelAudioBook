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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bethel.domain.model.Chapter

@Composable
fun BibleChaptersColumn(
    chapters: List<Chapter>,
    isLightMode: MutableState<Boolean>,
    onChapterItemClick: (Chapter) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 16.dp)
            .heightIn(0.dp, 1000.dp)
    ) {
        items(chapters) { chapter ->
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { onChapterItemClick(chapter) }
                    .fillMaxWidth()
                    .background(if (isLightMode.value) Color(0xFFF3F3F3) else Color(0xFF010101)),
            ) {
                Text(
                    text = chapter.title,
                    color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
                    fontSize = 12.sp,
                    modifier = Modifier
                )
            }
        }
    }
}
