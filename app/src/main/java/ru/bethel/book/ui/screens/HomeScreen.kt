package ru.bethel.book.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.items.ChapterColumnItem
import ru.bethel.book.ui.pager.ImagePager
import ru.bethel.book.ui.player.ChapterPlayer
import ru.bethel.book.ui.theme.dark_subTitlesBackgroundColor
import ru.bethel.book.ui.theme.light_subTitlesBackgroundColor


@Composable
fun HomeScreen(isLightMode: MutableState<Boolean>) {
    val images = listOf(
        painterResource(id = R.drawable.ic_chapter),
        painterResource(id = R.drawable.ic_chapter),
        painterResource(id = R.drawable.ic_chapter),
        painterResource(id = R.drawable.ic_chapter),
    )
    val currentProgress = remember { mutableStateOf(0.5f) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        ImagePager(images = images)

        Spacer(modifier = Modifier.height(48.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            color = if (isLightMode.value) light_subTitlesBackgroundColor else dark_subTitlesBackgroundColor,
            shape = RoundedCornerShape(25.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 10.dp, max = 1000.dp)
                    .padding(start = 12.dp, end = 22.dp, top = 19.dp, bottom = 21.dp)
            ) {
                itemsIndexed(images) { index, item ->
                    ChapterColumnItem(
                        isLightMode, isNowPlaying = index == 1
                    )
                }
            }

        }


        ChapterPlayer(isLightMode = isLightMode, currentProgress = currentProgress)

        Spacer(modifier = Modifier.height(58.dp))
    }
}

