package com.mrfrogman.traveller2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mrfrogman.traveller2.ui.theme.TravellerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravellerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

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
                )}})},
        content = {innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..20).map { it.toString() }
                items(count = list.size) {
                    Column {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp))
                        Row {
                            Text(
                                text = "list[it]",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp))

                            Text(
                                text = "list[it]2",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp))

                            Text(
                                text = "list[it]3",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp))
                        }
                    }
                } } },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Add,"Add Button") },
                text = { Text(stringResource(R.string.add_plan)) },
                onClick = {
                    Log.d("test", "MainScreen: clicked add plan button")
        })}
    )
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun MainPreview() {
    TravellerTheme {
        MainScreen()
    }
}