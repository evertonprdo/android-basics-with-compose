package com.evertonprdo.cornelioprocopio_pr.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.ui.components.AppDynamicNavMenu
import com.evertonprdo.cornelioprocopio_pr.ui.screens.DescriptionScreen
import com.evertonprdo.cornelioprocopio_pr.ui.screens.HomeScreen
import com.evertonprdo.cornelioprocopio_pr.ui.screens.places.PlacesScreen
import com.evertonprdo.cornelioprocopio_pr.ui.theme.AppTheme
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppContentType
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppListType
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppNavigationType
import com.evertonprdo.cornelioprocopio_pr.ui.utils.popUpTo

enum class AppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    List(title = R.string.list_screen),
    Details(title = R.string.details_screen)
}

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val appUiState = viewModel.uiState.collectAsState().value
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Start.name
    )

    val navigationType: AppNavigationType
    val contentType: AppContentType
    val listType: AppListType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_COLUMN
            listType = AppListType.TOP_FILTER
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = AppNavigationType.NAVIGATION_RAIL
            contentType = AppContentType.SINGLE_COLUMN
            listType = AppListType.SIDE_FILTER
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = AppNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = AppContentType.SIDE_BY_SIDE
            listType = AppListType.SIDE_FILTER
        }

        else -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_COLUMN
            listType = AppListType.SIDE_FILTER
        }
    }

    Surface {
        AppDynamicNavMenu(
            navigationType = navigationType,
            currentTab = currentScreen,
            onTabPressed = { navController.popUpTo(it) },
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = AppScreen.Start.name,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = AppScreen.Start.name) {
                    HomeScreen(contentType = contentType)
                }

                composable(route = AppScreen.List.name) {
                    PlacesScreen(
                        listType = listType,
                        uiState = appUiState,
                        onCategorySelected = {
                            viewModel.updateCurrentCategory(
                                it
                            )
                        },
                        onLocationSelected = {
                            viewModel.updateCurrentLocation(
                                it
                            )
                            navController.navigate(AppScreen.Details.name)
                        }
                    )
                }

                composable(route = AppScreen.Details.name) {
                    DescriptionScreen(
                        location = appUiState.currentLocation,
                        contentType = contentType,
                        onBackPressed = { navController.navigateUp() }
                    )
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
