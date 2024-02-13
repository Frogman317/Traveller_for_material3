package com.mrfrogman.traveller2.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.ExpensesEntity
import com.mrfrogman.traveller2.database.MemberEntity
import com.mrfrogman.traveller2.database.PlanEntity
import com.mrfrogman.traveller2.ui.theme.darkBlue
import com.mrfrogman.traveller2.ui.theme.darkRed
import com.mrfrogman.traveller2.ui.theme.lightBlue
import com.mrfrogman.traveller2.ui.theme.lightRed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

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
    val memberAmountList = remember { mutableListOf<MemberAmount>() }
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
                    if (expenses.payer == id){
                        b += expenses.amount
                    }
                    a += expenses.receipt[id.toString()]?.toIntOrNull() ?: 0
                }
                val addList = MemberAmount(member.name,a,b)
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
            val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            memberAmountList.forEach{ data ->
                var expanded by remember { mutableStateOf(false) }
                val pay = data.amount - data.pay
                val text = if (pay >= 0) "${pay}円支払ってください" else "${-pay}円受け取ってください"
                val color = if (isSystemInDarkTheme()) {
                    if (pay >= 0) darkBlue else darkRed
                } else {
                    if (pay >= 0) lightBlue else lightRed
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(bottom = 8.dp)
                        .animateContentSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            expanded = !expanded
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = data.name)
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = ""
                            )
                        }
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = text
                        )
                        if (expanded) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "支払った金額:")
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = numberFormat.format(data.pay).toString() + "円")
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "使った金額:")
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = numberFormat.format(data.amount).toString() + "円")
                            }
                        }
                    }
                }
            }
        }
    }
}
data class MemberAmount(
    val name: String,
    val amount: Int,
    val pay: Int
)