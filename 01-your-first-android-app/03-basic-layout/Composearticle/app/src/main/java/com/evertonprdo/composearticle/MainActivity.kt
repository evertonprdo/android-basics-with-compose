package com.evertonprdo.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Tutorial(
                        title = stringResource(R.string.jp_tutorial_title),
                        introduction = stringResource(R.string.jp_tutorial_introduction),
                        content = stringResource(R.string.jp_tutorial_content),
                    )
                }
            }
        }
    }
}

@Composable
fun Tutorial(
    title: String,
    introduction: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Banner()
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Paragraph(content = introduction)
        Paragraph(content = content)
    }
}

@Composable
fun Paragraph(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(16.dp),
    )
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialPreview() {
    ComposeArticleTheme {
        Tutorial(
            title = stringResource(R.string.jp_tutorial_title),
            introduction = stringResource(R.string.jp_tutorial_introduction),
            content = stringResource(R.string.jp_tutorial_content),
        )
    }
}