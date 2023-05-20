package com.example.spendtrack

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddSpendScreen(
    navController: NavController
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
                value = "" ,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                label = { Text(text = "Amount") },
                onValueChange = {  }
                        //textValue -> textState.value = textValue
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(vertical = 20.dp, horizontal = 25.dp)

        ) {
            OutlinedTextField(
                value = "" ,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                label = { Text(text = "Description") },
                onValueChange = {  }
                        //textValue -> textState.value = textValue
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = 25.dp)
        ) {
            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Text("ADD SPEND")
            }
        }

    }

}