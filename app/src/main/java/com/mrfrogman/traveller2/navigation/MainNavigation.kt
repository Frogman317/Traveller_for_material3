package com.mrfrogman.traveller2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrfrogman.traveller2.views.AddPayView
import com.mrfrogman.traveller2.views.HomeView
import com.mrfrogman.traveller2.views.StarterView
import com.mrfrogman.traveller2.views.TitleView
import com.mrfrogman.traveller2.views.account.LoginView
import com.mrfrogman.traveller2.views.account.RegisterView
import com.mrfrogman.traveller2.views.plan.CreatePlanView
import com.mrfrogman.traveller2.views.plan.JoinPlanView

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "pay",
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
        composable(route = "starter") {
            StarterView(
                navController = navController,
            )
        }
        composable(route = "plan/create") {
            CreatePlanView(
                navController = navController,
            )
        }
        composable(route = "plan/join") {
            JoinPlanView(
                navController = navController,
            )
        }
        composable(route = "login") {
            LoginView(
                navController = navController,
            )
        }
        composable(route = "register") {
            RegisterView(
                navController = navController,
            )
        }
        composable(route = "home") {
            HomeView(
                navController = navController,
            )
        }
        composable(route = "pay") {
            AddPayView(
                navController = navController,
            )
        }
    }
}