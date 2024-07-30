package ru.bethel.book.ui.actionBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.bethel.book.R
import ru.bethel.book.ui.theme.darkIconColor
import ru.bethel.book.ui.theme.lightIconColor

@Composable
fun HomeActionBar(isLightMode: Boolean, onMenuClick: () -> Unit, onThemeClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(), color = Color.Transparent) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = if (isLightMode) lightIconColor else darkIconColor
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Column {
                Image(
                    painter = painterResource(if (isLightMode) R.drawable.bethel_logo else R.drawable.bethel_logo_dark),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(height = 48.dp, width = 100.dp)
                        .padding(bottom = 16.dp)

                )
                Spacer(modifier = Modifier.height(12.dp))

            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onThemeClick) {
                Icon(
                    painterResource(id = R.drawable.ic_dark),
                    contentDescription = "Dark Mode",
                    tint = if (isLightMode) lightIconColor else darkIconColor
                )
            }
        }

    }
}