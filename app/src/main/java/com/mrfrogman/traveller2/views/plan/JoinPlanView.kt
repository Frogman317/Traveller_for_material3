package com.mrfrogman.traveller2.views.plan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
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
import com.mrfrogman.traveller2.views.compose.TicketTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinPlanView(
    navController: NavHostController,
) {
    var invitation by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "旅行を始める") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TicketTextField(
                modifier = Modifier.padding(top = 20.dp),
                contentDescription = "",
                label = "招待コード",
                value = invitation,
                onValueChange = {
                    invitation = it
                },
            )

            Button(
                modifier = Modifier
                    .width(150.dp)
                    .padding(top = 32.dp),
                onClick = {
                    navController.navigate("home")
                }
            ){
                Text(text = "参加",)
            }
        }
    }
}