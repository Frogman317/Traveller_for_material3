package com.mrfrogman.traveller2.views.plan

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
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
import com.mrfrogman.traveller2.views.compose.AddMemberList
import com.mrfrogman.traveller2.views.compose.TicketTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanView(
    navController: NavHostController
) {
    var planTitle by remember { mutableStateOf("") }
    var addMemberName by remember { mutableStateOf("") }
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
                },
                actions = {
                    Button(
                        onClick = {
                            navController.navigate("home")
                        }
                    ){
                        Text(text = "作成")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TicketTextField(
                modifier = Modifier.padding(top = 20.dp),
                contentDescription = "",
                label = "タイトル",
                value = planTitle,
            ) {
                planTitle = it
            }
            TicketTextField(
                contentDescription = "",
                label = "メンバーの追加",
                value = addMemberName,
            ){
                addMemberName = it
            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FilledTonalButton(
                    modifier = Modifier.width(150.dp),
                    onClick = {

                    }) {
                    Text(text = "リストから追加")
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    modifier = Modifier.width(150.dp),
                    onClick = {

                    }) {
                    Text(text = "メンバーの追加")
                }
            }
            for (i in 0..8){
                AddMemberList("test")
            }
        }
    }
}

