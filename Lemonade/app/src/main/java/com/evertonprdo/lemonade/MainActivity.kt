package com.evertonprdo.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evertonprdo.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { LemonadeApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeSteps(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var tapsRemaining by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            when (currentStep) {
                1 -> LemonadeStep(
                    messageId = R.string.lemon_tree_msg,
                    painterImageId = R.drawable.lemon_tree,
                    descriptionId = R.string.lemon_tree_img_desc,
                ) {
                    currentStep = 2
                    tapsRemaining = (2..4).random()
                }

                2 -> LemonadeStep(
                    messageId = R.string.lemon_tapping_msg,
                    painterImageId = R.drawable.lemon_squeeze,
                    descriptionId = R.string.lemon_tapping_img_desc,
                ) {
                    --tapsRemaining
                    if (tapsRemaining == 0) {
                        currentStep = 3
                    }
                }

                3 -> LemonadeStep(
                    messageId = R.string.lemon_drink_msg,
                    painterImageId = R.drawable.lemon_drink,
                    descriptionId = R.string.lemon_drink_img_desc,
                ) { currentStep = 4 }

                else -> LemonadeStep(
                    messageId = R.string.empty_glass_msg,
                    painterImageId = R.drawable.lemon_restart,
                    descriptionId = R.string.empty_glass_img_desc,
                ) { currentStep = 1 }
            }
        }
    }
}

@Composable
fun LemonadeStep(
    messageId: Int,
    painterImageId: Int,
    descriptionId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        val borderColor = Color(red = 105, green = 205, blue = 216)
        val bgColor = Color(red = 130, green = 225, blue = 230)
        val shape = RoundedCornerShape(32.dp)

        Image(
            painter = painterResource(painterImageId),
            contentDescription = stringResource(descriptionId),
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .clip(shape)
                .clickable { onClick() }
                .border(BorderStroke(2.dp, borderColor), shape)
                .background(bgColor, shape)
                .padding(8.dp)

        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(messageId),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeSteps(modifier = Modifier.fillMaxSize())
    }
}