package com.mrfrogman.traveller2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mrfrogman.traveller2.navigation.MainNavigation
import com.mrfrogman.traveller2.ui.theme.TravellerTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("DataStore", Context.MODE_PRIVATE) ?: return
        val planId = sharedPref.getString(getString(R.string.saved_plan_id), "")

        setContent {
            TravellerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (planId != null) {
                        MainNavigation(planId = planId)
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