package ru.bethel.book.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkIconColor
import ru.bethel.book.ui.theme.darkPrimaryTextColor
import ru.bethel.book.ui.theme.darkSecondaryTextColor
import ru.bethel.book.ui.theme.lightIconColor
import ru.bethel.book.ui.theme.lightPrimaryTextColor
import ru.bethel.book.ui.theme.lightSecondaryTextColor
import ru.bethel.domain.model.SubTitle

@Composable
fun ChapterColumnItem(
    isLightMode: MutableState<Boolean>,
    isNowPlaying: Boolean,
    subTitle: SubTitle,
    onItemCLick: () -> Unit
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemCLick() }
        .padding(top = 9.dp, bottom = 9.dp, start = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = subTitle.title,
            fontSize = 13.sp,
            color = if (isLightMode.value) lightPrimaryTextColor else darkPrimaryTextColor,
            fontFamily = FontFamily(Font(R.font.montserratarm_regular))
        )
        Spacer(modifier = Modifier.width(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isNowPlaying) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_now_playing),
                    contentDescription = null,
                    tint = if (isLightMode.value) lightIconColor else darkIconColor,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = subTitle.startEndRange,
                color = if (isLightMode.value) lightSecondaryTextColor else darkSecondaryTextColor,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.montserratarm_light))
            )
        }
    }
}

