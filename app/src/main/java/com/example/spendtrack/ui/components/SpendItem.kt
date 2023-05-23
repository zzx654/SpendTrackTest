package com.example.spendtrack

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.spendtrack.data.Spend
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SpendItem(
    spend: Spend
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("SpendItem")
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.weight(3f),
                text = getDateStr(spend.date)
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
        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }


}
@SuppressLint("SimpleDateFormat")
private fun getDateStr(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    return simpleDateFormat.format(date)
}