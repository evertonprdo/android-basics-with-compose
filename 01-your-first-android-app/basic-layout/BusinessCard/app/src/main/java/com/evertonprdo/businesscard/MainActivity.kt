package com.evertonprdo.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.weight(.8f))
        ResumeCard(modifier = Modifier.weight(1f))
        ContactSection(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ResumeCard(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.evertonprdo),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .border(3.dp, MaterialTheme.colorScheme.outline, CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.full_name),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.title),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxHeight()
    ) {
        ContactItem(
            content = stringResource(R.string.phone_number),
            icon = Icons.Rounded.Call,
            contentDescription = "Phone",
        )

        ContactItem(
            content = stringResource(R.string.github),
            icon = Icons.Rounded.Share,
            contentDescription = "Github",
        )

        ContactItem(
            content = stringResource(R.string.email),
            icon = Icons.Rounded.Email,
            contentDescription = "Email",
        )
    }
}

@Composable
fun ContactItem(
    content: String,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(bottom = 16.dp)) {
        Icon(
            icon,
            contentDescription,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(end = 24.dp)
        )
        Text(
            text = content,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}