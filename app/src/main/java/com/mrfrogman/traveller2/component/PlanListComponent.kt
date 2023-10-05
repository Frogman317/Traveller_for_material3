package com.mrfrogman.traveller2.component

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R
import com.mrfrogman.traveller2.database.DatabaseHelper
import java.time.LocalDateTime

@SuppressLint("Range")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanListComponent(navController: NavHostController) {

    val helper = DatabaseHelper(LocalContext.current)
    val dataList: MutableList<Map<String, Any>> = ArrayList()
    val db: SQLiteDatabase = helper.readableDatabase
    val cursor = db.rawQuery("select * from plans",null)
    if (cursor.moveToFirst()) {
        do {
            val dataMap = mutableMapOf<String, Any>().apply {
                put("_id",    cursor.getInt(cursor.getColumnIndex("_id")))
                put("title",  cursor.getString(cursor.getColumnIndex("title")))
                put("detail", cursor.getString(cursor.getColumnIndex("detail")))
                put("member", cursor.getInt(cursor.getColumnIndex("member")))
                put("amount", cursor.getInt(cursor.getColumnIndex("amount")))
                put("date",   cursor.getString(cursor.getColumnIndex("date")))
            }
            dataList.add(dataMap)
        } while (cursor.moveToNext())
    }
    cursor.close()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Traveller",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                actions = {
                    IconButton(onClick = {
                        /* doSomething() */
                    }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings Button")
                    }
                })
        },
        content = {
            Card(it,dataList,navController)
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Add,"Add Button") },
                text = { Text(stringResource(R.string.Create_page_title)) },
                onClick = {
                    navController.navigate("create")
                    Log.d("test", "MainScreen: clicked add plan button")
                })
        }
    )
}

@Composable
private fun Card(
    paddingValues: PaddingValues,
    dataList: MutableList<Map<String, Any>>,
    navController: NavHostController
) {
    LazyColumn(
        Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dataList.size) {
            androidx.compose.material3.Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    .clickable {
                        val planID = dataList[it]["_id"]
                        val title = dataList[it]["title"]
                        navController.navigate("detail/$planID/$title")
                    }
            ) {
                CardContent(dataList[it])
            }
        }
    }
}

@Composable
private fun CardContent(data: Map<String, Any>) {
    var dateStr = data["date"].toString()
    val date = LocalDateTime.parse(dateStr)
    dateStr = "${date.year}/${date.monthValue}/${date.dayOfMonth}"
    Column(
        Modifier.padding(12.dp)
    ) {
        Text(
            text = data["title"].toString(),
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Icon(Icons.Filled.Person, contentDescription = "")
            Text(
                text = data["member"].toString(),
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(Icons.Filled.ShoppingCart, contentDescription = "")
            Text(
                text = data["amount"].toString(),
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(Icons.Filled.DateRange, contentDescription = "")
            Text(
                text = dateStr,
            )
        }
    }
}