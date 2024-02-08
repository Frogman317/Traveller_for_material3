package com.mrfrogman.traveller2.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.room.Room
import com.mrfrogman.traveller2.database.ApplicationDatabase
import com.mrfrogman.traveller2.database.PlanDAO
import com.mrfrogman.traveller2.database.PlanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    navController: NavHostController,
    planId: String
) {
    val context = LocalContext.current
    val db = remember { Room.databaseBuilder(context, ApplicationDatabase::class.java, "my-database").build() }
    val planDao = remember(db) { db.planDAO() }
    var planData: PlanEntity? = null
    LaunchedEffect(true){
        withContext(Dispatchers.IO){
            planData = planDao.search(planId)
        }
    }
    val title by remember(planData) { mutableStateOf(planData?.title ?: "") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title) },
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
    ) { innerPadding ->
        var isShowDeleteDialog by remember { mutableStateOf(false) }
        DeleteDialog(
            navController = navController,
            planDao = planDao,
            planId = planId,
            isShow = isShowDeleteDialog
        ){
            isShowDeleteDialog = !isShowDeleteDialog
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                onClick = {
                    isShowDeleteDialog = true
                }
            ) {
                Text(
                    text = "計画を削除する",
                    color = colorScheme.error
                )
            }
        }
    }
}
@Composable
fun DeleteDialog(
    navController: NavHostController,
    planDao: PlanDAO,
    planId: String,
    isShow: Boolean,
    onDismissRequest: () -> Unit
) {
    if (isShow) AlertDialog(
        icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
        title = { Text(text = "本当に削除しますか？") },
        text = { Text(text = "削除すると元には戻せません") },
        confirmButton = {
            val scope = rememberCoroutineScope()
            TextButton(
                onClick = {
                    scope.launch {
                        withContext(Dispatchers.IO){
                            planDao.delete(planId)
                            navController.navigate("starter")
                        }
                    }
                }
            ) {
                Text("削除")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("キャンセル")
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}