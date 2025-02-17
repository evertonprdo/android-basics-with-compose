package com.evertonprdo.cornelioprocopio_pr.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppSideBanner
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppTopBanner
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppContentType

@Composable
fun HomeScreen(
    contentType: AppContentType,
    modifier: Modifier = Modifier,
) {
    when (contentType) {
        AppContentType.SINGLE_COLUMN -> {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                AppTopBanner(
                    R.drawable.city,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
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
                        .padding(end = 16.dp)
                        .safeDrawingPadding()
                ) {
                    HomeScreenContent()
                    Spacer(modifier = Modifier.height(100.dp))
                }

                AppSideBanner(R.drawable.city)
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
        HomeScreen(AppContentType.SINGLE_COLUMN)
    }
}