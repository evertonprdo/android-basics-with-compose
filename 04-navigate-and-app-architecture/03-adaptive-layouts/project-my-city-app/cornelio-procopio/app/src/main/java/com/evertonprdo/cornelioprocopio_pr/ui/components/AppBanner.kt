package com.evertonprdo.cornelioprocopio_pr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.evertonprdo.cornelioprocopio_pr.R

@Composable
fun AppTopBanner(
    painter: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(painter),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.banner_height))
    )
}

@Composable
fun AppSideBanner(
    painter: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(painter),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(.5f)
    )
}