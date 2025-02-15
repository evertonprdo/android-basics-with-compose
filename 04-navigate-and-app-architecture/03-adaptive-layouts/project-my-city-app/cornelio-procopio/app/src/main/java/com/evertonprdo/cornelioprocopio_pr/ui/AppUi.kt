package com.evertonprdo.cornelioprocopio_pr.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppDynamicNavMenu
import com.evertonprdo.cornelioprocopio_pr.ui.screens.HomeScreen
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppContentType
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppNavigationType

enum class AppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    List(title = R.string.list_screen),
    Details(title = R.string.details_screen)
}

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Start.name
    )

    val navigationType: AppNavigationType
    val contentType: AppContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_COLUMN
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = AppNavigationType.NAVIGATION_RAIL
            contentType = AppContentType.SINGLE_COLUMN
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = AppNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = AppContentType.SIDE_BY_SIDE
        }

        else -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_COLUMN
        }
    }

    Scaffold { innerPadding ->
        AppDynamicNavMenu(
            navigationType = navigationType,
            currentTab = currentScreen,
            onTabPressed = { navController.navigate(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = AppScreen.Start.name,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = AppScreen.Start.name) {
                    HomeScreen(contentType)
                }

                composable(route = AppScreen.List.name) {
                    Text("ListScreen")
                }

                composable(route = AppScreen.Details.name) {
                    Text("DetailsScreen")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppTheme {
        CityApp(WindowWidthSizeClass.Compact)
    }
}