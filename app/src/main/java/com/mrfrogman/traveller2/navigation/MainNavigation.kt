package com.mrfrogman.traveller2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrfrogman.traveller2.views.TitleView

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "title"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "title") {
            TitleView()
        }
    }
}