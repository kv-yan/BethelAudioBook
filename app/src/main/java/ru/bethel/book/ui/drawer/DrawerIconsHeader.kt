package ru.bethel.book.ui.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkIconColor
import ru.bethel.book.ui.theme.lightIconColor
import ru.bethel.domain.model.ui.BooksUiType

@Composable
fun DrawerIconsHeader(
    isShowBackBtn: Boolean = false,
    isLightMode: Boolean,
    bookUiType: BooksUiType,
    onBackBtnClick: () -> Unit = {},
    onUiStateBtnClick: () -> Unit,
    onCloseBtnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        ,verticalAlignment = Alignment.CenterVertically
    ) {
        if (isShowBackBtn) {
            IconButton(onClick = { onBackBtnClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Dark Mode",
                    tint = if (isLightMode) lightIconColor else darkIconColor
                )
            }

        }
        IconButton(onClick = { onUiStateBtnClick() }) {
            Icon(
                painterResource(if (bookUiType == BooksUiType.GRID) R.drawable.ic_chapter_type_title else R.drawable.ic_chapter_type_number),
                contentDescription = "Dark Mode",
                tint = if (isLightMode) lightIconColor else darkIconColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onCloseBtnClick() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Menu",
                    tint = if (isLightMode) lightIconColor else darkIconColor
                )
            }

        }
    }
}
