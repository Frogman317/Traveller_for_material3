package com.mrfrogman.traveller2.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R

@Composable
fun StarterView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.fillMaxSize(0.25f),
            contentDescription = "Title logo"
        )
        TitleButton(text = "旅行を始める"){
            navController.navigate("plan/create")
        }
        TitleButton(text = "旅行に参加する"){
            navController.navigate("plan/join")
        }
    }
}
