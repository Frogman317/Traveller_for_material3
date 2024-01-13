package com.mrfrogman.traveller2.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.ExpensesEntity
import com.mrfrogman.traveller2.database.PlanEntity
import com.mrfrogman.traveller2.views.compose.AmountBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavHostController,
    planId: String,
    setPlanId: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    var segmentIndex by remember { mutableIntStateOf(0) }
    val themeGray = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
    var fabOffset by remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val db = remember { Room.databaseBuilder(context, ApplicationDatabase::class.java, "my-database").build() }
    val planDao = remember(db) { db.planDAO() }
    val expensesDao = remember(db) { db.expensesDAO() }
    DisposableEffect(Unit) { onDispose { db.close() } }
    val dateTime = LocalDateTime.MIN
    var planData by remember { mutableStateOf(PlanEntity(
        id = 0,
        title = "",
        detail = "",
        create = dateTime,
        timestamp = dateTime
    )) }
    var amount by remember  { mutableStateOf("0")}
    var expensesList by remember { mutableStateOf(emptyList<ExpensesEntity>()) }
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            amount = expensesDao.getAmount(planId).toString()
            expensesList = expensesDao.listSearch(planId)
        }
    }
    var planList by remember { mutableStateOf(emptyList<PlanEntity>()) }
    LaunchedEffect(true) {
        planList = withContext(Dispatchers.IO) {
            planDao.getAll()
        }
        planList.forEach {
            if (it.id.toString() == planId){
                planData = it
            }
        }
    }
    var drawerSelected by remember { mutableStateOf(planId) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val drawerItemModifier = Modifier.padding(4.dp)
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                Column(Modifier.weight(1f)) {
                    planList.forEach{
                        NavigationDrawerItem(
                                modifier = drawerItemModifier,
                        label = { Text(text = it.title) },
                        selected = it.id.toString() == drawerSelected,
                        onClick = {
                            drawerSelected = it.id.toString()
                            setPlanId(it.id.toString())
                        }
                        )
                    }
                }
                HorizontalDivider()
                NavigationDrawerItem(
                    modifier = drawerItemModifier,
                    icon = { Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Setting icon") },
                    label = { Text(text = "設定") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = planData.title) },//TODO 仮タイトル
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Back screen button"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                if (scrollState.value >= 50 && fabOffset < 80) {
                    fabOffset += 3
                } else if (fabOffset > 0) {
                    fabOffset -= 5
                }
                ExtendedFloatingActionButton(
                    modifier = Modifier.offset(
                        y = fabOffset.dp
                    ),
                    onClick = {
                        navController.navigate("pay/0")
                    },
                    icon = { Icon(Icons.Filled.Add, "Localized Description") },
                    text = { Text(text = "支払いの追加") },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
            ) {
                AmountBoard(
                    amount = amount,
                    themeGray = themeGray
                )
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilledTonalButton(
                        modifier = Modifier.width(150.dp),
                        onClick = {

                        }) {
                        Text(text = "詳細")
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        modifier = Modifier.width(150.dp),
                        onClick = {

                        }) {
                        Text(text = "メンバーの追加")
                    }
                }
                val options = listOf("すべての表示", "自分の表示")
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .background(colorScheme.background)
                        .padding(20.dp)
                        .fillMaxWidth(),
                ) {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size
                            ),
                            onClick = { segmentIndex = index },
                            selected = index == segmentIndex
                        ) {
                            Text(label)
                        }
                    }
                }
                expensesList.reversed().forEach { data ->
                    ListContent(
                        data = data,
                    ){ id ->
                        Log.d("expensesId", "HomeView: $id")
                        navController.navigate("pay/$id")
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Stable
@Composable
fun ListContent(
    data: ExpensesEntity,
    onClick: (String) -> Unit
) {
    val themeGray = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray
    val date = data.create
    val format = DateTimeFormatter.ofPattern("MM/dd")
    Text(
        modifier = Modifier.padding(start = 16.dp),
        color = themeGray,
        text = date.format(format)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick(data.id.toString())
            }
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = data.title,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.weight(1f))
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        val formattedAmount = numberFormat.format(data.amount)
        Text(
            text = formattedAmount+"円",
            fontWeight = FontWeight.Medium,
        )
        Icon(
            modifier = Modifier.padding(end = 12.dp),
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Navigate Button for detail of list",
            tint = themeGray
        )
    }
}