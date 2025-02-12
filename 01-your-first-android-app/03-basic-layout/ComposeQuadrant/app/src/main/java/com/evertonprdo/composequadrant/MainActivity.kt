package com.evertonprdo.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeQuadrantTheme {
                ComposableScreen(
                    modifier = Modifier.fillMaxSize(),
                    content = arrayOf(
                        Triple(
                            stringResource(R.string.txt_composable_title),
                            stringResource(R.string.txt_composable_sentence),
                            colorResource(R.color.purple_top_left),
                        ),
                        Triple(
                            stringResource(R.string.img_composable_title),
                            stringResource(R.string.img_composable_sentence),
                            colorResource(R.color.purple_top_right),
                        ),
                        Triple(
                            stringResource(R.string.row_composable_title),
                            stringResource(R.string.row_composable_sentence),
                            colorResource(R.color.purple_bottom_left),
                        ),
                        Triple(
                            stringResource(R.string.col_composable_title),
                            stringResource(R.string.col_composable_sentence),
                            colorResource(R.color.purple_bottom_right),
                        ),
                    )
                )
            }
        }
    }
}

@Composable
fun ComposableScreen(
    content: Array<Triple<String, String, Color>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.weight(1f)) {
            ComposeCard(
                title = content[0].first,
                sentence = content[0].second,
                backgroundColor = content[0].third,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                title = content[1].first,
                sentence = content[1].second,
                backgroundColor = content[1].third,
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ComposeCard(
                title = content[2].first,
                sentence = content[2].second,
                backgroundColor = content[2].third,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                title = content[3].first,
                sentence = content[3].second,
                backgroundColor = content[3].third,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeCard(
    title: String,
    sentence: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = sentence,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        ComposableScreen(
            modifier = Modifier.fillMaxSize(),
            content = arrayOf(
                Triple(
                    stringResource(R.string.txt_composable_title),
                    stringResource(R.string.txt_composable_sentence),
                    colorResource(R.color.purple_top_left),
                ),
                Triple(
                    stringResource(R.string.img_composable_title),
                    stringResource(R.string.img_composable_sentence),
                    colorResource(R.color.purple_top_right),
                ),
                Triple(
                    stringResource(R.string.row_composable_title),
                    stringResource(R.string.row_composable_sentence),
                    colorResource(R.color.purple_bottom_left),
                ),
                Triple(
                    stringResource(R.string.col_composable_title),
                    stringResource(R.string.col_composable_sentence),
                    colorResource(R.color.purple_bottom_right),
                ),
            )
        )
    }
}