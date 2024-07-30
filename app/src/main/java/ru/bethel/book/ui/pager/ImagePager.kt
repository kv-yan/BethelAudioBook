package ru.bethel.book.ui.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(images: List<Painter>) {
    val pagerState = rememberPagerState()
    var isFirstOrLastElement by remember {
        mutableStateOf(true)
    }
    HorizontalPager(
        count = images.size,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 38.dp),
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val isCurrentPage = pagerState.currentPage == page
        LaunchedEffect(isFirstOrLastElement, page) {
            isFirstOrLastElement = page == 0 || page == images.size - 1
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                val pageOffset = pagerState.currentPageOffset
                val offset = pageOffset.toDp() * 0.2f

                translationX = if (isCurrentPage) 0f else -offset.toPx()
                scaleX = if (isCurrentPage) 1f else 0.85f
                scaleY = if (isCurrentPage) 1f else 0.85f
            }) {
            Card(
                shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()/* // Adjust size to make space for adjacent images
                    .aspectRatio(1f)*/, // Maintain aspect ratio
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
