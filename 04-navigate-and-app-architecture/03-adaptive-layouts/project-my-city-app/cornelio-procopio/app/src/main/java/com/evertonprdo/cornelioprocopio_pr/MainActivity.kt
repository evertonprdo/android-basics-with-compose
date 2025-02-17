package com.evertonprdo.cornelioprocopio_pr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.evertonprdo.cornelioprocopio_pr.ui.CityApp
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val windowSize = calculateWindowSizeClass(this)
                CityApp(windowSize.widthSizeClass)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCompactPreview() {
    AppTheme {
        CityApp(WindowWidthSizeClass.Compact)
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun AppMediumPreview() {
    AppTheme {
        CityApp(WindowWidthSizeClass.Medium)
    }
}