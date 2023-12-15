package com.mrfrogman.traveller2.views.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.views.TitleButton
import com.mrfrogman.traveller2.views.compose.TicketTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    navController: NavHostController,
) {
    var user   by remember { mutableStateOf("") }
    var email  by remember { mutableStateOf("") }
    var pass   by remember { mutableStateOf("") }
    var repass by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "新規登録") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back screen button"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TicketTextField(
                modifier = Modifier.padding(top = 20.dp),
                contentDescription = "",
                label = "ユーザー名",
                value = user,
            ){
                user = it
            }
            TicketTextField(
                contentDescription = "",
                label = "メールアドレス",
                value = email,
            ){
                email = it
            }
            TicketTextField(
                contentDescription = "",
                label = "パスワード",
                value = pass,
            ){
                pass = it
            }
            TicketTextField(
                contentDescription = "",
                label = "パスワード再入力",
                value = repass,
            ){
                repass = it
            }

            Spacer(modifier = Modifier.weight(1f))

            TitleButton(
                text = "新規登録",
                modifier = Modifier.padding(bottom = 32.dp)
            ){
                navController.navigate("starter")
            }
        }
    }
}