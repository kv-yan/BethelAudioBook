package ru.bethel.book.ui.pager

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(
    images: List<Painter>,
    pagerState: PagerState,
    chapterTitles: List<String>,
    onPageChanged: (Int) -> Unit
) {
    var isFirstOrLastElement by remember { mutableStateOf(true) }

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
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

        val scale by animateFloatAsState(
            targetValue = if (isCurrentPage) 1f else 0.85f,
            animationSpec = tween(durationMillis = 150), label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset = pagerState.currentPageOffset
                    val offset = pageOffset.toDp() * 0.2f

                    translationX = if (isCurrentPage) 0f else -offset.toPx()
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent, disabledContainerColor = Color.Transparent
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Add the watermark (chapter title)
            Text(
                text = chapterTitles[page],
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp) // Padding around the text
                    .align(Alignment.TopStart) // Position at the top-left corner
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    ) // Semi-transparent background
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ) // Additional padding for the background
            )
        }
    }
}
