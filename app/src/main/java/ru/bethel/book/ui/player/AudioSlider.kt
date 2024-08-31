package ru.bethel.book.ui.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import ru.bethel.book.view_model.MainViewModel
import ru.bethel.domain.model.SubTitle

@Composable
fun AudioBookSlider(
    viewModel: MainViewModel,
    chapters: List<SubTitle>,
    modifier: Modifier = Modifier,
    trackColor: Color = Color(0xFFF9F2FA),
    progressColor: Color = Color(0xFF9570FF),
    trackedChapterMarkerColor: Color = Color(0xFF9570FF),
    untrackedChapterMarkerColor: Color = Color(0xFFF9F2FA),
    chapterMarkerRadius: Float = 16f,
    trackHeight: Float = 16f,
    thumbRadius: Float = 16f,
    thumbColor: Color = Color(0xFF9570FF)
) {
    val currentProgress =
        viewModel.currentPosition.floatValue / viewModel.totalDuration.value.toFloat()

    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height((trackHeight * 2).dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val position = change.position.x
                    val width = size.width
                    val newProgress = (position / width).coerceIn(0f, 1f)
                    val newPosition = (newProgress * viewModel.totalDuration.intValue).toInt()
                    viewModel.seekToPosition(newPosition)
                }
                detectTapGestures { tapOffset ->
                    val width = size.width
                    val newProgress = (tapOffset.x / width).coerceIn(0f, 1f)
                    val newPosition = (newProgress * viewModel.totalDuration.intValue).toInt()
                    viewModel.seekToPosition(newPosition)
                }

            }) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Draw the untracked part of the track
            drawRoundRect(
                color = trackColor,
                size = androidx.compose.ui.geometry.Size(width = canvasWidth, height = trackHeight),
                topLeft = Offset(0f, (canvasHeight - trackHeight) / 2),
                cornerRadius = CornerRadius(trackHeight / 2, trackHeight / 2)
            )

            // Draw the tracked part of the track
            drawRoundRect(
                color = progressColor,
                size = androidx.compose.ui.geometry.Size(
                    width = canvasWidth * currentProgress, height = trackHeight
                ),
                topLeft = Offset(0f, (canvasHeight - trackHeight) / 2),
                cornerRadius = CornerRadius(trackHeight / 2, trackHeight / 2)
            )

            // Draw the chapter markers
            chapters.forEach { chapter ->
                val markerX = canvasWidth * chapter.startPosition
                val markerColor = if (chapter.startPosition <= currentProgress) {
                    trackedChapterMarkerColor
                } else {
                    untrackedChapterMarkerColor
                }
                drawCircle(
                    color = markerColor,
                    radius = chapterMarkerRadius,
                    center = Offset(markerX, canvasHeight / 2)
                )
            }

            val thumbX = canvasWidth * currentProgress
            drawCircle(
                color = thumbColor, radius = thumbRadius, center = Offset(thumbX, canvasHeight / 2)
            )
        }
    }
}


@Composable
fun AudioSlider(mainViewModel: MainViewModel) {
    val chapters = mainViewModel.currentChapter.value.subTitles
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AudioBookSlider(
            mainViewModel,
            chapters = chapters,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}