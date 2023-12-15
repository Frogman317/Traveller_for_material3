package com.mrfrogman.traveller2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Test() {
    LazyColumn(content = {
        items(100){
            Text(text = it.toString()+" item")
        }
    })
}