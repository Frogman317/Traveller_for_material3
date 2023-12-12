package com.mrfrogman.traveller2.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R

@Composable
fun TitleView(
    navController: NavHostController,
) {
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
        TitleButton(text = "ログイン"){
            navController.navigate("login")
        }
        TitleButton(text = "新規登録"){
            navController.navigate("register")
        }
        TitleButton(text = "ゲストログイン"){
            navController.navigate("home")
        }

        //TODO debug buttons
        Spacer(modifier = Modifier.weight(1f))
        TitleButton(text = "starter"){
            navController.navigate("starter")
        }
    }
}

@Composable
fun TitleButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth(0.75f),
        onClick = onClick
    ) {
        Text(text = text)
    }
}