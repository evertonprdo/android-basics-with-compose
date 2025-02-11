package com.evertonprdo.thirtydaysofsermononthemount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
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
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            DailyCard()
        }
    }
}

@Composable
fun DailyCard(modifier: Modifier = Modifier) {
    var expanded: Boolean by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Image(
            painterResource(R.drawable.banner_01),
            null
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = stringResource(R.string.day_label, 1))
                Icon(
                    painter = painterResource(R.drawable.chevron_up),
                    contentDescription = stringResource(R.string.expand_button_content_description)
                )
            }

            Text(
                text = stringResource(R.string.day_01_title),
                style = MaterialTheme.typography.headlineSmall,
            )

            FoldableSection(expanded = expanded)
        }
    }
}

@Composable
fun FoldableSection(expanded: Boolean, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.day_01_content),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.day_01_reference),
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ThirtyDaysOfSermonOnTheMountTheme {
        MainAppLayout()
    }
}