package com.example.spendtrack.utils

sealed class Screen(val route: String) {
    object SpendsScreen: Screen("spends_screen")
    object AddSpendsScreen: Screen("add_spend_screen")
}
