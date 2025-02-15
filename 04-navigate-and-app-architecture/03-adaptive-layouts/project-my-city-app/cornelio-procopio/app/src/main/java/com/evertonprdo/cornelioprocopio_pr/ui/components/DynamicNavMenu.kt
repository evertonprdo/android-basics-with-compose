package com.evertonprdo.cornelioprocopio_pr.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.evertonprdo.cornelioprocopio_pr.R
import com.evertonprdo.cornelioprocopio_pr.ui.AppScreen
import com.evertonprdo.cornelioprocopio_pr.ui.utils.AppNavigationType

@Composable
fun DynamicNavMenu(
    navigationType: AppNavigationType,
    currentTab: AppScreen,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    when (navigationType) {
        AppNavigationType.BOTTOM_NAVIGATION -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    content()
                }
                AppBottomNavigationBar(
                    navigationItemContentList = navigationItemContentList,
                    currentTab = currentTab,
                    onTabPressed = onTabPressed,
                )
            }
        }

        AppNavigationType.NAVIGATION_RAIL -> {
            Row(
                modifier = modifier.fillMaxSize()
            ) {
                AppNavigationRail(
                    navigationItemContentList = navigationItemContentList,
                    currentTab = currentTab,
                    onTabPressed = onTabPressed,
                )
                Box(modifier = Modifier.weight(1f)) {
                    content()
                }
            }
        }

        AppNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet {
                        NavigationDrawerContent(
                            navigationItemContentList = navigationItemContentList,
                            currentTab = currentTab,
                            onTabPressed = onTabPressed,
                        )
                    }
                },
                modifier = modifier
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AppBottomNavigationBar(
    navigationItemContentList: List<NavigationItemContent>,
    currentTab: AppScreen,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.destination,
                onClick = { onTabPressed(navItem.destination.name) },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = "University"
                    )
                }
            )
        }
    }
}

@Composable
private fun AppNavigationRail(
    navigationItemContentList: List<NavigationItemContent>,
    currentTab: AppScreen,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.destination,
                onClick = { onTabPressed(navItem.destination.name) },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = stringResource(navItem.text)
                    )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    navigationItemContentList: List<NavigationItemContent>,
    currentTab: AppScreen,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = currentTab == navItem.destination,
                label = {
                    Text(
                        text = stringResource(navItem.text)
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = null
                    )
                },
                onClick = { onTabPressed(navItem.destination.name) }
            )
        }
    }
}

private data class NavigationItemContent(
    val destination: AppScreen,
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
)

private val navigationItemContentList: List<NavigationItemContent> =
    listOf(
        NavigationItemContent(
            destination = AppScreen.Start,
            icon = R.drawable.hotel_icon,
            text = R.string.app_name
        ),
        NavigationItemContent(
            destination = AppScreen.List,
            icon = R.drawable.restaurant,
            text = R.string.list_screen
        ),
        NavigationItemContent(
            destination = AppScreen.Details,
            icon = R.drawable.university,
            text = R.string.details_screen
        ),
    )
