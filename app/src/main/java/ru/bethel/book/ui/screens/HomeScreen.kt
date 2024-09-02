package ru.bethel.book.ui.screens


import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.bethel.book.R
import ru.bethel.book.ui.items.ChapterColumnItem
import ru.bethel.book.ui.pager.ImagePager
import ru.bethel.book.ui.player.ChapterPlayer
import ru.bethel.book.ui.theme.dark_subTitlesBackgroundColor
import ru.bethel.book.ui.theme.light_subTitlesBackgroundColor
import ru.bethel.book.view_model.MainViewModel


/*
@Composable
fun HomeScreen(isLightMode: MutableState<Boolean>, mainViewModel: MainViewModel) {
    val currentChapter = mainViewModel.currentChapter.value
    val currentBook = mainViewModel.currentBook.value
    val images = mutableListOf<Painter>()

    val audioUrl = currentChapter.audioURL

    currentBook.chapters.forEach { chapter ->
        images.add(painterResource(id = R.drawable.ic_chapter))
    }

    val currentProgress = remember { mutableFloatStateOf(0.5f) }
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
                itemsIndexed(currentChapter.subTitles) { index, item ->
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
*/


private const val TAG = "url"

@Composable
fun HomeScreen(isLightMode: MutableState<Boolean>, mainViewModel: MainViewModel) {
    val currentChapter by remember { mainViewModel.currentChapter }

    val audioUrl = currentChapter.audioURL

    LaunchedEffect(currentChapter) {
        Log.e(TAG, "HomeScreen: audioUrl $audioUrl")
        mainViewModel.prepareMediaPlayer()
    }
    MainContent(mainViewModel = mainViewModel, isLightMode = isLightMode)

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainContent(mainViewModel: MainViewModel, isLightMode: MutableState<Boolean>) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val currentChapter = mainViewModel.currentChapter.value
    val currentBook = mainViewModel.currentBook.value
    val images = currentBook.chapters.map { painterResource(id = R.drawable.ic_chapter) }
    val chapterTitles = currentBook.chapters.map { "${currentBook.fullName} ${it.shortTitle}" }
    val pagerState = rememberPagerState(initialPage = currentBook.chapters.indexOf(currentChapter))


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        ImagePager(
            images = images,
            chapterTitles = chapterTitles,
            pagerState = pagerState,
            currentChapterIndex = currentBook.chapters.indexOf(currentChapter)
        ) { pageIndex ->
            if (pageIndex > currentBook.chapters.indexOf(currentChapter)) {
                mainViewModel.onNextChapter()
            } else if (pageIndex < currentBook.chapters.indexOf(currentChapter)) {
                mainViewModel.onPreviousChapter()
            }
        }
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
                item {
                    Text(
                        text = "${mainViewModel.currentBook.value.shortName} , ${currentChapter.shortTitle} ",
                        color = if (isLightMode.value) Color(0xFF1A1A1A) else Color(0xFFFAFAFA),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        fontFamily = FontFamily(Font(R.font.montserratarm_regular))

                    )
                }

                itemsIndexed(currentChapter.subTitles) { index, item ->
                    val currentPosition = mainViewModel.currentPosition.floatValue.toInt()

                    // Check if this is the last item
                    val isLastItem = index == currentChapter.subTitles.size - 1

                    // Determine if the current subtitle is playing
                    val isNowPlaying = if (isLastItem) {
                        currentPosition >= item.startTime // If it's the last item, check if the position is at or beyond the start time
                    } else {
                        val nextItem = currentChapter.subTitles.getOrNull(index + 1)
                        currentPosition in item.startTime until (nextItem?.startTime
                            ?: item.startTime)
                    }

                    ChapterColumnItem(
                        isLightMode, isNowPlaying = isNowPlaying, subTitle = item
                    ) {
                        mainViewModel.seekToPosition(item.startTime)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(45.dp))

        ChapterPlayer(mainViewModel,
            player = mainViewModel.mediaPlayer,
            isLightMode = isLightMode,
            currentProgress = mainViewModel.currentPosition,
            isPlaying = mainViewModel.isPlaying,
            onPlayPauseClick = {
                if (!mainViewModel.isPlaying.value) {
                    mainViewModel.play()
                } else {
                    mainViewModel.pause()
                }
            },
            onNextChapterClick = {
                mainViewModel.onNextChapter()
                scope.launch {
                    val index = currentBook.chapters.indexOf(currentChapter)
                    if (index < currentBook.chapters.size - 1) {
                        pagerState.animateScrollToPage(index + 1)
                    } else {
                        pagerState.animateScrollToPage(0)
                    }
                }
            },
            onPrevChapterClick = {
                scope.launch {
                    val index = currentBook.chapters.indexOf(currentChapter)
                    if (index == 0) {
                        val prevBook = mainViewModel.getPreviousBook()
                        pagerState.animateScrollToPage(prevBook.chapters.size - 1)
                    } else {
                        pagerState.animateScrollToPage(index - 1)
                    }
                }
                mainViewModel.onPreviousChapter()
            },
            onNext10SecClick = { mainViewModel.skipForward10Seconds() },
            onPrev10SecClick = { mainViewModel.skipBackward10Seconds() })

        Spacer(modifier = Modifier.height(58.dp))
    }

}