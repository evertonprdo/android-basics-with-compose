package com.evertonprdo.cornelioprocopio_pr.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.evertonprdo.cornelioprocopio_pr.R

@Composable
fun AppBanner(
    @DrawableRes image: Int,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(image),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxHeight()
            .width(dimensionResource(R.dimen.banner_height))
    )
}