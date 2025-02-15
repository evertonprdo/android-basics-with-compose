package com.evertonprdo.cornelioprocopio_pr.ui.screens.places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory
import com.evertonprdo.cornelioprocopio_pr.ui.AppUiState
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppListType
import com.evertonprdo.cornelioprocopio_pr.ui.utils.ScrollDirection

@Composable
fun PlacesScreen(
    listType: AppListType,
    uiState: AppUiState,
    onCategorySelected: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val locations = uiState.currentLocations

    when (listType) {
        AppListType.TOP_FILTER -> {
            Column(modifier = modifier.safeDrawingPadding()) {
                AppCategoryList(
                    categories = LocationCategory.entries,
                    scrollDirection = ScrollDirection.HORIZONTAL,
                    currentCategory = uiState.currentCategory,
                    onClickCategory = onCategorySelected,
                    modifier = Modifier.wrapContentWidth()
                )
                AppLocationGrid(
                    locations = locations,
                    modifier.weight(1f)
                )
            }
        }

        AppListType.SIDE_FILTER -> {
            Row(modifier = modifier.safeDrawingPadding()) {
                AppLocationGrid(
                    locations = locations,
                    modifier.weight(1f)
                )
                AppCategoryList(
                    categories = LocationCategory.entries,
                    scrollDirection = ScrollDirection.VERTICAL,
                    currentCategory = uiState.currentCategory,
                    onClickCategory = onCategorySelected,
                )
            }
        }
    }
}

