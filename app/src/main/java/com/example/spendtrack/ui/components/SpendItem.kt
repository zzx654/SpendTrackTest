package com.example.spendtrack

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendtrack.data.Spend

@Composable
fun SpendItem(
    spend: Spend
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(3f),
            text = spend.date.toString()
        )
        Text(
            modifier = Modifier.weight(5f),
            text = spend.description
        )
        Text(
            modifier = Modifier.weight(1f),
            text = spend.amount.toString()
        )



    }

}