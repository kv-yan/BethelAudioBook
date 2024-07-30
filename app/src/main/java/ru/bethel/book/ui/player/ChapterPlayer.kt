package ru.bethel.book.ui.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkMinusPlusIconColor
import ru.bethel.book.ui.theme.darkPrevIconColor
import ru.bethel.book.ui.theme.lightMinusPlusIconColor
import ru.bethel.book.ui.theme.lightPrevIconColor
import ru.bethel.book.ui.theme.playerNotPlayedText
import ru.bethel.book.ui.theme.playerPlayedText

@Composable
fun ChapterPlayer(isLightMode: MutableState<Boolean>, currentProgress: MutableState<Float>) {
    FavoriteSection(isLightMode = isLightMode)

    AudioSlider(isLightMode = isLightMode, currentProgress = currentProgress)

    BtnSection(isLightMode = isLightMode, currentProgress = currentProgress)

}

@Composable
fun BtnSection(isLightMode: MutableState<Boolean>, currentProgress: MutableState<Float>) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
    ) {
        Row(
//        horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "0:36", color = playerPlayedText)

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_prev),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
                )
            }

            /***  ***/
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_minus_10_sec),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightMinusPlusIconColor else darkMinusPlusIconColor
                )
            }


            Image(
                modifier = Modifier
                    .height(91.dp)
                    .width(91.dp),
                painter = painterResource(id = (if (isLightMode.value) R.drawable.ic_player_play_light else R.drawable.ic_player_play_dark)),
                contentDescription = null
            )


            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_plus_10_sec),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightMinusPlusIconColor else darkMinusPlusIconColor
                )
            }




            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_player_next),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightPrevIconColor else darkPrevIconColor
                )
            }


            Text(text = "2:36", color = playerNotPlayedText)
        }

    }


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