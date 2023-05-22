package com.example.spendtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendtrack.ui.theme.SpendTrackTheme
import com.example.spendtrack.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendTrackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SpendsScreen.route
                    ) {
                        composable(route = Screen.SpendsScreen.route) {
                            SpendsScreen(navController = navController)
                        }
                        composable(route = Screen.AddSpendsScreen.route) {
                            AddSpendScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

