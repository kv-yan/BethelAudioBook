package ru.bethel.book.ui.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


data class Chapter(
    val startTime: Float, // normalized start time (0f to 1f)
)

@Composable
private fun AudioBookSlider(
    isLightMode: Boolean = false,
    progress: Float = 0.5f,
    chapters: List<Chapter>,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    trackColor: Color = Color(0xFFF9F2FA),
    progressColor: Color = Color(0xFF9570FF),
    trackedChapterMarkerColor: Color = Color(0xFF9570FF),
    untrackedChapterMarkerColor: Color = Color(0xFFF9F2FA),
    chapterMarkerRadius: Float = 16f,
    trackHeight: Float = 16f,
    thumbRadius: Float = 16f,
    thumbColor: Color = if (isLightMode) Color(0xFF9570FF) else Color(0xFF9570FF)
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height((trackHeight * 2).dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val position = change.position.x
                    val width = size.width
                    val newProgress = (position / width).coerceIn(0f, 1f)
                    onValueChange(newProgress)
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
                    width = canvasWidth * progress, height = trackHeight
                ),
                topLeft = Offset(0f, (canvasHeight - trackHeight) / 2),
                cornerRadius = CornerRadius(trackHeight / 2, trackHeight / 2)
            )

            // Draw the chapter markers
            chapters.forEach { chapter ->
                val markerX = canvasWidth * chapter.startTime
                val markerColor = if (chapter.startTime <= progress) {
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

            // Draw the thumb as a circle
            val thumbX = canvasWidth * progress
            drawCircle(
                color = thumbColor, radius = thumbRadius, center = Offset(thumbX, canvasHeight / 2)
            )
        }
    }
}


@Composable
fun AudioSlider(isLightMode: MutableState<Boolean>, currentProgress: MutableState<Float>) {
    val chapters = listOf(
        Chapter(0.1f), Chapter(0.5f), Chapter(0.7f), Chapter(0.9f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AudioBookSlider(
            isLightMode = isLightMode.value,
            progress = currentProgress.value,
            chapters = chapters,
            onValueChange = { newProgress ->
                currentProgress.value = newProgress
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}