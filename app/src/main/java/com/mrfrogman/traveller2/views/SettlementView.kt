package com.mrfrogman.traveller2.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.ExpensesEntity
import com.mrfrogman.traveller2.database.MemberEntity
import com.mrfrogman.traveller2.database.PlanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    var planData: PlanEntity? by remember { mutableStateOf(null) }
    var memberList by remember(planDao) { mutableStateOf(emptyList<MemberEntity>()) }
    var memberAmountList = remember { mutableListOf<memberAmount>() }
    LaunchedEffect(key1 = true){
        withContext(Dispatchers.IO){
            planData = planDao.search(planId)
            expensesList = expensesDao.listSearch(planId)
            memberList = memberDAO.search(planId)
            memberList.forEach { member ->
                val id = member.id
                var a = 0
                var b = 0
                expensesList.forEach{ expenses ->
                    Log.d("TAG", "SettlementView: $expenses")
                    if (expenses.payer == id){
                        b += expenses.amount
                    }
                    a += expenses.receipt[id.toString()]?.toIntOrNull() ?: 0
                }
                val addList = memberAmount(member.name,a,b)
                memberAmountList.add(addList)
            }
        }
    }
    val titleText = planData?.title ?: ""
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = titleText)
                },//TODO 仮タイトル
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
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            memberAmountList.forEach{ data ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = data.name)
                    Text(text = "支払った金額:")
                    Text(text = data.pay.toString())
                    Text(text = "使った金額:")
                    Text(text = data.amount.toString())
                }
            }
        }
    }
}
data class memberAmount(
    val name: String,
    val amount: Int,
    val pay: Int
)