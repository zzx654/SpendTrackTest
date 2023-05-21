package com.example.spendtrack

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spendtrack.ui.SpendsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SpendsScreen(
    navController: NavController,
    viewModel: SpendsViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val spendsState = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddSpendsScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "Your Last 20 Spends",
                    style = MaterialTheme.typography.h4,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(spendsState) { spend ->
                        SpendItem(spend)
                        Divider()

                    }
                }
                /**LazyColumn(
                modifier = Modifier.fillMaxSize()
                ) {
                items() {

                }
                }**/

            }


        }
    }

}