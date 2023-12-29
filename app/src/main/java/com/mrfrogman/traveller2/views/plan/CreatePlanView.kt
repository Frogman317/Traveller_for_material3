package com.mrfrogman.traveller2.views.plan

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDataStore
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.MemberEntity
import com.mrfrogman.traveller2.database.PlanEntity
import com.mrfrogman.traveller2.views.compose.AddMemberList
import com.mrfrogman.traveller2.views.compose.TicketTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanView(
    navController: NavHostController
) {
    var planTitle by remember { mutableStateOf("") }
    var addMemberName by remember { mutableStateOf("") }
    val memberList = remember { mutableStateListOf<String>() }

    val context = LocalContext.current
    val db = remember { Room.databaseBuilder(context, ApplicationDatabase::class.java, "my-database").build() }
    val planDao = remember(db) { db.planDAO() }
    val memberDao = remember(db) { db.memberDAO() }
    DisposableEffect(Unit) {
        onDispose { db.close() }
    }
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
                    val composableScope = rememberCoroutineScope()
                    Button(
                        onClick = {
                            composableScope.launch {
                                withContext(Dispatchers.IO) {
                                    val localTime = LocalDateTime.now()
                                    val id = planDao.insert(PlanEntity(
                                        id = 0,
                                        title = planTitle,
                                        detail = "",
                                        create = localTime,
                                        timestamp = localTime
                                    ))
                                    memberList.forEach{
                                        memberDao.insert(MemberEntity(
                                            id = 0,
                                            name = it,
                                            detail = "",
                                            planId = id.toInt(),
                                            create = localTime,
                                            timestamp = localTime
                                        ))
                                    }
                                    val dataStore = ApplicationDataStore(context,"planId")
                                    dataStore.saveData(id.toString())
                                    Log.d("planId", "CreatePlanId: $id")
                                }
                                navController.navigate("home")
                            }
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
                onValueChange = {
                    planTitle = it
                },
            )
            TicketTextField(
                contentDescription = "",
                label = "メンバーの追加",
                value = addMemberName,
                onValueChange = {
                    addMemberName = it
                },
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FilledTonalButton(
                    modifier = Modifier.width(150.dp),
                    onClick = {
                        //TODO
                    }) {
                    Text(text = "リストから追加")
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    modifier = Modifier.width(150.dp),
                    onClick = {
                        if (addMemberName != ""){
                            memberList.add(addMemberName)
                            addMemberName = ""
                        }
                    }) {
                    Text(text = "メンバーの追加")
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            for (i in memberList.indices) {
                val name = memberList[i]
                AddMemberList(name) { dltName ->
                    memberList.remove(dltName)
                }
            }
        }
    }
}