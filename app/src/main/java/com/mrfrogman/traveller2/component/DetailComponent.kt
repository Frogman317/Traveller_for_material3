package com.mrfrogman.traveller2.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailComponent(planID: Comparable<*>, title: String){

//    val context = LocalContext.current
//    val helper = DatabaseHelper(context)
//    val db: SQLiteDatabase = helper.writableDatabase

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                actions = {
                    IconButton(onClick = {

                    }) { Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings Button"
                    )}
                })
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = planID.toString())
            }
        }
    )
}