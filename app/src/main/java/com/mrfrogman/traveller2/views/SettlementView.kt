package com.mrfrogman.traveller2.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.ExpensesEntity
import com.mrfrogman.traveller2.database.MemberEntity
import com.mrfrogman.traveller2.database.PlanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettlementView(navController: NavHostController, planId: String) {
    val context = LocalContext.current
    val db = remember { Room.databaseBuilder(context, ApplicationDatabase::class.java, "my-database").build() }
    val planDao = remember(db) { db.planDAO() }
    val expensesDao = remember(db) { db.expensesDAO() }
    val memberDAO = remember(db) { db.memberDAO() }
    var expensesList by remember { mutableStateOf(emptyList<ExpensesEntity>()) }
    var planList by remember { mutableStateOf(emptyList<PlanEntity>()) }
    var memberList by remember { mutableStateOf(emptyList<MemberEntity>()) }
    LaunchedEffect(key1 = true){
        withContext(Dispatchers.IO){
            planList = planDao.search(planId)
            expensesList = expensesDao.search(planId)
            memberList = memberDAO.search(planId)
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = planList[0].title) },//TODO 仮タイトル
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
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Card {
                Text(text = "")
            }
        }
    }
}