package com.mrfrogman.traveller2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mrfrogman.traveller2.component.MainScreen
import com.mrfrogman.traveller2.component.CreateComponent
import com.mrfrogman.traveller2.ui.theme.TravellerTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravellerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable(route = "main") {
            MainScreen()
        }
        composable(route = "create") {
            CreateComponent()
        }
    }
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun MainPreview() {
    TravellerTheme {
        AppScreen()
    }
}