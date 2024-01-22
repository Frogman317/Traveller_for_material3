package com.mrfrogman.traveller2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mrfrogman.traveller2.views.AddPayView
import com.mrfrogman.traveller2.views.HomeView
import com.mrfrogman.traveller2.views.SettlementView
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
    id: String = "0"
) {
    var planId = id
    var startDestination = "starter"
    if (planId != "null"){
        startDestination = "home"
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = "starter") {
            StarterView(
                navController = navController,
            )
        }
        composable(route = "plan/create") {
            CreatePlanView(
                navController = navController,
            ){
                planId = it
            }
        }
        composable(route = "plan/join") {
            JoinPlanView(
                navController = navController,
            )
        }
        composable(route = "home") {
            HomeView(
                navController = navController,
                planId = planId
            ){
                planId = it
            }
        }
        composable(route = "settlement") {
            SettlementView(
                navController = navController,
                planId = planId
            )
        }
        composable(
            route = "pay/{expensesId}",
            arguments = listOf(navArgument("expensesId") { type = NavType.StringType } )
        ) {
            AddPayView(
                navController = navController,
                planId = planId,
                expensesId = (it.arguments?.getString("expensesId") ?: "0").toInt()
            )
        }

        // ログイン機能は実装予定
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
            RegisterView(
                navController = navController,
            )
        }
    }
}