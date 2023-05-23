package com.example.spendtrack

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendtrack.di.DatabaseModule
import com.example.spendtrack.di.RepositoryModule
import com.example.spendtrack.ui.theme.SpendTrackTheme
import com.example.spendtrack.utils.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, RepositoryModule::class)
class SpendsEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {

        hiltRule.inject()

        composeRule.activity.setContent {

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

    @Test
    fun saveNewSpend() {
        composeRule.onNodeWithContentDescription("Add").performClick()

        composeRule
            .onNodeWithTag("amount_textfield")
            .performTextInput("100")
        composeRule
            .onNodeWithTag("description_textfield")
            .performTextInput("계란")

        composeRule.onNodeWithTag("Save").performClick()

        composeRule.onAllNodesWithTag("SpendItem")[0].onChildAt(1)
            .assertTextContains("계란")

        composeRule.onAllNodesWithTag("SpendItem")[0].onChildAt(2)
            .assertTextContains("100")
          //  .assertTextContains("계란")
        //composeRule.onNodeWithText("계란").assertIsDisplayed()

        //composeRule.onNodeWithText("100").assertIsDisplayed()

    }
}