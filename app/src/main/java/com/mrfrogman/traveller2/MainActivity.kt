package com.mrfrogman.traveller2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mrfrogman.traveller2.database.ApplicationDataStore
import com.mrfrogman.traveller2.navigation.MainNavigation
import com.mrfrogman.traveller2.ui.theme.TravellerTheme
import kotlinx.coroutines.flow.first


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravellerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var planId by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    LaunchedEffect(key1 = true){
                        val dataStore = ApplicationDataStore(context,"planId")
                        planId = dataStore.getData.first() ?: "null"
                        Log.d("planId", "get plan id: $planId")
                    }
                    if (planId != ""){
                        MainNavigation(id = planId)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Prev() {
    MainNavigation()
}