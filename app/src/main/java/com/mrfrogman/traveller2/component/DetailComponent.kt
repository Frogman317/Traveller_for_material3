package com.mrfrogman.traveller2.component

import android.database.sqlite.SQLiteDatabase
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.mrfrogman.traveller2.R
import com.mrfrogman.traveller2.database.DatabaseHelper


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailComponent(navController: NavHostController){

    val context = LocalContext.current
    val planData = navController.currentBackStackEntry?.arguments?.getString("data")

    val helper = DatabaseHelper(context)
    val db: SQLiteDatabase = helper.writableDatabase

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = planData?.get(0).toString(),
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

            }
        }
    )
}