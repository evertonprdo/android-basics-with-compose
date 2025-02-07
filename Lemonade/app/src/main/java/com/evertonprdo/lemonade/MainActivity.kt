package com.evertonprdo.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun LemonadeSteps(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var tapsRemaining by remember { mutableIntStateOf((2..4).random()) }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(top = 32.dp, bottom = 16.dp)
        )

        when (currentStep) {
            1 -> LemonadeStep(
                message = stringResource(R.string.lemon_tree_msg),
                painterImage = painterResource(R.drawable.lemon_tree),
                description = stringResource(R.string.lemon_tree_img_desc),
                modifier = Modifier.weight(1f)
            ) { currentStep++ }

            2 -> LemonadeStep(
                message = stringResource(R.string.lemon_tapping_msg),
                painterImage = painterResource(R.drawable.lemon_squeeze),
                description = stringResource(R.string.lemon_tapping_img_desc),
                modifier = Modifier.weight(1f)
            ) {
                if (tapsRemaining > 1) {
                    --tapsRemaining
                } else {
                    currentStep++
                }
            }

            3 -> LemonadeStep(
                message = stringResource(R.string.lemon_drink_msg),
                painterImage = painterResource(R.drawable.lemon_drink),
                description = stringResource(R.string.lemon_drink_img_desc),
                modifier = Modifier.weight(1f)
            ) { currentStep++ }

            else -> LemonadeStep(
                message = stringResource(R.string.empty_glass_msg),
                painterImage = painterResource(R.drawable.lemon_restart),
                description = stringResource(R.string.empty_glass_img_desc),
                modifier = Modifier.weight(1f)
            ) {
                currentStep = 1
                tapsRemaining = (2..4).random()
            }
        }
    }
}

@Composable
fun LemonadeStep(
    message: String,
    painterImage: Painter,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(32.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        ) {
            Image(
                painter = painterImage,
                contentDescription = description
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(text = message, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeSteps(modifier = Modifier.fillMaxSize())
    }
}