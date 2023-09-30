package com.mrfrogman.traveller2.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrfrogman.traveller2.R
import com.mrfrogman.traveller2.ui.theme.TravellerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Traveller",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) { Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings Button"
                    )
                    }
                })
        },
        content = {
            Card(it)
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Add,"Add Button") },
                text = { Text(stringResource(R.string.add_plan)) },
                onClick = {
                    Log.d("test", "MainScreen: clicked add plan button")
                })
        }
    )
}

@Composable
private fun Card(paddingValues: PaddingValues) {
    LazyColumn(
        Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val list = (0..20).map { it.toString() }
        items(count = list.size) {
            androidx.compose.material3.Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                CardContent(it)
            }
        }
    }
}

@Composable
private fun CardContent(i: Int) {
    Column(
        Modifier.background(color = MaterialTheme.colorScheme.primary).padding(vertical = 8.dp)
    ) {
        Text(
            text = "Title",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
        Row {
            Text(
                text = i.toString(),
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp))

            Text(
                text = "123456",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f))

            Text(
                text = "2023/09/30",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun MainPreview() {
    TravellerTheme {
        MainScreen()
    }
}