package com.evertonprdo.cornelioprocopio_pr.ui.utils

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

// Helper for tab navigation
fun NavHostController.popUpTo(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}