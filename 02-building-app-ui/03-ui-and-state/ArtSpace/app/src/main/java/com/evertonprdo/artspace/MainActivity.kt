package com.evertonprdo.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artsArray = arrayOf(
            Art(
                titleResId = R.string.art_01_title,
                artistResId = R.string.art_01_artist,
                yearResI = R.string.art_01_year,
                imageResId = R.drawable.art_01
            ),
            Art(
                titleResId = R.string.art_02_title,
                artistResId = R.string.art_02_artist,
                yearResI = R.string.art_02_year,
                imageResId = R.drawable.art_02
            ),
            Art(
                titleResId = R.string.art_03_title,
                artistResId = R.string.art_03_artist,
                yearResI = R.string.art_03_year,
                imageResId = R.drawable.art_03
            ),
            Art(
                titleResId = R.string.art_04_title,
                artistResId = R.string.art_04_artist,
                yearResI = R.string.art_04_year,
                imageResId = R.drawable.art_04
            ),
            Art(
                titleResId = R.string.art_05_title,
                artistResId = R.string.art_05_artist,
                yearResI = R.string.art_05_year,
                imageResId = R.drawable.art_05
            )
        )

        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        artsArray = artsArray,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Art(
    @StringRes val titleResId: Int,
    @StringRes val artistResId: Int,
    @StringRes val yearResI: Int,
    @DrawableRes val imageResId: Int,
)

@Composable
fun ArtSpaceLayout(artsArray: Array<Art>, modifier: Modifier = Modifier) {
    var currentArt by remember { mutableIntStateOf(0) }
    val lastIndex = artsArray.size - 1

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ArtCard(
            titleResId = artsArray[currentArt].titleResId,
            artistResId = artsArray[currentArt].artistResId,
            yearResId = artsArray[currentArt].yearResI,
            imageResId = artsArray[currentArt].imageResId,
            modifier = Modifier.weight(1f)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Button(
                onClick = { currentArt-- },
                modifier = Modifier.widthIn(min = 160.dp),
                enabled = currentArt > 0
            ) {
                Text(
                    text = stringResource(R.string.prev_btn_content),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Button(
                onClick = { currentArt++ },
                enabled = currentArt < lastIndex,
                modifier = Modifier.widthIn(min = 160.dp)
            ) {
                Text(
                    text = stringResource(R.string.next_btn_content),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun ArtCard(
    @StringRes titleResId: Int,
    @StringRes artistResId: Int,
    @StringRes yearResId: Int,
    @DrawableRes imageResId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .aspectRatio(0.63f)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .shadow(8.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(32.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(titleResId),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row {
                Text(
                    text = stringResource(artistResId),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 16.sp,
                )
                Text(
                    text = " (${stringResource(yearResId)})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    val artsArray = arrayOf(
        Art(
            titleResId = R.string.art_01_title,
            artistResId = R.string.art_01_artist,
            yearResI = R.string.art_01_year,
            imageResId = R.drawable.art_01
        ),
        Art(
            titleResId = R.string.art_02_title,
            artistResId = R.string.art_02_artist,
            yearResI = R.string.art_02_year,
            imageResId = R.drawable.art_02
        ),
        Art(
            titleResId = R.string.art_03_title,
            artistResId = R.string.art_03_artist,
            yearResI = R.string.art_03_year,
            imageResId = R.drawable.art_03
        ),
        Art(
            titleResId = R.string.art_04_title,
            artistResId = R.string.art_04_artist,
            yearResI = R.string.art_04_year,
            imageResId = R.drawable.art_04
        ),
        Art(
            titleResId = R.string.art_05_title,
            artistResId = R.string.art_05_artist,
            yearResI = R.string.art_05_year,
            imageResId = R.drawable.art_05
        )
    )

    ArtSpaceTheme {
        ArtSpaceLayout(artsArray)
    }
}