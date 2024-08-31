package ru.bethel.book.ui.player

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkMinusPlusIconColor
import ru.bethel.book.ui.theme.darkPrevIconColor
import ru.bethel.book.ui.theme.lightMinusPlusIconColor
import ru.bethel.book.ui.theme.lightPrevIconColor
import ru.bethel.book.ui.theme.playerNotPlayedText
import ru.bethel.book.ui.theme.playerPlayedText
import ru.bethel.book.view_model.MainViewModel

@Composable
fun ChapterPlayer(
    mainViewModel: MainViewModel,
    player: MediaPlayer?,
    isLightMode: MutableState<Boolean>,
    currentProgress: MutableState<Float>,
    isPlaying: MutableState<Boolean>,
    onPlayPauseClick: (Boolean) -> Unit,
    onNextChapterClick: () -> Unit,
    onPrevChapterClick: () -> Unit,
    onNext10SecClick: () -> Unit,
    onPrev10SecClick: () -> Unit
) {
    FavoriteSection(isLightMode = isLightMode)

    AudioSlider(
        mainViewModel = mainViewModel
    )

    BtnSection(isPlaying = isPlaying,
        isLightMode = isLightMode,
        currentProgress = currentProgress,
        currentPosition = player?.currentPosition ?: 0,
        totalDuration = player?.duration ?: 0,
        onPlayPauseClick = { onPlayPauseClick(isPlaying.value) },
        onNextChapterClick = { onNextChapterClick() },
        onPrevChapterClick = { onPrevChapterClick() },
        onNext10SecClick = { onNext10SecClick() },
        onPrev10SecClick = { onPrev10SecClick() })

}

@Composable
fun BtnSection(
    isPlaying: MutableState<Boolean>,
    isLightMode: MutableState<Boolean>,
    currentProgress: MutableState<Float>,
    currentPosition: Int,
    totalDuration: Int,
    onPlayPauseClick: (Boolean) -> Unit,
    onNextChapterClick: () -> Unit,
    onPrevChapterClick: () -> Unit,
    onNext10SecClick: () -> Unit,
    onPrev10SecClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatTime(currentProgress.value.div(1000).toInt()),
                color = playerPlayedText
            )

            IconButton(onClick = onPrevChapterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_prev),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
                )
            }

            IconButton(onClick = onPrev10SecClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_minus_10_sec),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightMinusPlusIconColor else darkMinusPlusIconColor
                )
            }

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(61.dp)
                    .width(61.dp)
                    .background(color = Color(0xFFFFFFFF), shape = CircleShape)
                    .clickable { onPlayPauseClick(isPlaying.value) }) {

                Icon(
                    tint = Color(0xFF212114),
                    painter = if (isPlaying.value) painterResource(id = R.drawable.ic_player_pause) else painterResource(
                        id = R.drawable.ic_player_play
                    ),
                    contentDescription = null
                )
            }

            IconButton(onClick = onNext10SecClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_plus_10_sec),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightMinusPlusIconColor else darkMinusPlusIconColor
                )
            }

            IconButton(onClick = onNextChapterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_next),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
                )
            }

            Text(text = formatTime(totalDuration / 1000), color = playerNotPlayedText)
        }

    }
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}


@Composable
fun FavoriteSection(isLightMode: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_player_bookmark),
                contentDescription = null,
                tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
            )

        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_favorite),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
                )

            }

        }
    }

}