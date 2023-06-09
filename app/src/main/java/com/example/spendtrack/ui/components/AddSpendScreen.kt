package com.example.spendtrack

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spendtrack.ui.AddSpendViewModel
import com.example.spendtrack.utils.AddSpendEvent
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddSpendScreen(
    navController: NavController,
    viewModel: AddSpendViewModel = hiltViewModel()
) {
    val amountState = viewModel.spendAmount
    val descriptionState = viewModel.spendDescription

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddSpendViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddSpendViewModel.UiEvent.SaveSpend -> {
                    navController.navigateUp()
                }
                else -> null
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
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
                    value = amountState.value ,
                    modifier = Modifier
                        .testTag("amount_textfield")
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    label = { Text(text = "Amount") },
                    onValueChange = {
                        viewModel.onEvent(AddSpendEvent.EnteredAmount(it))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(vertical = 20.dp, horizontal = 25.dp)

            ) {
                OutlinedTextField(
                    value = descriptionState.value ,
                    modifier = Modifier
                        .testTag("description_textfield")
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    label = { Text(text = "Description") },
                    onValueChange = { viewModel.onEvent(AddSpendEvent.EnteredDescription(it)) }

                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(horizontal = 25.dp)
            ) {
                Button(
                    onClick = { viewModel.onEvent(AddSpendEvent.SaveSpend) },
                    modifier = Modifier
                        .testTag("Save")
                        .align(Alignment.Center)
                        .fillMaxWidth()
                ) {
                    Text("ADD SPEND")
                }
            }

        }
    }


}