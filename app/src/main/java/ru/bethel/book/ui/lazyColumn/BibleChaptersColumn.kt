package ru.bethel.book.ui.lazyColumn

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
import ru.bethel.book.ui.theme.darkSecondaryTextColor
import ru.bethel.book.ui.theme.lightSecondaryTextColor
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
            ) {
                Text(
                    text = chapter.fullTitle,
                    color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontFamily = FontFamily(Font(R.font.montserratarm_regular))
                )
            }
        }
    }
}
