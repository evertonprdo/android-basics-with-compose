package com.evertonprdo.cornelioprocopio_pr.ui.screens.places

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            Column(
                modifier = modifier
                    .width(IntrinsicSize.Min)
                    .verticalScroll(rememberScrollState())
                    .padding(end = 16.dp)
            ) {
                for (category in categories) {
                    AppCategoryItem(
                        title = stringResource(category.title),
                        icon = painterResource(category.icon),
                        onClick = { onClickCategory(category) },
                        focused = currentCategory == category,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        ScrollDirection.HORIZONTAL -> {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
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
) {
    val bgColor =
        if (focused) MaterialTheme.colorScheme.inversePrimary
        else MaterialTheme.colorScheme.primary

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        onClick = onClick,
        shape = RoundedCornerShape(100),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .width(IntrinsicSize.Max)
        ) {
            Icon(icon, null)
            Text(title)
        }
    }
}
