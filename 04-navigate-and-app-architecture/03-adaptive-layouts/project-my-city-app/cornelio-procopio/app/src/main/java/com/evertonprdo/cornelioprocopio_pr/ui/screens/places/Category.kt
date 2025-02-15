package com.evertonprdo.cornelioprocopio_pr.ui.screens.places

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory
import com.evertonprdo.cornelioprocopio_pr.ui.utils.ScrollDirection
import kotlin.enums.EnumEntries

@Composable
fun AppCategoryList(
    categories: EnumEntries<LocationCategory>,
    currentCategory: LocationCategory,
    onClickCategory: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier,
    scrollDirection: ScrollDirection = ScrollDirection.VERTICAL
) {
    when (scrollDirection) {
        ScrollDirection.VERTICAL -> {
            LazyColumn(modifier = modifier) {
                items(categories) { category ->
                    AppCategoryItem(
                        title = stringResource(category.title),
                        icon = painterResource(category.icon),
                        onClick = { onClickCategory(category) },
                        focused = currentCategory == category,
                        fillWidth = true
                    )
                }
            }
        }

        ScrollDirection.HORIZONTAL -> {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                items(categories) { category ->
                    AppCategoryItem(
                        title = stringResource(category.title),
                        icon = painterResource(category.icon),
                        onClick = { onClickCategory(category) },
                        focused = currentCategory == category,
                    )
                }
            }
        }
    }
}

@Composable
private fun AppCategoryItem(
    title: String,
    icon: Painter,
    focused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fillWidth: Boolean = false,
) {
    val bgColor =
        if (focused) MaterialTheme.colorScheme.inversePrimary
        else MaterialTheme.colorScheme.primary

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        onClick = onClick,
        shape = RoundedCornerShape(100),
        modifier = if (fillWidth)
            modifier
                .widthIn(max = dimensionResource(R.dimen.drawer_width))
                .fillMaxWidth()
                .padding(end = 8.dp)
        else
            modifier
                .wrapContentWidth()
                .padding(horizontal = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Icon(icon, null)
            Text(title)
        }
    }
}
