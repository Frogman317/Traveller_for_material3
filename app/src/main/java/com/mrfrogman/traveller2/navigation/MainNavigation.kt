package com.mrfrogman.traveller2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrfrogman.traveller2.views.HomeView
import com.mrfrogman.traveller2.views.TitleView
import com.mrfrogman.traveller2.views.account.LoginView

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "title",
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = "title") {
            TitleView(
                navController = navController,
            )
        }
        composable(route = "login") {
            LoginView(
                navController = navController,
            )
        }
        composable(route = "register") {
            TitleView(
                navController = navController,
            )
        }
        composable(route = "home") {
            HomeView(
                navController = navController,
            )
        }
    }
}