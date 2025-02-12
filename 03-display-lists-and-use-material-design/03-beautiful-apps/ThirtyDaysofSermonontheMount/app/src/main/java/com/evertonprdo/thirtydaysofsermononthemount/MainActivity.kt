package com.evertonprdo.thirtydaysofsermononthemount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evertonprdo.thirtydaysofsermononthemount.ui.theme.ThirtyDaysOfSermonOnTheMountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysOfSermonOnTheMountTheme {
                MainAppLayout()
            }
        }
    }
}

@Composable
fun MainAppLayout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { SermonOnTheMountTopAppBar() },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            SermonOnTheMountScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SermonOnTheMountTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ThirtyDaysOfSermonOnTheMountTheme {
        MainAppLayout()
    }
}