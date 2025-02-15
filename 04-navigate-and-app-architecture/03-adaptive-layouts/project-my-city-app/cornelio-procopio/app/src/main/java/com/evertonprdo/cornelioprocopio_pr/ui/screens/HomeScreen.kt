package com.evertonprdo.cornelioprocopio_pr.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppContentType

@Composable
fun HomeScreen(
    contentType: AppContentType,
    modifier: Modifier = Modifier
) {
    when (contentType) {
        AppContentType.SINGLE_COLUMN -> {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(R.drawable.city),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.banner_height))
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    HomeScreenContent()
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        AppContentType.SIDE_BY_SIDE -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(end = 16.dp, top = 16.dp)
                ) {
                    HomeScreenContent()
                    Spacer(modifier = Modifier.height(100.dp))
                }

                Image(
                    painter = painterResource(R.drawable.city),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f)
                )
            }
        }
    }
}

@Composable
private fun HomeScreenContent() {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.headlineLarge
    )
    Text(
        text = stringResource(R.string.city_subtitle),
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = stringResource(R.string.city_about),
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(widthDp = 800)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(AppContentType.SIDE_BY_SIDE)
    }
}