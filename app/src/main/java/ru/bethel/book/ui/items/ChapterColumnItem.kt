package ru.bethel.book.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkIconColor
import ru.bethel.book.ui.theme.darkPrimaryTextColor
import ru.bethel.book.ui.theme.darkSecondaryTextColor
import ru.bethel.book.ui.theme.lightIconColor
import ru.bethel.book.ui.theme.lightPrimaryTextColor
import ru.bethel.book.ui.theme.lightSecondaryTextColor

@Composable
fun ChapterColumnItem(isLightMode: MutableState<Boolean>, isNowPlaying: Boolean) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp, bottom = 9.dp, start = 8.dp)
    ) {
        Text(
            text = "1․ Մոգերի երկրպագությունը",
            color = if (isLightMode.value) lightPrimaryTextColor else darkPrimaryTextColor
        )
        Spacer(modifier = Modifier.width(16.dp))

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            if (isNowPlaying) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_now_playing),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightIconColor else darkIconColor
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = "0:00-3:29",
                color = if (isLightMode.value) lightSecondaryTextColor else darkSecondaryTextColor
            )
        }
    }
}

