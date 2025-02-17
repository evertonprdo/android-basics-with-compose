package com.evertonprdo.cornelioprocopio_pr.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.data.Location
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory
import com.evertonprdo.cornelioprocopio_pr.data.local.LocalLocationProvider
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppSideBanner
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppTopBanner
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppContentType

@Composable
fun DescriptionScreen(
    location: Location?,
    contentType: AppContentType,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (location == null) {
        Text("Please select a place")
        return
    }

    when (contentType) {
        AppContentType.SINGLE_COLUMN -> {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState())
            ) {
                location.apply {
                    Box(modifier = Modifier.padding(bottom = 16.dp)) {
                        AppTopBanner(banner)
                        UpButton(
                            onClick = onBackPressed,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 16.dp)
                        )
                    }

                    DescriptionContent(
                        title = title,
                        address = address,
                        about = about,
                        phone = phone,
                        category = category,
                        website = website,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }

        AppContentType.SIDE_BY_SIDE -> {
            Row(
                modifier = modifier
            ) {
                location.apply {
                    DescriptionContent(
                        title = title,
                        address = address,
                        about = about,
                        phone = phone,
                        category = category,
                        website = website,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .safeDrawingPadding()
                            .padding(end = 16.dp)
                    )

                    Box {
                        AppSideBanner(banner)
                        UpButton(
                            onClick = onBackPressed,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = onClick,
        modifier = modifier.safeDrawingPadding()
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            null
        )
    }
}

@Composable
fun DescriptionContent(
    @StringRes title: Int,
    @StringRes address: Int,
    @StringRes about: Int,
    category: LocationCategory,
    @StringRes phone: Int?,
    @StringRes website: Int?,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineSmall
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.details_sect_title),
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(painter = painterResource(category.icon), null)
                Text(text = stringResource(category.title))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Place, null)
                Text(stringResource(address))
            }

            if (phone != null)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Phone, null)
                    Text(stringResource(phone))
                }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.about_sect_title),
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = stringResource(about),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }

        if (website != null) {
            val uriHandler = LocalUriHandler.current
            val uri = stringResource(website)

            Button(onClick = { uriHandler.openUri(uri) }) {
                Text(stringResource(R.string.website_button))
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Preview
@Composable
fun DescriptionScreenPreview() {
    AppTheme {
        DescriptionScreen(
            location = LocalLocationProvider.allLocations[1],
            contentType = AppContentType.SINGLE_COLUMN,
            onBackPressed = {}
        )
    }
}
