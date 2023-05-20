package com.example.spendtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendtrack.ui.theme.SpendTrackTheme

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

@Preview
@Composable
fun PreviewMessageCard() {
    SpendTrackTheme {
        val textState = remember{
            mutableStateOf("")
        }

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),


                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) {
                    Text(
                        text = "Add a new Spend",
                        style = MaterialTheme.typography.h4,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 25.dp)

                ) {
                    OutlinedTextField(
                        value = textState.value ,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        label = { Text(text = "Amount") },
                        onValueChange = { textValue -> textState.value = textValue }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .padding(vertical = 20.dp, horizontal = 25.dp)

                ) {
                    OutlinedTextField(
                        value = textState.value ,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(),
                        label = { Text(text = "Description") },
                        onValueChange = { textValue -> textState.value = textValue }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .padding(horizontal = 25.dp)
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                    ) {
                        Text("ADD SPEND")
                    }
                }

            }

        }
    }
}
